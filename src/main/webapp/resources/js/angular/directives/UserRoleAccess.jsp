<form action="searchUser" role="form" novalidate>
                <div class="panel-body">
                      <div class="form-group">
                         <div class="row">  
                            <div class="panel-title col-md-2">Role Name:</div>
                            <div class="col-md-6">
                                  <input type="text" name="vendor" placeholder="- Choose Role -" class="form-control" ng-model="Role" required>
                            </div> 
                            <div class="col-md-4">
                                 <a href="" btn btn-danger><i class="fa fa-search"></i></a>&nbsp;&nbsp;|&nbsp;&nbsp;
                                 <a href="" btn btn-danger><i class="fa fa-refresh"></i></a>
                            </div>
                        </div>
                   </div>
                </div>
        </form></br>
        
        <form action="#" role="form">
                <div class="panel panel-default">
                    <div class="panel-body">
                        <div class="block"></br>
                        <div class="row"> 
                            <div class="panel-heading col-md-2 block"><h6 class="panel-title"><i class="fa fa-navicon"></i>MODULES</h6></div>
                            <div class="panel-heading col-md-10 block"><h6 class="panel-title"><i class="fa fa-navicon"></i>PAGE DETAILS</h6></div>
                         </div>
                         <div class="row">
                             <div class="col-md-2">
                                <table class="table table-bordered">
                                <tr><td>Accounts</td></tr>
                                <tr><td>Settings</td></tr>
                                <tr><td>Reports</td></tr>
                                </table>
                              </div>
                              <div class="col-md-10">
                                <table class="table table-bordered">
                                    <thead>
                                        <tr>
                                            <th class="">Page Name</th>
                                            <th class="">View</th>
                                            <th class="">Add</th>
                                            <th class="">Modify</th>
                                            <th class="">Delete</th>
                                            <th class="">Download&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                            <i style="color:#428bca;" class="fa fa-download"></th>
                                            <th class=""></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                   
                                        <tr>
                                            <td>sample 1</td>
                                            <td>
                                                <span ng-click="viewcheck=!viewcheck" ng-hide="!viewcheck">
                                                   <a href=""><i style="color:#808080;" class="fa fa-check"></i></a>
                                                </span>
                                                <span ng-click="viewcheck=!viewcheck" ng-hide="viewcheck">
                                                    <a href=""><i style="color:#78FA89;" class="fa fa-check"></i></a>
                                                </span>
                                             </td>
                                            <td>
                                                <span ng-click="addcheck=!addcheck" ng-hide="!addcheck">
                                                   <a href=""><i style="color:#808080;" class="fa fa-check"></i></a>
                                                </span>
                                                <span ng-click="addcheck=!addcheck" ng-hide="addcheck">
                                                    <a href=""><i style="color:#78FA89;" class="fa fa-check"></i></a>
                                                </span>
                                            </td>
                                            <td>
                                                <span ng-click="modcheck=!modcheck" ng-hide="!modcheck">
                                                   <a href=""><i style="color:#808080;" class="fa fa-check"></i></a>
                                                </span>
                                                <span ng-click="modcheck=!modcheck" ng-hide="modcheck">
                                                    <a href=""><i style="color:#78FA89;" class="fa fa-check"></i></a>
                                                </span>
                                             </td>
                                            <td>
                                                <span ng-click="deletecheck=!deletecheck" ng-hide="!deletecheck">
                                                   <a href=""><i style="color:#808080;" class="fa fa-check"></i></a>
                                                </span>
                                                <span ng-click="deletecheck=!deletecheck" ng-hide="deletecheck">
                                                    <a href=""><i style="color:#78FA89;" class="fa fa-check"></i></a>
                                                </span>
                                            </td>
                                            <td>
                                                <span ng-click="downcheck=!downcheck" ng-hide="!downcheck">
                                                   <a href=""><i style="color:#808080;" class="fa fa-check"></i></a>
                                                </span>
                                                <span ng-click="downcheck=!downcheck" ng-hide="downcheck">
                                                    <a href=""><i style="color:#78FA89;" class="fa fa-check"></i></a>
                                                </span>
                                             </td>
                                            <td>
                                                <span ng-click="accesscheck=!accesscheck" ng-hide="accesscheck">
                                                  <div class="form-actions text-middle">
                                                     <input type="submit" value="  +   " class="btn btn-primary">
                                                  </div>
                                                </span>
                                                <span ng-click="accesscheck=!accesscheck" ng-hide="!accesscheck">
                                                    <a href=""><i style="color:#808080;" class="fa fa-floppy-o"></i></a> &nbsp;&nbsp;&nbsp;
                                                    <a href=""><i style="color:#F38598;" class="fa fa-times"></i></a>
                                                </span>
                                            </td>
                                        </tr>
                                        
                                        <tr>
                                            <td>sample 1</td>
                                            <td><a href=""><i style="color:#78FA89;" class="fa fa-check"></i></a></td>
                                            <td><a href=""><i style="color:#78FA89;" class="fa fa-check"></i></a></td>
                                            <td><a href=""><i style="color:#808080;" class="fa fa-check"></i></a></td>
                                            <td><a href=""><i style="color:#808080;" class="fa fa-check"></i></a></td>
                                            <td><a href=""><i style="color:#78FA89;" class="fa fa-check"></i></a></td>
                                            <td>
                                            <p>
                                                <a href=""><i style="color:#F57A8F;" class="fa fa-times"></i></a> &nbsp;&nbsp;&nbsp;
                                                <a href=""><i style="color:#808080;" class="icon-pencil2"></i></a>
                                            </p>
                                            </td>
                                        </tr>
                                        
                                    </tbody>
                               </table>
                               </div>
                            </div>                    
                        </div>
                    </div>
                </div>
            </form>