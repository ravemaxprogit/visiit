'use strict';
var manageUsers = angular.module('manageUsers', [ 
        'ui.router', 
        'validation',
		'validation.rule'
]);


//Routers
manageUsers.config(function($stateProvider,$urlRouterProvider) {
  $stateProvider.state('manageUsers', {
	  url: '/masters/manageUsers',
      templateUrl: 'resources/views/masters/manageUsers/manageUsers_grid.html',
      data: {
          auth: true
      },
      controller: 'manageUsersController'
  });
  
});


manageUsers.factory('manageUsersServices', ['$http', '$rootScope',
    function ($http) {
        var factoryDefinitions = {
            getUsers: function () {
                return $http.get('getAllUsers').success(function (data) {
                    return data;
                });
            },
            getUserDetails: function (userId) {
                return $http.get('getUser?uSeqId='+userId).success(function (data) {
                    return data;
                });
            },
            saveUserDetails: function (postdata) {
	        	 return $http.post("saveUser", postdata);
	        },
	        getAllModules: function () {
                return $http.get('moduleList').success(function (data) {
                    return data;
                });
            },
            getSubModulesByModuleIds: function (ids) {
                return $http.get('getSubModulesByModuleIds?moduleIds='+ids).success(function (data) {
                    return data;
                });
            },
            deleteUserById: function (userSeqId) {
		    	   return $http.post('deleteUser','userSeqId='+userSeqId).success(function (data) {
	                    return data;
	                });;
		       }
        }
        return factoryDefinitions;
    }
]);

