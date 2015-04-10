
<div ng-controller="genderController as genderCtrl">
                                    <div class="btn-group" data-toggle="buttons" ng-repeat="gender in genderCtrl.gender">
                                    <label class="btn btn-default">
                                    <input type="radio" name="gender" value="" /> {{gender}}
                                    </label>
                                   </div></div>
                                   
                          