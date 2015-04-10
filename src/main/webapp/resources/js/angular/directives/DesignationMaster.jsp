
<form action="searchUser" role="form" novalidate>
                <div class="panel-body">
                      <div class="form-group">
                         <div class="row">  
                            <div class="col-md-8">
                                  <input type="text" name="vendor" placeholder="- Choose Designation -" class="form-control" ng-model="Designation" required>
                            </div> 
                            <div class="form-actions text-middle">
                            <input type="submit" value="search" class="btn btn-danger">
                            </div>
                        </div>
                   </div>
                </div>
        </form></br>
        
        <form action="addDesignation" role="form">
                <div class="panel panel-default">
                    <div class="panel-heading"><h6 class="panel-title"><i class="icon-pencil3"></i> Data Grid</h6></div>
                    <div class="panel-body">

                        <div class="block">
                            <div class="">
                                <table class="table table-bordered">
                                    <thead>
                                        <tr>

                                            <th class="">Code</th>
                                            <th class="">Designation Name</th>
                                            <th class="">Active</th>
                                            <th></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                   
                                        <tr>
                                           <td><input type="text" name="desgCode" class="form-control" ng-model="desgCode" required></td>
                                            <td><input type="text" name="desgName" class="form-control" ng-model="desgName" required></td>
                                            <td>
                                                <span ng-click="isDesg=!isDesg" ng-hide="!isDesg">
                                                   <a href=""><i style="color:#808080;" class="fa fa-check"></i></a>
                                                </span>
                                                <span ng-click="isDesg=!isDesg" ng-hide="isDesg">
                                                    <a href=""><i style="color:#78FA89;" class="fa fa-check"></i></a>
                                                </span>
                                            </td>
                                            <td>
                                                <span ng-click="dsgncheck=!dsgncheck" ng-hide="dsgncheck">
                                                  <div class="form-actions text-middle">
                                                     <button class="btn btn-primary btn-large">Add</button>
                                                  </div>
                                                </span>
                                                <span ng-click="dsgncheck=!dsgncheck" ng-hide="!dsgncheck">
                                                    <button class="btn btn-primary btn-large" onclick="submit()"><i style="color:#808080;" class="fa fa-floppy-o"></i></button>&nbsp;&nbsp;&nbsp;
                                                    <button class="btn btn-danger btn-large"><i style="color:#F38598;" class="fa fa-times"></i></button>
                                                </span>
                                            </td>
                                        </tr>
                                         <c:if test="${!empty designationList}">
                                       <c:forEach items="${designationList}" var="desg">
                                        <tr>
                                            <td>${desg.dsgCode}</td>
                                            <td>${desg.dsgName}</td>
                                            <td><a href=""><i style="color:#78FA89;" class="fa fa-check"></i></a></td>
                                            <td>
                                            <p>
                                                <a href=""><i style="color:#F57A8F;" class="fa fa-times"></i></a> &nbsp;&nbsp;&nbsp;
                                                <a href=""><i style="color:#808080;" class="icon-pencil2"></i></a>
                                            </p>
                                            </td>
                                        </tr>
                                        </c:forEach>
                                        </c:if>
                                    </tbody>
                                </table>
                            </div>
                        </div>


                    </div>
                </div>
            </form>

