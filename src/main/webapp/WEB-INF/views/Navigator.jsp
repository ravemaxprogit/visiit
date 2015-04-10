<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <!-- Sidebar -->
        <div class="sidebar left-side-menu-fixed">
            <div class="sidebar-content">
                <!-- User dropdown -->
                <div class="user-menu dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <img src="resources/images/demo/users/bird.png" alt="">
                        <div class="user-info" style="hieght=200px">
                         Visiit<!-- Admin <span>Visiit</span> -->
                        </div>
                    </a>

                </div>
                <!-- Main navigation -->
                <ul class="navigation">
                    <li data-ng-class="$state.current.name == 'dashboard'?'active':''">
                    <a ui-sref="dashboard"><span>Dashboard</span> <i class="icon-screen2"></i></a></li>

                    <li data-ng-class="($state.current.name == 'country'|| $state.current.name == 'Module'|| $state.current.name == 'department' )?'active':''">
                        <a href="#"><span>Masters</span> <i class="icon-file"></i></a>
                        <ul data-ng-style="($state.current.name == 'country' || $state.current.name == 'Module'|| $state.current.name == 'department' )?{'display': 'block'}:''">
                            <!--<li><a href="login">Employee</a></li> -->
                            <li><a ui-sref="country">Country</a></li>
                            <li><a ui-sref="Module">Module</a></li>
                            <li><a ui-sref="department">Department</a></li>
                             <li><a ui-sref="designation">Designation</a></li>
                            <li><a ui-sref="manageUsers">Users</a></li>
                            <!--<li><a href="department">Department</a></li>
                            <li><a href="designation">Designation</a></li>
                            <li><a href="roleMaster">User Role</a></li>
                            <li><a href="department">User Role Access</a></li>
                            <li><a href="login">User</a></li> -->
                        </ul>
                    </li>

                    <li data-ng-class="($state.current.name == 'vendor' || $state.current.name == 'hotel' || $state.current.name == 'package' || $state.current.name == 'package_category' )?'active':''">
                        <a href="#"><span>Product</span> <i class="icon-bubble6"></i></a>
                        <ul data-ng-style="($state.current.name == 'vendor' || $state.current.name == 'hotel' || $state.current.name == 'package' || $state.current.name == 'package_category' )?{'display': 'block'}:''">
                            <li><a ui-sref="vendor">Vendor</a></li>
                            <li><a ui-sref="hotel">Hotel</a></li>
                            <li><a ui-sref="package">Package</a></li>
                            <li><a ui-sref="package_category">Package Category</a></li>
                            <!--<li><a href="testfrm">Inward </a></li> -->
                        </ul>
                    </li>


                    <li>
                        <a href="#"><span>Customer Care</span> <i class="icon-quill2"></i></a>
                        <ul>
                            <li><a ui-sref="customer_care">Ticket Support</a></li>
                            <li><a href="customerCare">User Accounts</a></li>
                        </ul>
                    </li>

                </ul>
                <!-- /main navigation -->

            </div>
        </div>
        <!-- /sidebar -->
