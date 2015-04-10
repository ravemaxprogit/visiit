'use strict';
var newsletter = angular.module('newsletter', [ 
        'ui.router', 
        'validation',
		'validation.rule'
]);


//Routers
newsletter.config(function($stateProvider,$urlRouterProvider) {  
  $stateProvider.state('newsletter', {
	  url: '/customer-care/newsletter',
      templateUrl: 'resources/views/customercare/newsletter/newsletter.html',
      data: {
          auth: true
      },
      controller: 'newsletterController'
  });
  
});

newsletter.factory('newsletterServices', ['$http', '$rootScope',function ($http) {
	    var factoryDefinitions = {
	    	getNewsletter : function () {
	    		return $http.get('getNewsLetterFileName').success(function (data) {
	                return data;
	            });
	        }
        }
        return factoryDefinitions;
	    }
]);

newsletter.controller('newsletterController', ['$scope', '$rootScope','$http', '$state','$upload','$timeout','newsletterServices', function ($scope, $rootScope, $http, $state,$upload,$timeout,newsletterServices) {
	$scope.hideImageSaveBtn = true;
	$scope.filename= '';
	newsletterServices.getNewsletter().then(function (response) {
			if(response.data.Status == 'Ok'){
				$scope.filename= response.data.fileName;
				$scope.filenamelink  = ctx+'/getNewsLetter';
			}else if(response.data.Status == 'Error'){
				$scope.messagedata.uploadsuccess = false;
				$scope.messagedata.uploadmessage = response.data.message;
				
			}
	 });
	
	$scope.fileSelected = function($files,$event){
		$scope.messagedata={"uploadmessage":""};
		if($files.length >0){
			var selectedFile = $files[0].name;
			var fileExtension = selectedFile.substr(selectedFile.length - 4);
			$scope.filenamelink = '';
			//console.log(fileExtension);
			if(fileExtension.toLowerCase() == 'html' || fileExtension.toLowerCase() == '.htm'){
				$scope.myFiles = $files;		
			}else{
				$scope.filename = '';
				$scope.myFiles = {};
				$scope.messagedata.uploadsuccess = false;
				$scope.messagedata.uploadmessage="Allowed file extension(*.html or *.htm)"
				
			}
			
		}
		
	}
	
	$scope.myFiles = {};
	$scope.finalFiles = {};
	$scope.$watch('myFiles', function() {
		if($scope.myFiles.length >0){
			var file = $scope.myFiles[0];
			$scope.filename = file.name;
		    $scope.finalFiles = file;
		}
	});
	
	
	$scope.saveNewsletter = function(){	
		$scope.messagedata={};
		$scope.upload = $upload.upload({
		        url: "saveNewsLetter",
		        method: 'POST',
				file: $scope.finalFiles,
				fileFormDataName: 'file'
		        }).progress(function(evt) {
		        	}).success(function(data, status, headers, config) {
		        		$scope.filenamelink = false;
			        	if(data[0].Status == 'Ok'){
			        		$scope.filename = data[0].fileName;
			        		$scope.filenamelink  = ctx+'/getNewsLetter';
			    			$scope.messagedata.uploadsuccess = true;
							$scope.messagedata.uploadmessage="You have successfully uploaded.";
			        	}else if(data[0].Status == 'Error'){	 
			        		$scope.messagedata.uploadsuccess = false;
							$scope.messagedata.uploadmessage=data[0].message;
						}		        	
		        });
		}
			
}]);