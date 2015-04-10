
<%@ include file="Header.jsp" %>

<body ng-app="content">
    <!-- Page container -->
    
    <div class="page-container">  
    <%@ include file="Navigator.jsp" %>

        <!-- Page content -->
        <div class="page-content">
        
        <!-- Breadcrumbs line -->
            <br/>
            <div class="breadcrumb-line">
                <ul class="breadcrumb">
                    <li><a href="index-2.html">Home</a></li>
                    <li class="active">Dashboard</li>
                </ul>
            </div>
            <!-- /breadcrumbs line -->
     
            <div class="well block">
                <div class="tabbable"> 
                    <ul  ng-init="tab = ${tid}" class="nav nav-pills">
                        <li ng-class="{active:tab===1}"><a href="country" ng-click="tab = 1" data-toggle="tab">Department</a></li>
                        <li ng-class="{active:tab===2}"><a href="designation" ng-click="tab = 2" data-toggle="tab">Designation</a></li>
                        <li ng-class="{active:tab===3}"><a href="country" ng-click="tab = 3" data-toggle="tab">User Role</a></li>
                        <li ng-class="{active:tab===4}"><a href="country" ng-click="tab = 4" data-toggle="tab">User Role Access</a></li>  
                    </ul>
                    
                    <div class="tab-content pill-content">
                        <div ng-show="tab === 1">
                            <department-master></department-master>
                        </div>
                        <div ng-show="tab === 2">
                            <designation-master></designation-master>
                        </div>
                        <div ng-show="tab === 3">
                             <userrole-master></userrole-master>
                        </div>
                        <div ng-show="tab === 4">
                             <userrole-access></userrole-access>
                        </div>
                    </div>
                </div>
            </div>

            <br /><br /><br /><br /><br /><br /><br/>            
            
            <%@ include file="Footer.jsp" %>

        </div>
        <!-- /page content -->

    </div>
    <!-- /page container -->


</body>
