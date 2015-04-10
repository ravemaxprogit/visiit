app.config(function($stateProvider,$urlRouterProvider, $httpProvider) {
	  if(!window.sessionStorage["userInfo"]){
		$urlRouterProvider.otherwise("/login")
	  }else{
		$urlRouterProvider.otherwise("/dashboard")
	  }
});