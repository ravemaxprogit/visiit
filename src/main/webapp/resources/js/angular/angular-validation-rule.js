(function() {
    angular.module('validation.rule', ['validation'])
        .config(['$validationProvider',
            function($validationProvider) {

                var expression = {
                    required: function(value) {
                    	if(value==undefined)
                    		{
                    			return true;
                    		}
                    	else
                    		{
                    				return !!value;
                    		}
                    },
                    number: /^\d+$/,
                    alpha: /^[a-zA-Z]+$/,
                    email: /^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/,
                    validateUrl: /((([A-Za-z]{3,9}:(?:\/\/)?)(?:[-;:&=\+\$,\w]+@)?[A-Za-z0-9.-]+|(?:www.|[-;:&=\+\$,\w]+@)[A-Za-z0-9.-]+)((?:\/[\+~%\/.\w-_]*)?\??(?:[-\+=&;%@.\w_]*)#?(?:[\w]*))?)/,
                    validateEmail: /^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/,
                    validateNumber: /^\d+$/,
                    validateAlpha: /^[a-zA-Z]+$/,
                    validateAlphaSpace: /^[a-zA-Z\s]+$/,
                    validateAlphanum: /^[a-zA-Z0-9]+$/,
                    validateAlphanumSpace: /^[a-zA-Z0-9\s]+$/,
					validateAlphaspecial: /^[a-zA-Z.,]+$/,
					validateAlphanumspecial: /^[a-zA-Z0-9\s-*&()!@#$%^|\\/\:;?_+=.,`~'"]+$/,
					validateMobilenumspecial: /^[0-9\s-+()]+$/,
					validateFaxnumspecial: /^[0-9\s-+()]+$/,					
					validateNotEmpty: function(value){
						if(value.length==0){
							return false;
						}else{
							return true;
						}
					},
					validateNumberWithDollar: function(text){
						firstCharName=text.substring(0,1);
						totalCharName=text.substring(1,text.length);
						//firstCharName = firstCharName.ReplaceAll("$","");
						firstCharName = firstCharName.replace(/$/g,"");
						text=firstCharName+totalCharName;
						text = text.replace(/[,]/g,"");
						if (text.indexOf("(") != -1) {
							if (text.indexOf(")") != -1) {
								text = text.replace(/[(]/g,"-");
							} else {
								return true;
							}
						}
						text = text.replace(/[)]/g,"");
						var dot = text.indexOf(".");
						if (dot != -1) {
							var dotArr = text.split(".");
							if (dotArr.length >= 3) {
								text = dotArr[0] + "." +dotArr[1];
							}
						}
						if (text.match(/-/)) {
						  return true;
						 }
						if (!text.match(/^-?(?:\d+|\d{1,3}(?:,\d{3})+)(?:\.\d+)?$/)) {
							return true;
						}
						return false;
					}
                };

                var defaultMsg = {
                    required: {
                        error: 'This should be Required!!',
                        success: 'It\'s Required'
                    },
                    validateUrl: {
                        error: 'Please enter valid url',
                        success: 'It\'s Url'
                    },
                    validateEmail: {
                        error: 'Please enter valid email address',
                        success: 'It\'s Email'
                    },
                    validateNumber: {
                        error: 'Please enter numbers only',
                        success: 'It\'s Number'
                    },
                    validateMobilenumspecial: {
                        error: 'Please enter valid mobile number',
                        success: 'It\'s Mobile Number'
                    },
                    validateFaxnumspecial: {
                        error: 'Please enter valid fax number',
                        success: 'It\'s Fax Number'
                    },
                    validateAlpha: {
                        error: 'Please enter alphabets only',
                        success: 'It\'s alphabets'
                    },
                    validateAlphaSpace: {
                        error: 'Please enter alphabets only',
                        success: 'It\'s alphabets with space'
                    },
                    validateAlphanum: {
                        error: 'Please enter alphanumberic characters only',
                        success: 'It\'s alphanumberic'
                    },
                    validateAlphanumSpace: {
                        error: 'Please enter alphanumberic characters only',
                        success: 'It\'s alphanumberic with space'
                    },
                    validatePassword: {
                        error: 'Password',
                        success: 'It\'s password'
                    },
                    validateConfirmPassword: {
                        error: 'Password and Confirm Password are not same',
                        success: 'It\'s password and confirm password'
                    },
                    alpha: {
                        error: 'Please enter alphabets only',
                        success: 'It\'s alphabets'
                    },
                    number: {
                        error: 'Please enter numbers only',
                        success: 'It\'s Number'
                    },
                    email: {
                        error: 'Please enter valid email address',
                        success: 'It\'s Email'
                    }
                    
                };

                $validationProvider.setExpression(expression).setDefaultMsg(defaultMsg);
				
				$validationProvider.showSuccessMessage = false; // or true(default)
			    $validationProvider.showErrorMessage = true; // or true(default)
				
			    // Range max Validation
		            $validationProvider
		                .setExpression({
		                    maxval: function (value, scope, element, attrs) {
		                if (parseInt(value.length) <= parseInt(attrs.max)) 
		                	{
		                	return value;
		                        }
		                    }
		                })
		                .setDefaultMsg({
		                    maxval: {
		                        error: '',
		                        success: 'good'
		                    }
		                });
		           
		            $validationProvider
	                .setExpression({
	                    currentdatevalidation: function(value, scope, element, attrs) {
	                        var today = new Date(new Date().getFullYear(), new Date().getMonth(), new Date().getDate()).getTime();
	                        var getdate = new Date(value).getTime();
	                        		if(getdate >= today)
	                        		{
	                        			return value;
	                        		}
	                    }
	                })
	                .setDefaultMsg({
	                    currentdatevalidation: {
	                        error: 'Future Date only',
	                        success: 'Done'
	                    }
	                });
		             	          
			    
            			}
        		 ]);
    
 
   // Range min Validation
                

}).call(this);
