

(function() {
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
	var app = angular.module('content',['generalContent','angularFileUpload','ngAnimate','ngCookies','ngDraggable','ngDialog','ngSanitize','ui.select','user', 'auth','designation','department','manageUsers','enquiry','manageTripDetail','ngImgCrop','newsletter']);

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
	
	
	app.config(['$httpProvider', '$urlRouterProvider', function ($httpProvider, $urlRouterProvider) {    
		$httpProvider.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded; charset=UTF-8';
		$httpProvider.interceptors.push('sessionInterceptor');	
	}]);
	
	app.run(['$rootScope', '$location', '$cookies', function ($rootScope,$location,$cookies) {    
		  if($cookies.userDetails){
			  	
			  	$rootScope.userDetails = $cookies;
			  
			  	//Convert string to object for ACL data
				var userModulesACL = $cookies.userModuleNames;
				userModulesACL = userModulesACL.split('"').join('');
				userModulesACL = userModulesACL.split('+').join(' ');
				userModulesACL = userModulesACL.split(",");
				
				var userSubModuleNamesACL = $cookies.userSubModuleNames;
				userSubModuleNamesACL = userSubModuleNamesACL.split('"').join('');
				userSubModuleNamesACL = userSubModuleNamesACL.split('+').join(' ');
				userSubModuleNamesACL = userSubModuleNamesACL.split(",");
				
				var userModules = [];
				userModules = userModules.concat(userModulesACL, userSubModuleNamesACL);
				
				//Assign to rootscope for checking ACL in template level
				$rootScope.acl = {"userModules":userModules}; 
		}else{
			  $location.path('/login');
		  }
	}]);

	//ACL
	app.directive('aclModule', function($rootScope) {
	 return {
	        restrict: 'A',
	        scope: false,
	        link: function(scope, element, attributes) {
	         var modAvail = false;
	         
	         if($rootScope.acl.userModules.indexOf(attributes.resourceId) != -1){
	        	 modAvail = true; 
	         }
	        
	         
	         if(!modAvail) {
	              element.remove();
	              return;
	         }
	         
	        }
	 }
	});
	//<--------------------- filter ---------------------------->
	app.filter('propsFilter', function() {
		  return function(items, props) {
		    var out = [];

		    if (angular.isArray(items)) {
		      items.forEach(function(item) {
		        var itemMatches = false;

		        var keys = Object.keys(props);
		        for (var i = 0; i < keys.length; i++) {
		          var prop = keys[i];
		          var text = props[prop].toLowerCase();
		          if (item[prop].toString().toLowerCase().indexOf(text) !== -1) {
		            itemMatches = true;
		            break;
		          }
		        }

		        if (itemMatches) {
		          out.push(item);
		        }
		      });
		    } else {
		      // Let the output be the input untouched
		      out = items;
		    }

		    return out;
		  };
		});
	
	app.filter('true_false', function() {
	    return function(text, length, end) {
	        if (text) {
	            return 'Y';
	        }
	        return 'N';
	    }
	});
	
	
	app.controller('countryCtrl', function ($scope,$http) {
		
		$http.get("getAllCountries").success(function(data) {	
			 $scope.countries = data[0];
	    }).error(function(data,status) {	
			 
	    });
		
	});

	//<--------------------- factory ---------------------------->
	
		app.factory('country', function(){
		     
		    var fac = {};
		     
		    fac.setLoc = function(cid,sid,ctid,loc) {
	           
	        }
		     
		    return fac;
		 
		});
		
	
	//<--------------------    Navigation controller   ------------------------->
	
	app.controller('navigController', function ($scope,$http,dataFactory) {	
	});
	
	//<--------------------    Country controller   ------------------------->
	
	app.controller('countryCtrl', function ($scope,$http) {
		
		$http.get("getAllCountries").success(function(data) {	
			 $scope.countries = data[0];
	    }).error(function(data,status) {	
			 
	    });
		
	});
	
	//<--------------------    Department controller   ------------------------->
	
	app.controller('deptController', function ($scope,$http,dataFactory) {		

		$scope.getDept = function(){
			$http.get("deptList").success(function(data) {	
				 $scope.departments = data[0];
		    });	
		}
		
		  $scope.getCode = function(urlLink) {	  
			  $scope.deptCheck=!$scope.deptCheck;
			  var response = $http.get(""+urlLink);
				response.success(function(data) {	
					 $scope.deptCode = data;
			    });	
				response.error(function(data,status) {	
						
				});
		    }
		  $scope.save = function() { 
			  var dept ={};
			  if($scope.deptId != 0 || $scope.deptId != '' || $scope.deptId != null) dept.deptId = $scope.deptId;
			  dept.deptCode = $scope.deptCode;
			  dept.deptName = $scope.deptName;
			  dept.deptIsactive = $scope.isDept ? "N" : "Y";
			  
			  var myData = 'deptStr='+JSON.stringify(dept);
			  var response = $http.post("saveDept", myData);
				response.success(function(data) {
					 $scope.clear();
					 $scope.getDept();
			    });	
				response.error(function(data,status) {	
						
				});
		  }
		  
        $scope.remove = function(id) {		  
        	  var myData = 'deptStr='+id;
			  var response = $http.post("removeDept", myData);
				response.success(function(data) {
					 $scope.getDept();
			    });	
				response.error(function(data,status) {	
					
				});
		  }
        
        $scope.getById = function(id) {		  
      	  var myData = 'deptStr='+id;
			  $http.post("getDeptById", myData).success(function(data) {
					 $scope.update(data.deptId,data.deptCode,data.deptName);
			    }).error(function(data,status) {	
					
				});
		  }
        
        $scope.update = function(id,code,name) { 
			  $scope.deptCheck=true;
			  $scope.deptCode = code;
		      $scope.deptName = name;
		      $scope.deptId = id;
		  }
        
        $scope.clear = function(){
        	  $scope.deptCheck=!$scope.deptCheck;
			  $scope.deptCode = "";
		      $scope.deptName = "";
		      $scope.deptId = "";
        }
        
        dataFactory.get("deptList").then(function(data) {
            $scope.items = data[0];
            $scope.departments = data[0];
          });
          $scope.name = ''; // This will hold the selected item
          $scope.onItemSelected = function() { // this gets executed when an item is selected
        	$scope.getById($scope.obj.deptId);
            //console.log('selected=' + $scope.name);
          };
        
	});
	
	
	//<--------------------    Category controller   ------------------------->
	
   app.controller('categoryController', function ($scope,$http,dataFactory, $upload, $timeout,ngDialog,$rootScope) {
	 //Filter
		$scope.categorySearch = function (obj) {
			 var re = new RegExp($scope.pc_search, 'i');
		        return !$scope.pc_search || re.test(obj.catTitle.toString());
	    };
	    $scope.isProcessingfalse = function() {
			 $timeout(function() {
				$scope.isProcessing = false;
				}, 1000);
		};
	 
	   $scope.message =[{
			'success':0,
			'error':0
		}];
	   $scope.myFiles = {};
	   $scope.category = {};
	   //get Category List
	   $scope.getCategory = function(){
		   dataFactory.get("categoryList").then(function(data) {
			   $scope.categories = data[0];
		   });
	   }
	   $scope.getCategory();
	   $scope.hideImageSaveBtn = 0;
	   // add/edit package category
	   var copyCategory = {};
	   $scope.addPackageCategory = function(category){
		   $scope.message ={
					'success':0,
					'error':0
				};
		   $rootScope.showLoader();
		    $scope.addEditcategory = {};
		    $scope.addEditcategory.isCategory = false;
			if(category != undefined && category !=''){
				copyCategory = angular.copy(category);
				$scope.addEditcategory = copyCategory;
				$scope.addEditcategory.isCategory = copyCategory.catIsactive;
			}
			$scope.isProcessing = true;
			ngDialog.open({
				 template: 'resources/views/product/addPackageCategory.html', 
				 className: 'ngdialog-theme-default',
			   	 scope: $scope
			});
			$rootScope.hideLoader();
			$scope.isProcessingfalse();
		}
	   //Save package categoty
	   $scope.savePackageCategory = function(){
		   $scope.addEditcategory.catIsactive = $scope.addEditcategory.isCategory;
		 //delete $scope.addEditcategory.$$hashKey;
		   delete $scope.addEditcategory.isCategory;
		   var myData = 'catStr='+JSON.stringify($scope.addEditcategory);
		   $http.post("saveCategory", myData).success(function(data) {
			   $scope.packageCategoryResponse = data;
			   if(data.Status == 'Error'){
				   $scope.message ={
						'success':1,
						'error':0
					};
			   }
			   if(data.Status == 'Error'){
				   $scope.message ={
						'success':0,
						'error':1
					};
			   }
			   $scope.getCategory();
			   ngDialog.close();
		    }).error(function(data,status) {	
				$scope.message ={
						'success':0,
						'error':1
				};
				$scope.packageCategoryResponse.message = 'Package category not saved';
			});
		   
	   }
	   //Delete package category
	  $scope.deletePackageCategory = function(categoryId){
		   var result = confirm("Are you sure want to delete?");
			if (result==true) {
				$rootScope.showLoader();
				$http.post("deleteCategory", 'catId='+categoryId).success(function(data) {
					$scope.packageCategoryResponse = data;
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
					}
					$rootScope.hideLoader();
				});
				$scope.getCategory();
			}
	   }
	   // close the dailog box
	   	$scope.cancel = function(){
			ngDialog.close();
	   	}
	   	$scope.addPackageCategoryImage = function(category){
	   	//	addPackageCategory
	   		$scope.imgfile = {};
	   		$scope.hideImageSaveBtn = 0;
	   		$scope.packageCategoryImageResponse = {};
	   		if(category != undefined && category !=''){
				$scope.category = category;
				$scope.getAllImg($scope.category.catId);
	   		}
	   		ngDialog.open({
				 template: 'resources/views/product/addPackageCategoryImage.html', 
				 className: 'ngdialog-theme-default',
			   	 scope: $scope
			});
	   		$rootScope.hideLoader();
	   	}
	   	$scope.addPackageCategoryIconImage = function(category){
	   		$rootScope.showLoader();
	   		$scope.imgEditicon= false;
	   		if(category.iconUrl != null)
	   		$scope.imgEditicon= true;
	   		
	   		$scope.hideImageSaveBtn = 0;   		
	   		if(category != undefined && category !=''){
				$scope.category = category;
				$scope.imgfile = $rootScope.imagePath+$scope.category.iconUrl;
	   		}
	   		ngDialog.open({
				 template: 'resources/views/product/addPackageCategoryIconImage.html', 
				 className: 'ngdialog-theme-default',
			   	 scope: $scope
			});
	   		$rootScope.hideLoader();
	   	}
	   	$scope.mySplit = function(string, nb) {	   		
	   		if(string != undefined && string !=''){	   			
		   	    $scope.array = string.split(',');
		   	    return $scope.result = $scope.array[nb];
	   		}else{
	   			return '/no-image.png';
	   		}
	   	}
	   	$scope.fileSelected = function($files,$event){
			$scope.myFiles = $files;
		}
	   	//upload file
	   	$scope.myFinalIconFiles = {};
	   	$scope.$watch('myFiles', function() {
		    for (var i = 0; i < $scope.myFiles.length; i++) {
		      $scope.hideImageSaveBtn = 1; 	
		      var file = $scope.myFiles[i];
		      $scope.generateThumb(file);		
		      $scope.myFinalIconFiles = file;
		      /*$scope.upload = $upload.upload({
		        url: "uploadImg",
		        method: 'POST',
		        data: {myObj: $scope.myModelObj},
		        file: file 
		        }).progress(function(evt) {
		        //console.log('progress: ' + parseInt(100.0 * evt.loaded / evt.total) + '% file :'+ evt.config.file.name);
		      }).success(function(data, status, headers, config) {
		        //console.log('file ' + config.file.name + 'is uploaded successfully. Response: ' + data);
		      }).error(function(data, status, headers, config) {
		        //console.log('file ' + config.file.name + 'is uploaded error. Response: ' + data);
		      });*/
		      
		  }
		});
	   	$scope.generateThumb = function(file) {
		  var imgfile;
		  console.log(JSON.stringfy(file));
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
	   	$scope.saveImgIcon = function(categoryId){
         	/*var myData = 'imgStr=category&imgSubStr='+categoryId+'/icon';*/
         	/*$http.post("saveImg", myData).success(function(data) {
         		$scope.hideImageSaveBtn = 0;
         		$scope.getCategory();
         		ngDialog.close();
         	}).error(function(data,status) {
         	});*/
         	
         	$scope.upload = $upload.upload({
		        url: "uploadImage",
		        method: 'POST',
		        data: {module: 'category',subModule:categoryId+'/icon',objectId:categoryId},
		        file: 	$scope.myFinalIconFiles 
		        }).progress(function(evt) {
		        }).success(function(data, status, headers, config) {
		    	    $scope.hideImageSaveBtn = 0;
	         	    $scope.getCategory();
	         		ngDialog.close();
		      }).error(function(data, status, headers, config) {
		      });
         	
         }
	   	$scope.saveImg = function(categoryId){
        	$scope.imgfile='';
        	$scope.packageCategoryImageResponse = {};
        	$scope.message = {};
        	/*myData = 'imgStr=category&imgSubStr='+categoryId;*/
        	/*$http.post("saveImg", myData).success(function(data) {
        		$scope.message ={
					'success':1,
					'error':0
				};
        		$scope.hideImageSaveBtn = 0;
        		$scope.packageCategoryImageResponse.message = 'Image saved successfully.';
        		$scope.getAllImg($scope.category.catId);
        	}).error(function(data,status) {
        	});*/
        	
        	$scope.upload = $upload.upload({
		        url: "uploadImage",
		        method: 'POST',
		        data: {module: 'category',subModule:categoryId,objectId:categoryId},
		        file: 	$scope.myFinalIconFiles 
		        }).progress(function(evt) {
		        }).success(function(data, status, headers, config) {
		        	$scope.message ={
							'success':1,
							'error':0
					};
		        	$scope.hideImageSaveBtn = 0;
		        	$scope.packageCategoryImageResponse.message = 'Image saved successfully.';
		        	$scope.getAllImg($scope.category.catId);
		      }).error(function(data, status, headers, config) {
		      });
        	
        }
	   	//get ALl image by category ID
	   	$scope.getAllImg = function(myData){
        	//console.log(myData);
        	$http.post("getAllImg", 'imgStr=category&imgSubStr='+myData).success(function(data) {
        		$scope.imgList = data[0];
        	}).error(function(data,status) {
        		
        	});	
        }
	   	//delete image
	   	$scope.removeImg = function(myData){
	   		$scope.packageCategoryImageResponse = {};
        	var img = {};
        	img.imgName = myData+"";
        	img.imgModule = $scope.catTit;
        	$scope.imgname = img.imgName; 
        	$http.post("removeImg", 'imgStr=category&imgSubStr='+$scope.category.catId+'&imgName='+$scope.imgname).success(function(data) {
        		$scope.message ={
					'success':1,
					'error':0
				};
        		$scope.packageCategoryImageResponse.message = 'Image was deleted successfully.';
        		$scope.getAllImg(data);
        	}).error(function(data,status) {
        		
        	});	
        }
	   	//Drag and drop
	   	$scope.onDropComplete = function (index, obj, evt) {
            var otherObj = $scope.imgList[index];
            var otherIndex = $scope.imgList.indexOf(obj);
            $scope.imgList[index] = obj;
            $scope.imgList[otherIndex] = otherObj;
            var myData = 'imgStr=category&imgSubStr='+$scope.category.catId+','+(index+1)+','+(otherIndex+1);
            $http.post("reorderImg",myData).success(function(data) {
        		$scope.getAllImg(data);
        	}).error(function(data,status) {
        		
        	});
        }
	});

	
	//<--------------------    Package controller   ------------------------->
	
  app.controller('packageController', function ($scope,$http,dataFactory, $upload, $timeout,ngDialog,$injector,iconconfig,$filter,$rootScope,$timeout) {
	  $scope.isProcessingfalse = function() {
			 $timeout(function() {
				$scope.isProcessing = false;
				}, 1000);
		};
		$scope.hideeerormsg = function() {
			$scope.message ={
					'success':0,
					'error':0
				};
		};
  var $validationProvider = $injector.get('$validation');  
	  $scope.packmod = 'Package Detail';
	  $scope.package_name_error=0;
	  $scope.county_mutli_error=0;  
	  $scope.appIcons=iconconfig;
        $scope.state_mutli_error=0;
    	$scope.city_mutli_error=0;  
    	$scope.locations_mutli_error=0; 
      	$scope.package_category_error = 0;
      	$scope.package_vendorname_error = 0;
      	$scope.package_day_error=0;
      	$scope.package_night_error=0;
      	$scope.package_overview_error=0;
      	$scope.package_packagetype_error = 0;
      	$scope.packagefeaturesCount = 0;
	  $scope.packmodule = ['Package Detail','Activity','Price Detail','File Upload','Attraction','Package Hotel','Conditions'];

	  $scope.packtype = [{"id":"1","name":"Domestic"},{"id":"2","name":"International"}];
	  $scope.packspecial = [{"id":"","name":""},{"id":"1","name":"Hot Deals"},{"id":"2","name":"Trending"},{"id":"3","name":"Best Sellers"},{"id":"4","name":"Summer Specials"},{"id":"5","name":"Get Aways"}];
	 //$scope.packorder = [{"id":"1","name":"1"},{"id":"2","name":"2"},{"id":"3","name":"3"},{"id":"4","name":"4"},{"id":"5","name":"5"},{"id":"6","name":"6"},{"id":"7","name":"7"},{"id":"8","name":"8"},{"id":"9","name":"9"}];
	  $scope.packorder = [{"id":"2","name":"2"},{"id":"4","name":"4"},{"id":"5","name":"5"},{"id":"9","name":"9"}];
	  $scope.filemod = [{"id":"0","name":"select.."},{"id":"1","name":"Package"},{"id":"2","name":"Attraction"},{"id":"3","name":"Featured"},{"id":"4","name":"Activity"}];
	  $scope.filemod_package = [{"id":"1","name":"Package Detail"},{"id":"2","name":"Package Tile"}];
      
	  $scope.multi = {};
      
      $scope.countries = [];
      $scope.States = [];
      $scope.cities = [];
      $scope.places = [];
      $scope.categories = [];
      $scope.message =[{
	  		'success':0,
	  		'error':0
	  	}];
      
      
      $scope.changeSpecial=function()
      { 
    	  if($scope.selpackSpecial != undefined && $scope.selpackSpecial !=''){   		  	
    		  	switch ($scope.selpackSpecial) 
    		  	{
	    		    case 'Hot Deals'      :    $scope.packorder = [{"id":"1","name":"1"}];
	    		                               break;
	    		    case 'Trending'       :    $scope.packorder = [{"id":"3","name":"3"}];
	                				           break; 
	    		    case 'Best Sellers'   :    $scope.packorder = [{"id":"6","name":"6"}];
	                            	           break; 
	    		    case 'Summer Specials':    $scope.packorder = [{"id":"7","name":"7"}];
     	           							   break;	                				
	    		    case 'Get Aways'      :    $scope.packorder = [{"id":"8","name":"8"}];
						   					   break;	                				  
	    		    default	    		  :    "";      
    		   }
    	  }
    	  else
    			{
    		  		$scope.packorder = [{"id":"2","name":"2"},{"id":"4","name":"4"},{"id":"5","name":"5"},{"id":"9","name":"9"}];
    			}
      }
      $rootScope.showLoader();
      
    //load category for token input
      $scope.getCategories = function(){
          $http.get("categoryList").success(function(data) {
	         $scope.categories  = data[0];
	         $scope.items = data[0];
           });
      }
         $scope.getCategories();
      
      //populate country for multiple select
	       $http.get("getAllCountries").success(function(data) {	
				 $scope.countries = data.cntryList;
				 $rootScope.hideLoader();
		    });
	       $scope.pakageAdd = function(pack){	
	    	   $rootScope.showLoader();
	    	   $scope.updatePack(pack);
	    	   ngDialog.open({
		   			template: 'resources/views/product/addPackageDetails.html', 
		    		    className: 'ngdialog-theme-default',
		    		    preCloseCallback: function(value) {
		    		    	$scope.getAllPack();
		    		        return true;
		    		    },
		    			scope: $scope
		   		});
	    	   $rootScope.hideLoader();
	   	}
	       
	       $scope.pakageAddButton = function(){	   
	    	   $rootScope.showLoader();
	    	   $scope.isProcessing = true;
	    	   ngDialog.open({
		   			template: 'resources/views/product/addPackageDetails.html', 
		    		    className: 'ngdialog-theme-default',
		    		    preCloseCallback: function(value) {
		    		    	$scope.getAllPack();
		    		        return true;
		    		    }
		    	});
	    	   $rootScope.hideLoader();
	    	   $scope.isProcessingfalse();
	   	}
	       $scope.$watch('pack.catName', function() {
	    	    // $scope.multiValid();
	    	});
	       $scope.$watch('packCode', function() {
	    	    // $scope.multiValid();
	    	});
	       $scope.$watch('packCode', function() {
	    	    // $scope.multiValid();
	    	});
	       
	$scope.multiValid = function(){
		    $scope.county_mutli_error=0;  
	      	$scope.state_mutli_error=0;
	      	$scope.city_mutli_error=0;  
	      	$scope.locations_mutli_error=0;  
	      	$scope.package_packagetype_error = 0;
	      	$scope.package_packagetype_error = 0;
	      	$scope.package_category_error = 0;
	      	$scope.package_vendorname_error = 0;
	      	$scope.pack ={};
		   $scope.errorcount=0;
	      		/*if($scope.multi.country ==''){
	      			$scope.county_mutli_error = 1;
	      			$scope.form_sumbit = 0;
	      			$scope.errorcount+=1;
	      		}
	      	    if($scope.multi.state ==''){
	      			$scope.state_mutli_error = 1;
	      			$scope.form_sumbit = 0;
	      			$scope.errorcount+=1;
	      		}if($scope.multi.city ==''){
	      			$scope.city_mutli_error = 1;
	      			$scope.form_sumbit = 0;
	      			$scope.errorcount+=1;
	      		}if($scope.multi.locations ==''){
	      			$scope.locations_mutli_error = 1;
	      			$scope.form_sumbit = 0;
	      			$scope.errorcount+=1;
	      		}*/
	      		if($scope.pack.catName == 'undefined' || $scope.pack.catName ==''){
	      			$scope.package_category_error = 1;
	      			$scope.form_sumbit = 0;
	      			$scope.errorcount+=1;
	      		}if($scope.pack.vendName == 'undefined' || $scope.pack.vendName == ''){
	      			$scope.package_vendorname_error = 1;
	      			$scope.form_sumbit = 0;
	      			$scope.errorcount+=1;
	      		}if($scope.selpacktype == 0){
	      			$scope.package_packagetype_error = 1;
	      			$scope.form_sumbit = 0;
	      			$scope.errorcount+=1;
	      		}
	      		
	      		if($scope.errorcount > 0)
	      			{
	      			return false;
	      			}
	      		else
	      			{
	      			return true;
	      			}
	   }    
	//Filter
	$scope.package_search = function (obj) {
		 var re = new RegExp($scope.p_search, 'i');
	        return !$scope.p_search || re.test(obj.pkCode) || re.test(obj.pkName.toString());
    };
	$scope.dayError=0;
    $scope.checkDayNight=function()
    {
    	
    	if($scope.packDays!=undefined)
    	{ 
    		$scope.package_day_error=0;
    	}
    	else
    	{
    		$scope.package_day_error=1;
    		
    	}
    	if($scope.packNights!=undefined)
    	{ 
    		$scope.package_night_error=0;
    	}
    	else
    	{
    		$scope.package_night_error=1;
    		
    	}
    	if($scope.packDays!=undefined && $scope.packNights!=undefined)
        {
    		if($scope.packDays<$scope.packNights)
    		{	
    			$scope.dayError=1;
    		}
    		else
    		{
    			$scope.dayError=0;
    		}
    	}
    	
    }    
    $scope.validError=0;
	$scope.getpackageName=function()
	{
	    	   if($scope.packName=="")
	    	   {
	    		   $scope.package_name_error=1;
	    		   $scope.nameError='Enter the package name';
	    	   }
	    	   else
	    	   {
	    		   var packageNameCheck=new RegExp(/^[ A-Za-z0-9_@./#()&+-]*$/);	    		   
	    		   packageNameValid=packageNameCheck.exec($scope.packName);
	    		   if(packageNameValid==null)
	    		   {
	    			   $scope.package_name_error=1;
	    			   $scope.nameError='Enter valid characters only';
	    			   $scope.validError=1;
	    		   }	
	    		   else
	    		   {
	    			   $scope.package_name_error=0;   
	    			   $scope.validError=0;
	    		   }
	    		   
	    	   }
	    	   
	  }
	$scope.getpackageDesc=function()
	{
	    	   if($scope.packDesc=="")
	    	   {
	    		   $scope.package_overview_error=1;	    		
	    	   }
	    	   else
	    	   {
	    		   $scope.package_overview_error=0;   
	    		 
	    	   }
	    	   
   }
	  $scope.savePack = function(){			
      	var pack = {};
      	$scope.form_sumbit = 1;
      	if(pack.pkId!="" || !pack.pkId===undefined ) pack.pkId = $scope.pkId;
      	pack.pkCode = $scope.packCode;
      	pack.pkName = $scope.packName;
      	pack.pkDays = $scope.packDays;
      	pack.pkNights = $scope.packNights;
      	pack.pkType = $scope.selpacktype;
      	pack.pkSpecial = $scope.selpackSpecial;
      	pack.pkOrder = $scope.selpackOrder;
      	pack.pkDescripton = $scope.packDesc;
      	pack.pkFlight = $scope.planecheck ? true : false;
      	pack.pkHotel = $scope.hotelchck ? true : false;
      	pack.pkFood = $scope.foodcheck ? true : false;
      	pack.pkTrain = $scope.traincheck ? true : false;
      	pack.pkBus = $scope.buscheck ? true : false;
      	pack.pkFerry = $scope.ferrycheck ? true : false;
      	pack.pkVisaFeeInclusion = $scope.visacheck ? true : false;
      	pack.pkTransfer = $scope.transfercheck ? true : false;
      	pack.pkGuide = $scope.guidecheck ? true : false;
      	pack.pkJungle = $scope.zoocheck ? true : false;
      	pack.pkShows = $scope.showscheck ? true : false;
      	pack.pkSports = $scope.gamescheck ? true : false;
      	pack.pkCampfire = $scope.campcheck ? true : false;
      	pack.pkSeaActivity = $scope.seacheck ? true : false;
      	pack.pkPickUp = $scope.aircheck ? true : false;
      	var vend = {};     	
      	var cat = {}; 
      	
      		$scope.errorcount = 0;
      		if($scope.packName ==undefined || $scope.packName =='')
          	{	
          		$scope.package_name_error=1;
          		$scope.form_sumbit = 0;
      			$scope.errorcount+=1;
          	}
      		if($scope.multi.category ==''){
      			$scope.category_mutli_error = 1;
      			$scope.form_sumbit = 0;
      			$scope.errorcount+=1;
      		}
      	    if($scope.multi.country ==''){
      			$scope.county_mutli_error = 1;
      			$scope.form_sumbit = 0;
      			$scope.errorcount+=1;
      		}
      	    if($scope.multi.state ==''){
      			$scope.state_mutli_error = 1;
      			$scope.form_sumbit = 0;
      			$scope.errorcount+=1;
      		}if($scope.multi.city ==''){
      			$scope.city_mutli_error = 1;
      			$scope.form_sumbit = 0;
      			$scope.errorcount+=1;
      		}if($scope.multi.locations ==''){
      			$scope.locations_mutli_error = 1;
      			$scope.form_sumbit = 0;
      			$scope.errorcount+=1;
      		}if($scope.vendName == undefined || $scope.vendName == ''){
      			$scope.package_vendorname_error = 1;
      			$scope.form_sumbit = 0;
      			$scope.errorcount+=1;
      		}
      		if($scope.packDays ==undefined  || $scope.packDays =='')
          	{	
          		$scope.package_day_error=1;
          		$scope.form_sumbit = 0;
      			$scope.errorcount+=1;
          	}
      		if($scope.packNights ==undefined  || $scope.packNights =='')
          	{	
          		$scope.package_night_error=1;
          		$scope.form_sumbit = 0;
      			$scope.errorcount+=1;
          	}
      		if($scope.packDesc ==undefined  || $scope.packDesc =='')
          	{	
          		$scope.package_overview_error=1;
          		$scope.form_sumbit = 0;
      			$scope.errorcount+=1;
          	}      		
      		if($scope.errorcount > 0 || $scope.validError==1)
      		{
      			return false;
      		}
      		else
      			{
      			
      			var fpk = $scope.isfeatured ? 'Y' : 'N';
      			vend.viSeqId = $scope.vendObj.viSeqId;
      			var myData = "packStr="+JSON.stringify(pack)+"&vendStr="+JSON.stringify(vend)+"&fpkStr="+fpk+"&catArr="+JSON.stringify($scope.multi.category)+"&countryArr="+JSON.stringify($scope.multi.country)+"&stateArr="+encodeURIComponent(JSON.stringify($scope.multi.state))+"&cityArr="+JSON.stringify($scope.multi.city)+"&locArr="+JSON.stringify($scope.multi.locations);
      	      	$http.post("savePack", myData).success(function(data) {
      	      	var pkIdValue=$scope.pkId;
      				 $scope.clear();
      				 $scope.getAllPack();
      				 $scope.packageAddResponse = data;
      					if(data.Status == 'Error'){
      						$scope.message ={
      								'success':0,
      								'error':1
      							};
      					}
      					if(data.Status == 'Ok'){
      						$scope.message ={
      							'success':1,
      							'error':0
      						};
      					} 
      					 //$scope.tab=1;
      					 //$scope.setPackmod('1','Activity',pack.pkId);
      					ngDialog.close();
      		    }).error(function(data,status) {	
      		    	$scope.message ={
      						'success':0,
      						'error':1
      					};
      		    	$scope.packageResponse.message ='Package was not saved.Please try again';
      			});
      			return true;
      	}
      
      }
	  $scope.cancel = function(){
		  ngDialog.close();
	  }
	  
	  $scope.clear = function(){
		  $scope.pkId = "";
		  $scope.pkName = "";
		  $scope.packCode = "";
	      $scope.packName = "";
	      $scope.packCategory = "";
	      $scope.packVend = "";
	      $scope.packDays = "";
	      $scope.packNights = "";
	      $scope.packDesc = "";
	      $scope.vendName = "";
	      $scope.selpacktype = 0;
	      $scope.multi.category = {};
	      $scope.multi.country = {};
	      $scope.multi.state = {};
	      $scope.multi.city = {};
	      $scope.multi.locations = {};
	      $scope.planecheck = false;
	      $scope.hotelchck = false;
	      $scope.foodcheck = false;
	      $scope.traincheck = false;
	      $scope.buscheck = false;
	      $scope.ferrycheck = false;
	      $scope.visacheck = false;
	      $scope.transfercheck = false;
	      $scope.guidecheck = false;
	      $scope.zoocheck = false;
	      $scope.showscheck = false;
	      $scope.gamescheck = false;
	      $scope.campcheck = false;
	      $scope.seacheck = false;
	      $scope.aircheck = false;	      
	      $scope.isfeatured = false;
	      $scope.getCode();
	  }
	  
	  $scope.getPack = function(pack){
		  $scope.updatePack(pack);
	  }
	  
	  $scope.updatePack = function(pack){
		  $scope.pkName = pack.pkName;
		  $scope.pkId = pack.pkId;
		  $scope.packCode = pack.pkCode;
	      $scope.packName = pack.pkName;
	      $scope.vendObj = pack.vendorInformation;
	      $scope.vendName = pack.vendorInformation.viVendorName;
	      $scope.packDays = pack.pkDays;
	      $scope.packNights = pack.pkNights;
	      $scope.selpacktype = pack.pkType;
	      $scope.pkType = pack.pkType;
	      $scope.selpackSpecial = pack.pkSpecial;
	      $scope.pkOrder = pack.pkOrder;
	      $scope.packDesc = pack.pkDescripton;
	      //$scope.getPackLoc(pack.pkId);
	      $scope.getFeaturedPack(pack.pkId);
	      $scope.planecheck = pack.pkFlight!=false;
	      $scope.hotelchck = pack.pkHotel!=false;
	      $scope.foodcheck = pack.pkFood!=false;
	      $scope.traincheck = pack.pkTrain!=false;
	      $scope.buscheck = pack.pkBus!=false;
	      $scope.ferrycheck = pack.pkFerry!=false;
	      $scope.visacheck = pack.pkVisaFeeInclusion!=false;
	      $scope.transfercheck = pack.pkTransfer!=false;
	      $scope.guidecheck = pack.pkGuide!=false;
	      $scope.zoocheck = pack.pkJungle!=false;
	      $scope.showscheck = pack.pkShows!=false;
	      $scope.gamescheck = pack.pkSports!=false;
	      $scope.campcheck = pack.pkCampfire!=false;
	      $scope.seacheck = pack.pkSeaActivity!=false;
	      $scope.aircheck = pack.pkPickUp!=false;
	  }
	  
	  $scope.removePack = function(id){
		  $rootScope.showLoader();
		  var myData = 'packStr='+id;
		  $http.post("removePack", myData).success(function(data) {
			     $scope.clear();
				 $scope.getAllPack();
				 $scope.packageResponse = data;
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
					}
		    }).error(function(data,status) {
		    	$scope.message ={
						'success':0,
						'error':1
					};
		    	$scope.packageResponse.message='Package was not deleted';
			});
	  }
	  
	  $scope.getAllPack = function(){
		  dataFactory.get("getAllPack").then(function(data) {
			  $scope.packages = data.packList;
	          $scope.items = data.packList;
	        });
	  }
	  
	  $scope.getCat = function(){
		  dataFactory.get("categoryList").then(function(data) {
	          $scope.items = data[0];
	        });
	  }
	  
	  $scope.getVend = function(){
		  dataFactory.get("getVendor").then(function(data) {
	          $scope.items = data.message;
	        });
	  }
	  
	  $scope.getFeaturedPack = function(pkId){
		  //$http.post("checkIsPackFeatured", 'packStr='+pkId).success(function(data) {
			  //console.log(JSON.stringify(data));
			  //$scope.isfeatured = data=="Y" ;
		  //});
	  }
	  
	  $scope.getPackLoc = function(pkId){
		  if(pkId != undefined && pkId !=''){
			  $http.post("getPackLoc", 'packStr='+pkId).success(function(data) {				  
			      $scope.countries = data.countries;
			      $scope.States = data.states;
			      $scope.cities = data.cities;
			      $scope.places = data.locations;
			      $scope.categories = data.categories;
			      $scope.multi.country = $scope.countries;
			      $scope.multi.state = $scope.States;
			      $scope.multi.city = $scope.cities;
			      $scope.multi.locations = $scope.places;	
			      $scope.multi.category = $scope.categories;
			      $scope.vendName = data.pack.vendorInformation.viVendorName;
			      $scope.planecheck = data.pack.pkFlight;			      
			      $scope.hotelchck = data.pack.pkHotel;
			      $scope.foodcheck = data.pack.pkFood;
			      $scope.traincheck = data.pack.pkTrain;
			      $scope.buscheck = data.pack.pkBus;
			      $scope.ferrycheck = data.pack.pkFerry;
			      $scope.visacheck = data.pack.pkVisaFeeInclusion;
			      $scope.transfercheck = data.pack.pkTransfer;
			      $scope.guidecheck = data.pack.pkGuide;
			      $scope.zoocheck = data.pack.pkJungle;
			      $scope.showscheck = data.pack.pkShows;
			      $scope.gamescheck = data.pack.pkSports;
			      $scope.campcheck = data.pack.pkCampfire;
			      $scope.seacheck = data.pack.pkSeaActivity;
			      $scope.aircheck = data.pack.pkPickUp;	
			      $scope.pkType = data.pack.pkType;
			      $scope.selpackSpecial = data.pack.pkSpecial;
			      var packagefeaturesArray=["planecheck","hotelchck","foodcheck","traincheck","buscheck","ferrycheck","visacheck","transfercheck","guidecheck","zoocheck","showscheck","gamescheck","campcheck","seacheck","aircheck"];
				  var featuresCount=0;
				  angular.forEach(packagefeaturesArray,function(value,key)
				  {	
					  
					  if($scope[value])
						{
							featuresCount++;						
						}	
				   });					
				  $scope.packagefeaturesCount=featuresCount;				
			      switch ($scope.selpackSpecial) 
	    		  	{
		    		    case 'Hot Deals'      :    $scope.packorder = [{"id":"1","name":"1"}];
		    		                               break;
		    		    case 'Trending'       :    $scope.packorder = [{"id":"3","name":"3"}];
		                				           break; 
		    		    case 'Best Sellers'   :    $scope.packorder = [{"id":"6","name":"6"}];
		                            	           break; 
		    		    case 'Summer Specials':    $scope.packorder = [{"id":"7","name":"7"}];
	     	           							   break;	                				
		    		    case 'Get Aways'      :    $scope.packorder = [{"id":"8","name":"8"}];
							   					   break;	                		
		    		    default	    		  :   $scope.packorder = [{"id":"2","name":"2"},{"id":"4","name":"4"},{"id":"5","name":"5"},{"id":"9","name":"9"}];     
	    		   }
			      $scope.pkOrder = data.pack.pkOrder;			      
	 	       }).error(function(data,status) {
			  });
		  }
	  }
	  
	  $scope.people = [];
	  dataFactory.get("getAllPack").then(function(data) {
		  $scope.packages = data.packList;
		  $scope.people = data.packList;
          $scope.items = data.packList;
        });
	  
	   /*dataFactory.get("getPackCode").then(function(data) {
		  $scope.packCode = data;
       });
	  */
	  $scope.getCode = function(){
		  /*dataFactory.get("getPackCode").then(function(data) {
			  $scope.packCode = data;
	       });*/
	  }
	  
        $scope.onPackSelected = function() { 
        	$scope.updatePack($scope.packObj);
       };
        
       $scope.setPackmod = function(idx,mod,pkid){
    	  angular.element('.validation-invalid').remove();
    	  if(pkid!="" && pkid!=undefined)
    	  {
    		  $scope.pkId=pkid;
    	  } 
 		  if(idx != 0){
 			 if($scope.pkId == "" || $scope.pkId === undefined) {
 				 alert("Please complete package details");
 				 return;
 			 }
 		  } 		
 		  $scope.tab = idx;
 		  $scope.packmod = mod;
 		  $scope.clearAct();
 		  $scope.clearPrice();
 		  if(idx==1) 
 		  {
 			 $scope.message ={'success':0,'error':0};
 			 $scope.pkactUploadshow = 0;
 			 $scope.getAllAct();
 		  }
 		  else if(idx==2)
 		  {
 			 $scope.message ={'success':0,'error':0};
 			  $scope.getAllPrice();
 		  } 		
 		  else if(idx==3)
 		  { 
 	    	   var imgSubPath = $scope.packCode;
 	    	   if(selfilemod==4) imgSubPath=$scope.packCode+"/activity";
 			  $scope.getAllImg(imgSubPath);
 		  }
 		  else if(idx==4)
 		  { 
 			 $scope.message ={'success':0,'error':0};
 			 $scope.getAllAttract();
		  }
 		  else if(idx==5)
 		  { 
 			$scope.message ={'success':0,'error':0};
 			$scope.getAllPackageHotel();
		  }
 		  else if(idx==6)
 		  { 
 			 $scope.message ={'success':0,'error':0};
			 $scope.getAllCondtions();
		  }
 	  }
       
       $scope.addCountry = function(){
    	   ngDialog.open({template: 'resources/js/angular/directives/country.jsp',
           className: 'ngdialog-theme-default'
    	   });
       }
       
       $scope.checkLoc = function(){
   		if(Object.keys($scope.multi.country).length < 1){
   			  $scope.States = [];
		      $scope.cities = [];
		      $scope.places = [];
		      $scope.multi.state = $scope.States;
		      $scope.multi.city = $scope.cities;
		      $scope.multi.locations = $scope.places;
   		}else if(Object.keys($scope.multi.state).length < 1){
   			  $scope.cities = [];
		      $scope.places = [];
		      $scope.multi.city = $scope.cities;
		      $scope.multi.locations = $scope.places;
   		}else if(Object.keys($scope.multi.city).length < 1){
		      $scope.places = [];
		      $scope.multi.locations = $scope.places;
   		}  
   	  }
       
       $scope.getCountry = function(){
    	   //$http.get("getAllCountries").success(function(data) {	
    		   //$scope.countries = data.cntryList;
		    //});
    	   $scope.checkLoc();
       }
       
       $scope.getState = function(){
    	   var cntryObj = $scope.multi.country;
    	   var stObj = $scope.multi.state;
    	   if(Object.keys(cntryObj).length > 0){
    	   $http.post("getAllStates","countryArr="+JSON.stringify(cntryObj)).success(function(data) {
  			 $scope.States = data.states;
 			 for(var i in stObj){
 				 var flag = false;
 				 for(var j in cntryObj){
 					 if(cntryObj[j].countryId == stObj[i].country){
 						flag = true;
 						break;
 					 }
 				 }
					if(!flag) delete stObj[i];
 			 }
 			$scope.multi.state = stObj;
  	    });}
       }
       
       $scope.getCity = function(){
    	   var stObj = $scope.multi.state;
    	   if(Object.keys(stObj).length > 0){
    	   $http.post("getAllCities","stateArr="+encodeURIComponent(JSON.stringify(stObj))).success(function(data) {	
  			 $scope.cities = data.cities;
  			 var ctyObj = $scope.multi.city;
  			 for(var i in ctyObj){
 				 var flag = false;
 				 for(var j in stObj){
 					 if(stObj[j].stateId == ctyObj[i].state){
 						flag = true;
 						break;
 					 }
 				 }
					if(!flag) delete ctyObj[i];
 			 }
 			$scope.multi.city = ctyObj;
  	    });
    	}
       }
       
       $scope.getLocation = function(){
 		  var ctyObj = $scope.multi.city;
   	      if(Object.keys(ctyObj).length > 0){
    	   $http.post("getAllLocations","cityArr="+JSON.stringify(ctyObj)).success(function(data) {	
  			 $scope.places = data.locations;
     	     var locObj = $scope.multi.locations;
 			 for(var i in locObj){
				 var flag = false;
				 for(var j in ctyObj){
					 if(ctyObj[j].cityId == locObj[i].city){
						flag = true;
						break;
					 }
				 }
					if(!flag) delete locObj[i];
			 }
			$scope.multi.locations = locObj;
  	    });
   	    }
       }
     //hide fileerrot
       $scope.uploadfileError = function(){
        $scope.fileError = false;
       }
    // package hotel image
       $scope.pkHotelImg = {};
       $scope.hideImageHotelSaveBtn="";
       $scope.$watch('pkHotelImg', function() {
		    for (var i = 0; i < $scope.pkHotelImg.length; i++) {
		      var file = $scope.pkHotelImg[i];
				var maxFileSize = 2 * 1024 * 1024;	     
			    var fileSize=file.size;	      
			    if(maxFileSize<fileSize)
				{
				    	  $scope.fileError="File size must be less than 2 MB";		    	  
				    	  return false;
				}	  
			    else
			    {	
			    		  $scope.hideImageHotelSaveBtn=1;	
					      $scope.generatepackageHotelThumb(file);
					      /*$scope.upload = $upload.upload({
					        url: "uploadImg",
					        method: 'POST',
					        data: {myObj: $scope.myModelObj},
					        file: file 
					        }).progress(function(evt) {
					      }).success(function(data, status, headers, config) {
					      }).error(function(data, status, headers, config) {
					      });*/
				}
		    }
		});
       
       /*$scope.savePkHotelImg = function(){
    	   var imgSubPath = "";
    	   if(selfilemod==6) imgSubPath="/package";
    	   var myData = 'imgStr=package&imgSubStr='+$scope.packCode+imgSubPath+'/hotel';
       	  $http.post("saveImg", myData).success(function(data) {
       		//$scope.getAllDayImg(data);
       	  }).error(function(data,status) {
       		
       	  });
       }*/
      
       
       $scope.removePackageHotel = function(myData){
    	   var imgSubPath = "";
    	   if(selfilemod==5) imgSubPath="/package";
       	var img = {};
       	img.imgName = myData+"";
       	img.imgModule = $scope.packCode+imgSubPath;
       	$scope.imgname = img.imgName 
    	$http.post("removeImg", 'imgStr=package&imgSubStr='+$scope.packCode+'&imgName='+$scope.imgname).success(function(data) {
       		$scope.getAllDayImg(data);
       		$scope.getAllPackageHotel();
       		console.log("removeHotel");
       	}).error(function(data,status) {
       		
       	});	
       }
       $scope.generatepackageHotelThumb = function(file) {
			  var imgfile;
				if (file != null) {
					if (file.type.indexOf('image') > -1) {
						$timeout(function() {
							var fileReader = new FileReader();
							fileReader.readAsDataURL(file);
							fileReader.onload = function(e) {
								$timeout(function() {
									file.dataUrl = e.target.result;
									$scope.iconPkHotelImage = file.dataUrl;
								});
							}
						});
					}
				}
			}
       
       // package activity
      
       $scope.getAllAct = function(){
    	   $scope.activity = {};
  		   $http.post("getAllAct", 'packStr='+$scope.pkId).success(function(data) {
 	          $scope.activity = data.packList; 	          
 	        });
       }
        
       $scope.addAct = function(form){
    	   $rootScope.showLoader();
    	   $scope.packageActivityResponse = {};
           $scope.message ={
        		   'success':0,
        		   'error':0
           };           
    	   var act = {};
    	   act.pkiSeqId = $scope.pkiSeqId;
    	   act.pkiDay = $scope.day;
    	   act.pkiItinerary = $scope.actDesc;
    	   act.pkFlight = $scope.planecheck ? true : false;
    	   act.pkHotel = $scope.hotelchck ? true : false;
    	   act.pkFood = $scope.foodcheck ? true : false;
    	   act.pkTrain = $scope.traincheck ? true : false;
    	   act.pkBus = $scope.buscheck ? true : false;
    	   act.pkFerry = $scope.ferrycheck ? true : false;
    	   var pack ={};
    	   pack.pkId = $scope.pkId;
    	   var myData = 'actStr='+JSON.stringify(act)+'&packStr='+JSON.stringify(pack);
    	   $http.post("saveAct", myData).success(function(data) {
    	   $scope.clearAct();
  		   $scope.getAllAct(); 
  		   $scope.packageActivityResponse = data;
		   if(data.Status == 'Error'){
				$scope.message ={
						'success':0,
						'error':1
					};
				$rootScope.hideLoader();
				$validationProvider.reset(form);
				}
			if(data.Status == 'Ok'){
				$scope.message ={
					'success':1,
					'error':0
				};
				$rootScope.hideLoader();
			}
			//form.$setPristine(true);
			
  	      }).error(function(data,status) {	
  			 
  		  });
    	   $validationProvider.reset(form);
       }
	  
       $scope.clearAct = function(){
    	   $scope.pkiSeqId = "";
    	   $scope.day = ""; 
    	   $scope.actDesc= "";
       }
       $scope.pksact="";
       $scope.hideImageSaveBtn = 1;
       $scope.packageActiveImg = function(id,img_url){
    	   $scope.pkactUploadshow = 1;
    	   $scope.pksact = id;
    	   $scope.iconDayImage = 'resources/images'+img_url;
    	   $scope.hideImageSaveBtn = 0;
       }
       $scope.getAct = function(act){
    	   $scope.pkiSeqId = act.pkiSeqId;
    	   $scope.day = act.pkiDay;
    	   $scope.actDesc = act.pkiItinerary;   
       }
       
       $scope.removeAct = function(id){
    	   $rootScope.showLoader();
 		  var myData = 'actStr='+id;
 		  $http.post("removeAct", myData).success(function(data) {
 			     $scope.clearAct();
 				 $scope.getAllAct();
 				$scope.removeDayImg();
 		    }).error(function(data,status) {	
 					
 			});
 		 $rootScope.hideLoader();
 	   }
       
       //Attraction
       $scope.getAllAttract = function(){
    	  /* if($scope.attrac == 'exclus')
    		   {
  		   $http.post("getExclusionByPackage", 'packStr='+$scope.pkId).success(function(data) {
 	          $scope.packAttraction = data.packageExclution;
 	          console.log($scope.packAttraction);
 	        });
    		   }
    	   else
    		   {
    		   $http.post("getInclusionByPackage", 'packStr='+$scope.pkId).success(function(data) {
    	 	          $scope.packAttraction = data.packageInclusion;
    	 	          console.log($scope.packAttraction);
    	 	        });
    		   }*/
       }
       $scope.packAttractionInclus ={};
       $scope.packAttractionExclus= {};
       $scope.getExclusionByPackage = function(){
    	   $http.get('getExclusionByPackage?packStr='+$scope.pkId).success(function(data) {
    		  $scope.packAttractionInclus ={};
 	          $scope.packAttractionExclus = data.packageExclution;
 	         });
       }
       
       $scope.getInclusionByPackage = function(){
    	   $http.get('getInclusionByPackage?packStr='+$scope.pkId).success(function(data) {
    		   $scope.packAttractionExclus= {};
			   $scope.packAttractionInclus = data.packageInclusion;
	 	      });
	  }
       
       $scope.changeAttraction = function(){
    	   $scope.attractIconImg = {};
    	   $scope.attractIconList = {};
    	   $scope.pkattUploadshow = 0;
    	   $scope.saveAttractionBtn=1;
    	   $scope.pksatt = '';
    	   if($scope.attrac != undefined && $scope.attrac !=''){
	    	   if($scope.attrac == 'exclus')
	    		   $scope.getExclusionByPackage($scope.attrac);
	    	   if($scope.attrac == 'inclus')
	    		   $scope.getInclusionByPackage();
    	   }
       }
      $scope.clearAttraction = function()
      {   
//    	  $scope.attrac="";
    	  $scope.attrdesc="";
      }
      $scope.saveAttraction = function(form){
          $scope.packageAttractionResponse = {};
             $scope.message ={
               'success':0,
               'error':0
             };
          if($scope.attrac == 'exclus'){
           $rootScope.showLoader();
              //$scope.saveDAttractionIconImg();
              var att = {};
              //att.pkAttrac = $scope.attrac;
              att.pkexDescription = $scope.attrdesc;
              if($scope.pksatt!=undefined)
              {
               att.pkexSeqId = $scope.pksatt;
              }            
             
              var pack ={};
              pack.pkId = $scope.pkId;            
             
              var myData = 'packagesaveExclusionStr='+JSON.stringify(att)+'&packStr='+JSON.stringify(pack);
             
              $http.post("saveExclusion", myData).success(function(data) {
               $scope.getExclusionByPackage();
               $scope.packageAttractionResponse = data;
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
           $scope.clearAttraction(); 
           }
               
               }).error(function(data,status) { 
               });
          }else{ 
           $rootScope.showLoader();
          // $scope.saveDAttractionIconImg();
              var att = {};
              //att.pkAttrac = $scope.pkinInclusion;
              att.pkinDescription = $scope.attrdesc;
              if($scope.pksatt!=undefined)
              {
               att.pkinSeqId = $scope.pksatt;
              } 
              
              var pack ={};
              pack.pkId = $scope.pkId;
              
              
              
              var myData = 'packageInclusionStr='+JSON.stringify(att)+'&packStr='+JSON.stringify(pack);
              $http.post("saveInclusion", myData).success(function(data) {
               $scope.getInclusionByPackage();
               $scope.packageAttractionResponse = data;
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
           $scope.clearAttraction(); 
          }
               }).error(function(data,status) { 
            
            });
             
          }    
          $scope.attractionval = $scope.attrac;
          $validationProvider.reset(form);
             $scope.attrac = $scope.attractionval;
         }
       $scope.removeAttract = function(id,type){
    	   $rootScope.showLoader();
    	   if(type != undefined && $scope.attrac !=''){
	    	   if(type == 'exclus'){
	    		   	  var myData = 'exclusionStr='+id;
	    	   		  $http.post("deleteExclusion", myData).success(function(data) {
	    	   			  $scope.getExclusionByPackage();
		         		   $scope.packageAttractionResponse = data;
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
		 	   				}
	    	   		    }).error(function(data,status) {	
	    	   					
	    	   			});
	    	   }
	    	   if(type == 'inclus'){
	    		   var myData = 'inclusionStr='+id;
	    	   		  $http.post("deleteInclusion", myData).success(function(data) {
	    	   			  $scope.getInclusionByPackage();
		         		   $scope.packageAttractionResponse = data;
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

		 	   				}
	    	   		    }).error(function(data,status) {	
	    	   					
	    	   			});
	    	   }
    	   } 
   	   }      
    // Attraction icon image
       $scope.packageAttractionImg = function(id,img_url,type,desc,editType){
    	   $scope.pksatt = id;
    	   if(editType==undefined)
    	   {   
    		   
    		   if(type=='exclusion'){
    			   $scope.attrac = 'exclus';
    		   }else {
    			   $scope.attrac = 'inclus';
    		   }
    		   $scope.attrdesc=desc;
        	   $scope.pkattUploadshow = 0;
        	   $scope.saveAttractionBtn=1;
    		   
    	   } 
    	   else
    	   {    
    		   $scope.type=type;
    		   $scope.pkattUploadshow = 1;
    		   $scope.saveAttractionBtn=0;
    	   }    	   
    	   if(img_url=="")
    	   {
    		   img_url = '/no-image.png';
    	   }    	      
    	   $scope.iconAttractImage = 'resources/images'+img_url;    	  
    	   $scope.hideImageSaveBtnAttraction = 0;
    	}
       
       $scope.attractIconImg = {};
       $scope.finalAttractIconImg = {};
       $scope.$watch('attractIconImg', function() {
    	   for (var i = 0; i < $scope.attractIconImg.length; i++) {		      
		      var file = $scope.attractIconImg[i];
		  	  var maxFileSize = 2 * 1024 * 1024;	     
		      var fileSize=file.size;	      
		      if(maxFileSize<fileSize)
			  {
			    	  $scope.fileError="File size must be less than 2 MB";		    	  
			    	  return false;
			  }	  
		      else
		      {
		    	  	 $scope.hideImageSaveBtnAttraction = 1;
				     $scope.generateAttractionThumb(file);
				     $scope.finalAttractIconImg = file;
				      /*$scope.upload = $upload.upload({
				        url: "uploadImg",
				        method: 'POST',
				        data: {myObj: $scope.myModelObj},
				        file: file 
				        }).progress(function(evt) {
				      }).success(function(data, status, headers, config) {
				      }).error(function(data, status, headers, config) {
				      });*/
			 }
    	   }
    	 });
       	  $scope.imageDataURI='';       	 
	      $scope.iconAttractImage='';                
		  $scope.cropperWidth="100";
		  $scope.cropperHeight="100";	
		  
		 
	      $scope.fileIconSelected=function(evt,currentImageType)
	      {  
	    	  /*var cropperWidth     = imageCropArray[currentImageType].width;
	    	  var cropperHeight    = imageCropArray[currentImageType].height;
	    	  $scope.cropperWidth  = cropperWidth;
	    	  $scope.cropperHeight = cropperHeight;
	    	  //console.log(evt);
	    	  var file=evt.currentTarget.files[0];
	    	  $scope.fileType=file.type;
	    	  if(file!=undefined)
	    	  {  
		          var reader = new FileReader();	          
		          reader.onload = function (evt) {
		            $scope.$apply(function($scope){
		                    $scope.imageDataURI=evt.target.result;	                   
		            });
		          };	
		          reader.readAsDataURL(file);	
		         
	    	  } 
	    	  $scope.hideImageSaveBtnAttraction = 1;	*/  
	    	  
	      }	   
	      $scope.saveDAttractionIconImg = function(pksatt,type){       
	          $rootScope.showLoader();
	          var imgSubPath = "";
	          $scope.hideImageSaveBtnAttraction = 0;
	          //console.log($scope.iconAttractImage);
	          
	          /*var image = new Image();
	          image.src = $scope.iconAttractImage;*/
	        
	          var myData = 'imgStr=package&imgSubStr='+$scope.packCode+'/'+$scope.type+'/'+$scope.pksatt+'&objectId='+$scope.pksatt; 
	            $http.post("saveImage", myData).success(function(data) {
	            // console.log('test');
	            $scope.iconAttractImage='resources/images/no-image.png';   
	            $scope.hideImageSaveBtnAttraction = 0;
	           //$scope.getAllDayImg(data);
	            }).error(function(data,status) {
	           
	            });
	          $scope.upload = $upload.upload({
	            url: "uploadImage",
	            method: 'POST',
	            data: {module: 'package',subModule:$scope.packCode+'/'+$scope.type+'/'+$scope.pksatt,objectId:$scope.pksatt},
	            file: $scope.finalAttractIconImg 
	            }).progress(function(evt) {
	          }).success(function(data, status, headers, config) {
	           $scope.iconAttractImage='resources/images/no-image.png';   
	              $scope.hideImageSaveBtnAttraction = 0;
	          }).error(function(data, status, headers, config) {
	          });
	          $rootScope.hideLoader();
	         }
       
      
       
       $scope.removeAttractImg = function(myData){
    	   $rootScope.showLoader();
    	   var imgSubPath = "";
    	   if(selfilemod==5) imgSubPath="/activity";
       	var img = {};
       	img.imgName = myData+"";
       	img.imgModule = $scope.packCode+imgSubPath;
       	$scope.imgname = img.imgName 
    	$http.post("removeImg", 'imgStr=package&imgSubStr='+$scope.packCode+'&imgName='+$scope.imgname).success(function(data) {
       		$scope.getAllDayImg(data);
       	}).error(function(data,status) {
       	});	
       	$rootScope.hideLoader();
       }
       $scope.generateAttractionThumb = function(file) {
			  var imgfile;
				if (file != null) {
					if (file.type.indexOf('image') > -1) {
						$timeout(function() {
							var fileReader = new FileReader();
							fileReader.readAsDataURL(file);
							fileReader.onload = function(e) {
								$timeout(function() {
									file.dataUrl = e.target.result;
									$scope.iconAttractImage = file.dataUrl;
								});
							}
						});
					}
				}
			}
    // package Price

       $scope.getAllPrice = function(){
  		   $http.post("getAllPrice", 'packStr='+$scope.pkId).success(function(data) {
 	          $scope.packPrice = data.packList;
 	        });
       }
       
       $scope.addPrice = function(form){
    	   $rootScope.showLoader();
    	   $scope.packagePriceResponse = {};
           $scope.message ={
        		   'success':0,
        		   'error':0
           };     
    	   $scope.package_date_error =  0;
    	   $scope.validFromError="";
    	   $scope.validToError="";
    	   if($scope.pkcCost == ""){
    		   alert("Please fill the Cost Field");
    		   return;
    	   }
    	   if($scope.validfrom == ""){
    		   //alert("Please select From Date");
    		   $scope.validFromError=1;
    		   return;
    	   }
    	   if($scope.validto == ""){
    		   //alert("Please select From Date");
    		   $scope.validToError=1;
    		   return;
    	   } 
    	   var today = new Date();
    	   var dd = today.getDate(); 
    	   var mm = today.getMonth()+1; 
    	   var yyyy = today.getFullYear(); 
    	   var today = yyyy+'-'+mm+'-'+dd;    	  
    	   var today = new Date(new Date().getFullYear(), new Date().getMonth(), new Date().getDate()).getTime();
    	   var getdate = new Date($scope.validfrom).getTime();
    	   var validFrom = $scope.validfrom.split("/");
    	   	   validFrom = new Date(validFrom[2], validFrom[1]- 1, validFrom[0]);
    	       validFrom = $filter('date')(validFrom, 'yyyy-MM-dd');
    	   var validTo = $scope.validto.split("/");
    	       validTo = new Date(validTo[2], validTo[1]- 1, validTo[0]);
    	       validTo = $filter('date')(validTo, 'yyyy-MM-dd');
    	      if(getdate)
    	      {
    		   var price = {};
        	   price.pkcId = $scope.pkcId;
        	   price.pkcCost = $scope.price;
        	   price.pkcValidFrom = validFrom; 
        	   price.pkcValidTo =validTo;
        	   price.pkcDescription = $scope.priceDesc;
        	   price.offerPrice = $scope.priceOffer;
        	   var pack ={};
        	   pack.pkId = $scope.pkId;
        	   var myData = 'priceStr='+JSON.stringify(price)+'&packStr='+JSON.stringify(pack);
        	   $http.post("savePrice", myData).success(function(data) {
        		 $scope.clearPrice();
      			 $scope.getAllPrice();
      			 $scope.packagePriceResponse = data;
      		   if(data.Status == 'Error'){
      				$scope.message ={
      						'success':0,
      						'error':1
      					};
      				//$validationProvider.reset(form);
      				}
      			if(data.Status == 'Ok'){
      				$scope.message ={
      					'success':1,
      					'error':0
      				};
      				//$validationProvider.reset(form);
      			}
      			 $validationProvider.reset(form);  
      	      }).error(function(data,status) {	
      			  
      		  });
    	   }
    	   else
    	   {
    		  
    	   $scope.package_date_error=1;
    	   }
    	      $rootScope.hideLoader();
       } 
       
       $scope.clearPrice = function(){
    	   $scope.pkcId = "";
    	   $scope.price = "";
    	   $scope.validfrom = "";
    	   $scope.validto = "";
    	   $scope.priceDesc = "";
    	   $scope.priceOffer= "";
       }
       $scope.getPrice = function(price){    	   
    	   $scope.pkcId = price.pkcId;
    	   $scope.price = price.pkcCost;
    	   $scope.validfrom =price.validFrom;
    	   $scope.validto =price.validTo;
    	   $scope.priceDesc = price.pkcDescription;   
    	   $scope.priceOffer = price.offerPrice;
       }
       
       $scope.removePrice = function(id){
    	   $rootScope.showLoader();
 		  $http.post("removePrice", 'priceStr='+id).success(function(data) {
 			     $scope.clearPrice();
 				 $scope.getAllPrice();
 		    }).error(function(data,status) {	
 					
 			});
 		 $rootScope.hideLoader();
 	   }       
       $scope.packConditions = {};
       $scope.getAllCondtions = function(){
    	   $http.post("getAllConditions", 'packStr='+$scope.pkId).success(function(data) {
  	          $scope.packConditions = data;
  	         // console.log($scope.packPrice);
  	        });
       }
       $scope.savePackCondtion = function(form){
    	   $rootScope.showLoader();
    	   var pack = {};
    	   if(pack.pkId!="" || !pack.pkId===undefined ) pack.pkId = $scope.pkId;
         	pack.pkCode = $scope.packCode;
         	pack.pkName = $scope.packName;
         	pack.pkDays = $scope.packDays;
         	pack.pkNights = $scope.packNights;
         	pack.pkType = $scope.selpacktype;
         	pack.pkDescripton = $scope.packDesc;
         	pack.pkFlight = $scope.planecheck ? true : false;
         	pack.pkHotel = $scope.hotelchck ? true : false;
         	pack.pkFood = $scope.foodcheck ? true : false;
         	pack.pkTrain = $scope.traincheck ? true : false;
         	pack.pkBus = $scope.buscheck ? true : false;
         	pack.pkFerry = $scope.ferrycheck ? true : false;
         	var packDesc = {};
         	packDesc.pcDescriptions = $scope.pcDescriptions;
    	   var myData = 'conStr='+JSON.stringify(packDesc)+'&packStr='+JSON.stringify(pack);
    	   $http.post("saveConditions", myData).success(function(data) {
    		   $scope.packageConditionResponse = data;
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
					$scope.pcDescriptions="";
					$validationProvider.reset(form);
				}
				$scope.getAllCondtions();
   	        });
        }
       $scope.removePackCondtion = function(id){
    	   $rootScope.showLoader();
    	   $http.post("removeCondition", 'conStr='+id).success(function(data) {
    		   $scope.packageConditionResponse = data;
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
				}
    		   $scope.getAllCondtions();
		    }).error(function(data,status) {
		    	
			});
       }
       $scope.packageHotels = {};
       $scope.packageHotelResponse = {};
       $scope.packageHotelsCities = {};
       $scope.packageHotelsByCities = {};
       $scope.pkhotel = {};
       $scope.pkhotel.status = 0;
       $scope.pkhotelUploadshow = 0;
       
       $scope.packageHotelImg = function(id,img_url){
    	   $scope.pkhotelUploadshow = 1;
    	   $scope.pkshtl = id;
    	   $scope.phId = id;
    	   $scope.iconHotelImage = 'resources/images'+img_url;
    	   $scope.hideImageSaveBtnHotel = 0;
       }
       
       $scope.getAllPackageHotel = function(){
    	   $http.get('getAllPackageHotel?pkId='+$scope.pkId).success(function(data) {
   	          $scope.packageHotels = data;
   	          $scope.pkhotel.city ="";
   	          $scope.pkhotel.pkhotelId ="";
   	          if($scope.packageHotelsCities != '')
   	        	  $scope.getAllCitiesByPackage();
   	        });
        }
       $scope.getAllCitiesByPackage = function(){
    	   $http.get('getAllCitiesByPackage?packStr='+$scope.pkId).success(function(data) {
   	          $scope.packageHotelsCities = data;
   	          $scope.packageHotelsByCities = {};
   	        });
        }
       $scope.changePackageCity = function(){
    	   $http.get('getHotelsByCity?cityId='+$scope.pkhotel.city).success(function(data) {
   	          $scope.packageHotelsByCities = data;
   	        });
        }
       
       $scope.savePackageHotel = function(form){
    	   $rootScope.showLoader();
    	      //$scope.pkhotel.status = ($scope.pkhotel.status)?1:0;
    	      $scope.pkhotel.status = ($scope.pkhotel.pkhotelIsactiveTrue)?true:false;
    	      delete $scope.pkhotel.pkhotelIsactiveTrue;
        	   var myData = 'hotel='+JSON.stringify($scope.pkhotel)+'&pkId='+$scope.pkId+'&hdId='+$scope.pkhotel.pkhotelId;
        	   $http.post("savePackageHotel", myData).success(function(data) {
        		   $scope.pkhotel.pkhotelIsactiveTrue = ($scope.pkhotel.status== 'Y')?true:false;
        		   $scope.packageHotelResponse = data;
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
    					$validationProvider.reset(form);
    				}
    				$scope.getAllPackageHotel();
       	        });
    	   }
    	
       $scope.changeStatusActive = function(){
    	   $scope.pkhotel.status = !$scope.pkhotel.status;
       }
       $scope.removePackageHotel = function(id){
    	   var pkhtl = 'hotelId='+id;
  		   $http.post("deletePackageHotel", pkhtl).success(function(data) {
    	      $scope.packageHotelResponse = data;
    		   if(data.Status == 'Error'){
					$scope.message ={
							'success':0,
							'error':1
						};
				}
				if(data.Status == 'Ok'){
					$scope.message ={
						'success':1,
						'error':0
					};
					 $scope.removePackageHotelImage();
					 $scope.getAllPackageHotel();
				}
    		   //$scope.getAllPackageHotel();
		    }).error(function(data,status) {
		    	
			});
       }
       
       // package hotel image
       $scope.hideImageHotelSaveBtn="";
       $scope.pkHotelImg = {};
       $scope.finalPkHotelImg = {};
       $scope.$watch('pkHotelImg', function() {
		    for (var i = 0; i < $scope.pkHotelImg.length; i++) {
		      var file = $scope.pkHotelImg[i];
		      var maxFileSize = 2 * 1024 * 1024;	     
		      var fileSize=file.size;	      
		      if(maxFileSize<fileSize)
			  {
			    	  $scope.fileError="File size must be less than 2 MB";		    	  
			    	  return false;
			  }	  
		      else
		      {		    	     
		    	      $scope.hideImageHotelSaveBtn=1;
				      $scope.generatepackageHotelThumb(file);
				      $scope.finalPkHotelImg = file;
				      /*$scope.upload = $upload.upload({
				        url: "uploadImg",
				        method: 'POST',
				        data: {myObj: $scope.myModelObj},
				        file: file 
				        }).progress(function(evt) {
				      }).success(function(data, status, headers, config) {
				      }).error(function(data, status, headers, config) {
				      });*/
				      
				  }
		    }
		});
       
       $scope.savePkHotelImg = function(pkshtl){
           $rootScope.showLoader();
           var imgSubPath = "";
           if(selfilemod==6) imgSubPath="/package";
           $scope.iconPkHotelImage = '';
           /*var myData = 'imgStr=package&imgSubStr='+$scope.packCode+imgSubPath+'/hotel&objectId='+pkshtl;*/
           
             /* $http.post("saveImage", myData).success(function(data) {
            //$scope.getAllDayImg(data);
             }).error(function(data,status) {
            
             });*/
           
           $scope.upload = $upload.upload({
             url: "uploadImage",
             method: 'POST',
             data: {module: 'package',subModule:$scope.packCode+imgSubPath+'/hotel/'+pkshtl,objectId:pkshtl},
             file: $scope.finalPkHotelImg 
             }).progress(function(evt) {
           }).success(function(data, status, headers, config) {
            $scope.hideImageHotelSaveBtn=0;
           }).error(function(data, status, headers, config) {
           });
           $rootScope.hideLoader();
           $scope.pkhotelUploadshow = 0;
          }
      
       
       $scope.removePackageHotelImage = function(myData){
    	   var imgSubPath = "";
    	   if(selfilemod==2) imgSubPath="/tile";
       	var img = {};
       	img.imgName = myData+"";
       	img.imgModule = $scope.packCode+imgSubPath;
       	$scope.imgname = img.imgName 
    	$http.post("removeImg", 'imgStr=package&imgSubStr='+$scope.packCode+'&imgName='+$scope.imgname).success(function(data) {
       		$scope.getAllDayImg(data);
       		$scope.getAllPackageHotel
       	}).error(function(data,status) {
       		
       	});	
       }
       $scope.generatepackageHotelThumb = function(file) {
			  var imgfile;
				if (file != null) {
					if (file.type.indexOf('image') > -1) {
						$timeout(function() {
							var fileReader = new FileReader();
							fileReader.readAsDataURL(file);
							fileReader.onload = function(e) {
								$timeout(function() {
									file.dataUrl = e.target.result;
									$scope.iconPkHotelImage = file.dataUrl;
								});
							}
						});
					}
				}
			}
       
       // Package File
       $scope.myFiles = {};
       $scope.cropedImage="";
       $scope.$watch('myFiles', function() {
		    for (var i = 0; i < $scope.myFiles.length; i++) {
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
				      /*$scope.generateThumb(file);		      
				      $scope.upload = $upload.upload({
				        url: "uploadImg",
				        method: 'POST',
				        data: {myObj: $scope.myModelObj},
				        file: file 
				        }).progress(function(evt) {
				        //console.log('progress: ' + parseInt(100.0 * evt.loaded / evt.total) + '% file :'+ evt.config.file.name);
				      }).success(function(data, status, headers, config) {
				        //console.log('file ' + config.file.name + 'is uploaded successfully. Response: ' + data);
				      }).error(function(data, status, headers, config) {
				        //console.log('file ' + config.file.name + 'is uploaded error. Response: ' + data);
				      });*/
		    	  
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
		    	          $scope.cropedImage=file;
		       	  } 	
		      
			  }
		      
		    }
		});
       
       $scope.saveImg = function(){
    	   $rootScope.showLoader();
    	   var imgSubPath = "";
    	   //console.log("sathish");
    	   //console.log($scope.pkId);
    	   if($scope.selfilemod==4) 
    	   {
    		   imgSubPath="/activity";
    	   }
    	   else if($scope.selfilemod==2)
    	   {
    		   imgSubPath="/tile";
    	   }    	  
    	   var myData = 'imgStr=package&imgSubStr='+$scope.packCode+imgSubPath;
       	   /*$http.post("saveImg", myData).success(function(data) {
       		$scope.imgfile = '';       		 
       		$scope.getAllImg(data);
       		  	
       	  }).error(function(data,status) {
       		
       	  });*/
    	   
    	   $scope.upload = $upload.upload({
		        url: "uploadImage",
		        method: 'POST',
		        /*data: {myObj: $scope.myModelObj},
		        file: file*/ 
		        data: {module: 'package',subModule:$scope.packCode+imgSubPath,objectId:$scope.pkId},	        
		        file: $scope.cropedImage 
		        }).progress(function(evt) {
		        //console.log('progress: ' + parseInt(100.0 * evt.loaded / evt.total) + '% file :'+ evt.config.file.name);
		      }).success(function(data, status, headers, config) {
		    	  $scope.imgfile = '';       		 
		       		$scope.getAllImg(data);
		        //console.log('file ' + config.file.name + 'is uploaded successfully. Response: ' + data);
		      }).error(function(data, status, headers, config) {
		        //console.log('file ' + config.file.name + 'is uploaded error. Response: ' + data);
		      });
    	   $rootScope.hideLoader();
       }
       
       $scope.getAllImg = function(myData){    	   
    	   $rootScope.showLoader();
       	$http.post("getAllImg", 'imgStr=package&imgSubStr='+myData).success(function(data) {       		
       		$scope.imgList = data[0];
       	}).error(function(data,status) {
       		
       	});	
       	$rootScope.hideLoader();
       }       
       $scope.removePackageImg = function(myData){    	   
    	   $rootScope.showLoader();
    	   var imgSubPath = "";
    	   if($scope.selfilemod==4) 
    		   {
    		   	imgSubPath="/activity";
    		   }
    	   else if($scope.selfilemod==2) 
    		   {
    			imgSubPath="/tile";
    		   
    		   }
       	var img = {};
       	img.imgName = myData+"";
       	img.imgModule = $scope.packCode+imgSubPath;
       	var post_data = 'imgStr=package&imgSubStr='+$scope.packCode+imgSubPath+'&imgName='+img.imgName;
       	$http.post("removeImg", post_data).success(function(data) {       	
       	$scope.getAllImg(data);       		
       	}).error(function(data,status) {
       		
       	});	
       	$rootScope.hideLoader();
       }
       $scope.removeImg = function(myData){
    	   var imgSubPath = "";
    	   if(selfilemod==4) imgSubPath="/activity";
       	var img = {};
       	img.imgName = myData+"";
       	img.imgModule = $scope.packCode+imgSubPath;
       	$http.post("removeImg", 'imgStr=package&imgSubStr='+JSON.stringify(img)).success(function(data) {
       		$scope.getAllImg(data);
       	}).error(function(data,status) {
       		
       	});	
       }
       
    // day icon image
       $scope.dayIconImg = {};
       $scope.finalDayIconImg = {};
       $scope.$watch('dayIconImg', function() {
		    for (var i = 0; i < $scope.dayIconImg.length; i++) {		      
		      var file = $scope.dayIconImg[i];
		      var maxFileSize = 2 * 1024 * 1024;	     
		      var fileSize=file.size;	      
		      if(maxFileSize<fileSize)
			  {
			    	  $scope.fileError="File size must be less than 2 MB";		    	  
			    	  return false;
			  }	  
		      else
		      {
		    	  	  $scope.hideImageSaveBtn = 1;
				      $scope.generateDayThumb(file);
				      $scope.finalDayIconImg = file;
				     /* $scope.upload = $upload.upload({
				        url: "uploadImg",
				        method: 'POST',
				        data: {myObj: $scope.myModelObj},
				        file: file 
				        }).progress(function(evt) {
				      }).success(function(data, status, headers, config) {
				      }).error(function(data, status, headers, config) {
				      });*/
				  }
		    }
		});
       
       $scope.saveDayIconImg = function(pkiSeqId){
           var imgSubPath = "";
           if(selfilemod==4) imgSubPath="/activity";
           $scope.dayIconImg = '';
           //var myData = 'imgStr=package&imgSubStr='+$scope.packCode+imgSubPath+'/activity/'+$scope.pksact+'&objectId='+$scope.pksact;
           
            /* $http.post("saveImage", myData).success(function(data) {
            $scope.getAllAct();
            $scope.iconDayImage={};
             }).error(function(data,status) {
            
             });*/
           $scope.upload = $upload.upload({
             url: "uploadImage",
             method: 'POST',
             file: $scope.finalDayIconImg,
             data: {module: 'package',subModule:$scope.packCode+imgSubPath+'/activity/'+$scope.pksact,objectId:$scope.pksact} 
            }).progress(function(evt) {
           }).success(function(data, status, headers, config) {
          $scope.getAllAct();
             $scope.iconDayImage={};
          $scope.pkactUploadshow = 0;
           }).error(function(data, status, headers, config) {
           });
          }
       
       $scope.getAllDayImg = function(myData){
    	   
       	$http.post("getAllImg", 'imgStr=package&imgSubStr='+myData).success(function(data) {
       		$scope.dayIconList = data[0];
       	}).error(function(data,status) {
       		
       	});	
       }
       
       $scope.removeDayImg = function(myData){    	   
    	   var imgSubPath = "";
    	   if(selfilemod==4) imgSubPath="/activity";
       	var img = {};
       	img.imgName = myData+"";
       	img.imgModule = $scope.packCode+imgSubPath;
       	$scope.imgname = img.imgName 
    	$http.post("removeImg", 'imgStr=package&imgSubStr='+$scope.packCode+'&imgName='+$scope.imgname).success(function(data) {
       		$scope.getAllDayImg(data);
       	}).error(function(data,status) {
       		
       	});	
       }
       $scope.generateDayThumb = function(file) {
			  var imgfile;
				if (file != null) {
					if (file.type.indexOf('image') > -1) {
						$timeout(function() {
							var fileReader = new FileReader();
							fileReader.readAsDataURL(file);
							fileReader.onload = function(e) {
								$timeout(function() {
									file.dataUrl = e.target.result;
									$scope.iconDayImage = file.dataUrl;
								});
							}
						});
					}
				}
			}
      
       
       $scope.swapfilemod = function(id){
		   var imgSubPath = $scope.packCode;
    	   if(id==4)
    	   {
 	    	   if(selfilemod==4) imgSubPath=$scope.packCode+"/activity";
    	   }
    	   else 
    	   {
    		   if(id==1)
    		   {
    			   $scope.fileLength=4;    			
    		   }
    		   else if(id==2)
    		   {
    			   imgSubPath=$scope.packCode+"/tile";    			  
    			   $scope.fileLength=2;
    		   }
    		      $scope.getAllImg(imgSubPath);
    			
    		   
    	   }
       }
       
       $scope.onDropComplete = function (index, obj, evt) {
    	   var imgSubPath = "";
    	   if($scope.selfilemod==2) imgSubPath="/tile";
    	   else imgSubPath = "";
           var otherObj = $scope.imgList[index];
           var otherIndex = $scope.imgList.indexOf(obj);
           $scope.imgList[index] = obj;
           $scope.imgList[otherIndex] = otherObj;
           var myData = 'imgStr=package&imgSubStr='+$scope.packCode+imgSubPath+','+(index+1)+','+(otherIndex+1);
           $http.post("reorderImg",myData).success(function(data) {
       		$scope.getAllImg(data);
       	}).error(function(data,status) {
       		
       	});
       }
       
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
       
  });
	
	//<--------------------    Typeahead controller   ------------------------->
	
	app.directive('ctypeahead', function($timeout) {
		  return {
		    restrict: 'AEC',
		    scope: {
				items: '=',
				prompt:'@',
				title: '@',
				subtitle:'@',
				model: '=',
				modobj: '=',
				modby: '@',
				onSelect:'&'
			},
			link:function(scope,elem,attrs){
			   scope.handleSelection=function(selectedItem,item){
				 scope.modobj=item;
				 scope.model=selectedItem;
				 scope.current=0;
				 scope.selected=true;        
				 $timeout(function(){
					 scope.onSelect();
				  },200);
			  };
			  scope.current=0;
			  scope.selected=true;
			  scope.isCurrent=function(index){
				 return scope.current==index;
			  };
			  var idx = 0;
			  scope.goDown=function(fl){
				  if(idx < fl.length-1) scope.setCurrent(1+idx++);
			  };
			  scope.goUp=function(fl){
				  idx>0 ? idx-- : idx = 0;
				  scope.setCurrent(idx);
			  };
			  scope.insert=function(fl){
				  scope.modobj=fl[idx];
				  scope.model=fl[idx][scope.modby];
				  scope.current=0;
					 scope.selected=true;        
					 $timeout(function(){
						 scope.onSelect();
					  },200);
			  }
			  scope.setCurrent=function(index){
				 scope.current=index;
				 idx = index;
			  };
			},
		    templateUrl: 'resources/js/angular/directives/templateurl.html'
		  }
		});
	
	app.factory('dataFactory', function($http) {
		  return {
		    get: function(url) {
		      return $http.get(url).then(function(resp) {
		        return resp.data; // success callback returns this
		      });
		    }
		  };
		});
	
	//<--------------------    Image upload controller   ------------------------->
	
	app.controller('fileCtrl', [ '$scope', '$upload', '$timeout', function($scope, $upload, $timeout) {
		$scope.fileReaderSupported = window.FileReader != null && (window.FileAPI == null || FileAPI.html5 != false);
           $scope.$watch('myFiles', function() {
			    for (var i = 0; i < $scope.myFiles.length; i++) {
			      var file = $scope.myFiles[i];
			      $scope.generateThumb(file);			     
			      $scope.upload = $upload.upload({
			        url: "uploadImg", // upload.php script, node.js route, or servlet url
			        method: 'POST',
			        data: {myObj: $scope.myModelObj},
			        file: file // single file or a list of files. list is only for html5
			        //fileName: 'doc.jpg' or ['1.jpg', '2.jpg', ...] // to modify the name of the file(s)
			        //fileFormDataName: myFile, // file formData name ('Content-Disposition'), server side request form name
			                                    // could be a list of names for multiple files (html5). Default is 'file'
			        //formDataAppender: function(formData, key, val){}  // customize how data is added to the formData.  // See #40#issuecomment-28612000 for sample code

			      }).progress(function(evt) {
			        //console.log('progress: ' + parseInt(100.0 * evt.loaded / evt.total) + '% file :'+ evt.config.file.name);
			      }).success(function(data, status, headers, config) {
			        //console.log('file ' + config.file.name + 'is uploaded successfully. Response: ' + data);
			      }).error(function(data, status, headers, config) {
			        //console.log('file ' + config.file.name + 'is uploaded error. Response: ' + data);
			      });
			  }
			});
		  
		  $scope.generateThumb = function(file) {
			  var imgfile;
				if (file != null) {
					if ($scope.fileReaderSupported && file.type.indexOf('image') > -1) {
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
	}]);
	

	//<--------------------    Serverside grid directive   ------------------------->
	app.directive('serverSideGrid', function($injector, ngTableParams, $http,$rootScope,$q){
			var service,operation,paginationStyle = {};
			$rootScope.statusTypes = function ($rootScope) 
			{
			    var def = $q.defer();
			    var docType = [
			        {'id':'Open', 'title':'Open'},
			        {'id':'Followup', 'title':'Followup'},
			        {'id':'Closed', 'title':'Closed'}
			      ];
			    def.resolve(docType);
			    return def;
			};
			$rootScope.tripStatusTypes = function ($rootScope) 
			{
			    var def = $q.defer();
			    var docType = [
			        {'id':'Pending', 'title':'Pending'},
			        {'id':'Approved', 'title':'Approved'},
			        {'id':'Refund', 'title':'Refund'},
			        {'id':' Partial Refund', 'title':' Partial Refund'}
			      ];
			    def.resolve(docType);
			    return def;
			};
			$rootScope.voucherStatusTypes = function ($rootScope) 
			{
			    var def = $q.defer();
			    var docType = [
			        {'id':'Available', 'title':'Available'},
			        {'id':'Not Available', 'title':'Not Available'},
			      ];
			    def.resolve(docType);
			    return def;
			};
			
			$rootScope.paymentStatusTypes = function ($rootScope) 
			{
			    var def = $q.defer();
			    var docType = [
			        {'id':'Pending', 'title':'Pending'},
			        {'id':'Success', 'title':'Success'},
			      ];
			    def.resolve(docType);
			    return def;
			};
			return {
				scope: true,
		        restrict: 'E',
				link: function (scope, element, attr) {
					element = element,
					paginationStyle = attr.paginationStyle;
					var finalFilterObj = {},
						finalSortObj = {};
					var tableTag = element.find('table');
					if(attr.defaultFilter){
						var defaultFilterAttr = attr.defaultFilter.split(',');
						var defaultFilterLength = defaultFilterAttr.length;
						for(var i=0; i<defaultFilterLength; i++){
							var singleFilters = defaultFilterAttr[i].split('=');
							finalFilterObj[singleFilters[0]] = singleFilters[1];
						}
					}
					if(attr.defaultSort){
						var defaultSortAttr = attr.defaultSort.split(',');
						var defaultSortLength = defaultSortAttr.length;
						for(var j=0; j<defaultSortLength; j++){
							var singleSortField = defaultSortAttr[j].split('=');
							finalSortObj[singleSortField[0]] = singleSortField[1];
						}
					}
					//console.log(finalFilterObj);
					scope[tableTag.attr('ng-table')] = new ngTableParams({
					        page: 1,
					        total: 1, // value less than count hide pagination
					        count: 10,
					        sorting: finalSortObj,
					        filter: finalFilterObj
					    }, {
					    	getData: function ($defer, params) {
					    		var startIndexStr, endIndexStr = 0, service = attr.service;
					    		if(scope[tableTag.attr('ng-table')] .page() == 1)
					    			startIndexStr = 0;
					    		else
					    			startIndexStr = (scope[tableTag.attr('ng-table')] .page()-1)*scope[tableTag.attr('ng-table')] .count();
					    		//params.filter()['approvedStatus'] = 'Pending';
					    		var postdata = 'searchFieldsStr='+JSON.stringify(params.filter())+'&';
					    		postdata = postdata + 'sortValueStr='+JSON.stringify(params.sorting())+'&';
					    		postdata = postdata+'sortTypeStr=&filterType=&startIndexStr='+startIndexStr+'&endIndexStr='+scope[tableTag.attr('ng-table')] .count();
								$http.post(service,postdata).success(function(result) {									
					                scope.data = result;
					                if (paginationStyle){ // pagination style from template
										scope[tableTag.attr('ng-table')] .settings().counts = paginationStyle.split(",");
									}
									if (result.listData){										
										params.total(result.TotalRecords);	
									}
									/*if(result.Status != 'Ok'){
										 scope.data.success=false;
										 scope.data.successmessage=result.message;
									}*/
									
									$defer.resolve(result);
					            });
					        }
					    });
						
						 scope.$watch('data.length', function(newx, oldx) {
					        if (newx != null && newx !== oldx && newx > 0) {
					        	scope[tableTag.attr('ng-table')].reload();					        	
					        }
					        
					    });
					    
					    scope.$watch('refresh', function(newVal, oldVal) {
					    	//console.log(newVal);
					    	if(newVal != undefined){
					    		scope[tableTag.attr('ng-table')].reload();
					    	}
					    });
					    
					    scope.serversidegridDateFilter = function(field, startDate, endDate){
					    	startDate=(startDate && startDate.indexOf('/') != -1) ? startDate : '';
					    	endDate=(endDate && endDate.indexOf('/') != -1) ? endDate : '';
					    	//console.log(startDate +"--"+ endDate);
					    	if(startDate && endDate){
					    		var validFromDate = startDate.split('/');
					    		var validToDate = endDate.split('/');
					    		var fromDate = new Date(validFromDate[2],validFromDate[1],validFromDate[0]); //Year, Month, Date					    		
					    		var toDate = new Date(validToDate[2],validToDate[1],validToDate[0]);    		
					    		
					    		if(fromDate > toDate){
					    			alert('From date must be less than or equal to To date.');
					    		}else{
					    			startDate = validFromDate[0]+'-'+validFromDate[1]+'-'+validFromDate[2];
					    			endDate = validToDate[0]+'-'+validToDate[1]+'-'+validToDate[2];
					    			scope.tableParams.filter()[field] = startDate+'#'+endDate;
					    		}    		
					    		
					    	}else if(startDate == '' && endDate){
				    			alert('Please choose From date.')
				    		}else if(startDate && endDate == ''){
				    			alert('Please choose To date.')
				    		}else{
					    		delete scope.tableParams.filter()[field];
					    	}
					    	
					    }
					    scope.dateFormat = function(date){
					    	var dd = date.getDate();
			    		    var mm = date.getMonth()+1; //January is 0!
			    		    var yyyy = date.getFullYear();
			    		    return dd+'-'+mm+'-'+yyyy;
					    }
				}
		    }
	});
	
//<--------------------    update the scope value for browser auto filled data   ------------------------->

	app.directive("autofill", function () {
	    return {
	        require: "ngModel",
	        link: function (scope, element, attrs, ngModel) {
	            scope.$on("autofill:update", function() {
	                ngModel.$setViewValue(element.val());
	            });
	        }
	    }
	});
	
	app.directive('uiSelectRequired', function() {
		  return {
		    require: 'ngModel',
		    link: function(scope, elm, attrs, ctrl) {
		      ctrl.$validators.uiSelectRequired = function(modelValue, viewValue) {

		        var determineVal;
		        if (angular.isArray(modelValue)) {
		          determineVal = modelValue;
		        } else if (angular.isArray(viewValue)) {
		          determineVal = viewValue;
		        } else {
		          return false;
		        }

		        return determineVal.length > 0;
		      };
		    }
		  };
		});
	
	app.directive('validDate', function ($filter, $window, $parse, $timeout) {
		return {
		    require: '?ngModel',
		    restrict: 'A',
		    compile: function () {
		        var moment = $window.moment;
		        var getter, setter;
		        return function (scope, element, attrs, ngModel) {
		            //Declaring the getter and setter
		            getter = $parse(attrs.ngModel);
		            setter = getter.assign;
		            //Set the initial value to the View and the Model
		            ngModel.$formatters.unshift(function (modelValue) {
		                if (!modelValue) return "";
		                var retVal = $filter('date')(modelValue, "dd/MM/yyyy");
		                setter(scope, retVal);
		                //console.log('Set initial View/Model value from: ' + modelValue + ' to ' + retVal);
		                return retVal;
		            });		           
		         };
		      }
		  }; });
	
	base64 = new Nibbler({
	    dataBits: 8,
	    codeBits: 6,
	    keyString: 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/',
	    pad: '='
	});
	
})();

