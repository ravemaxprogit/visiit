<html xmlns="http://www.w3.org/1999/xhtml">
<head>
   <title>.:: Visiit ::.</title>
    <link href="resources/css/bootstrap.min.css" rel="stylesheet"></link>
    <link href="resources/css/oversheet.css" rel="stylesheet" />
    <!-- <script src="resources/js/angularJS.js"></script>
    <script src="resources/js/angular/angular-validation.js"></script>
    <script src="resources/js/angular/angular-validation-rule.js"></script>
    <script src="resources/js/angular/angular_controller.js"></script>
     -->
    <script src="resources/js/jquery.min.js"></script>
    <script src="resources/js/jquery-ui.min.js"></script>
    <script src="resources/js/angular/angular.min.js"></script>
    <script src="resources/js/angular/angular-sanitize.min.js"></script>
    <!--<script src="resources/js/angular/angular-sanitize.min.js.map"></script> -->
    <script src="resources/js/angular/angular-animate.min.js"></script>
    <!-- <script src="resources/js/angular/angular-animate.min.js.map"></script> -->
    <script src="resources/js/angular/ngDialog.min.js"></script>
    <script src="resources/js/angular/select.js"></script>
    <script src="resources/js/angular/angular-file-upload.js"></script>
    <script src="resources/js/angular/angular-file-upload-shim.js"></script>
    <script src="resources/js/angular/ngDraggable.js"></script>
    <script src="resources/js/angular/angular-ui-router.min.js"></script>
    <script src="resources/js/angular/ng-table.min.js"></script>
    <script src="resources/js/angular/angular-validation.js"></script>
    <script src="resources/js/angular/angular-validation-rule.js"></script>
    <script src="resources/js/angular/md5.js"></script>
    <script src="resources/js/angular/app.js"></script>
    <script src="resources/js/angular/angular_controller.js"></script>
    <script src="resources/js/angular/interceptor.js"></script>
    <script src="resources/js/angular/general-module.js"></script>

    
    <style type="text/css">

        .bg {
            background-image:url('resources/images/login-bg.jpg');
            background-repeat:no-repeat;
            /*background-size:cover;*/
            background-position:top center;
            width:100%;
        }

        .validation-invalid
        {
        	color:red;
        }
        .login-div {
            width: 306px;
            min-height: 100px;
            margin: 10% auto;
            /*background-image: url('src/main/webapp/resources/images/login-bg-repeat.png');
            background-repeat: repeat;*/
            background-color: rgba(255, 255, 255, 0.8);
            -webkit-box-shadow: 0px 0px 5px 0px rgba(50, 50, 50, 0.5);
            -moz-box-shadow: 0px 0px 5px 0px rgba(50, 50, 50, 0.5);
            box-shadow: 0px 0px 5px 0px rgba(50, 50, 50, 0.5);
            border-bottom: 3px #ef3723 solid;
        }

        .login-headear {
            width: 306px;
            height: 77px;
            background: #e6e6e6;
            background-image: url('resources/images/login-logo.png');
            background-position: center;
            background-repeat: no-repeat;
        }
        
        .login-body
        {
            width:306px;
            min-height:100px;
        }

        .login-body input{
            padding:5px;
            width:250px;
            text-align:center;
            margin-bottom:8px;
            border:1px #102a7e dotted;
            outline:none;
           
        }
        
    </style>

</head>
<body class="bg" ng-app="content" data-ng-controller="userController">
<form name="changepwd" action="login" novalidate id="loginFrm">
    <div class="login-div">
        <div class="login-headear">
        </div>
        <div class="login-body">
            <div align="center" style="width:306px;">
                <img style="margin-top:15px;" src="resources/images/login-key.png"></img>
            </div>
            <!-- <div data-ng-show="message.success" class="sucess_message">{{user.message}}</div>
			<div data-ng-show="message.error" class="sucess_error">{{user.message}}</div> -->
            <div align="center" style="width:306px; min-height:100px;">
                <br></br>
                <input  id="userName" name="userName" placeholder="New Password" data-ng-model="changepwd1" type="text" data-validator="required" data-valid-method="watch" data-required-error-message="Enter your username" autofocus="autofocus">
                <input type="password" id="password" name="passWord" placeholder="Confirm Password" data-ng-model="changepwd1" data-validator="required" data-valid-method="watch" data-required-error-message="Enter your password">
                </div>
            <!-- <div align="center" style="width:306px; height:80px;">
                <input data-validation-submit="loginForm" data-ng-click="checkLogin()" type="image" src="resources/images/login-btn.png" alt="Login"></input>
            </div> -->
            <div align="center">
                <input type="button" value="Ok" data-validation-submit="changepwd" class="btn btn-danger" data-ng-click="changePwd();"/>
                <input type="button" value="Cancel" class="btn btn-danger" data-ng-click="showRegistration();"/>
            </div>
        </div>
    </div>
    </form>

</body>
<!-- 
<script type="text/javascript">
var ctx = "<%=request.getContextPath()%>";
function login(){
	var usrName = $("#userName").val();
	var pwd = $("#password").val();
}
function showForgetPwd(){
	window.location.href = ctx+"/forgetPassword";
}
function showRegistration(){
	window.location.href = ctx+"/registration";
}

</script>
 -->
</html>
