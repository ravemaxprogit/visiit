<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1, maximum-scale=1">
    <title>Visiit</title>
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
    <script src="resources/js/jquery.min.js"></script>
    <script src="resources/js/jquery-ui.min.js"></script>
    <script src="resources/js/angular/angular.min.js"></script>
    <!--<script src="resources/js/angular/angular.min.js.map"></script>-->
    <script src="resources/js/angular/angular-sanitize.min.js"></script>
    <!--<script src="resources/js/angular/angular-sanitize.min.js.map"></script>-->
    <script src="resources/js/angular/angular-animate.min.js"></script>
    <!--<script src="resources/js/angular/angular-animate.min.js.map"></script>-->
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
    <script type="text/javascript" src="resources/js/application.js"></script>
    <script type="text/javascript" src="resources/views/masters/department/department.js"></script>
    <script type="text/javascript" src="resources/views/masters/designation/designation.js"></script>
    <script type="text/javascript" src="resources/views/masters/manageUsers/manageUsers.js "></script>  
    <script type="text/javascript" src="resources/views/customercare/enquiry/enquiry.js "></script>
    <script type="text/javascript" src="resources/views/customercare/manageTripDetail/manageTripDetail.js "></script>    


</head>
<body ng-app="content">
        <!-- Navbar -->
    <div class="navbar navbar-inverse header-fixed-new" role="navigation">
        <div class="navbar-header">
            <a class="navbar-brand" href="#"><img src="resources/images/logo.png" alt=""></a>
            <a class="sidebar-toggle"><i class="icon-paragraph-justify2"></i></a>
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbar-icons">
                <span class="sr-only">Toggle navbar</span>
                <i class="icon-grid3"></i>
            </button>
            <button type="button" class="navbar-toggle offcanvas">
                <span class="sr-only">Toggle navigation</span>
                <i class="icon-paragraph-justify2"></i>
            </button>
        </div>
        <ul style="border-right: 5px #ffd800 solid; border-left: 5px #ffd800 solid;" class="nav navbar-nav navbar-right collapse" id="navbar-icons">
           <!-- <li class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown">
                    <i class="icon-bell"></i>
                    <span class="label label-default">2</span>
                </a>
                <div class="popup dropdown-menu dropdown-menu-right">
                    <div class="popup-header">
                        <a href="#" class="pull-left"><i class="icon-bell"></i></a>
                        <span>Reminders</span>
                        <a href="#" class="pull-right"><i class="icon-paragraph-justify"></i></a>
                    </div>
                    <ul class="activity">
                        <li>
                            <i class="icon-cart-checkout text-success"></i>
                            <div>
                                Some task waiting <a href="#">Vijayaragavan</a>
                                <span>14 minutes ago</span>
                            </div>
                        </li>
                        <li>
                            <i class="icon-heart text-danger"></i>
                            <div>
                                Some task waiting <a href="#">Some Name</a>
                                <span>14 minutes ago</span>
                            </div>
                        </li>
                        <li>
                            <i class="icon-checkmark3 text-success"></i>
                            <div>
                                Some task waiting <a href="#">Some One Here</a>
                                <span>14 minutes ago</span>
                            </div>
                        </li>
                        <li>
                            <i class="icon-paragraph-justify2 text-warning"></i>
                            <div>
                                Some task waiting <a href="#">Nijam Max</a>
                                <span>14 minutes ago</span>
                            </div>
                        </li>
                    </ul>
                </div>
            </li>-->

            <li class="user dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown">
                    <i style="margin-top: 10px;" class="icon-user4"></i>
                    <span>Welcome {{userInfo}}</span>
                    <i class="caret"></i>
                </a>
                <ul class="dropdown-menu dropdown-menu-right icons-right">
                    <!--<li><a href="#"><i class="icon-user"></i> Profile</a></li>
                    <li><a href="#"><i class="icon-bubble4"></i> Messages</a></li>
                    <li><a href="#"><i class="icon-cog"></i> Settings</a></li>-->
                    <li><a ui-sref="logout"><i class="icon-exit"></i> Logout</a></li>
                </ul>
            </li>
        </ul>
    </div>
    <!-- /navbar -->