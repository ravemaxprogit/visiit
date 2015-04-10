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

            <form action="employeeRegistration" role="form" novalidate>
                <div class="panel panel-default">
                    <div class="panel-heading"><h6 class="panel-title"><i class="icon-pencil3"></i>PERSONAL INFORMATION</h6></div>
                    <div class="panel-body">

                        <div class="form-group">
                            <div class="row">
                                <div class="col-md-4">
                                    <label>First Name:</label>
                                    <input type="text" name="empFirstName" placeholder="First Name" class="form-control" ng-model="fname" required>
                                </div>
                                
                                <div class="col-md-4">
                                <label>Last Name</label>
                                <input type="text" name="empLastName" placeholder="Last Name" class="form-control" ng-model="lname" required>
                                </div>

                                <div class="col-md-4">
                                    <label>Email address:</label>
                                    <input type="email" name="empPersonalMail" placeholder="name@email.com" class="form-control" ng-model="email" required>
                                </div>
                            </div>


                            <div class="row">
                                <div class="col-md-4">
                                    <label>Gender:</label>
                                    <gender-tag></gender-tag>
                                </div>
                                
                                 <div class="col-md-4">
                                    <label>Date Of Birth:</label>
                                   <input type="text" name="empDob" class="datepicker-restricted form-control" ng-model="dob" placeholder=""  required>
                                </div>

                                <div class="col-md-4">
                                    <label>Marital Status:</label>
                                    <input type="text" placeholder="choose" class="form-control" ng-model="maritalStatus" required>
                                </div>
                            </div>
                            
                            <div class="row">                               

                                <div class="col-md-4">
                                    <label>Contact No:</label>
                                    <input type="number" name="empPhone" placeholder="mobile no" class="form-control" ng-model="contactNo" required>
                                </div>
 
                                <div class="col-md-4">
                                    <label>Blood Group:</label>
                                   <input type="text" class="form-control" placeholder="" ng-model="bloodGroup" required>
                                </div>

                                <div class="col-md-4">
                                    <label>Highest Qualification:</label>
                                    <input type="text" placeholder="degree/studies" class="form-control" ng-model="qualification" required>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>
        
                <div class="panel panel-default">
                    <div class="panel-heading"><h6 class="panel-title"><i class="icon-pencil3"></i>ADDRESS Detail</h6></div>
                    <div class="panel-body">

                        <div class="form-group">
                            <div class="row">
                                <div class="col-md-8">
                                    <label>Permanent Address:</label>
                                    <input type="text" placeholder="#No, Street Name" class="form-control" ng-model="Address1" required>
                                </div>

                                <div class="col-md-4">
                                    <label>City:</label>
                                    <input type="text" placeholder="select" class="form-control" ng-model="city1" required>
                                </div>
                            </div>


                            <div class="row">
                               
                                <div class="col-md-4">
                                    <label>State:</label>
                                    <input type="text" placeholder="select" class="form-control" ng-model=""state1 required>
                                </div>

                                <div class="col-md-4">
                                    <label>Country:</label>
                                    <input type="text" placeholder="select" class="form-control" ng-model="country1" required>
                                </div>

                                <div class="col-md-4">
                                    <label>Pin code:</label>
                                    <input type="number" placeholder="enter pin" class="form-control" ng-model="pincode1" required>
                                </div>
                            </div>


                        </div>
                        
                     <div class="form-group">
                            <div class="row">
                                <div class="col-md-8">
                                    <label>Present Address:</label>
                                    <input type="text" placeholder="#No, Street Name" class="form-control" ng-model="address2" required>
                                </div>

                                <div class="col-md-4">
                                    <label>City:</label>
                                    <input type="text" placeholder="select" class="form-control" ng-model="city2" required>
                                </div>
                            </div>


                            <div class="row">
                               
                                <div class="col-md-4">
                                    <label>State:</label>
                                    <input type="text" placeholder="select" class="form-control" ng-model="state2" required>
                                </div>

                                <div class="col-md-4">
                                    <label>Country:</label>
                                    <input type="text" placeholder="select" class="form-control" ng-model="country2" required>
                                </div>

                                <div class="col-md-4">
                                    <label>Pin code:</label>
                                    <input type="number" placeholder="enter pin" class="form-control" ng-model="pincode2" required>
                                </div>
                            </div>

                        </div>

                    </div>
                </div>

                <div class="panel panel-default">
                    <div class="panel-heading"><h6 class="panel-title"><i class="icon-pencil3"></i>EMERGENCY CONTACT</h6></div>
                    <div class="panel-body">
                      <div class="form-group">

                            <div class="row">  
                                <div class="col-md-4">
                                    <label>[Person 1] Name:</label>
                                    <input type="text" placeholder="First Name - Last Name" class="form-control" ng-model="emgName1" required>
                                </div>

                                <div class="col-md-4">
                                    <label>Contact:</label>
                                    <input type="number" placeholder="mobile no" class="form-control" ng-model="emgContact1" required>
                                </div>

                                <div class="col-md-4">
                                    <label>Relation:</label>
                                    <input type="text" placeholder="select" class="form-control" ng-model="emgRelation1" required>
                                </div>
                            </div>
                            
                            <div class="row">  
                                <div class="col-md-4">
                                    <label>[Person 2] Name:</label>
                                    <input type="text" placeholder="First Name - Last Name" class="form-control" ng-model="emgName2" required>
                                </div>

                                <div class="col-md-4">
                                    <label>Contact:</label>
                                    <input type="number" placeholder="mobile no" class="form-control" ng-model="emgContact2" required>
                                </div>

                                <div class="col-md-4">
                                    <label>Relation:</label>
                                    <input type="text" placeholder="select" class="form-control" ng-model="emgRelation2" required>
                                </div>
                            </div>

                        </div>

                    </div>
                </div>

                <div class="panel panel-default">
                    <div class="panel-heading"><h6 class="panel-title"><i class="icon-pencil3"></i>OFFICIAL INFORMATION</h6></div>
                    <div class="panel-body">

                        <div class="form-group">


                            <div class="row">
                                <div class="col-md-4">
                                    <label>Employee code:</label>
                                    <input type="text" name="empCode" placeholder="" class="form-control" ng-model="empCode" required>
                                </div>

                                <div class="col-md-4">
                                    <label>Date Of Join:</label>
                                    <input type="text" name="empDoj" class="datepicker-restricted form-control" placeholder="" ng-model="doj" required>
                                </div>

                                <div class="col-md-4">
                                    <label>Department:</label>
                                    <input type="text" name="empDept" placeholder="select" class="form-control" ng-model="department">
                                </div>
                            </div>
                            
                            <div class="row">
                                <div class="col-md-4">
                                    <label>Designation:</label>
                                    <input type="text" name="empDsgn" placeholder="select" class="form-control" ng-model="designation">
                                </div>

                                <div class="col-md-4">
                                    <label>Office Mail:</label>
                                    <input type="email" name="empOfficeMail" placeholder="name@email.com" class="form-control" ng-model="offMail" required>
                                </div>

                                <div class="col-md-4">
                                    <label>ESI No:</label>
                                    <input type="text" name="empEsiNo" placeholder="" class="form-control" ng-model="esiNo" required>
                                </div>
                            </div>
                            
                            <div class="row">
                                <div class="col-md-4">
                                    <label>PAN No:</label>
                                    <input type="text" name="empPanNo" placeholder="" class="form-control" ng-model="panNo" required>
                                </div>

                                <div class="col-md-4">
                                    <label>Passport No:</label>
                                    <input type="number" name="empPassportNo" placeholder="" class="form-control" ng-model="passportNo" required>
                                </div>

                                <div class="col-md-4">
                                    <label>PF No:</label>
                                    <input type="number" name="empPfNO" placeholder="" class="form-control" ng-model="pfNo" required>
                                </div>
                            </div>

                        </div>

                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading"><h6 class="panel-title"><i class="icon-pencil3"></i>Additional Detail</h6></div>
                    <div class="panel-body">

                        <div class="form-group">


                            <div class="row">

                                <div class="col-md-4">
                                    <label>Active:</label>
                                    <input type="text" name="isactive" class="form-control" placeholder="" ng-model="isactive" required>
                                </div>

                                <div class="col-md-6">
                                    <label>Remark:</label>
                                    <input type="text" name="remark" placeholder="" class="form-control" ng-model="remark">
                                </div>
                            </div>

                        </div>

                    </div>
                </div>
                
                        <div class="form-actions text-right">
                            <input type="submit" value="Save" class="btn btn-danger">
                            <input type="reset" value="Cancel" class="btn btn-primary">
                            <input type="reset" value="Clear" class="btn btn-default">

                        </div>
            </form><br/>
           
           
           <div>
           <form action="addUser">
                <div class="panel panel-default" ng-click="custom=!custom">
                    <div class="panel-heading"><a><h6 class="panel-title" ><i class="icon-pencil3"></i>SOFTWARE CREDENTIAL</h6></a></div>
                    <div class="panel-body" ng-hide="!custom">

                        <div class="form-group">
                            <div class="row">
                                <div class="col-md-4">
                                    <label>Login Id:</label>
                                    <input type="text" name="loginid" placeholder="Login Id" class="form-control" ng-model="loginid" required>
                                </div>
                                
                                <div class="col-md-4">
                                <label>Password</label>
                                <input type="password" name="password" placeholder="6+ alphanumeric" class="form-control" ng-model="password" required>
                                </div>

                                <div class="col-md-4">
                                    <label>Role Name:</label>
                                    <input type="text" name="userrole" placeholder="select" class="form-control" ng-model="userrole" required>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-4">
                                    <label>Active:</label>
                                    <input type="text" name="isUserActive" class="form-control" ng-model="isUserActive" placeholder="select"  required>
                                </div>
                            </div>
                        </div>
                        <div class="form-actions text-right">
                            <input type="submit" value="Save" class="btn btn-danger">
                            <input type="reset" value="Cancel" class="btn btn-primary">
                        </div>
                    </div>
                    <span ng-show="custom"></span>
                </div>
                </form>
         </div>
    <form action="searchUser" role="form" novalidate>
                <div class="panel-body">
                      <div class="form-group">
                         <div class="row">  
                            <div class="col-md-8">
                                  <input type="text" name="vendor" placeholder="- Choose Employee -" class="form-control" ng-model="vendor" required>
                            </div> 
                            <div class="form-actions text-middle">
                            <input type="submit" value="search" class="btn btn-danger">
                            </div>
                        </div>
                   </div>
                </div>
        </form>

            <form action="#" role="form">
                <div class="panel panel-default">
                    <div class="panel-heading"><h6 class="panel-title"><i class="icon-pencil3"></i> Data Grid</h6></div>
                    <div class="panel-body">

                        <div class="block">
                            <div class="">
                                <table class="table table-bordered">
                                    <thead>
                                        <tr>

                                            <th class="">Code</th>
                                            <th class="">Name</th>
                                            <th class="">Email</th>
                                            <th class="">Phone</th>
                                            <th class="">Department</th>
                                            <th class="">Edit</th>

                                        </tr>
                                    </thead>
                                    <tbody>
                                    <c:if test="${!empty listEmployees}">
                                     <c:forEach items="${listEmployees}" var="employee">
                                        <tr>
                                            <td>${employee.empCode}</td>
                                            <td>${employee.empLastName}</td>
                                            <td>${employee.empMail}</td>
                                            <td>${employee.empPhone}</td>
                                            <td>${employee.departments}</td><td>
                                                <p>
                                                    <a href=""><i style="color:red;" class="icon-close"></i></a> &nbsp;&nbsp;&nbsp;
                                                    <a href=""><i style="color:#808080;" class="icon-pencil2"></i></a>
                                                </p>
                                            </td>
                                        </tr>
                                        </c:forEach>
                                        </c:if>
                                    </tbody>
                                </table>
                                <%= request.getAttribute("listEmployees") %>
                                ${listEmployees} is the value
                            </div>
                        </div>


                    </div>
                </div>
            </form>
            
            

            <!-- /tasks table -->
            <br /><br /><br /><br /><br /><br /><br/>            
            
            <%@ include file="Footer.jsp" %>

        </div>
        <!-- /page content -->
<div>

</div>
    </div>
    <!-- /page container -->
    
</body>
