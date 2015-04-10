<%@ include file="Header.jsp" %>

<body ng-app="content">
    <!-- Page container -->

    <div class="page-container">  
    <%@ include file="Navigator.jsp" %>

        <!-- Page content -->
        <div class="page-content"><br><br><br>
        
        <form action="addDepartment" role="form" novalidate>
                <div class="panel panel-default">
                  <div class="panel-heading"><h6 class="panel-title"><i class="icon-pencil3"></i>PERSONAL INFORMATION</h6></div>
                    <div class="panel-body">
                       
                       <div class="form-group">
                            
                         <div class="row">
                            <div class="col-md-2  subtitle">Search By:</div>
                            <div class="col-md-4">
                               <input type="text" name="serchBy" placeholder="-- select --" class="form-control" ng-model="serchBy" required>
                            </div>
                            <div class="col-md-6">
                                <input type="text" name="serchTerm" placeholder="search term" class="form-control" ng-model="serchTerm" required>
                            </div>
                          </div><br>
                            
                          <div class="row">
                               <div class="col-md-2  subtitle">Date Range:</div>
                               <div class="col-md-4">
                                    <input type="text" class="datepicker-restricted form-control" placeholder="From Date">
                                </div>
                                <div class="col-md-4">
                                     <input type="text" class="datepicker-restricted form-control" placeholder="To Date">
                               </div>
                               <div class="col-md-2">
                                    <input type="text" name="status" placeholder="-- select --" class="form-control" ng-model="status" required>
                               </div>
                         </div>
                           
                        </div>
                   </div>
                            
             <div class="form-actions text-right">
                    <input type="submit" value="Search" class="btn btn-danger">
                    <input type="reset" value="Clear" class="btn btn-default">
              </div>
        
        </form></br>
        
        <form action="#" role="form">
                <div class="panel panel-default">
                    <div class="panel-heading"><h6 class="panel-title"><i class="icon-pencil3"></i> Data Grid</h6></div>
                    <div class="panel-body">

                        <div class="block">
                            <div class="">
                                <table class="table table-bordered">
                                    <thead>
                                        <tr>

                                            <th class="">S.NO</th>
                                            <th class="">Ticket</th>
                                            <th class="">Date & Time</th>
                                            <th class="">Customer</th>
                                            <th class="">Subject</th>
                                            <th class="">Status</th>

                                        </tr>
                                    </thead>
                                    <tbody>
                                   
                                        <tr>
                                            <td>sample 1</td>
                                            <td>sample 2</td>
                                            <td>sample 3</td>
                                            <td>sample 4</td>
                                            <td>sample 5</td>
                                        </tr>
                                        
                                    </tbody>
                                </table>
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