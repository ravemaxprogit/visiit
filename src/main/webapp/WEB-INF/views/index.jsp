<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html xmlns="http://www.w3.org/1999/xhtml" ng-app="content">
<head>
    <title>.:: Visiit ::.</title>
    <link href="resources/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="resources/css/apear.css" rel="stylesheet" type="text/css">
    <link href="resources/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="resources/css/oversheet.css" rel="stylesheet" />
    <link href="resources/css/styles.css" rel="stylesheet" type="text/css">
    <link href="resources/css/icons.css" rel="stylesheet" type="text/css">
    <link href="resources/css/app.css" rel="stylesheet" type="text/css">
    <link href="resources/css/select.css" rel="stylesheet" type="text/css">
    <link href="resources/css/ngDialog.min.css" rel="stylesheet" type="text/css">
    <link href="resources/css/ngDialog-theme-default.min.css" rel="stylesheet" type="text/css">
    <link href="resources/css/ngDialog-theme-plain.min.css" rel="stylesheet" type="text/css">
    <link href="resources/css/ngImgCrop.css" rel="stylesheet" type="text/css">
    <link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700&amp;subset=latin,cyrillic-ext" rel="stylesheet" type="text/css">
    <script src="resources/js/angular/md5.js"></script>
    <script src="resources/js/jquery.min.js"></script>
    <script src="resources/js/jquery-ui.min.js"></script>
    <script src="resources/js/angular/angular.min.js"></script>
    <script src="resources/js/angular/angular-cookies.min.js"></script>
    <script src="resources/js/angular/nibbler.js"></script>
    <script src="resources/js/ui-bootstrap-tpls.min.js"></script>
    <script src="resources/js/angular/angular-sanitize.min.js"></script>
    <script src="resources/js/angular/angular-animate.min.js"></script>    
    <script src="resources/js/angular/ngDialog.min.js"></script>
    <script src="resources/js/angular/select.js"></script>
    <script src="resources/js/angular/angular-file-upload.js"></script>
    <script src="resources/js/angular/angular-file-upload-shim.js"></script>
    <script src="resources/js/angular/ngDraggable.js"></script>
    <script src="resources/js/angular/angular-ui-router.min.js"></script>
    <script src="resources/js/angular/ng-table.min.js"></script>
    <script src="resources/js/angular/angular-validation.js"></script>
    <script src="resources/js/angular/angular-validation-rule.js"></script>
    <script src="resources/js/angular/ngImgCrop.js"></script>
    <script src="resources/js/angular/app.js"></script>
    <script src="resources/js/angular/angular_controller.js"></script>
    <script src="resources/js/angular/interceptor.js"></script>
    <script src="resources/js/angular/general-module.js"></script>
    <script type="text/javascript" src="resources/js/plugins/charts/sparkline.min.js"></script>
    <script type="text/javascript" src="resources/js/plugins/forms/uniform.min.js"></script>
    <script type="text/javascript" src="resources/js/plugins/forms/select2.min.js"></script>
    <script type="text/javascript" src="resources/js/plugins/forms/inputmask.js"></script>
    <script type="text/javascript" src="resources/js/plugins/forms/autosize.js"></script>
    <script type="text/javascript" src="resources/js/plugins/forms/inputlimit.min.js"></script>
    <script type="text/javascript" src="resources/js/plugins/forms/listbox.js"></script>
    <script type="text/javascript" src="resources/js/plugins/forms/multiselect.js"></script>
    <script type="text/javascript" src="resources/js/plugins/forms/validate.min.js"></script>
    <script type="text/javascript" src="resources/js/plugins/forms/tags.min.js"></script>
    <script type="text/javascript" src="resources/js/plugins/forms/switch.min.js"></script>
    <script type="text/javascript" src="resources/js/plugins/forms/uploader/plupload.full.min.js"></script>
    <script type="text/javascript" src="resources/js/plugins/forms/uploader/plupload.queue.min.js"></script>
    <script type="text/javascript" src="resources/js/plugins/forms/wysihtml5/wysihtml5.min.js"></script>
    <script type="text/javascript" src="resources/js/plugins/forms/wysihtml5/toolbar.js"></script>
    <script type="text/javascript" src="resources/js/plugins/interface/daterangepicker.js"></script>
    <script type="text/javascript" src="resources/js/plugins/interface/fancybox.min.js"></script>
    <script type="text/javascript" src="resources/js/plugins/interface/moment.js"></script>
    <script type="text/javascript" src="resources/js/plugins/interface/jgrowl.min.js"></script>
    <script type="text/javascript" src="resources/js/plugins/interface/datatables.min.js"></script>
    <script type="text/javascript" src="resources/js/plugins/interface/colorpicker.js"></script>
    <script type="text/javascript" src="resources/js/plugins/interface/fullcalendar.min.js"></script>
    <script type="text/javascript" src="resources/js/plugins/interface/timepicker.min.js"></script>
    <script type="text/javascript" src="resources/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="resources/views/auth/auth.js"></script>  
    <script type="text/javascript" src="resources/views/masters/department/department.js"></script>
    <script type="text/javascript" src="resources/views/masters/designation/designation.js"></script>
    <script type="text/javascript" src="resources/views/masters/manageUsers/manageUsers.js "></script>
    <script type="text/javascript" src="resources/views/customercare/enquiry/enquiry.js "></script>
    <script type="text/javascript" src="resources/views/customercare/manageTripDetail/manageTripDetail.js "></script>
    <script type="text/javascript" src="resources/views/customercare/newsletter/newsletter.js "></script> 
    
</head>
<body ng-class="{'bg': !userDetails }">
	
	<div data-ui-view="loginPage"></div> <!-- Login Page -->
	
	<div data-ui-view="resetPasswordPage"></div> <!-- Reset Page -->
	
	<div data-ui-view="changePasswordPage"></div> <!-- Change Password Page -->
	
	<div data-ui-view="registerPage"></div> <!-- Change Password Page -->
	
        
    <div class="page-container" ng-if="userDetails">  
       <div ng-include="'resources/views/common/dashboardHeader.html'"></div> <!-- userHeader -->

     <!-- Page container -->
    
    <div class="page-container">  
    <div ng-include="'resources/views/common/navigations.html'" ng-if="userDetails"></div> <!-- navigation -->
        <!-- Page content -->
        <div class="page-content">
        <div style="padding:70px 0 0 0 ">&nbsp;</div>
           <div class="visiit-container" data-ui-view></div>     
            <!-- /tasks table -->
            <br /><br /><br /><br /><br /><br /><br/>            
            
            <div ng-include="'resources/views/common/dashboardFooter.html'"></div> <!-- userFoter -->

        </div>
        <!-- /page content -->
    </div>
    <!-- /page container -->    
    
  </div>
    <!-- /page container -->     
	
</body>
<script type="text/javascript">
var ctx = "<%=request.getContextPath()%>";
</script>
</html>