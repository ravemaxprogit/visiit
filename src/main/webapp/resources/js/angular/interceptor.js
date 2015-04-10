app.factory('sessionInterceptor', [function() {
        var checkSession = {
            request: function(config) {
                /*if (location.hash != "" && location.hash != "#/login" && !window.sessionStorage["userInfo"]) {
                    window.location = "";
                }*/
                return config;
            },
        };
        return checkSession;
    }]);
