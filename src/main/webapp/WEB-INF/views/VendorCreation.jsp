<%@page import="com.rave.visiit.entity.Vendor"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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

            <form action="${pageContext.request.contextPath}/saveVendor" role="form" method="POST" novalidate>
                <div class="panel panel-default">
                    <div class="panel-heading"><h6 class="panel-title"><i class="icon-pencil3"></i>VENDOR INFORMATION</h6></div>
                    <div class="panel-body">

                        <div class="form-group">
                            <div class="row">
                                <div class="col-md-4">
                                    <label>Name:</label>
                                    <input type="text" name="viVendorName" placeholder="First Name" class="form-control" ng-model="fname" required>
                                </div> 
                                <div class="col-md-4">
                                    <label>Phone No:</label>
                                    <input type="number" name="viVendorPhone" placeholder="mobile no" class="form-control" ng-model="contactNo" required>
                                </div>
                                
                                 <div class="col-md-4">
                                    <label>Email address:</label>
                                    <input type="email" name="viVendorEmail" placeholder="name@email.com" class="form-control" ng-model="email" required>
                                </div>
                            </div>


                            <div class="row">
                                <div class="col-md-8">
                                    <label>Address:</label>
                                    <input type="text" name="viAddress" placeholder="#No, Street Name" class="form-control" ng-model="address" required>
                                </div>  
                                 <div class="col-md-4">
                                    <label>City:</label>
                                    <input type="text" name="viCity" class="form-control" ng-model="city" required>
                                </div>    
                            </div>
                            
                            <div class="row">                               
                                <div class="col-md-4">
                                    <label>State:</label>
                                    <input type="text" name="viState" placeholder="select" class="form-control" ng-model="state">
                                </div>
 
                                <div class="col-md-4">
                                    <label>Country:</label>
                                   <input type="text" name="viCountry" class="form-control" placeholder="select" ng-model="country" required>
                                </div>

                                <div class="col-md-4">
                                    <label>Fax:</label>
                                    <input type="number" name="viFax" placeholder="Fax No." class="form-control" ng-model="fax" required>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>
        
                <div class="panel panel-default">
                    <div class="panel-heading"><h6 class="panel-title"><i class="icon-pencil3"></i>Contact Detail</h6></div>
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
                                    <input type="text" placeholder="select" class="form-control" ng-model="state1">
                                </div>

                                <div class="col-md-4">
                                    <label>Country:</label>
                                    <input type="text" placeholder="select" class="form-control" ng-model="country1" required>
                                </div>

                                <div class="col-md-4">
                                    <label>Pin code:</label>
                                    <input type="number" name="viPincode" placeholder="enter pin" class="form-control" ng-model="pincode1" required>
                               </div>
                        </div>
                </div>
                        

                <div class="panel panel-default">
                    <div class="panel-heading"><h6 class="panel-title"><i class="icon-pencil3"></i>CONTACT Detail</h6></div>
                    <div class="panel-body">
                      <div class="form-group">

                            <div class="row">  
                                <div class="col-md-4">
                                    <label>[Person 1] Name:</label>
                                    <input type="text" name="viContactName1" placeholder="First Name - Last Name" class="form-control" ng-model="name1" required>
                                </div>

                                <div class="col-md-4">
                                    <label>Contact:</label>
                                    <input type="text" name="viContactNo1" placeholder="mobile no" class="form-control" ng-model="contact1" required>
                                </div>

                                <div class="col-md-4">
                                    <label>Email:</label>
                                    <input type="email" name="viContactEmail1" placeholder="name@email.com" class="form-control" ng-model="email1" required>
                                </div>
                            </div>
                            
                            <div class="row">  
                                <div class="col-md-4">
                                    <label>[Person 2] Name:</label>
                                    <input type="text" name="viContactName2" placeholder="First Name - Last Name" class="form-control" ng-model="name2" required>
                                </div>

                                <div class="col-md-4">
                                    <label>Contact:</label>
                                    <input type="text" name="viContactNo2" placeholder="mobile no" class="form-control" ng-model="contact2" required>
                                </div>

                                <div class="col-md-4">
                                    <label>Email:</label>
                                    <input type="email" name="viContactEmail2" placeholder="name@email.com" class="form-control" ng-model="email2" required>
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
                                    <label>Vendor Code:</label>
                                    <input type="text" name="viVendorId" placeholder="" class="form-control" ng-model="code" required>
                                </div>

                                <div class="col-md-4">
                                    <label>Active:</label>
                                    <input type="text" name="viIsactive" class="form-control" placeholder="" ng-model="isactive" required>
                                </div>

                                <div class="col-md-4">
                                    <label>Remark:</label>
                                    <input type="text" name="viRemark" placeholder="" class="form-control" ng-model="remark">
                                </div>
                            </div>

                        </div>

                    </div>
                </div>
                
                        <div class="form-actions text-right">
                            <input type="submit" id="submit" name="save" value="Save" class="btn btn-danger">
                            <input type="reset" value="Cancel" class="btn btn-primary">
                            <input type="reset" value="Clear" class="btn btn-default">

                        </div>
            </form><br/>
            <form action="searchVendor" role="form" novalidate>
                <div class="panel-body">
                      <div class="form-group">
                         <div class="row">  
                            <div class="col-md-8">
                                  <input type="text" name="vendor" placeholder="- Choose Vendor -" class="form-control" ng-model="vendor" required>
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
                              <c:if  test="${!empty vendorList}">
                                <table class="table table-bordered">
                                    <thead>
                                        <tr>
                                            <th class="">Code</th>
                                            <th class="">Name</th>
                                            <th class="">Email</th>
                                            <th class="">Phone</th>
                                            <th class="">Active</th>
                                            <th class="">Edit</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${vendorList}" var="vendor"> 
                                        <tr>
                                            <td>${vendor.viVendorId}</td>
                                            <td>${vendor.viVendorName}</td>
                                            <td>${vendor.viVendorEmail}</td>
                                            <td>${vendor.viContactNo1}</td>
                                            <td>${vendor.viIsactive}</td>
                                            <td>
                                                <p>
                                                    <a href=""><i style="color:red;" class="icon-close"></i></a> &nbsp;&nbsp;&nbsp;
                                                    <a href=""><i style="color:#808080;" class="icon-pencil2"></i></a>
                                                </p>
                                            </td>
                                          </tr>     
										</c:forEach>                   
                                    </tbody>
                                </table>
                                </c:if>
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

    </div>
    <!-- /page container --> 
    
</body>
