(function() {
	var raServices = angular.module("raServices",['ngResource']);
	
	raServices.factory('employeeService', ['$resource',function($resource){
		return $resource('http://localhost:8080/Visiit/service/:call',{},{
			employees: {method:'get',params:{call:employees},isArray:true},
			employee: {method:'get',params:{call:employees},isArray:false}
		});
	}]);
	
})();

