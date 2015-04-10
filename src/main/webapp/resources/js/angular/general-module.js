/**
 Here are js arrays and objects used for filling drop-down lists, radio buttons, check boxes, multiple select which are generally
 used.
 */

	(function() {
	var app = angular.module('generalContent',['ui.date']);
	
	app.controller("genderController", function() {
		this.gender=genders;
	});
	
	var genders=["Male","Female","Other"];
	
	app.directive('genderTag', function(){
	    return{
	      restrict: 'E',
	      templateUrl: 'resources/js/angular/directives/gender.jsp',
	      controller: function(){
	    	  this.gender=genders;
	      },
	      controllerAs:'genderController'
	    };
	  });
	
	app.directive("departmentMaster", function() {
	    return {
	      restrict: 'E',
	      templateUrl: "resources/js/angular/directives/DepartmentMaster.jsp"
	    };
	  });
	
	app.directive("designationMaster", function() {
	    return {
	      restrict: 'E',
	      templateUrl: "resources/js/angular/directives/DesignationMaster.jsp"
	    };
	  });
	
	app.directive("userroleMaster", function() {
	    return {
	      restrict: 'E',
	      templateUrl: "resources/js/angular/directives/UserRoleMaster.jsp"
	    };
	  });
	
	app.directive("userroleAccess", function() {
	    return {
	      restrict: 'E',
	      templateUrl: "resources/js/angular/directives/UserRoleAccess.jsp"
	    };
	  });
	
	app.directive("packageDetail", function() {
	    return {
	      restrict: 'E',
	      templateUrl: "resources/js/angular/directives/PackageDetail.jsp"
	    };
	  });
	
	app.directive("packageActivity", function() {
	    return {
	      restrict: 'E',
	      templateUrl: "resources/js/angular/directives/PackageActivity.jsp"
	    };
	  });
	
	app.directive("packagePrice", function() {
	    return {
	      restrict: 'E',
	      templateUrl: "resources/js/angular/directives/PackagePrice.jsp"
	    };
	  });
	
	
	app.directive("packageFile", function() {
	    return {
	      restrict: 'E',
	      templateUrl: "resources/js/angular/directives/PackageFile.jsp"
	    };
	  });
	
	app.directive("packageHotel", function() {
	    return {
	      restrict: 'E',
	      templateUrl: "resources/js/angular/directives/PackageHotel.jsp"
	    };
	  });
	
	app.directive("packageAttraction", function() {
	    return {
	      restrict: 'E',
	      templateUrl: "resources/js/angular/directives/PackageAttraction.jsp"
	    };
	  });
	
	app.directive("packageCondition", function() {
	    return {
	      restrict: 'E',
	      templateUrl: "resources/js/angular/directives/PackageCondition.jsp"
	    };
	  });
	
	app.directive("locationTab", function() {
	    return {
	      restrict: 'E',
	      templateUrl: "resources/js/angular/directives/location.jsp"
	    };
	  });
	
	app.directive("multiLocation", function() {
	    return {
	      restrict: 'E',
	      templateUrl: "resources/js/angular/directives/multiLocation.jsp"
	    };
	  });
	
	app.directive('ngEnter', function () {
	    return function (scope, element, attrs) {
	        element.bind("keydown keypress", function (event) {
	            if(event.which === 13) {
	                scope.$apply(function (){
	                    scope.$eval(attrs.ngEnter);
	                });
	 
	                event.preventDefault();
	            }
	        });
	    };
	});
	
	app.directive('ngUp', function () {
	    return function (scope, element, attrs) {
	        element.bind("keydown keypress", function (event) {
	            if(event.which === 38) {
	                scope.$apply(function (){
	                    scope.$eval(attrs.ngUp);
	                });
	 
	                event.preventDefault();
	            }
	        });
	    };
	});
	
	app.directive('ngDown', function () {
	    return function (scope, element, attrs) {
	        element.bind("keydown keypress", function (event) {
	            if(event.which === 40) {
	                scope.$apply(function (){
	                    scope.$eval(attrs.ngDown);
	                });
	 
	                event.preventDefault();
	            }
	        });
	    };
	});
	
	app.directive('ngBackspace', function () {
	    return function (scope, element, attrs) {
	        element.bind("keydown keypress", function (event) {
	            if(event.which === 8) {
	                scope.$apply(function (){
	                    scope.$eval(attrs.ngDown);
	                });
	 
	                event.preventDefault();
	            }
	        });
	    };
	});
	
	app.directive('ngFocus', ['$parse', function($parse) {
		  return function(scope, element, attr) {
		    var fn = $parse(attr['ngFocus']);
		    element.bind('focus', function(event) {
		      scope.$apply(function() {
		        fn(scope, {$event:event});
		      });
		    });
		  }
		}]);
		 
		app.directive('ngBlur', ['$parse', function($parse) {
		  return function(scope, element, attr) {
		    var fn = $parse(attr['ngBlur']);
		    element.bind('blur', function(event) {
		      scope.$apply(function() {
		        fn(scope, {$event:event});
		      });
		    });
		  }
		}]);
		
		app.directive('ngReallyClick', [function() {
		    return {
		        restrict: 'A',
		        link: function(scope, element, attrs) {
		            element.bind('click', function() {
		                var message = attrs.ngReallyMessage;
		                if (message && confirm(message)) {
		                    scope.$apply(attrs.ngReallyClick);
		                }
		            });
		        }
		    }
		}]);
		
		app.directive('customDatepicker',function($compile){
	        return {
	            replace:true,
	            templateUrl:'custom-datepicker.html',
	            scope: {
	                ngModel: '=',
	                dateOptions: '=',
	                placeholder: '@'
	            },
	            link: function($scope, $element, $attrs, $controller){
	                var $button = $element.find('button');
	                var $input = $element.find('input');
	                $button.on('click',function(){
	                    if($input.is(':focus')){
	                        $input.trigger('blur');
	                    } else {
	                        $input.trigger('focus');
	                    }
	                });
	            }    
	        };
	    });

	
})();
	
	/*global angular */
	/*
	 jQuery UI Datepicker plugin wrapper

	 @note If ≤ IE8 make sure you have a polyfill for Date.toISOString()
	 @param [ui-date] {object} Options to pass to $.fn.datepicker() merged onto uiDateConfig
	 */

	angular.module('ui.date', [])

	.constant('uiDateConfig', {})

	.directive('uiDate', ['uiDateConfig', '$timeout', function (uiDateConfig, $timeout) {
	  'use strict';
	  var options;
	  options = {};
	  angular.extend(options, uiDateConfig);
	  return {
	    require:'?ngModel',
	    link:function (scope, element, attrs, controller) {
	      var getOptions = function () {
	        return angular.extend({}, uiDateConfig, scope.$eval(attrs.uiDate));
	      };
	      var initDateWidget = function () {
	        var showing = false;
	        var opts = getOptions();

	        // If we have a controller (i.e. ngModelController) then wire it up
	        if (controller) {

	          // Set the view value in a $apply block when users selects
	          // (calling directive user's function too if provided)
	          var _onSelect = opts.onSelect || angular.noop;
	          opts.onSelect = function (value, picker) {
	            scope.$apply(function() {
	              showing = true;
	              controller.$setViewValue(element.datepicker("getDate"));
	              _onSelect(value, picker);
	              element.blur();
	            });
	          };
	          opts.beforeShow = function() {
	            showing = true;
	          };
	          opts.onClose = function(value, picker) {
	            showing = false;
	          };
	          element.on('blur', function() {
	            if ( !showing ) {
	              scope.$apply(function() {
	                element.datepicker("setDate", element.datepicker("getDate"));
	                controller.$setViewValue(element.datepicker("getDate"));
	              });
	            }
	          });

	          // Update the date picker when the model changes
	          controller.$render = function () {
	            var date = controller.$viewValue;
	            if ( angular.isDefined(date) && date !== null && !angular.isDate(date) ) {
	              throw new Error('ng-Model value must be a Date object - currently it is a ' + typeof date + ' - use ui-date-format to convert it from a string');
	            }
	            element.datepicker("setDate", date);
	          };
	        }
	        // If we don't destroy the old one it doesn't update properly when the config changes
	        element.datepicker('destroy');
	        // Create the new datepicker widget
	        element.datepicker(opts);
	        if ( controller ) {
	          // Force a render to override whatever is in the input text box
	          controller.$render();
	        }
	      };
	      // Watch for changes to the directives options
	      scope.$watch(getOptions, initDateWidget, true);
	    }
	  };
	}
	])

	.constant('uiDateFormatConfig', '')

	.directive('uiDateFormat', ['uiDateFormatConfig', function(uiDateFormatConfig) {
	  var directive = {
	    require:'ngModel',
	    link: function(scope, element, attrs, modelCtrl) {
	      var dateFormat = attrs.uiDateFormat || uiDateFormatConfig;
	      if ( dateFormat ) {
	        // Use the datepicker with the attribute value as the dateFormat string to convert to and from a string
	        modelCtrl.$formatters.push(function(value) {
	          if (angular.isString(value) ) {
	            return jQuery.datepicker.parseDate(dateFormat, value);
	          }
	          return null;
	        });
	        modelCtrl.$parsers.push(function(value){
	          if (value) {
	            return jQuery.datepicker.formatDate(dateFormat, value);
	          }
	          return null;
	        });
	      } else {
	        // Default to ISO formatting
	        modelCtrl.$formatters.push(function(value) {
	          if (angular.isString(value) ) {
	            return new Date(value);
	          }
	          return null;
	        });
	        modelCtrl.$parsers.push(function(value){
	          if (value) {
	            return value.toISOString();
	          }
	          return null;
	        });
	      }
	    }
	  };
	  return directive;
	}]);	
