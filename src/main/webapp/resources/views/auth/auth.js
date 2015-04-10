'use strict';
var auth = angular.module('auth', ['ui.router', 'validation', 'validation.rule','ngCookies']);

//Routers
auth.config(function($stateProvider) {
  //Login
  $stateProvider.state('login', {
    url: '/login',
    views: {
        "loginPage": {
          templateUrl: 'resources/views/auth/login.html'
        }
    }	 
 });
  
 //Reset password
 $stateProvider.state('resetpassword', {
    url: '/resetpassword',
    views: {
        "resetPasswordPage": {
          templateUrl: 'resources/views/auth/resetPassword.html',
          controller: 'resetPasswordController'
        }
    },
    resolve: {
        getEmailId: function($rootScope, $state) {
	          if($rootScope.email){
	        	  return $rootScope.email;
	          }else{
	        	  return false;
	          }
        }
    }
 });  
 
//Change password
 $stateProvider.state('forgotPassword', {
     url: '/forgotPassword',
     views: {
         "changePasswordPage": {
           templateUrl: 'resources/views/auth/changePassword.html',
           controller: 'changePasswordController'
         }
     }	        
 }); 
  
  //Signup
  $stateProvider.state('register', {
	url: "/register",
	views: {
        "registerPage": {
          templateUrl: 'resources/views/auth/register.html'
          //controller: 'registerController'
        }
    }	 
  });
  
  //Logout
  $stateProvider.state('logout', {
	url: "/logout",
	template: "<h3>Logging out...</h3>",
	 data: {
         auth: true
     },
    controller: 'logoutController'
  });
  
});

//Factories for user services
app.factory('userServices', ['$http', '$rootScope',
    function ($http) {
        var url = "";
        var factoryDefinitions = {
            getLogin: function (loginData) {
                return $http.post('login',loginData).success(function (data) {
                    return data;
                });
            }
        }
        return factoryDefinitions;
    }
]);

//Factories
auth.factory('userServices', ['$http', function($http) {

    var factoryDefinitions = {
      getLogin: function(loginData) {
        return $http.post('login', loginData).success(function(data) { return data; });
      },
      changePassword: function(data) {
          return $http.post('forgotpassword', data).success(function(data) { return data; });
      },
      resetPassword: function(data) {
          return $http.post('changepassword', data).success(function(data) { return data; });
      },
	  register: function(registerData) {
        return $http.post('register', registerData).success(function(data) { return data; });
      }
	}
	
    return factoryDefinitions;
  }
]);
	  
//Controllers
//user controller for login
auth.controller('userController', ['$scope', '$rootScope', "$sce", '$http', '$state','$location', 'userServices', '$cookies','$injector', function ($scope, $rootScope, $sce, $http, $state,$location, userServices,$cookies,$injector) {
	$scope.login = {"username":"", "password":""};
	$scope.message ={
			'success':0,
			'error':0
		};
	$scope.footerDate = new Date();
	
	var $validationProvider = $injector.get('$validation');
	
	$scope.clearerrmsg = function(){
		$scope.message ={
				'success':0,
				'error':0
			};
	}
	$scope.checkLogin = function(formname){
		$scope.$broadcast("autofill:update");
		var salt = uid();
		var original_password = $scope.login.password;
		var salt_1 = MD5(original_password);
		var salt_2 = salt_1+salt;
		var final_hash = MD5(salt_2);
		var loginData = 'userName='+$scope.login.username+'&password='+final_hash+'&skey='+salt;
		userServices.getLogin(loginData).then(function (response) {
			$scope.user = response.data;
			if(response.data.Status == 'Error'){
				
				if(response.data.message == 'Your password has been expired'){
					$rootScope.email = $scope.login.username;
					$state.go('resetpassword');
				}else{
					$scope.message ={
							'success':0,
							'error':1
						};
				}
			}
			if(response.data.Status == 'Ok'){
				//$cookieStore.put('userDetails',$scope.user);
				setTimeout(function(){ 
					//console.log($cookies);
					$rootScope.userDetails = $cookies;
						
					//Convert string to object for ACL data
					var userModulesACL = $cookies.userModuleNames;
					
					userModulesACL = userModulesACL.split('"').join('');
					userModulesACL = userModulesACL.split('+').join(' ');
					userModulesACL = userModulesACL.split(",");
					//console.log(userModulesACL);
					var userSubModuleNamesACL = $cookies.userSubModuleNames;
					userSubModuleNamesACL = userSubModuleNamesACL.split('"').join('');
					userSubModuleNamesACL = userSubModuleNamesACL.split('+').join(' ');
					userSubModuleNamesACL = userSubModuleNamesACL.split(",");
					//console.log(userSubModuleNamesACL);
					var userModules = [];
					userModules = userModules.concat(userModulesACL, userSubModuleNamesACL);
					//console.log(userModules.toString());
					
					//Assign to rootscope for checking ACL in template level
					$rootScope.acl = {"userModules":userModules}; 

					$state.go('dashboard');
				}, 500);
				//console.log($cookies);
				
				//$rootScope.acl = {"userModules":$scope.user.userModules.concat($scope.user.userSubModules)}
				//window.sessionStorage["userInfo"] = $scope.user.firstName;	            
	            //$rootScope.acl = {"userModules":$scope.user.userModules.concat($scope.user.userSubModules)}
	            //window.sessionStorage["aclSession"] = JSON.stringify($rootScope.acl);
	            
			}    
			$validationProvider.reset(formname);
	    });

	}	   
}]);

