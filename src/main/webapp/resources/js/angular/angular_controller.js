'use strict';
var app = angular.module('user', [ 
        'ui.router', 
        'validation',
		'validation.rule',
		'ngTable'
]);
var imageCropArray={				
		packageCategory : {
			width : '68',
			height : '66'
		},
		packageDetail : {
			width : '258',
			height : '259'
		},
		packageHotel : {
			width : '181',
			height : '146'
		},	
		packageDetailsSlide : {
			width : '524',
			height : '338'
		},
		packageInclusion : {
			width : '99',
			height : '99'
		},
		packageExclusion : {
			width : '99',
			height : '99'
		},
		
		packageTile : {
			width : '100',
			height : '100'
		},
		packageItenary : {
			width : '147',
			height : '147'
		},
		packageList : {
			width : '400',
			height : '401'
		},
		packageTile1 : {
			width : '263',
			height : '306'
		},
		packageTile2 : {
			width : '263',
			height : '306'
		},
		packageTile3 : {
			width : '258',
			height : '259'
		},
		packageTile4 : {
			width : '273',
			height : '614'
		},
		packageTile5 : {
			width : '264',
			height : '260'
		},
		packageTile6 : {
			width : '264',
			height : '260'
		},
		packageTile7 : {
			width : '264',
			height : '260'
		},
		packageTile8 : {
			width : '524',
			height : '273'
		},
		packageTile9 : {
			width : '259',
			height : '274'
		}			
};

app.constant('iconconfig', {
    acInactive: 'resources/images/icons/hotelDisable/AirCondition.png',
    airportInactive: 'resources/images/icons/hotelDisable/AirportPickupDrop.png',
    businessInactive: 'resources/images/icons/hotelDisable/BusinessCenter.png',
    carInactive: 'resources/images/icons/hotelDisable/CarParking.png',
    dialInactive: 'resources/images/icons/hotelDisable/DialPhones.png',
    doctorInactive: 'resources/images/icons/hotelDisable/DoctorOnCall.png',
    floralInactive: 'resources/images/icons/hotelDisable/FloralRequest.png',
    gymInactive: 'resources/images/icons/hotelDisable/Gym.png',
    heaterInactive: 'resources/images/icons/hotelDisable/Heater.png',
    ironInactive: 'resources/images/icons/hotelDisable/Ironingboard.png',
    kidsInactive: 'resources/images/icons/hotelDisable/KidsDayCare.png',
    laundryInactive: 'resources/images/icons/hotelDisable/Laundry.png',
    libraryInactive: 'resources/images/icons/hotelDisable/Library.png',
    barInactive: 'resources/images/icons/hotelDisable/MiniBarGourmet.png',
    foodInactive: 'resources/images/icons/hotelDisable/Restaurant.png',
    roomsafeInactive: 'resources/images/icons/hotelDisable/RoomSafe.png',
    spaInactive: 'resources/images/icons/hotelDisable/SaloonSPA.png',
    showerInactive: 'resources/images/icons/hotelDisable/Shower.png',
    swimmingInactive: 'resources/images/icons/hotelDisable/SwimmingPool.png',
    traveldeskInactive: 'resources/images/icons/hotelDisable/TravelDesk.png',
    tvInactive: 'resources/images/icons/hotelDisable/TVDVDMP3Player.png',
    villaInactive: 'resources/images/icons/hotelDisable/VillaType.png',
    yogaInactive: 'resources/images/icons/hotelDisable/YogaMeditation.png',
    acActive: 'resources/images/icons/hotelEnable/AirCondition.png',
    airportActive: 'resources/images/icons/hotelEnable/AirportPickupDrop.png',
    businessActive: 'resources/images/icons/hotelEnable/BusinessCenter.png',
    carActive: 'resources/images/icons/hotelEnable/CarParking.png',
    dialActive: 'resources/images/icons/hotelEnable/DialPhones.png',
    doctorActive: 'resources/images/icons/hotelEnable/DoctorOnCall.png',
    floralActive: 'resources/images/icons/hotelEnable/FloralRequest.png',
    gymActive: 'resources/images/icons/hotelEnable/Gym.png',
    heaterActive: 'resources/images/icons/hotelEnable/Heater.png',
    ironActive: 'resources/images/icons/hotelEnable/Ironingboard.png',
    kidsActive: 'resources/images/icons/hotelEnable/KidsDayCare.png',
    laundryActive: 'resources/images/icons/hotelEnable/Laundry.png',
    libraryActive: 'resources/images/icons/hotelEnable/Library.png',
    barActive: 'resources/images/icons/hotelEnable/MiniBarGourmet.png',
    foodActive: 'resources/images/icons/hotelEnable/Restaurant.png',
    roomsafeActive: 'resources/images/icons/hotelEnable/RoomSafe.png',
    spaActive: 'resources/images/icons/hotelEnable/SaloonSPA.png',
    showerActive: 'resources/images/icons/hotelEnable/Shower.png',
    swimmingActive: 'resources/images/icons/hotelEnable/SwimmingPool.png',
    traveldeskActive: 'resources/images/icons/hotelEnable/TravelDesk.png',
    tvActive: 'resources/images/icons/hotelEnable/TVDVDMP3Player.png',
    villaActive: 'resources/images/icons/hotelEnable/VillaType.png',
    yogaActive: 'resources/images/icons/hotelEnable/YogaMeditation.png',
    airportStationInactive: 'resources/images/icons/packageDisable/Airportportstationpickup.png',
    breakfastInactive: 'resources/images/icons/packageDisable/Breakfast.png',
    busInactive: 'resources/images/icons/packageDisable/Bus.png',
    campfireInactive: 'resources/images/icons/packageDisable/Campfire.png',
    ferryInactive: 'resources/images/icons/packageDisable/Ferry.png',
    flightInactive: 'resources/images/icons/packageDisable/Flight.png',
    gamesInactive: 'resources/images/icons/packageDisable/GamesIndoorOutdoor.png',
	guideInactive: 'resources/images/icons/packageDisable/Guide.png',
    hotelInactive: 'resources/images/icons/packageDisable/Hotel.png',
    zooInactive: 'resources/images/icons/packageDisable/SafariJungleZoo.png',
    seaInactive: 'resources/images/icons/packageDisable/SeaActivitySnorklingScuba.png',
    showInactive: 'resources/images/icons/packageDisable/Shows.png',
    trainInactive: 'resources/images/icons/packageDisable/Train.png',
    transferInactive: 'resources/images/icons/packageDisable/Transfers.png',
	visaInactive: 'resources/images/icons/packageDisable/Visa.png',
	airportStationActive: 'resources/images/icons/packageEnable/Airportportstationpickup.png',
    breakfastActive: 'resources/images/icons/packageEnable/Breakfast.png',
    busActive: 'resources/images/icons/packageEnable/Bus.png',
    campfireActive: 'resources/images/icons/packageEnable/Campfire.png',
    ferryActive: 'resources/images/icons/packageEnable/Ferry.png',
    flightActive: 'resources/images/icons/packageEnable/Flight.png',
    gamesActive: 'resources/images/icons/packageEnable/GamesIndoorOutdoor.png',
	guideActive: 'resources/images/icons/packageEnable/Guide.png',
    hotelActive: 'resources/images/icons/packageEnable/Hotel.png',
    zooActive: 'resources/images/icons/packageEnable/SafariJungleZoo.png',
    seaActive: 'resources/images/icons/packageEnable/SeaActivitySnorklingScuba.png',
    showActive: 'resources/images/icons/packageEnable/Shows.png',
    trainActive: 'resources/images/icons/packageEnable/Train.png',
    transferActive: 'resources/images/icons/packageEnable/Transfers.png',
    visaActive: 'resources/images/icons/packageEnable/Visa.png'
  });
