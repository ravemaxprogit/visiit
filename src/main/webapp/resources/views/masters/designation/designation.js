'use strict';

var des = angular.module('designation', [ 
        'ui.router', 
        'validation',
		'validation.rule'
]);

//Routers
des.config(function($stateProvider) {
 
  $stateProvider.state('designation', {
    url: '/masters/designation',
    templateUrl: 'resources/views/masters/designation/designation.html',
	controller: 'designationController'
  }); 
    
});

//Factories
des.factory('designationServices', ['$http', function($http) {

    var factoryDefinitions = {
	  getAllDesignations: function(){
          return $http.get('designationList').success(function (data) {
              return data;
          });
      },
      getDesignation : function (designationId) {
    	  return $http.get('getDesignation?designationId='+designationId).success(function (data) {
              return data;
          });
      },
      saveDesignation : function (designationScope) {
    	  var postdata = "dsgStr="+JSON.stringify(designationScope);
    	//  var data = {"dsgStr":designationScope};
    	  return $http.post('saveDesignation', postdata );
      },
      updateDesignation : function (designationScope) {
    	  var postdata = "dsgStr="+JSON.stringify(designationScope);
    	  return $http.post('saveDesignation', postdata );
      },
      deleteDesignation : function (designationId) {
    	  return $http.post('deleteDesignation', 'designationId='+designationId);
      }
	}
	
    return factoryDefinitions;
  }
]);

//Controllers
des.controller('designationController', ['$scope', 'designationServices','$state','ngDialog','$rootScope','$timeout', function($scope, designationServices,$state,ngDialog,$rootScope,$timeout) {
	$scope.designations = {};
	$scope.designation = {};
	//Filter
	$scope.dsg_search = function (obj) {
		 var re = new RegExp($scope.des_search, 'i');
	        return !$scope.des_search || re.test(obj.dsgName) || re.test(obj.dsgCode.toString());
   };
   
   $scope.isProcessingfalse = function() {
		 $timeout(function() {
			$scope.isProcessing = false;
			}, 1000);
	};

	//cancel - close dialog
	$scope.cancel = function()
	{
		ngDialog.close();
	}
	
	//get designation list
	designationServices.getAllDesignations().success(function (response) {
		$scope.designations = response;		
    });
	
	// load add designation and edit designation
	$scope.designationAdd = function(designationId){	
		$scope.designation = {};
		$scope.data = {};
		if(designationId){
			$rootScope.showLoader();
			designationServices.getDesignation(designationId).then(function (response) {
				//$scope.desgActive = response.data.designation.dsgIsActive;
				$scope.designation = response.data.designation;
				$scope.isProcessing = true;
				ngDialog.open({
		 			template: 'resources/views/masters/designation/addDesignation.html', 
		  		   	className: 'ngdialog-theme-default',
		  		   	scope: $scope
		 		});
				$rootScope.hideLoader();
				$scope.isProcessingfalse();
		    });	
		}else{
			$rootScope.showLoader();
			$scope.designation.dsgIsActive = false;
			$scope.isProcessing = true;
			ngDialog.open({
	 			template: 'resources/views/masters/designation/addDesignation.html', 
	  		   	className: 'ngdialog-theme-default',
	  		   	scope: $scope
	 		});
			$rootScope.hideLoader();
			$scope.isProcessingfalse();
		}		
	}
	
	// save/update designation
	$scope.designationSave = function(){
		$rootScope.showLoader();
        designationServices.saveDesignation($scope.designation)
            .success(function (data) {
            	$scope.data = data;
            	if(data.Status.toLowerCase() == 'ok'){            	
            		designationServices.getAllDesignations().success(function (response) {
            			$scope.designations = response;		
            			$rootScope.hideLoader();
            	    });
            		// $scope.message.success=true;
            		 ngDialog.close();
            		 $scope.data.success = true;	
            	}else{
            		$scope.data.addSuccess = false;	
            	}
            	
            	
               
            }).
            error(function(error) {
            	$scope.designationResponse = error;
            	$scope.data.success = false;	
            	
            });
	}
	
	// delete designation
	$scope.designationDelete = function(designationId){
		if(designationId){
			var result = confirm("Are you sure want to delete?");
			if (result==true) {
				$rootScope.showLoader();
				 designationServices.deleteDesignation(designationId)
		            .success(function (data) {
		            	$scope.data = data;
		            	if(data.Status.toLowerCase() == 'ok'){            	
		            		designationServices.getAllDesignations().success(function (response) {
		            			$scope.designations = response;
		            			$rootScope.hideLoader();
		 	                });
		            		$scope.data.success = true;	
		            	}else{
		            		 $scope.data.success = false;	
		            	}
		            	
		            	
		            }).
		            error(function(error) {
		            	 $scope.data.success = false;	
		            });
			}
		}	    
	}	
	
	
	
}]);


