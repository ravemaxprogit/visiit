'use strict';
var enquiry = angular.module('enquiry', [ 
        'ui.router', 
        'validation',
		'validation.rule'
]);


//Routers
enquiry.config(function($stateProvider,$urlRouterProvider) {  
  $stateProvider.state('enquiry_support', {
	  url: '/customer-care/enquiry',
      templateUrl: 'resources/views/customercare/enquiry/enquiry_grid.html',
      data: {
          auth: true
      },
      controller: 'enquiryController'
  });
  
});

enquiry.factory('enquiryServices', ['$http', '$rootScope',function ($http) {
	    var url = "";
	    var factoryDefinitions = {
	        getEnquiry: function () {
	            return $http.get('resources/views/customercare/enquiry/enquiry.json').success(function (data) {
	                return data;
	            });
	        },
	        getEnquiryById : function (id) {
	      	  return $http.get('getCustomerEnquiryById?enquriyIdStr='+id).success(function (data) {
	                return data;
	            });
	        },
	        saveCustomerEnquiry : function (enquiry,trip) {
	        	var travellerStr ='';
	        	if(trip){
	        		travellerStr = JSON.stringify(trip);
	        	}
	      	  var postdata = "customerEnquiryStr="+JSON.stringify(enquiry)+"&travellerStr="+travellerStr;
	      	  return $http.post('updateCustomerEnquiry', postdata ).success(function (data) {
	              return data;
	          });
	        }
        }
        return factoryDefinitions;
	    }
]);

enquiry.controller('enquiryController', ['$scope', '$rootScope', 'ngDialog',"$sce", '$http', '$state', 'enquiryServices', function ($scope, $rootScope,ngDialog, $sce, $http, $state, enquiryServices) {
	// edit enquiry
	$scope.enquiryEdit = function(id){
		$rootScope.showLoader();
		$scope.enquiry = {};
		$scope.data = {};
		$scope.trip = {};
		if(id){	
			$scope.statusList = [
			    	        {'id':'Open', 'title':'Open'},
			    	        {'id':'Followup', 'title':'Followup'},
			    	        {'id':'Closed', 'title':'Closed'}
			    	      ];
			enquiryServices.getEnquiryById(id).then(function (response) {
				$scope.enquiry = response.data.listData;
				$scope.trip = response.data.TRIP;
				ngDialog.open({
		 			template: 'resources/views/customercare/enquiry/editEnquiry.html', 
		  		   	className: 'ngdialog-theme-default',
		  		   	scope: $scope
		 		});
				if(response.data.Status.toLowerCase() != 'ok'){
					$scope.data.errorMessage = response.data.message;	
					$scope.data.editEnquirySuccess = false;	
				}
		    });	
			
		}	
		$rootScope.hideLoader();
	}
	
	$scope.enquiryView = function(id){	
		$rootScope.showLoader();
		$scope.enquiry = {};
		$scope.data = {};
		if(id){	
			enquiryServices.getEnquiryById(id).then(function (response) {
				//console.log(response);
				$scope.enquiry = response.data.listData;	
				$scope.trip = response.data.TRIP;
				$scope.persons = response.data.Person;
				ngDialog.open({
		 			template: 'resources/views/customercare/enquiry/viewEnquiry.html', 
		  		   	className: 'ngdialog-theme-default',
		  		   	scope: $scope
		 		});
				if(response.data.Status.toLowerCase() != 'ok'){
					$scope.data.errorMessage = response.data.message;	
					$scope.data.editEnquirySuccess = false;	
				}
		    });	
			
		}
		$rootScope.hideLoader();
	}
	
	$scope.enquirySave = function(){	
		$rootScope.showLoader();
		$scope.data.message = {};
		var tripDetails = $scope.trip ? $scope.trip : '';
		enquiryServices.saveCustomerEnquiry($scope.enquiry,tripDetails).then(function (response) {
			if(response.data.Status == 'Ok'){
				$scope.data.success = true;
				$scope.data.successmessage=response.data.message;
				ngDialog.close();
				$scope.refresh = Math.random();
				//$scope.tableParams.reload();
			}else{
				$scope.data.errorMessage = response.data.message;	
				$scope.data.editEnquirySuccess = false;	
			}
			
	    }); 
		$rootScope.hideLoader();
	}
	
	$scope.cancel = function()
	{
		ngDialog.close();
	}
	
			
}]);