app.config(function($stateProvider,$urlRouterProvider, $httpProvider) {
	 $httpProvider.interceptors.push('sessionInterceptor');
	  
	  /*if(!window.sessionStorage["userInfo"]){
		$urlRouterProvider.otherwise("/logout")
	  }else{
		  window.location.href="dashboard";
	  }*/

	$stateProvider.state('dashboard', {
        url: '/dashboard',
        templateUrl: 'resources/views/dashboard/dashboard.html',
        controller:"dashboardController"
    });
	$stateProvider.state('masters', {
        url: '/masters',
        controller: function($rootScope,$state){
        	if($rootScope.acl.userModules.indexOf('Country') != -1){
        		$state.go('country');
        	}else if($rootScope.acl.userModules.indexOf('Users') != -1){
        		$state.go('manageUsers');
        	}else if($rootScope.acl.userModules.indexOf('Department') != -1){
        		$state.go('department');
        	}else if($rootScope.acl.userModules.indexOf('Designation') != -1){
        		$state.go('designation');
        	}
        	//console.log($rootScope.acl);
        }
    });
	$stateProvider.state('product', {
        url: '/product',
        controller: function($rootScope,$state){
        	if($rootScope.acl.userModules.indexOf('Vendor') != -1){
        		$state.go('vendor');
        	}else if($rootScope.acl.userModules.indexOf('Hotel') != -1){
        		$state.go('hotel');
        	}else if($rootScope.acl.userModules.indexOf('Package') != -1){
        		$state.go('package');
        	}else if($rootScope.acl.userModules.indexOf('Package Category') != -1){
        		$state.go('package_category');
        	}
        }
    });
	$stateProvider.state('support', {
        url: '/support',
        controller: function($rootScope,$state){
        	if($rootScope.acl.userModules.indexOf('Enquiry Support') != -1 ){
        		$state.go('enquiry_support');
        	}else if($rootScope.acl.userModules.indexOf('Payment Approval') != -1){
        		$state.go('manageTrip_Detail');
        	}else if($rootScope.acl.userModules.indexOf('News Letter') != -1){
        		$state.go('newsletter');
        	}
        }
    });
	$stateProvider.state('approval', {
        url: '/approval',
        controller: function($rootScope,$state){
        	if($rootScope.acl.userModules.indexOf('Payment Approval') != -1){
        		$state.go('manageTrip_Detail');
        	}
        }
    });
	$stateProvider.state('country', {
        url: '/country',
        templateUrl: 'resources/views/masters/country.html',
        data: {
            auth: true
        },
        controller: 'masterController'
    });
	//url definition for products
	$stateProvider.state('vendor', {
        url: '/vendor',
        templateUrl: 'resources/views/product/vendor.html',
        data: {
            auth: true
        },
        controller: 'vendorController'
    });
	$stateProvider.state('hotel', {
        url: '/hotel',
        templateUrl: 'resources/views/product/hotel.html',
        data: {
            auth: true
        },
        controller: 'hotelController'
    });
	$stateProvider.state('package', {
        url: '/package',
        templateUrl: 'resources/views/product/package.html',
        data: {
            auth: true
        },
        controller: 'packageController'
    });    
	$stateProvider.state('package_category', {
        url: '/package_category',
        templateUrl: 'resources/views/product/package_category.html',
        data: {
            auth: true
        },
        controller: 'categoryController'
    });
	$stateProvider.state('customer_care', {
        url: '/customer_care',
        templateUrl: 'resources/views/customercare/CustomerCare.html',
        data: {
            auth: true
        },
        controller: 'customercareController'
    });	
	
});

//Run phase
app.run(function ($rootScope, $state, $http,$timeout) {
    $rootScope.$state = $state; 
    $rootScope.imagePath = 'http://www.visiit.com/res/images';
    $rootScope.showLoader = function() {
		$('.loader').show();
		// $('body').css('overflow', 'hidden');
	};

	$rootScope.hideLoader = function() {
		$('.loader').hide();
		// $('body').css('overflow', 'auto');
	};
	
});

//Factories for product services
app.factory('dashboardServices', ['$http',
    function ($http) {
        var factoryDefinitions = {
        		getStatistics: function () {
                return $http.get('getStatistics').success(function (data) {
                    return data;
                });
            }         
        }
        return factoryDefinitions;
    }
]);