//dialog controller for manage users page
manageUsers.controller('manageUsersController', ['$scope', '$rootScope', "$sce", '$http', '$state','$location','$filter', 'ngDialog','$injector','manageUsersServices','departmentServices','designationServices','$timeout', function ($scope, $rootScope, $sce, $http, $state,$location,$filter, ngDialog,$injector,manageUsersServices,departmentServices,designationServices,$timeout) {
	$scope.usersList = {};
	$scope.user = {};
	//Filter
	var $validationProvider = $injector.get('$validation');
	
	$scope.isProcessingfalse = function() {
		 $timeout(function() {
			$scope.isProcessing = false;
			}, 1000);
	};
		
	$scope.user_search = function (obj) {
		 var re = new RegExp($scope.u_search, 'i');
	        return !$scope.u_search || re.test(obj.userFirstName) || re.test(obj.userCode.toString()) || re.test(obj.userId.toString());
    };
	
	//cancel - close dialog
	$scope.cancel = function()
	{
		ngDialog.close();
	}
	//clear - clear dialog
	$scope.clear = function(){
  	  $scope.user.userIsActive=!$scope.user.userIsActive;
	  $scope.user.userDsgId = "";
	  $scope.user.userDeptId = "";
	}
	//clear - clear dialog
	$scope.clear1 = function(form){
	  console.log(form);
  	  $scope.user.userIsActive=!$scope.user.userIsActive;
	  $scope.user.userDsgId = "";
	  $scope.user.userDeptId = "";
	  delete $scope.user.userDsgId;
	  delete $scope.user.userDeptId;
	  //$scope.form.$setPristine();
	 // form.$setPristine(true);
	  $(".error").each(function(){
		  
		 $(this).remove(); 
	  });
	  //$validationProvider.reset(form);
	}
	//get users list
	manageUsersServices.getUsers().then(function (response) {
		$scope.usersGrid = response.data;		
    });
	
	// load add user form and edit with user details
	$scope.userAdd = function(userId){	
		$scope.user = {};
		$scope.mangeuser = {};
		$scope.user.modules=[];
		$scope.subModules=[];
		$scope.user.subModules = [];
		$scope.saveUserResponse = {};
		$scope.isProcessing = true;
		if(userId !='' && userId != undefined){
			$rootScope.showLoader();
			manageUsersServices.getUserDetails(userId).then(function (response) {
				$scope.user = response.data.User;
				$scope.user.modules=$scope.user.userModules;
				$scope.user.subModules = $scope.user.userSubModules;
				var moduleIds=[];
				angular.forEach($scope.user.userModules, function(value, key) {
					moduleIds.push(value.modId);
			    });
				moduleIds=moduleIds.toString();
				if(moduleIds){
					manageUsersServices.getSubModulesByModuleIds(moduleIds).then(function(response) {
						if(response.data.Status.toLowerCase() == 'ok'){
							$scope.subModules = response.data.subModuleList;
						}
					});
				}
				departmentServices.getDepartment().then(function (response) {
					$scope.mangeuser.departments = response.data;
					designationServices.getAllDesignations().success(function (response) {
						$scope.mangeuser.designations = response;
						manageUsersServices.getAllModules().then(function (response) {	
							$scope.modules = response.data.moduleList;	
							$rootScope.hideLoader();
							ngDialog.open({
					 			template: 'resources/views/masters/manageUsers/addUsersDetails.html', 
					  		   	className: 'ngdialog-theme-default',
					  		   	scope: $scope
					 		});
							$scope.isProcessingfalse();
						});
				   	});
				});
		    });
			
		}else{
			// alert($scope.isProcessing);
			$rootScope.showLoader();
			$scope.user = {};
			$scope.mangeuser = {};
			departmentServices.getDepartment().then(function (response) {
				$scope.mangeuser.departments = response.data;
				designationServices.getAllDesignations().success(function (response) {
					$scope.mangeuser.designations = response;	
					manageUsersServices.getAllModules().then(function (response) {
						$scope.modules = response.data.moduleList;
						$scope.user.modules=[];
						$scope.subModules=[];
						$scope.user.subModules = [];
					//	manageUsersServices.getSubModulesByModuleIds().then(function (response) {
							//$scope.subModules = response.data.subModulesList;	
						 $scope.isProcessing = true;
						 ngDialog.open({
					 			template: 'resources/views/masters/manageUsers/addUsersDetails.html', 
					  		   	className: 'ngdialog-theme-default',
					  		   	scope: $scope
							});
						 $rootScope.hideLoader();
						 $scope.isProcessingfalse();
					//	});
					});
				 });
			});
			//alert($scope.isProcessing);
		}		 
		
	}	
	
	//save user
	$scope.userSave = function(){
		$scope.user.userModules = $scope.user.modules;
		$scope.user.userSubModules = $scope.user.subModules;
		var FormtduserDoj = $scope.user.userDoj.split("/");
		FormtduserDoj = new Date(FormtduserDoj[2], FormtduserDoj[1]- 1, FormtduserDoj[0]);
		FormtduserDoj = $filter('date')(FormtduserDoj, 'yyyy-MM-dd');
		$scope.user.userDoj = FormtduserDoj;
		delete  $scope.user.modules;
		delete  $scope.user.subModules;
		var postdata = "userStr="+angular.toJson($scope.user);
		$rootScope.showLoader();
		manageUsersServices.saveUserDetails(postdata).then(function (response) {
			if(response.data.Status == 'Error'){
				$scope.saveUserResponse = response.data;
				$scope.message ={
						'success':0,
						'error':1
					};
				$rootScope.hideLoader();
			}
			if(response.data.Status == 'Ok'){
				$scope.userResponse = response.data;
				$scope.message ={
					'success':1,
					'error':0
				};
				//update scope
				manageUsersServices.getUsers().then(function (response) {
					$scope.usersGrid = response.data;
					$rootScope.hideLoader();
			    }); 
				ngDialog.close();
			}
	    }); 
	}
	//delete user
	$scope.deleteUser = function(userSeqId){
		var result = confirm("Are you sure want to delete?");
		if (result==true) {
			$rootScope.showLoader();
			manageUsersServices.deleteUserById(userSeqId).then(function (response) {
				$scope.userResponse = response.data;
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
					manageUsersServices.getUsers().then(function (response) {
						$scope.usersGrid = response.data;
						$rootScope.hideLoader();
				    });	 
				}
			});
		}
		
	}
	
}]);

