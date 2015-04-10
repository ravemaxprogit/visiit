'use strict';
var manageTripDetail = angular.module('manageTripDetail', [ 
        'ui.router', 
        'validation',
		'validation.rule',
		'ui.bootstrap'
]);


//Routers
manageTripDetail.config(function($stateProvider,$urlRouterProvider) {
  $stateProvider.state('manageTrip_Detail', {
	  url: '/trip-approval/Trip-Details',
      templateUrl: 'resources/views/customercare/manageTripDetail/manageTripDetails_grid.html',
      data: {
          auth: true
      },
      controller: 'manageTripDetailsController'
  });  
});

manageTripDetail.factory('manageTripService', ['$http', '$rootScope',function ($http) {
	
	    var factoryDefinitions = {
	        getTripDetailById : function (id) {
	      	  return $http.get('viewTripDetails?tripStr='+id).success(function (data) {
	                return data;
	            });
	        },
	        getTripVoucherNameByCode : function (tripCode) {
		      	  return $http.get('getTripVoucherFileName?tripCode='+tripCode).success(function (data) {
		                return data;
		            });
		        },
		        
		    deleteTripVoucherByCode : function (tripCode) {
	        	var postdata = "tripCode="+tripCode;
	      	     return $http.post('deleteTripVoucher', postdata ).success(function (data) {
	              return data;
	               });
	         },
	        addNewTripDetail : function (tripDetail) {
		      	  return $http.post('saveTravellerDetailByAdmin', tripDetail ).success(function (data) {
		              return data;
		          });
		     },
		     getTripPriceByPckcode : function (pckCode) {
		      	  return $http.get('getPriceByPackageId?packStr='+pckCode).success(function (data) {
		                return data;
		            });
		        },
	        saveTripDetailById : function (postdata) {
	      	  return $http.post('updateTravellerDetail', postdata ).success(function (data) {
	              return data;
	          });
	        }
        }
        return factoryDefinitions;
	    }
]);