app.controller('dashboardController', ['$scope', '$state', 'dashboardServices','$rootScope', function ($scope, $state, dashboardServices,$rootScope) {	
	$rootScope.showLoader();
	dashboardServices.getStatistics().then(function (response) {
		$scope.statistics = response.data;
		$rootScope.hideLoader();
    });	
	
}]);

//Factories for product services
app.factory('productServices', ['$http', '$rootScope',
    function ($http) {
        var url = "";
        var factoryDefinitions = {
            getVendor: function () {
                return $http.get('getVendor').success(function (data) {
                    return data;
                });
            },
            getVendorDetails: function (vendorId) {
                return $http.get('getVendorDetails?vendorId='+vendorId).success(function (data) {
                    return data;
                });
            },
            getHotel: function () {
                return $http.get('getAllHotels').success(function (data) {
                    return data;
                });
            },
            getHotelDetails: function (hotelId) {
                return $http.get('getHotel?hotelId='+hotelId).success(function (data) {
                    return data;
                });
            }            
        }
        return factoryDefinitions;
    }
]);

app.controller('hotelController', ['$scope', '$rootScope', "$sce", '$http', '$state','$location', 'ngDialog','productServices','$upload','$timeout','masterServices','iconconfig', function ($scope, $rootScope, $sce, $http, $state,$location, ngDialog,productServices,$upload, $timeout,masterServices,iconconfig) {		
	$scope.hotelList = {};
	$scope.hotel = {};
	$scope.contact = {};
	$scope.appIcons=iconconfig;
	$scope.hotel.viIsactiveTrue = 0;
	$scope.showaddHotelDeatils = 0;
	$scope.hotelfeaturesCount = 0;
	$scope.message =[{
		'success':0,
		'error':0
	}];
	
	
	$scope.master =[{
		'countries':[],
		'states':[],
		'cities':[],
		'locations':[]
	}];
	

	//country
	$scope.changeCountry = function(){
		$scope.master.states = [];
		$scope.master.cities = [];
		$scope.master.locations = [];
			
		if($scope.hotel.hdCountry != undefined && $scope.hotel.hdCountry !=''){
			masterServices.getState($scope.hotel.hdCountry).then(function (response) {
				$scope.master.states = response.data;
			});
		}else{
			$scope.country = {};
			$scope.state = {};
			$scope.city = {};
			}
	}
    // change the State 
	$scope.changeState = function(){
		
		$scope.master.cities = [];
		$scope.master.locations = [];
		if($scope.hotel.hdState != undefined && $scope.hotel.hdState !=''){
			masterServices.getCity($scope.hotel.hdState).then(function (response) {
				$scope.master.cities = response.data;
		    });
		}else{
			$scope.state = {};
			$scope.city = {};
			}
	}
	// change the City 
	$scope.changeCity = function(){
		$scope.location_id = '';
		$scope.master.locations = [];
		if($scope.hotel.hdCity != undefined && $scope.hotel.hdCity !=''){
			masterServices.getLocation($scope.hotel.hdCity).then(function (response) {
				$scope.master.locations = response.data;
		    });
		}else{
			// reset edit scope			
			$scope.city = {};
			}
	}
	//Filter
	$scope.hotel_search = function (obj) {
		 var re = new RegExp($scope.h_search, 'i');
	        return !$scope.h_search || re.test(obj.hdCode) || re.test(obj.hdName.toString()) || re.test(obj.hdEmail.toString());
    };
	
	productServices.getHotel().then(function (response) {
		$scope.hotelList = response.data;		
    });
	
	//cancel - close dialog
	$scope.cancel = function(){
		ngDialog.close();
	}	
	// load add form and edit with vendor details
	$scope.hotelAdd = function(hotelId){		
		$scope.message ={
				'success':0,
				'error':0
			};
		if(hotelId !='' && hotelId != undefined){
			productServices.getHotelDetails(hotelId).then(function (response) {
				$scope.hotelImage ={};
				$scope.hotel = response.data.hotel;
				$scope.hotelImage = response.data.hotelImg;
				var hotelFeaturesArray=["hdAirCondition","hdDoctorOnCall","hdlaundry","hdLibrary","hdTravelDesk","hdTVDVD","hdBussinessCenter","hdHeater","hdIroning","hdKids","hdRoomsafe","hdVilla","hdSPA","hdGym","hdShower","hdPickup","hdFloralRequest","hafHelthclubYoga","hdSwimmingpool","hdParking","hdBar","hdFood"];
				var featuresCount=0;
				angular.forEach(hotelFeaturesArray,function(value,key)
				{
					if($scope.hotel[value])
					{
						featuresCount++;						
					}	
				});				
				$scope.hotelfeaturesCount=featuresCount;
				//Load Country
				masterServices.getCountry().then(function (response) {
					$scope.master.countries = response.data;
					//Load State
					masterServices.getState($scope.hotel.hdCountry).then(function (response) {
						//Load City
						$scope.master.states = response.data;
						masterServices.getCity($scope.hotel.hdState).then(function (response) {
							$scope.master.cities = response.data;
							ngDialog.open({
								 template: 'resources/views/product/addHotelDetails.html', 
								 className: 'ngdialog-theme-default',
					  		   	 scope: $scope
					 		});
						});
					});
			    });				
			});			
		}else{
			$scope.hotel = {};
			$scope.hotelfeaturesCount=0;
			masterServices.getCountry().then(function (response) {
				$scope.master.countries = response.data;
				$scope.master.states = {};
				$scope.master.cities = {};
				ngDialog.open({
					 template: 'resources/views/product/addHotelDetails.html', 
					 className: 'ngdialog-theme-default',
		  		   	 scope: $scope
		 		});
		    });			
		}
		
		
		 //$scope.showaddHotelDeatils = 1;
	}	

	$scope.change = function(data,err){ 
		if(data != ''){
		 var errresults = eval("$scope." + err);
		  var letters = /^[a-zA-Z ]+$/;
          if (data.match(letters)){
        	  $scope.nameerror=0;
          }
          else{
     		 $scope.nameerror=1;
          }
		}
		else
			{
			 $scope.nameerror=0;
			}
	
	}

	$scope.positionchange = function(data,err){ 
		if(data != ''){
	 //var errresults = eval("$scope." + err);
	  var letters = /^[a-zA-Z0-9 ]+$/;
     if (data.match(letters)){
    	 $scope.positionerror=0;
     }
     else{
		 $scope.positionerror=1;
     }
		}
		else
		{
			$scope.positionerror=0;
		}
}

	$scope.mobilechange = function(data,err){  
	 //var errresults = eval("$scope." + err);
		if(data != ''){
	 var letters = /^[ 0-9/+()]*$/;
     if (data.match(letters)){ 
      if (parseInt(data.length) <= 15){ 
    	  $scope.mobilenumbererror=0;
     } 
     else{
		 $scope.mobilenumbererror=0;
     }
     }
     else{
		 $scope.mobilenumbererror=1;
     }
		}
		else
		{
			$scope.mobilenumbererror=0;
		}
		
}	

	$scope.emailchange = function(data,err){ 
		if(data != ''){
		 //var errresults = eval("$scope." + err);
		var reg = /^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/
			 if (reg.test(data)){
				 $scope.emailerror=0; }
			 else{
				 $scope.emailerror=1;
			 }
		}
		else
		{
			 $scope.emailerror=0; 
		}		
		
	}
	//Add hotel image in dialog box
	$scope.hideImageSaveBtn = 0;
	$scope.addHotelImage = function(hotel_data){
		$scope.hotel_details = hotel_data;
		$scope.hideImageSaveBtn = 0;
		if($scope.hotel_details.imageUrl !='')
			$scope.imgfile = 'resources/images'+$scope.hotel_details.imageUrl;
		ngDialog.open({
			 template: 'resources/views/product/addHotelImage.html', 
			 className: 'ngdialog-theme-default imageuploaddialog',
 		   	 scope: $scope
		});
	}
	$scope.cropedImage="";
	$scope.fileSelected = function($files,$event){
		$scope.myFiles = $files;
//$scope.hideImageSaveBtn = 1;
	}
	$scope.myFiles = {};
	$scope.$watch('myFiles', function() {
		$scope.hideImageSaveBtn = 1;
		if($scope.myFiles.length >0){
	    for (var i = 0; i < 1; i++) {
	      var file = $scope.myFiles[i];	     	     
	      var maxFileSize = 2 * 1024 * 1024;	     
	      var fileSize=file.size;	      
	      if(maxFileSize<fileSize)
		  {
		    	  $scope.fileError="File size must be less than 2 MB";		    	  
		    	  return false;
		  }	  
	      else
	      {
	    	  $scope.fileType=file.type;   	  
	       	  if(file!=undefined)
	       	  {  
	    	         var reader = new FileReader();	          
	    	          reader.onload = function (evt) {
	    	            $scope.$apply(function($scope){
	    	                    $scope.imgfile=evt.target.result;	
	    	                   
	    	            });
	    	          };	
	    	          reader.readAsDataURL(file);
	    	          $scope.hideImageSaveBtn = 1;	         
	    	          $scope.cropedImage=file;
	       	  } 	
	      
	     }
	  }
	}
	});
	
	$scope.generateThumb = function(file) {
		  var imgfile;
			if (file != null) {
				if (file.type.indexOf('image') > -1) {
					$timeout(function() {
						var fileReader = new FileReader();
						fileReader.readAsDataURL(file);
						fileReader.onload = function(e) {
							$timeout(function() {
								file.dataUrl = e.target.result;
								$scope.imgfile = file.dataUrl;
							});
						}
					});
				}
			}
		}
	
	 $scope.imageDataURI='';       	 
     $scope.imgfile='';                
	 $scope.cropperWidth="200";
	 $scope.cropperHeight="200";	
	 
	
	$scope.saveHotelImage = function(){	
		$scope.imgfile='';
		
		var imageTop=angular.element(".canvas").attr("topvalue");
		var imageLeft=angular.element(".canvas").attr("leftvalue");
		
		$scope.upload = $upload.upload({
	        url: "uploadImage",
	        method: 'POST',
	        data: {module: 'hotel',subModule:$scope.hotel_details.hdCode,objectId:$scope.hotel_details.hdSeqId},	        
	        file: $scope.cropedImage 
	        }).progress(function(evt) {
	        }).success(function(data, status, headers, config) {
	        	$scope.hotelResponse = {};
	    		$scope.hotelResponse.message = 'Image saved successfully';
	    		$scope.message ={
					'success':1,
					'error':0
				};
				//update scope
				productServices.getHotel().then(function (response) {
					$scope.hotelList = response.data;		
				});
				ngDialog.close();
	        }).error(function(data, status, headers, config) {
	        	$scope.message ={
	        			'success':0,
	    				'error':1
	        		};
	        		$scope.hotelResponse.message = 'Image was not saved.Please try again';
	        		ngDialog.close();
	        });
		
	}	
	
	//save hotel
	$scope.saveHotel = function(){
		$scope.hotelContactPostdata = $scope.hotel.hotelContacts
		$scope.hotel.hdCode = ($scope.hotel.hdCode =='' || $scope.hotel.hdCode == undefined)?'':$scope.hotel.hdCode;
		delete $scope.hotel.hotelContacts;
		$scope.hotelData = $scope.hotel;
		//var postdata = "hotelStr="+JSON.stringify($scope.hotelData)+"&hotelContStr="+JSON.stringify($scope.hotelContactPostdata)+'&imgStr=Hotel';
		var postdata = "hotelStr="+JSON.stringify($scope.hotelData)+"&hotelContStr="+JSON.stringify($scope.hotelContactPostdata);
		if($scope.mobilenumbererror!=1 && $scope.positionerror!=1 && $scope.emailerror!=1 && $scope.nameerror!=1){
			$rootScope.showLoader();
      	$http.post("saveHotel", postdata).success(function(data) {
				$scope.hotelResponse = data;
				if(data.Status == 'Error'){
					$scope.message ={
							'success':0,
							'error':1
						};
					$rootScope.hideLoader();
					ngDialog.close();
				}
				if(data.Status == 'Ok'){
					$scope.message ={
						'success':1,
						'error':0
					};
					ngDialog.close();
					//update scope
					productServices.getHotel().then(function (response) {
						$scope.hotelList = response.data;	
						$rootScope.hideLoader();
				    });
					ngDialog.close();
				}
		});
      
		}
	}
	
	//delete Hotel
	$scope.deleteHotel = function(hotelId){
		var result = confirm("Are you sure want to delete?");
		if (result==true) {
			$rootScope.showLoader();
			$http.post('deleteHotel','hotelId='+hotelId).success(function(data) {
				$scope.hotelResponse = data;
				if(data.Status == 'Error'){
					$scope.message ={
							'success':0,
							'error':1
						};
					$rootScope.hideLoader();
				}
				if(data.Status == 'Ok'){
					$scope.message ={
						'success':1,
						'error':0
					};
					productServices.getHotel().then(function (response) {
						$scope.hotelList = response.data;	
						$rootScope.hideLoader();
				    }); 
				}
			});
			
		}
		
	}
}]);


