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
        
        
        <form action="addDepartment" role="form" novalidate>
                <div class="panel panel-default">
                  <div class="panel-heading"><h6 class="panel-title"><i class="icon-pencil3"></i>PACKAGE</h6></div>
                    <div class="panel-body">
                     
                     <div class="row">
                       <div class="col-md-8">
                           <input type="text" name="hotel" placeholder="- Choose package -" class="form-control" ng-model="hotel" required>
                       </div>               
                        <div class="col-md-3">
                            <a href="" btn btn-danger><i class="fa fa-search"></i></a>
                        </div>
                     </div><br>
                     
                     <div class="panel-heading">
                        <div class="col-md-9"><h6 class="panel-title"><i class="icon-pencil3"></i>Package Detail</h6></div>
                        <div class="col-md-3"><input type="submit" value="Next" class="btn btn-default"></div>
                     </div><br>
                     
                     <div class="col-md-8">
                      <div class="row">
                            <div class="col-md-4  subtitle"> Name:</div>
                            <div class="col-md-6">
                               <input type="text" name="packName" placeholder="Package name" class="form-control" ng-model="packName" required>
                            </div>
                       </div><br>
                       
                       <div class="row">
                            <div class="col-md-4  subtitle">Category:</div>
                            <div class="col-md-6">
                               <input type="text" name="packCategory" placeholder="select" class="form-control" ng-model="packCategory" required>
                            </div>
                       </div>
                        
                        <div class="row">
                            <div class="col-md-4  subtitle">Code:</div>
                            <div class="col-md-6">
                               <input type="text" name="packCode" placeholder="" class="form-control" ng-model="packCode" required>
                            </div>
                       </div>
                        
                        <div class="row">
                            <div class="col-md-4  subtitle">Days:</div>
                            <div class="col-md-6">
                               <input type="number" name="packDays" placeholder="select" class="form-control" ng-model="packDays" required>
                            </div>
                       </div>
                       
                       <div class="row">
                            <div class="col-md-4  subtitle">Night:</div>
                            <div class="col-md-6">
                               <input type="number" name="packNight" placeholder="select" class="form-control" ng-model="packNight" required>
                            </div>
                       </div>
                       
                       <div class="row">
                            <div class="col-md-4  subtitle">Over View:</div>
                            <div class="col-md-6">
                               <input type="text" name="packOverview" placeholder="select" class="form-control" ng-model="packOverview" required>
                            </div>
                       </div>
                       
                   </div>
                   
                   <div class="col-md-4">
                       <div class="block">
                           <table class="table table-bordered">
                               <thead>
                                <tr>
                                  <th class="" colspan="2"><i class="fa fa-cog fa-spin"></i> Services </th>
                                </tr>
                                </thead>
                                <tbody>
                                  <tr>
                                  <td>Flight</td>
                                   <td>
                                   <span ng-click="planecheck=!planecheck" ng-hide="!planecheck">
                                      <a href=""><i style="color:#21b384;" class="fa fa-plane"></i></a>
                                   </span>
                                   <span ng-click="planecheck=!planecheck" ng-hide="planecheck">
                                     <a href=""><i style="color:#21b384;" class="fa fa-plane"></i></a>
                                   </span>
                                   </td>
                                 </tr>
                                 <tr>
                                  <td>Hotel</td>
                                  <td>
                                   <span ng-click="hotelchck=!hotelchck" ng-hide="!hotelchck">
                                     <a href=""><i style="color:#21b384;" class="fa fa-building-o"></i></a>
                                   </span>
                                   <span ng-click="hotelchck=!hotelchck" ng-hide="hotelchck">
                                     <a href=""><i style="color:#21b384;" class="fa fa-building-o"></i></a>
                                   </span>
                                  </td>
                                 </tr>
                                 <tr>
                                  <td>Food</td>
                                  <td>
                                   <span ng-click="foodcheck=!foodcheck" ng-hide="!foodcheck">
                                     <a href=""><i style="color:#21b384;" class="fa fa-cutlery"></i></a>
                                   </span>
                                   <span ng-click="foodcheck=!foodcheck" ng-hide="foodcheck">
                                     <a href=""><i style="color:#21b384;" class="fa fa-cutlery"></i></a>
                                   </span>
                                  </td>
                                 </tr>
                                 <tr>
                                  <td>Train</td>
                                  <td>
                                   <span ng-click="traincheck=!traincheck" ng-hide="!traincheck">
                                     <a href=""><i style="color:#21b384;" class="glyphicons glyphicons-train"></i></a>
                                   </span>
                                   <span ng-click="traincheck=!traincheck" ng-hide="traincheck">
                                     <a href=""><i style="color:#21b384;" class="glyphicons glyphicons-train"></i></a>
                                   </span>
                                  </td>
                                 </tr>
                                 <tr>
                                 <td>Bus</td>
                                  <td>
                                   <span ng-click="buscheck=!buscheck" ng-hide="!buscheck">
                                     <a href=""><i style="color:#21b384;" class="fa fa-bus"></i></a>
                                   </span>
                                   <span ng-click="buscheck=!buscheck" ng-hide="buscheck">
                                     <a href=""><i style="color:#21b384;" class="fa fa-bus"></i></a>
                                   </span>
                                  </td>
                                 </tr>
                                 <tr>
                                 <td>Ferry</td>
                                  <td>
                                   <span ng-click="ferrycheck=!ferrycheck" ng-hide="!ferrycheck">
                                     <a href=""><i style="color:#21b384;" class="icon-boat"></i></a>
                                   </span>
                                   <span ng-click="ferrycheck=!ferrycheck" ng-hide="ferrycheck">
                                     <a href=""><i style="color:#21b384;" class="icon-boat"></i></a>
                                   </span>
                                  </td>
                                 </tr>
                               </tbody>
                            </table>
                        </div>
                   </div><br>
                   
                   <div class="panel-heading">
                        <div class="col-md-9"><h6 class="panel-title"><i class="icon-pencil3"></i>Vendor Detail</h6></div>
                        <div class="col-md-3"><input type="submit" value="Next" class="btn btn-default"></div>
                   </div><br>
                   
                   <div class="row">
                            <div class="col-md-2  subtitle">Vendor Code:</div>
                            <div class="col-md-3">
                               <input type="text" name="vendorName" placeholder="select" class="form-control" ng-model="vendorName" required>
                            </div>
                            <div class="col-md-2  subtitle">Code:</div>
                            <div class="col-md-3">
                               <input type="text" name="Code" placeholder="" class="form-control" ng-model="Code" required>
                            </div>
                       </div>
                       
                       <div class="row">
                            <div class="col-md-2  subtitle">Day:</div>
                            <div class="col-md-3">
                               <input type="number" name="day" placeholder="1" class="form-control" ng-model="day" min="1" required>
                            </div>                           
                       </div>
                       
                       <div class="row">
                          <div class="col-md-4  subtitle">Activities:</div>
                       </div>
                       
                     <div class="row">
                       <div class="col-md-10">
                           <input type="text" name="hotel" placeholder="- describe activity -" class="form-control" ng-model="hotel" required>
                       </div>
                       <div class="col-md-2">
                            <a href="" ><i class="fa fa-upload"></i></a>
                        </div>
                     </div><br>
                     
                     <div class="block">
                            <div class="">
                                <table class="table table-bordered">
                                    <thead>
                                        <tr>
                                            <th class="">Day</th>
                                            <th class="">Activity</th>
                                            <th class="">Edit</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td>1</td>
                                            <td>sample 1</td>
                                            <td>
                                               <a href=""><i style="color:#808080;" class="icon-pencil2"></i></a> &nbsp;&nbsp;|&nbsp;&nbsp;
                                               <a href=""><i style="color:#808080;" class="fa fa-trash"></i></a>
                                            </td>
                                        </tr>
                                        
                                    </tbody>
                                </table>
                            </div>
                        </div><br>
                        
                    <div class="panel-heading">
                        <div class="col-md-9"><h6 class="panel-title"><i class="icon-pencil3"></i>Price Detail</h6></div>
                        <div class="col-md-3"><input type="submit" value="Next" class="btn btn-default"></div>
                   </div><br>
                     
                   <div class="row">
                            <div class="col-md-2  subtitle"> Price:</div>
                            <div class="col-md-4">
                               <input type="number" name="price" placeholder="" class="form-control" ng-model="price" required>
                            </div>
                            <div class="col-md-2  subtitle">Valid From:</div>
                            <div class="col-md-4">
                               <input type="text" name="validfrom" placeholder="" class="datepicker-restricted form-control" ng-model="validfrom" required>
                            </div>
                    </div>
                    
                    <div class="row">
                            <div class="col-md-2  subtitle"> Discription:</div>
                            <div class="col-md-4">
                               <input type="number" name="packDisc" placeholder="" class="form-control" ng-model="packDisc" required>
                            </div>
                            <div class="col-md-2  subtitle">Valid To:</div>
                            <div class="col-md-4">
                               <input type="text" name="validto" placeholder="" class="datepicker-restricted form-control" ng-model="validto" required>
                            </div>
                    </div>
                    
                    <div class="form-actions text-right">
                           <input type="submit" value="Save" class="btn btn-danger">
                    </div><br>
                    
                    <div class="block">
                            <div class="">
                                <table class="table table-bordered">
                                    <thead>
                                        <tr>
                                            <th class="">Price</th>
                                            <th class="">Discription</th>
                                            <th class="">Valid</th>
                                            <th class=""></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td>1000</td>
                                            <td>sample 1</td>
                                            <td>dd/mon/yyyy</td>
                                            <td>
                                               <a href=""><i style="color:#808080;" class="icon-pencil2"></i></a> &nbsp;&nbsp;|&nbsp;&nbsp;
                                               <a href=""><i style="color:#808080;" class="fa fa-trash"></i></a>
                                            </td>
                                        </tr>  
                                    </tbody>
                                </table>
                            </div>
                        </div><br>
        
                      <div class="panel-heading">
                        <div class="col-md-9"><h6 class="panel-title"><i class="icon-pencil3"></i>File Upload</h6></div>
                        <div class="col-md-3"><input type="submit" value="Next" class="btn btn-default"></div>
                   </div><br>
                   
                   <div class="row">
                            <div class="col-md-2  subtitle"> File Category:</div>
                            <div class="col-md-4">
                               <input type="number" name="fileCategory" placeholder="" class="form-control" ng-model="fileCategory" required>
                            </div>
                            <div class="col-md-2  subtitle">File Type:</div>
                            <div>
                                 <span ng-click="isCategory=!isCategory" ng-hide="isCategory">
                                     <a href=""><i style="color:#21b384;" class="fa fa-image"></i></a> &nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
                                     <a href=""><i style="color:#808080;" class="fa fa-video-camera"></i></a>
                                 </span>
                                  <span ng-click="isCategory=!isCategory" ng-hide="!isCategory">
                                     <a href=""><i style="color:#808080;" class="fa fa-image"></i></a> &nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
                                     <a href=""><i style="color:#21b384;" class="fa fa-video-camera"></i></a>
                                 </span>
                            </div>
                    </div>
                    
                    <div class="row">
                                <div class="col-md-6">
                                   <div  class="vid-area col-md-8"></div>
                                </div>
                                 <div class="col-md-6">
                                      <div class="row">
                                      <div class="col-md-10">
                                         <input type="file" value="Upload" class="btn btn-danger choose-file-grip">
                                      </div></div><br>
                                      <div class="row">
                                          <div class="col-md-10">
                                          <label>Discription:</label>
                                         <input type="text" name="fileDiscription" placeholder="" class="form-control" ng-model="fileDiscription" required>
                                      </div><br>
                                      <div class="row">
                                     <div class="col-md-10">
                                        <input type="submit" value="Upload" class="btn btn-danger ">
                                    </div></div>
                                 </div>
                       </div></div><br>
                       
                     <div class="block">
                            <div class="">
                                <table class="table table-bordered">
                                    <thead>
                                        <tr>
                                            <th class="">File Name</th>
                                            <th class="">Type</th>
                                            <th class="">Discription</th>
                                            <th class="">Edit</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td>Sample 1</td>
                                            <td>sample 1</td>
                                            <td><a href=""><i style="color:#21b384;" class="fa fa-image"></i></a></td>
                                            <td>
                                               <a href=""><i style="color:#808080;" class="icon-pencil2"></i></a> &nbsp;&nbsp;|&nbsp;&nbsp;
                                               <a href=""><i style="color:#808080;" class="fa fa-trash"></i></a>
                                            </td>
                                        </tr>  
                                    </tbody>
                                </table>
                            </div>
                        </div><br>
                        
                   <div class="panel-heading">
                        <div class="col-md-9"><h6 class="panel-title"><i class="icon-pencil3"></i>Attractions</h6></div>
                        <div class="col-md-3"><input type="submit" value="Next" class="btn btn-default"></div>
                   </div><br>
                   
                   <div class="row">
                           <div class="col-md-3">
                             <input type="text" name="attraction" placeholder="select" class="form-control" ng-model="attraction" required>
                            </div>
                            <div class="col-md-2  subtitle">Feature:</div>
                            <div class="col-md-5">
                               <input type="text" name="feature" placeholder="" class="form-control" ng-model="feature" required>
                            </div>
                            <div class="col-md-2">
                                 <span ng-click="isStar=!isStar" ng-hide="!isStar">
                                     <a href=""><i style="color:#21b384;" class="fa fa-magic"></i></a> 
                                     </span>
                                  <span ng-click="isStar=!isStar" ng-hide="isStar">
                                     <a href=""><i style="color:#808080;" class="fa fa-magic"></i></a>
                                  </span>
                            </div>
                       </div><br>
                       
                    <div class="row">
                       <div class="col-md-8">
                          <textarea class="form-control" rows="3" ng-model="description"></textarea>
                       </div>
                       <div class="col-md-4">
                              <input type="submit" value="Add" class="btn btn-danger">
                        </div>
                    </div><br> 
                    
                    <div class="block">
                            <div class="">
                                <table class="table table-bordered">
                                    <thead>
                                        <tr>
                                            <th class="">Feature</th>
                                            <th class="">Discription</th>
                                            <th class="">Edit</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td>Sample 1</td>
                                            <td>sample 1</td>
                                            <td>
                                               <a href=""><i style="color:#808080;" class="icon-pencil2"></i></a> &nbsp;&nbsp;|&nbsp;&nbsp;
                                               <a href=""><i style="color:#808080;" class="fa fa-trash"></i></a>
                                            </td>
                                        </tr>  
                                    </tbody>
                                </table>
                            </div>
                        </div><br> 
                        
                    <div class="panel-heading">
                        <div class="col-md-9"><h6 class="panel-title"><i class="icon-pencil3"></i>Hotels</h6></div>
                        <div class="col-md-3"><input type="submit" value="Next" class="btn btn-default"></div>
                   </div><br>
                   
                   <div class="row">
                            <div class="col-md-4">
                               <input type="text" name="hotelplace" placeholder="-- city/pincode --" class="form-control" ng-model="hotelplace" required>
                            </div>
                            <div class="col-md-6">
                               <input type="text" name="hotelName" placeholder="-- hotel name --" class="form-control" ng-model="hotelName" required>
                            </div>
                            <div class="col-md-2">
                                <input type="submit" value="Add" class="btn btn-danger">
                            </div>
                  </div><br>
                   
                   <div class="block">
                            <div class="">
                                <table class="table table-bordered">
                                    <thead>
                                        <tr>
                                            <th class="">Hotel</th>
                                            <th class="">Type</th>
                                            <th class="">City</th>
                                            <th class="">Edit</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td>Sample 1</td>
                                            <td>Sample 1</td>
                                            <td>Sample 1</td>
                                            <td>
                                               <a href=""><i style="color:#808080;" class="icon-pencil2"></i></a> &nbsp;&nbsp;|&nbsp;&nbsp;
                                               <a href=""><i style="color:#808080;" class="fa fa-trash"></i></a>
                                            </td>
                                        </tr>  
                                    </tbody>
                                </table>
                            </div>
                        </div><br>
                        
                    <div class="panel-heading">
                        <div class="col-md-9"><h6 class="panel-title"><i class="icon-pencil3"></i>Conditions</h6></div>
                        <div class="col-md-3"><input type="submit" value="Next" class="btn btn-default"></div>
                   </div><br>
                   
                   <div class="row">
                            <div class="col-md-2">
                                 <span ng-click="isCondition=!isCondition" ng-hide="!isCondition" ng-model="condition">
                                     <a href=""><i style="color:#21b384;" class="fa fa-chevron-circle-right"></i></a> 
                                     </span>
                                  <span ng-click="isCondition=!isCondition" ng-hide="isCondition" ng-model="condition">
                                     <a href=""><i style="color:#21b384;" class="fa fa-chevron-circle-left"></i></a>
                                  </span>
                            </div>
                            <div class="col-md-6">
                               <input type="text" name="inclusion" value="{{condition}}" placeholder="-- inclusions/exclusions --" class="form-control" ng-model="inclusion" required>
                            </div> 
                  </div><br>
                   
                   <div class="row">
                            <div class="col-md-6">
                               <input type="text" name="hoteldesc" placeholder="-- description --" class="form-control" ng-model="hoteldesc" required>
                            </div>
                            <div class="col-md-4">
                                <input type="submit" value="Add" class="btn btn-danger">
                            </div>
                  </div><br>
                  
                  <div class="block">
                            <div class="">
                                <table class="table table-bordered">
                                    <thead>
                                        <tr>
                                            <th class="">Type</th>
                                            <th class="">City</th>
                                            <th class="">Edit</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td>Sample 1</td>
                                            <td>Sample 1</td>
                                            <td>
                                               <a href=""><i style="color:#808080;" class="icon-pencil2"></i></a> &nbsp;&nbsp;|&nbsp;&nbsp;
                                               <a href=""><i style="color:#808080;" class="fa fa-trash"></i></a>
                                            </td>
                                        </tr>  
                                    </tbody>
                                </table>
                            </div>
                        </div><br>
                                         
               </div>
            </div>
                           
             <div class="form-actions text-right">
                    <input type="submit" value="Finish" class="btn btn-danger">
              </div>
        
        </form></br>
        
            <!-- /tasks table -->
            <br /><br /><br /><br /><br /><br /><br/>            
            
            <%@ include file="Footer.jsp" %>

        </div>
        <!-- /page content -->

    </div>
    <!-- /page container -->


</body>