//Change Password
auth.controller('changePasswordController', ['$scope', 'userServices', function($scope, userServices) {

	$scope.changePassword = {"email":""};
	$scope.doChangePassword = function() {
		$scope.data = {};
				
		if ($scope.changePasswordForm.$valid) {	
			var data = 'custEmail='+$scope.changePassword.email;
			userServices.changePassword(data).then(function(response){
				$scope.data = response.data;
				if(response.data.Status != 'Error'){
					$scope.data.success = true;					
				}else{
					$scope.data.success = false;
				}
			});	
		}
	};
}]);

//Reset password
auth.controller('resetPasswordController', ['$scope', '$rootScope','$location','$state', 'userServices', 'getEmailId','$cookies', function ($scope, $rootScope, $location, $state, userServices, getEmailId,$cookies) {
	
	if(!getEmailId){
		$state.go('login');
	}
	
	$scope.resetPassword = {"email":getEmailId};
		
	$scope.doResetPassword = function() {
		$scope.data = {};
				
		if ($scope.resetPasswordForm.$valid) {	
			var salt = uid();
			var original_password = $scope.resetPassword.oldPwd;
			var salt_1 = MD5(original_password);
			var salt_2 = salt_1+salt;
			var final_hash = MD5(salt_2);
			//$scope.encoded = $base64.encode($scope.resetPassword.newPwd);
			var data = 'userName='+$scope.resetPassword.email+'&oldPassword='+final_hash+'&password='+base64.encode($scope.resetPassword.newPwd)
						+'&confirmPassword='+base64.encode($scope.resetPassword.cnfPwd)+'&skey='+salt;

			userServices.resetPassword(data).then(function(response){
				$scope.data = response.data;
				if(response.data.Status != 'Error'){
					$scope.data.success = true;		
					/*$cookieStore.put('userDetails',$scope.data);
					$rootScope.userDetails = $cookies.get('userDetails');
					$rootScope.acl = {"userModules":$scope.data.userModules.concat($scope.data.userSubModules)}*/
					
					setTimeout(function(){ 
						$rootScope.userDetails = $cookies;
										
						//Convert string to object for ACL data
						var userModulesACL = $cookies.userModuleNames;
						
						userModulesACL = userModulesACL.split('"').join('');
						userModulesACL = userModulesACL.split('+').join(' ');
						userModulesACL = userModulesACL.split(",");
						//console.log(userModulesACL);
						
						var userSubModuleNamesACL = $cookies.userSubModuleNames;
						userSubModuleNamesACL = userSubModuleNamesACL.split('"').join('');
						userSubModuleNamesACL = userSubModuleNamesACL.split('+').join(' ');
						userSubModuleNamesACL = userSubModuleNamesACL.split(",");
						//console.log(userSubModuleNamesACL);
						
						var userModules = [];
						userModules = userModules.concat(userModulesACL, userSubModuleNamesACL);
						//console.log(userModules.toString());
						
						//Assign to rootscope for checking ACL in template level
						$rootScope.acl = {"userModules":userModules}; 

						$state.go('dashboard');
					}, 500);
					
					
		           // $state.go('dashboard');
				}else{
					$scope.data.success = false;
				}
			});	
		}
	};
	
}]);


//Logout
auth.controller('logoutController', ['$scope', '$rootScope','$location','$state', '$http','$cookieStore', function ($scope, $rootScope, $location, $state, $http,$cookieStore) {
	$http.get("logout").success(function(data) {
		
		$rootScope.userDetails = false;
		$rootScope.acl = false;
		$cookieStore.remove('userModuleIds');
		$cookieStore.remove('userSubModuleIds');
		$cookieStore.remove('userModuleNames');
		$cookieStore.remove('userSubModuleNames');
		$cookieStore.remove('firstName');
		$cookieStore.remove("userDetails");
		$state.go("login");
	});	
}]);