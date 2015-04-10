'use strict';
var deprt = angular.module('department', [ 
        'ui.router', 
        'validation',
		'validation.rule'
]);


//Routers
deprt.config(function($stateProvider,$urlRouterProvider) {
  
  
  $stateProvider.state('department', {
	  url: '/masters/department',
      templateUrl: 'resources/views/masters/department/department_grid.html',
      data: {
          auth: true
      },
      controller: 'departmentController'
  });
  
});

deprt.factory('departmentServices', ['$http', '$rootScope',function ($http) {
	    var url = "";
	    var factoryDefinitions = {
	        getDepartment: function () {
	            return $http.get('getDepartments').success(function (data) {
	                return data;
	            });
	          },           
	         saveDepartment: function (postdata) {
	        	 return $http.post("saveDepartment", postdata);
	          },
	          getDepartmentById: function (departmentId) {
		            return $http.get('getDepartmentById?departmentId='+departmentId).success(function (data) {
		                return data;
		            });
		       },
		       deleteDepartmentById: function (departmentId) {
		    	   return $http.post('deleteDepartment','departmentId='+departmentId);
		       }
	        }
	        return factoryDefinitions;
	    }
]);

deprt.controller('departmentController', ['$scope', '$rootScope', 'ngDialog',"$sce", '$http', '$state', 'departmentServices','$timeout', function ($scope, $rootScope,ngDialog, $sce, $http, $state, departmentServices,$timeout) {
	
	$scope.isProcessingfalse = function() {
		 $timeout(function() {
			$scope.isProcessing = false;
			}, 1000);
	};
	
	$scope.dept_search = function (obj) {
		 var re = new RegExp($scope.d_search, 'i');
	        return !$scope.d_search || re.test(obj.deptName) || re.test(obj.deptCode.toString());
		//return !!((row.deptCode.indexOf($scope.d_search || '') !== -1) || (row.deptName.indexOf($scope.d_search || '') !== -1) );
    };
	$scope.dapartments = {};
	$scope.addDepartments = {};
	$scope.departmentResponse = {};
	$scope.saveDepartmentResponse = {};
	
	$scope.message =[{
		'success':0,
		'error':0
	}];
	
	departmentServices.getDepartment().then(function (response) {
		$scope.dapartments = response.data;
    }); 
	
	$scope.deparmentAdd = function(){
		$rootScope.showLoader();
		$scope.departmentResponse = {};
		$scope.saveDepartmentResponse = {};
		$scope.addDepartments = {};
		$scope.addDepartments.deptIsActive = false;
		$scope.isProcessing = true;
		ngDialog.open({
			 template: 'resources/views/masters/department/addDepartmentDetails.html', 
			 className: 'ngdialog-theme-default',
  		   	 scope: $scope
 		});
		 $scope.isProcessingfalse();
		 $rootScope.hideLoader();
	}
	
	$scope.cancel = function(){
		ngDialog.close();
	}	
	
	$scope.saveDeparment = function(){
		$rootScope.showLoader();
		$scope.departmentResponse = {};
		$scope.saveDepartmentResponse = {};
		var postdata = "departmentStr="+JSON.stringify($scope.addDepartments);
		departmentServices.saveDepartment(postdata).then(function (response) {
			if(response.data.Status == 'Error'){
				$scope.saveDepartmentResponse = response.data;
				$scope.message ={
						'success':0,
						'error':1
					};
				$rootScope.hideLoader();
			}
			if(response.data.Status == 'Ok'){
				$scope.departmentResponse = response.data;
				$scope.message ={
					'success':1,
					'error':0
				};
				//update scope
				departmentServices.getDepartment().then(function (response) {
					$scope.dapartments = response.data;
					$rootScope.hideLoader();
			    }); 
				ngDialog.close();
			}
	    }); 
	}
	
	$scope.editDepartment = function(departmentId){
		$rootScope.showLoader();
		departmentServices.getDepartmentById(departmentId).then(function (response) {
			$scope.addDepartments = response.data.department;
			$rootScope.hideLoader();
			ngDialog.open({
				template: 'resources/views/masters/department/addDepartmentDetails.html',  
	  		   	className: 'ngdialog-theme-default',
	  		   	scope: $scope
	 		});
	    });
	}
	
  	$scope.deleteDepartment = function(departmentId){
		var result = confirm("Are you sure want to delete?");
		if (result==true) {
			$rootScope.hideLoader();
			departmentServices.deleteDepartmentById(departmentId).then(function (response) {
				$scope.departmentResponse = response.data;
				if(response.data.Status == 'Error'){
					$scope.message ={
							'success':0,
							'error':1
						};
					$rootScope.hideLoader();
				}
				if(response.data.Status == 'Ok'){
					$scope.message ={
						'success':1,
						'error':0
					};
					//update scope
					departmentServices.getDepartment().then(function (response) {
						$scope.dapartments = response.data;
						$rootScope.hideLoader();
				    }); 
				}
		    }); 
		}
	}
		
}]);