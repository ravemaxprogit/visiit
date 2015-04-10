
                 <div class="panel-body"> 
                   <div data-ng-show="message.success" class="sucess_message">{{packageConditionResponse.message}}</div>
			       <div data-ng-show="message.error" class="sucess_error">{{packageConditionResponse.message}}</div>
                   <div class="row">
                   		<form name="packageConditionAdd" role="form" method="POST" novalidate>
                            <div class="col-md-6">
                               <input type="text" name="hoteldesc" ng-model="pcDescriptions" ng-focus="hideeerormsg();" placeholder="-- description --" class="form-control" maxlength="300" ng-model="hoteldesc" data-validator="required" data-required-error-message="Enter the condition">
                            </div>
                            <div class="col-md-4">
                                <input type="submit" data-validation-submit="packageConditionAdd" ng-click="savePackCondtion(packageConditionAdd)" value="Add" class="btn btn-danger">
                            </div>
                            </form>
                  </div><br>
                   
                  <div class="block">
                            <div class="col-md-12">
                                <table class="table table-bordered">
                                    <thead>
                                        <tr>
                                            <th class="">Description</th>
                                            <th class="">Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr data-ng-show="packConditions.Status=='Ok'"data-ng-repeat="packCondition in packConditions.packList">
                                            <td>{{packCondition.pcDescription}}</td>
                                            <td>
                                               <!--<a href=""><i style="color:#808080;" class="icon-pencil2"></i></a> &nbsp;&nbsp;|&nbsp;&nbsp; -->                                               
                                               <a ng-click="removePackCondtion(packCondition.pcId)"><i
											style="color: red;" class="icon-close"></i></a>
                                            </td>
                                        </tr>  
                                        <tr data-ng-show="packConditions.Status=='Error'">
											<td colspan="2">{{packConditions.message}}</td>
										</tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                  </div>
                                       