//dialog controller for vendor page
app.controller('vendorController', ['$scope', '$rootScope', "$sce", '$http', '$state','$location', 'ngDialog','productServices','masterServices','$timeout', function ($scope, $rootScope, $sce, $http, $state,$location, ngDialog,productServices,masterServices,$timeout) {
	$scope.vendorList = {};
	$scope.vendor = {};
	$scope.vendor.viIsactiveTrue = false;
	
	$scope.isProcessingfalse = function() {
		 $timeout(function() {
			$scope.isProcessing = false;
			}, 1000);
	};
	
	$scope.message =[{
		'success':0,
		'error':0
	}];
	
	
	$scope.master =[{
		'countries':[],
		'states':[],
		'cities':[],
		'locations':[]
	}];
 
	//Filter
	$scope.vendor_search = function (obj) {
		 var re = new RegExp($scope.v_search, 'i');
	        return !$scope.v_search || re.test(obj.viSeqId) || re.test(obj.viVendorName.toString()) || re.test(obj.viVendorEmail.toString());
    };
	
	
	//country
	$scope.changeCountry = function(){
		$scope.master.states = [];
		$scope.master.cities = [];
		$scope.master.locations = [];
			
		if($scope.vendor.viCountry != undefined && $scope.vendor.viCountry !=''){
			masterServices.getState($scope.vendor.viCountry).then(function (response) {
				$scope.master.states = response.data;
			});
		}else{
			$scope.country = {};
			$scope.state = {};
			$scope.city = {};
			}
	}
    // change the State 
	$scope.changeState = function(){
		$scope.master.cities = [];
		$scope.master.locations = [];
		if($scope.vendor.viState != undefined && $scope.vendor.viState !=''){
			masterServices.getCity($scope.vendor.viState).then(function (response) {
				$scope.master.cities = response.data;
		    });
		}else{
			$scope.state = {};
			$scope.city = {};
			}
	}
	// change the City 
	$scope.changeCity = function(){
		$scope.location_id = '';
		$scope.master.locations = [];
		if($scope.vendor.viCity != undefined && $scope.vendor.viCity !=''){
			masterServices.getLocation($scope.vendor.viCity).then(function (response) {
				$scope.master.locations = response.data;
		    });
		}else{
			// reset edit scope			
			$scope.city = {};
			}
	}
	
	
	
	//cancel - close dialog
	$scope.cancel = function()
	{
		ngDialog.close();
	}
	//get vendor list
	productServices.getVendor().then(function (response) {
		$scope.vendorList = response.data;		
    });
	// Active vendor radio button	
	$scope.radioVendorActive = function(){	
		$scope.vendor.viIsactiveTrue = !$scope.vendor.viIsactiveTrue;	
	}
	//save vendor
	$scope.vendorSave = function(){		  
		//$scope.vendor.viIsactive = ($scope.vendor.viIsactiveTrue)?'y':'n';
		$scope.vendor.viIsactive = ($scope.vendor.viIsactiveTrue)?true:false;

		$scope.vendor.viCode = ($scope.vendor.viCode =='' || $scope.vendor.viCode == undefined)?'':$scope.vendor.viCode;
		delete $scope.vendor.viIsactiveTrue;
		$rootScope.showLoader();
		$http.post('saveVendor', $scope.vendor, { headers: { 'Content-Type': 'application/json; charset=UTF-8'} }
			).success(function(data) {
				//$scope.vendor.viIsactiveTrue = ($scope.vendor.viIsactive== 'y')?true:false;
				$scope.vendor.viIsactiveTrue = ($scope.vendor.viIsactive== 'Y')?true:false;
				$scope.vendorResponse = data;
				if(data.Status == 'Error'){
					$scope.message ={
							'success':0,
							'error':1
						};
					$rootScope.hideLoader();
				}
				if(data.Status == 'Ok'){
					$scope.message ={
						'success':1,
						'error':0
					};
					//update scope
					productServices.getVendor().then(function (response) {
						$scope.vendorList = response.data;		
						$rootScope.hideLoader();
				    });					
				}
				ngDialog.close();
		});
		
	}
	//delete vendor
	$scope.deleteVendor = function(vendorId){
		var result = confirm("Are you sure want to delete?");
		if (result==true) {
			$rootScope.showLoader();
			$http.get('deleteVendor?vendorId='+vendorId).success(function(data) {
				$scope.vendorResponse = data;
				if(data.Status == 'Error'){
					$scope.message ={
							'success':0,
							'error':1
						};
					$rootScope.hideLoader();
				}
				if(data.Status == 'Ok'){
					$scope.message ={
						'success':1,
						'error':0
					};
					productServices.getVendor().then(function (response) {
					$scope.vendorList = response.data;
					$rootScope.hideLoader();
				    }); 
				}
			});
		}
		
	}
	// load add form and edit with vendor details
	$scope.vendorAdd = function(vendorId){	
		$scope.message ={
				'success':0,
				'error':0
			};
		if(vendorId !='' && vendorId != undefined){
			$rootScope.showLoader();
			productServices.getVendorDetails(vendorId).then(function (response) {
				$scope.vendor = response.data.vendor;
				//$scope.vendor.viIsactiveTrue = response.data.vendor.viIsactive=='y'?true:false;
				$scope.vendor.viIsactiveTrue = response.data.vendor.viIsactive;
				//Load Country
				masterServices.getCountry().then(function (response) {
					$scope.master.countries = response.data;
					//Load State
					masterServices.getState($scope.vendor.viCountry).then(function (response) {
						//Load City
						$scope.master.states = response.data;
						masterServices.getCity($scope.vendor.viState).then(function (response) {
							$scope.master.cities = response.data;
							$scope.isProcessing = true;
							ngDialog.open({
					 			template: 'resources/views/product/addVendorDetails.html', 
					  		   	className: 'ngdialog-theme-default',
					  		   	scope: $scope
					 		});
							$rootScope.hideLoader();
							$scope.isProcessingfalse();
						});
					});
			    });
		    });
		}else{
			$rootScope.showLoader();
			$scope.vendor = {};
			masterServices.getCountry().then(function (response) {
				$scope.master.countries = response.data;
				$scope.isProcessing = true;
				ngDialog.open({
		 			template: 'resources/views/product/addVendorDetails.html', 
		  		   	className: 'ngdialog-theme-default',
		  		   	scope: $scope
		 		});
				$rootScope.hideLoader();
				$scope.isProcessingfalse();
			});
		}
		 
		
	}	   
	$scope.hotelAdd = function(){
		$scope.message ={
				'success':0,
				'error':0
			};
		$scope.isProcessing = true;
		$rootScope.showLoader();
		ngDialog.open({
			template: 'resources/views/product/addHotelDetails.html', 
 		   className: 'ngdialog-theme-default'
		});
		$rootScope.hideLoader();
		$scope.isProcessingfalse();
	}			
	$scope.pakagecategoryAdd = function(){
		$rootScope.showLoader();
		ngDialog.open({
			template: 'resources/views/product/addPackage_Category.html', 
 		   className: 'ngdialog-theme-default'
		});
		$rootScope.hideLoader();
	}	
}]);