manageTripDetail.controller('manageTripDetailsController', ['$scope', '$rootScope','ngDialog',"$sce", '$http', '$state','$upload','$timeout', 'manageTripService','dataFactory', function ($scope, $rootScope,ngDialog, $sce, $http, $state,$upload, $timeout, manageTripService,dataFactory) {
	
	$scope.isProcessingfalse = function() {
		 $timeout(function() {
			$scope.isProcessing = false;
			}, 1000);
	};
	$scope.newTrip = {};
	$scope.messagedata = {};
		
	$scope.gender = {};
	$scope.psngrfname = {};
	$scope.psngrlname = {};
	$scope.age = {};
	$scope.packPriceList = [];
	$scope.selectedpckPrice= 0;
	$scope.newTrip.selectedPackPriceList = {};
	$scope.getTripDetailBycode = function(tripcode){	
		$scope.manageTrip = {};
		$scope.messagedata = {};
		if(tripcode){	
			$scope.tripApprovalList = [
							{'id':'Pending', 'title':'Pending'},
							{'id':'Approved', 'title':'Approved'},
							{'id':'Refund', 'title':'Refund'},
							{'id':'Partial Refund', 'title':'Partial Refund'}
			    	        ];
			$scope.paymentList = [
							{'id':'Pending', 'title':'Pending'},
							{'id':'Success', 'title':'Success'},
			    	        ];
			$rootScope.showLoader();
			manageTripService.getTripDetailById(tripcode).then(function (response) {
				$scope.manageTrip = response.data;	
				$scope.showPaymentDD = false;
				if($scope.manageTrip.tripDetail.paymentStatus.toLowerCase() == 'pending'){
					$scope.showPaymentDD = true ;	
				}
				ngDialog.open({
		 			template: 'resources/views/customercare/manageTripDetail/editTripDetail.html', 
		  		   	className: 'ngdialog-theme-default',
		  		   	scope: $scope
		 		});
		    });	
			$rootScope.hideLoader();
		}		
	}
	
	$scope.addNewTrip = function(){
		$scope.newTrip = {};
		$scope.selectedPackageId=undefined;
		$scope.titles = {
			"salutation":[{"id":1,"value":"Dr","active":true},{"id":2,"value":"Miss","active":true},{"id":3,"value":"Mr","active":true},{"id":4,"value":"Mrs","active":true},{"id":5,"value":"Ms","active":true},{"id":6,"value":"Professor","active":true},{"id":7,"value":"Reverend","active":true}]
		};
	
		/*$scope.choices = [{id: 'choice1',gender:'',psngrfname: '',psngrlname: '',age: ''}];*/
		
		
		$scope.choices = [{id: 'choice1'}];
		
		$scope.addNewChoice = function() {
			  var newItemNo = $scope.choices.length+1;
			  $scope.choices.push({'id':'choice'+newItemNo});
			  if($scope.selectedpckPrice){
				  $scope.newTrip.finalPckageAmount = newItemNo *  $scope.selectedpckPrice;
			  }
			};
		$scope.removeperson=function(index){
			if($scope.choices.length>1){
				$scope.choices.splice(index, 1);
				if($scope.selectedpckPrice){
					$scope.newTrip.finalPckageAmount = $scope.choices.length *  $scope.selectedpckPrice;
				}
		      }
		};
		$rootScope.showLoader();
	    $scope.tripApprovalList = [
	   							{'id':'Pending', 'title':'Pending'},
	   							{'id':'Approved', 'title':'Approved'},
	   							{'id':'Refund', 'title':'Refund'},
	   							{'id':'Partial Refund', 'title':'Partial Refund'}
	   			    	        ];
	   			$scope.paymentList = [
	   							{'id':'Pending', 'title':'Pending'},
	   							{'id':'Success', 'title':'Success'},
	   			    	        ];
	   		 $scope.newTrip.paymentStatus='Pending';
	   		 $scope.newTrip.approvedStatus='Pending';
	   		$scope.isProcessing = true;
		ngDialog.open({
			 template: 'resources/views/customercare/manageTripDetail/addTripDetail.html', 
			 className: 'ngdialog-theme-default',
		   	 scope: $scope
		});
		$rootScope.hideLoader();
		$scope.isProcessingfalse();
	}
	
	 $scope.getPckName = function(val) {		
		var paramVal = 'packStr='+val;
	    return $http.get('getAllPackageAuto', {params: { packStr: val }}).then(function(response){
	    	 if(response.data.Status == 'Ok'){
	    		 return response.data.listData;
	    	 }else{	    		 
	    		 return [];
	    	 }
	       });
	 };
	 
	 $scope.selectedPckgAutoComplete = function($item, $model, $label){
		 $scope.selectedPackageId = $model.key;
		 manageTripService.getTripPriceByPckcode($scope.selectedPackageId).then(function (response) {
			 $scope.packPriceList= response.data.packList;
		 });
	 };	
	 $scope.resetPackages = function(){
		 $scope.selectedPackageId = undefined;
		 $scope.packPriceList='';
		 $scope.selectedpckPrice= 0;
	 };	
	
	$scope.updateTripDetail = function(){	
		var tripDetail = $scope.manageTrip.tripDetail;
		var tripApprovalComments = '';
    	var paymentApprovalComments = '';
    	if(tripDetail.statusCommands){
    		tripApprovalComments = tripDetail.statusCommands;
    	}
    	if(tripDetail.payementCommands){
    		paymentApprovalComments = tripDetail.payementCommands;
    	}
    	var postdata = "tripCodeStr="+tripDetail.tripcode+"&approvestatusStr="+tripDetail.approvedStatus+"&approvecomandsStr="+tripApprovalComments+"&paymentstatusStr="+tripDetail.paymentStatus+"&paymentcomandsStr="+paymentApprovalComments+"&finalAmount="+tripDetail.finalAmount;
		manageTripService.saveTripDetailById(postdata).then(function (response) {
			if(response.data.Status == 'Ok'){
				$scope.messagedata.success = true;
				$scope.messagedata.message=response.data.message;
				ngDialog.close();
				//$scope.tableParams.reload();
				$scope.refresh = Math.random();
			}else{
				$scope.messagedata.errorMessage = response.data.message;	
				$scope.messagedata.editEnquirySuccess = false;	
			}
			
	    }); 
	}

	$scope.hideImageSaveBtn = false;
	$scope.addTripVoucher = function(tripCode){
		$scope.filename= '';
		$scope.messagedata={};
		$scope.tripCode = tripCode;
		$scope.VoucherStatus = false;
		$scope.hideImageSaveBtn = false;
		ngDialog.open({
			 template: 'resources/views/customercare/manageTripDetail/addTripVoucher.html', 
			 className: 'ngdialog-theme-default imageuploaddialog',
 		   	 scope: $scope
		});
	}
	$scope.editTripVoucher = function(tripCode){
		$scope.messagedata={};
		$scope.tripCode = tripCode;
		$scope.hideImageSaveBtn = true;
		$scope.VoucherStatus = true;
		
		manageTripService.getTripVoucherNameByCode(tripCode).then(function (response) {
			$scope.filename= response.data.fileName;
			if(response.data.Status == 'Ok'){
				$scope.filenamelink  = ctx+'/getTripVoucher/?tripCode='+$scope.tripCode;
				ngDialog.open({
					 template: 'resources/views/customercare/manageTripDetail/addTripVoucher.html', 
					 className: 'ngdialog-theme-default imageuploaddialog',
		 		   	 scope: $scope
				});
			}else if(response.data.Status == 'Error'){
				$scope.messagedata.success = false;
				$scope.messagedata.message=response.data.message;
				
			}
		 }); 
	}
	
	$scope.fileSelected = function($files,$event){
		$scope.myFiles = $files;
	}
	$scope.generateThumb = function(file) {
		  var imgfile;
			if (file != null) {
					$timeout(function() {
						var fileReader = new FileReader();
						fileReader.readAsDataURL(file);
						fileReader.onload = function(e) {
							$timeout(function() {
								file.dataUrl = e.target.result;
								$scope.imgfile = file.dataUrl;
								$scope.filename = file.name;
								$scope.messagedata.Vouchermessage = '';
							});
						}
					});
			}
		}
	
	$scope.myFiles = {};
	$scope.finalFiles = {};
	$scope.$watch('myFiles', function() {
		$scope.hideImageSaveBtn = true;
		if($scope.myFiles.length >0){
	    for (var i = 0; i < 1; i++) {
	      var file = $scope.myFiles[i];
	      $scope.generateThumb(file);
	      $scope.finalFiles = file;
	  }
	}
	});
	
	
	$scope.saveTripVoucher = function(tripCode){
	$scope.tripCode=tripCode;
	
	$scope.messagedata={};
	$scope.upload = $upload.upload({
	        url: "saveTripVoucher",
	        method: 'POST',
	        fields: {tripCode: $scope.tripCode},
			file: $scope.finalFiles,
			fileFormDataName: 'file'
	        }).progress(function(evt) {
	        }).success(function(data, status, headers, config) {
	        	if(data[0].Status == 'Ok'){
					$scope.messagedata.success = true;
					$scope.messagedata.message=data[0].message;
		        	ngDialog.close();
		        	$scope.refresh = Math.random();
	        	}else if(data[0].Status == 'Error'){	 
	        		$scope.messagedata.Vouchersuccess = false;
					$scope.messagedata.Vouchersuccess=data[0].message;

				}
	        	
	        });
	}
		
	$scope.deleteTripVoucher = function(tripCode){
		$scope.messagedata={};
		$scope.tripCode = tripCode;
		manageTripService.deleteTripVoucherByCode(tripCode).then(function (response) {
			if(response.data.Status == 'Ok'){
				$scope.refresh = Math.random();
				$scope.hideImageSaveBtn = false;
				$scope.VoucherStatus = false;	
				$scope.filenamelink = '';
				$scope.filename = '';
				$scope.messagedata.Vouchersuccess = true;
				$scope.messagedata.Vouchermessage=response.data.message;
			}else if(response.data.Status == 'Error'){
				$scope.messagedata.Vouchersuccess = false;
				$scope.messagedata.Vouchermessage=response.data.message;
				
			}
			
		});
		
	}

	$scope.cancel = function()
	{
		ngDialog.close();
	}
	
	 
	 $scope.finalPriceCalc = function(pckPriceVal){		
		 $scope.selectedpckPrice = pckPriceVal.value;
		 $scope.newTrip.finalPckageAmount = pckPriceVal.value * $scope.choices.length;
    }; 
	  
	
	$scope.saveNewTripDetail = function(){	
		
		var contactDetailStr = {};
		var personDetailStr = {};
		var travellerDetailStr ={};
		var billingAddressStr = {};
		var packStr = $scope.selectedPackageId;
		$scope.addperson = [];
		for(var i=0;i<$scope.choices.length;i++)
		{
			$scope.addperson.push({"salutation":$scope.choices[i].gender,"firstname":$scope.choices[i].psngrfname,"lastname":$scope.choices[i].psngrlname,"age":$scope.choices[i].age});
		}
		personDetailStr = $scope.addperson;
		travellerDetailStr.travelDate = $scope.newTrip.travelDate;
		travellerDetailStr.firstname = $scope.newTrip.fname;
		travellerDetailStr.lastname = $scope.newTrip.lname;
		travellerDetailStr.email = $scope.newTrip.bkemail;
		travellerDetailStr.phone = $scope.newTrip.bkphone;

		travellerDetailStr.address = $scope.newTrip.address;
		travellerDetailStr.city = $scope.newTrip.city;
		travellerDetailStr.state = $scope.newTrip.states;
		travellerDetailStr.postelCode = $scope.newTrip.postal;
		travellerDetailStr.country = $scope.newTrip.country;
		travellerDetailStr.packageAmount = $scope.newTrip.pckPrice;
		travellerDetailStr.finalAmount = $scope.newTrip.finalPckageAmount;
		travellerDetailStr.totalAmount = $scope.newTrip.finalPckageAmount;
		travellerDetailStr.paymentStatus = $scope.newTrip.paymentStatus;
		travellerDetailStr.approvedStatus = $scope.newTrip.approvedStatus;
		travellerDetailStr.payementCommands= $scope.newTrip.payementCommands;
		travellerDetailStr.statusCommands= $scope.newTrip.statusCommands;

		if($scope.selectedPackageId && $scope.newTrip.selectedPackPriceList){
			travellerDetailStr.packageAmount=$scope.newTrip.selectedPackPriceList.value;
			var load = "packStr="+packStr+"&personDetailStr="+JSON.stringify(personDetailStr)+"&travellerDetailStr="+JSON.stringify(travellerDetailStr)+"&customerStr=&prizeStr="+$scope.newTrip.selectedPackPriceList.key;
			manageTripService.addNewTripDetail(load).then(function(response){
				if(response.data.Status == 'Ok'){
					$scope.messagedata.success = true;
					$scope.messagedata.message=response.data.message;
					ngDialog.close();
					$scope.refresh = Math.random();
				}else{
					$scope.newTrip.message = response.data.message;	
					$scope.newTrip.success = false;	
				}
				
			});
		}else{
			alert("Please choose the Package and Price.");
		}
		
	}
	
			
}]);