manageUsers.directive('multiSelect', function($q, manageUsersServices) {
	return {
		restrict: 'E',
		require: 'ngModel',
		scope: {
			selectedLabel: "@",
			availableLabel: "@",
			displayAttr: "@",
			available: "=",
			model: "=ngModel",
			subModules: "=subModules",
			userSubModules: "=userSubModules",			
			serviceCall: "@",
			validate:"@"
		},
		template: '<div class="multiSelect">' + '<div class="select"><select  ng-model="selected.available" multiple ' + 'class="pull-left" ng-options="e as e[displayAttr] for e in available">' + '</select>' + '</div>' + '<div class="select buttons" style="margin-top:1%;">' + '<button class="btn mover right" ng-click="add()" title="Add selected" ' + 'ng-disabled="selected.available.length == 0">' + '<i class="icon-arrow-right2"></i>' + '</button>' + '<button class="btn mover left" ng-click="remove()" title="Remove selected" ' + 'ng-disabled="selected.current.length == 0">' + '<i class="icon-arrow-left"></i>' + '</button>' + '</div>' + '<div class="select"><select name="available{{selectedLabel}}"  ng-model="selected.current" multiple ' + 'ng-options="e as e[displayAttr] for e in model"></select>' + '</div>' + '</div>',
		link: function(scope, elm, attrs) {
			scope.selected = {
				available: [],
				current: []
			};

			/* Handles cases where scope data hasn't been initialized yet */
			var dataLoading = function(scopeAttr) {
				var loading = $q.defer();
				if (scope[scopeAttr]) {
					loading.resolve(scope[scopeAttr]);
				} else {
					scope.$watch(scopeAttr, function(newValue, oldValue) {
						if (newValue !== undefined) loading.resolve(newValue);
					});
				}
				return loading.promise;
			};

			/* Filters out items in original that are also in toFilter. Compares by reference. */
			var filterOut = function(original, toFilter) {
				var filtered = [];
				angular.forEach(original, function(entity) {
					var match = false;
					for (var i = 0; i < toFilter.length; i++) {
						if (toFilter[i][attrs.displayAttr] == entity[attrs.displayAttr]) {
							match = true;
							break;
						}
					}
					if (!match) {
						filtered.push(entity);
					}
				});
				return filtered;
			};

			scope.refreshAvailable = function() {
				scope.available = filterOut(scope.available, scope.model);
				scope.selected.available = [];
				scope.selected.current = [];
			};

			scope.add = function() {
				if (scope.serviceCall == 'true') {
					var moduleIds=[];
					angular.forEach(scope.selected.available, function(value, key) {
						moduleIds.push(value.modId);
				    });
					moduleIds=moduleIds.toString();
					manageUsersServices.getSubModulesByModuleIds(moduleIds).then(function(response) {
						if(response.data.Status.toLowerCase() == 'ok'){
							scope.userSubModules = scope.userSubModules.concat(response.data.subModuleList);
						}
					});
					
				}

				scope.model = scope.model.concat(scope.selected.available);
				scope.refreshAvailable();

			};
			scope.remove = function() {
				//console.log(scope.selected.current);
				if (scope.serviceCall == 'true') {
					var subModuleIds=[];
					angular.forEach(scope.selected.current, function(value, key) {
						subModuleIds.push(value.modId);
				    });
					subModuleIds=subModuleIds.toString();
					manageUsersServices.getSubModulesByModuleIds(subModuleIds).then(function(response) {
						if(response.data.Status.toLowerCase() == 'ok'){
							var subModulesList = (response && response.data && response.data.subModuleList) ? response.data.subModuleList : [];
							scope.subModules = scope.removeSubSelection(scope.subModules, subModulesList);						
							scope.userSubModules = scope.removeSubSelection(scope.userSubModules, subModulesList);
						}

					});
				}

				scope.available = scope.available.concat(scope.selected.current);
				scope.model = filterOut(scope.model, scope.selected.current);
				scope.refreshAvailable();

			};

			$q.all([dataLoading("model"), dataLoading("available")]).then(function(results) {
				scope.refreshAvailable();
			});

			scope.removeSubSelection = (function(parentObj, childObj) {

				var parentIds = {};
				var childIds = {};
				var result = [];


				parentObj.forEach(function(el, i) {
					parentIds[el.modId] = parentObj[i];
				});
				//console.log(parentIds);

				childObj.forEach(function(el, i) {
					childIds[el.modId] = childObj[i];
				});
				//console.log(childIds);

				for (var i in parentIds) {
					if (!childIds.hasOwnProperty(i)) {
						result.push(parentIds[i]);
					}
				}
				//console.log(result);
				return result;
			});

		}
	};
});