//dialog controller for Customercare page
app.controller('customercareController', ['$scope', '$rootScope', "$sce", '$http', '$state','$location', 'ngDialog', function ($scope, $rootScope, $sce, $http, $state,$location, ngDialog) {
	$scope.customercareAdd = function(){
		$rootScope.showLoader();
		ngDialog.open({
			template: 'resources/views/customercare/addCustomerCare.html', 
 		   className: 'ngdialog-theme-default'
		});
		$rootScope.hideLoader();
	}	   
	   
}]);

//Factories for user masters
app.factory('masterServices', ['$http', '$rootScope',
    function ($http) {
        var url = "";
        var factoryDefinitions = {
            getCountry: function () {
                return $http.get('getAllCountries').success(function (data) {
                    return data;
                });
            },
	        getState: function (country_id) {
	            return $http.get('stateList?countryId='+country_id).success(function (data) {
	                return data;
	            });
	        },
	        getCity: function (stateId) {
	            return $http.get('cityList?stateId='+stateId).success(function (data) {
	                return data;
	            });
	        },
	        getLocation: function (cityId) {
	            return $http.get('locationList?cityId='+cityId).success(function (data) {
	                return data;
	            });
	        }
        }
        return factoryDefinitions;
    }
]);

app.controller('masterController', ['$scope', '$rootScope', "$sce", '$http', '$state', 'masterServices','ngTableParams','$injector', function ($scope, $rootScope, $sce, $http, $state, masterServices,ngTableParams,$injector) {
	var $validationProvider = $injector.get('$validation');  
	$scope.hideeerormsg = function() {
		$scope.message ={
				'success':0,
				'error':0
			};
	};
	$scope.showLoader = function() {
		$('.loader').show();
		// $('body').css('overflow', 'hidden');
	};

	$scope.hideLoader = function() {
		$('.loader').hide();
		// $('body').css('overflow', 'auto');
	};
	
	$scope.master =[{
		'countries':[],
		'states':[],
		'cities':[],
		'locations':[]
	}];
	$scope.message =[{
		'success':0,
		'error':0
	}];
	$scope.country = {
			countryIsactiveTrue :true
	};
	$scope.state = {
			stateIsactiveTrue :true
	};
	$scope.city = {
			cityIsactiveTrue :true
	};
	$scope.location = {
			locationIsactiveTrue:true
	};
	
	$scope.showgrid = 1;
	// load the country as default
	
	function loadCountry()
	{
		$rootScope.showLoader();
		masterServices.getCountry().then(function (response) {
			$scope.master.countries = response.data;	
			$rootScope.hideLoader();
			var data =response.data.cntryList;		
			//console.log(data.length);
		    $scope.countryTableParams = new ngTableParams({
		         page: 1,            // show first page
		         count: 10           // count per page
		     }, {
		         total: data.length, // length of data
		         getData: function($defer, params) {
		             $defer.resolve(data.slice((params.page() - 1) * params.count(), params.page() * params.count()));
		         }
		     });
	    });
	}
	loadCountry();
	// change the country 
	$scope.changeCountry = function(){
		$scope.master.states = [];
		$scope.master.cities = [];
		$scope.master.locations = [];
			
		if($scope.country_id != undefined && $scope.country_id !=''){
			$rootScope.showLoader();
			$scope.showgrid = 2;
			masterServices.getState($scope.country_id).then(function (response) {
				$scope.master.states = response.data;
				$rootScope.hideLoader();
		    });
		}else{
			$scope.country = {};
			$scope.state = {};
			$scope.city = {};
			$scope.location = {}
			$scope.showgrid = 1;
		}
	}
    // change the State 
	$scope.changeState = function(){
		$scope.master.cities = [];
		$scope.master.locations = [];
		if($scope.state_id != undefined && $scope.state_id !=''){
			$rootScope.showLoader();
			$scope.showgrid = 3;
			masterServices.getCity($scope.state_id).then(function (response) {
				$scope.master.cities = response.data;
				$rootScope.hideLoader();
		    });
		}else{
			$scope.state = {};
			$scope.city = {};
			$scope.location = {}
			$scope.showgrid = 2;
		}
	}
	// change the City 
	$scope.changeCity = function(){
		$scope.location_id = '';
		$scope.master.locations = [];
		if($scope.city_id != undefined && $scope.city_id !=''){
			$rootScope.showLoader();
			$scope.showgrid = 4;
			masterServices.getLocation($scope.city_id).then(function (response) {
				$scope.master.locations = response.data;
				$rootScope.hideLoader();
		    });
		}else{
			// reset edit scope			
			$scope.city = {};
			$scope.location = {}
			$scope.showgrid = 3;
		}
	}
	// change the Location 
	$scope.changeLocation = function(){
		if($scope.location_id != undefined && $scope.location_id !=''){
			$scope.showgrid = 4;
		}else{
			$scope.location = {}
			$scope.showgrid = 4;
		}
	}
	//edit the country
	$scope.editCountry = function(countryId,countryCode,countryName,countryDescription,countryIsactive){
		$scope.country = {};
		$scope.country.countryId = countryId;
		$scope.country.countryCode = countryCode;
		$scope.country.countryName = countryName;
		$scope.country.countryDescription = countryDescription;
		$scope.country.countryIsactive = countryIsactive;
		//console.log($scope.country.countryIsactive);
		$scope.country.countryIsactiveTrue = countryIsactive;
	}
	//edit the country
	$scope.editState = function(stateId,stateCode,stateName,stateDescription,stateIsactive){
		$scope.state = {};
		$scope.state.stateId = stateId;
		$scope.state.stateCode = stateCode;
		$scope.state.stateName = stateName;
		$scope.state.stateDescription = stateDescription;
		$scope.state.stateIsactive = stateIsactive;
		//$scope.state.stateIsactiveTrue = (stateIsactive) == 'Y'?true:false;
		$scope.state.stateIsactiveTrue= stateIsactive;
	}
	//edit the country
	$scope.editCity = function(cityId,cityCode,cityName,cityDescription,cityIsactive){
		$scope.city = {};
		$scope.city.cityId = cityId;
		$scope.city.cityCode = cityCode;
		$scope.city.cityName = cityName;
		$scope.city.cityDescription = cityDescription;
		$scope.city.cityIsactive = cityIsactive;
		//$scope.city.cityIsactiveTrue = (cityIsactive) == 'Y'?true:false;
		$scope.city.cityIsactiveTrue =cityIsactive;
	}
	//edit the location
	$scope.editLocation = function(locId,locCode,locName,locDescription,locIsactive,revCode){
		$scope.location = {};
		$scope.location.locId = locId;
		$scope.location.locCode = locCode;
		$scope.location.locName = locName;
		$scope.location.locDescription = locDescription;
		$scope.location.locIsactive = locIsactive;
		//$scope.location.locationIsactiveTrue = (locIsactive) == 'Y'?true:false;
		$scope.location.locationIsactiveTrue =locIsactive;
		$scope.location.reviewCode=revCode;
	}
	
	$scope.radioCountryActive = function(){	
		$scope.country.countryIsactiveTrue = !$scope.country.countryIsactiveTrue;	
	}
	$scope.resetCountry = function() {			
		$scope.country.countryCode = '';
		$scope.country.countryName = '';
		$scope.country.countryDescription= '';
		$scope.country.countryIsactive='';
		$scope.country.countryIsactiveTrue='';
	 };	 
	$scope.saveCountry = function(form){
		$scope.country.countryIsactive = ($scope.country.countryIsactiveTrue)?true:false;
		//console.log("countryMaster");		
		delete $scope.country.countryIsactiveTrue;
		$rootScope.showLoader();
		$http.post('saveCountry', $scope.country, { headers: { 'Content-Type': 'application/json; charset=UTF-8'} }
			).success(function(data) {
				$scope.country.countryIsactiveTrue = ($scope.country.countryIsactive== 'Y')?true:false;
				$scope.countryResponse = data;
				if(data.Status == 'Error'){
					$scope.message ={
							'success':0,
							'error':1
						};
					$rootScope.hideLoader();
				}
				if(data.Status == 'Ok'){
					$scope.message ={
						'success':1,
						'error':0
					};
					masterServices.getCountry().then(function (response) {
						$scope.master.countries = response.data;
						$rootScope.hideLoader();
				    }); 
					$scope.resetCountry();
					$validationProvider.reset(form); 
					$scope.country = {
							countryIsactiveTrue :true
					};
				}
		});	
		
		
	}
	$scope.deleteCountry = function(countryId){
		var result = confirm("Are you sure want to delete?");
		if (result==true) {
			$rootScope.showLoader();
		$http.post('deleteCountry', 'countryId='+countryId).success(function(data) {
			$scope.countryResponse = data;
			if(data.Status == 'Error'){
				$scope.message ={
						'success':0,
						'error':1
					};
				$rootScope.hideLoader();
			}
			if(data.Status == 'Ok'){
				$scope.message ={
					'success':1,
					'error':0
				};
				
				/*masterServices.getCountry().then(function (response) {
					//$scope.master.countries = response.data;		
					var data =response.data.cntryList;	
					console.log(data.length);
				    $scope.countryTableParams = new ngTableParams({
				         page: 1,            // show first page
				         count: 10           // count per page
				     }, {
				         total: data.length, // length of data
				         getData: function($defer, params) {
				             $defer.resolve(data.slice((params.page() - 1) * params.count(), params.page() * params.count()));
				         }
				     });			  
					
			    }); */
				masterServices.getCountry().then(function (response) {
					$scope.master.countries = response.data;
					$rootScope.hideLoader();
			    }); 
			}
		});
		
		}
	}
	$scope.radioStateActive = function(){	
		$scope.state.stateIsactiveTrue = !$scope.state.stateIsactiveTrue;	
	}
	$scope.resetState = function() {			
		$scope.state.stateCode = '';
		$scope.state.stateName = '';
		$scope.state.stateDescription= '';
		$scope.state.stateIsactive='';
		$scope.state.stateIsactiveTrue='';
	 };
	$scope.saveState = function(form){
		$scope.state.stateIsactive = ($scope.state.stateIsactiveTrue)?true:false;
		$scope.state.country = $scope.country_id;
		delete $scope.state.stateIsactiveTrue;
		$rootScope.showLoader();
		$http.post('saveState', $scope.state, { headers: { 'Content-Type': 'application/json; charset=UTF-8'} }
			).success(function(data) {
				$scope.state.stateIsactiveTrue = ($scope.state.stateIsactive== 'Y')?true:false;
				$scope.stateResponse = data;
				if(data.Status == 'Error'){
					$scope.message ={
							'success':0,
							'error':1
						};
					$rootScope.hideLoader();
				}
				if(data.Status == 'Ok'){
					$scope.message ={
						'success':1,
						'error':0
					};
					$rootScope.hideLoader();
					$scope.changeCountry(); 
					$scope.resetState();
					$validationProvider.reset(form); 
					$scope.state = {
							stateIsactiveTrue :true
					};
				}
				
		});
	}
	$scope.deleteState = function(stateId){
		var result = confirm("Are you sure want to delete?");
		if (result==true) {
			$rootScope.showLoader();
		$http.post('deleteStates', 'stateId='+stateId).success(function(data) {
			$scope.stateResponse = data;
			if(data.Status == 'Error'){
				$scope.message ={
						'success':0,
						'error':1
					};
				$rootScope.hideLoader();
			}
			if(data.Status == 'Ok'){
				$scope.message ={
					'success':1,
					'error':0
				};
				$scope.changeCountry();
				$rootScope.hideLoader();

			}
		});
		}
	}
	// city save/delte process
	$scope.radioCityActive = function(){	
		$scope.city.cityIsactiveTrue = !$scope.city.cityIsactiveTrue;	
	}
	$scope.resetCity = function() {			
		$scope.city.cityCode = '';
		$scope.city.cityName = '';
		$scope.city.cityDescription= '';
		$scope.city.cityIsactive='';
		$scope.city.cityIsactiveTrue='';
	 };
	$scope.saveCity = function(form){
		$scope.city.cityIsactive = ($scope.city.cityIsactiveTrue)?true:false;
		$scope.city.state = $scope.state_id;
		delete $scope.city.cityIsactiveTrue;
		$rootScope.showLoader();
		$http.post('saveCity', $scope.city, { headers: { 'Content-Type': 'application/json; charset=UTF-8'} }
			).success(function(data) {
				$scope.city.cityIsactiveTrue = ($scope.city.cityIsactive== 'Y')?true:false;
				$scope.cityResponse = data;
				if(data.Status == 'Error'){
					$scope.message ={
							'success':0,
							'error':1
						};
					$rootScope.hideLoader();
				}
				if(data.Status == 'Ok'){
					$scope.message ={
						'success':1,
						'error':0
					};
					$rootScope.hideLoader();
					$scope.changeState();
					$scope.resetCity();
					$validationProvider.reset(form); 
					$scope.city = {
							cityIsactiveTrue :true
					};
				}
				
		});
	}
	$scope.deleteCity = function(cityId){
		var result = confirm("Are you sure want to delete?");
		if (result==true) {
			$rootScope.showLoader();
		$http.post('deleteCity', 'cityId='+cityId).success(function(data) {
			$scope.cityResponse = data;
			if(data.Status == 'Error'){
				$scope.message ={
						'success':0,
						'error':1
					};
				$rootScope.hideLoader();
			}
			if(data.Status == 'Ok'){
				$scope.message ={
					'success':1,
					'error':0
				};
				$scope.changeState();
				$rootScope.hideLoader();
			}
		});
		}
	}
	// location save/delte process
	$scope.radioLocationActive = function(){	
		$scope.location.locationIsactiveTrue = !$scope.location.locationIsactiveTrue;	
	}
	$scope.resetLocation = function() {			
		$scope.location.locCode = '';
		$scope.location.locName = '';
		$scope.location.locDescription= '';
		$scope.location.reviewCode='';
		$scope.location.locIsactive='';		
		$scope.location.locationIsactiveTrue='';
	 };
	$scope.saveLocation = function(form){
		$scope.location.locIsactive = ($scope.location.locationIsactiveTrue)?true:false;
		$scope.location.city = $scope.city_id;
		delete $scope.location.locationIsactiveTrue;
		$rootScope.showLoader();
		$http.post('saveLocation', $scope.location, { headers: { 'Content-Type': 'application/json; charset=UTF-8'} }
			).success(function(data) {
				$scope.location.locationIsactiveTrue = ($scope.location.locIsactive== 'Y')?true:false;
				$scope.locationResponse = data;
				if(data.Status == 'Error'){
					$scope.message ={
							'success':0,
							'error':1
						};
					$rootScope.hideLoader();
				}
				if(data.Status == 'Ok'){
					$scope.message ={
						'success':1,
						'error':0
					};
					$rootScope.hideLoader();
					$scope.changeCity(); 
					$scope.resetLocation();
					$validationProvider.reset(form);
					$scope.location = {
							locationIsactiveTrue:true
					};
				}
				
		});
	}
	$scope.deleteLocation = function(locationId){
		var result = confirm("Are you sure want to delete?");
		if (result==true) {
			$rootScope.showLoader();
		$http.post('deleteLocation', 'locationId='+locationId).success(function(data) {
			$scope.locationResponse = data;
			if(data.Status == 'Error'){
				$scope.message ={
						'success':0,
						'error':1
					};
				$rootScope.hideLoader();
			}
			if(data.Status == 'Ok'){
				$scope.message ={
					'success':1,
					'error':0
				};
				$rootScope.hideLoader();
				$scope.changeCity();
			}
		});
		}
	}
}]);