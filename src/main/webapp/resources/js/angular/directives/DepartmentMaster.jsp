<div ng-controller="deptController">
                <div class="panel-body">
                      <div class="form-group">
                         <div class="row">  
                            
                            <div class="col-md-6">
	                                 <ctypeahead items="items" prompt="Start typing a Department" title="deptName" subtitle="deptCode" model="name" modobj="obj" modby="deptName" on-select="onItemSelected()" />
                            </div> 
                            <div class="col-md-2">&nbsp;</div>  
                            <div class="form-actions text-middle">
                            <input type="submit" value="search" class="btn btn-danger">
                            </div>
                        </div>
                   </div>
                </div></br>
        
                <div class="panel panel-default">
                    <div class="panel-heading"><h6 class="panel-title"><i class="icon-pencil3"></i> Data Grid</h6></div>
                    <div class="panel-body">

                        <div class="block">
                            <div class="">
                                <table class="table table-bordered">
                                    <thead>
                                        <tr>
                                            <th class="">Code</th>
                                            <th class="">Department Name</th>
                                            <th class="">Active</th>
                                            <th></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                   
                                        <tr>
                                            <input type="hidden" name="deptId" ng-model="deptId">
                                            <td><input type="text" name="deptCode" class="form-control" ng-model="deptCode" value={{deptCode}} ng-disabled="!deptCheck" required></td>
                                            <td><input type="text" name="deptName" class="form-control" ng-enter="search()" ng-model="deptName" ng-disabled="!deptCheck" required></td>
                                            <td>
                                                <span ng-click="isDept=!isDept" ng-hide="!isDept">
                                                   <a href=""><i style="color:#808080;" class="fa fa-check"></i></a>
                                                </span>
                                                <span ng-click="isDept=!isDept" ng-hide="isDept">
                                                    <a href=""><i style="color:#78FA89;" class="fa fa-check"></i></a>
                                                </span>
                                            </td>
                                            <td>
                                                <span ng-click="getCode('getDeptCode')" ng-hide="deptCheck">
                                                  <div class="form-actions text-middle">
                                                     <button class="btn btn-primary">+</button>
                                                  </div>
                                                </span>
                                                <span ng-hide="!deptCheck">
                                                    <a href="" ng-click="save()"><i style="color:#428bca;" class="fa fa-floppy-o"></i></a> &nbsp;&nbsp;&nbsp;
                                                    <a href="" ng-click="clear()"><i style="color:#F38598;" class="fa fa-times"></i></a>
                                                </span>
                                            </td>
                                        </tr>
                                        <tr ng-repeat="d in departments">
                                            <td>{{d.deptCode}}</td>
                                            <td>{{d.deptName}}</td>
                                            <td><a href=""><i style="color:#78FA89;" class="fa fa-check"></i></a></td>
                                            <td>
                                            <p>
                                                <a href="" ng-click="update(d.deptId,d.deptCode,d.deptName)"><i style="color:#808080;" class="icon-pencil2"></i></a>&nbsp;&nbsp;&nbsp;
                                                <a href="" ng-really-message="Are you sure?" ng-really-click="remove(d.deptId)"><i style="color:#F57A8F;" class="fa fa-trash" ></i></a> 
                                                
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
           