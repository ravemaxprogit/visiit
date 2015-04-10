
<script type="text/javascript" src="resources/js/views/cntrypop.js"></script>		
							<div class="row">
								<div class="col-md-4">
									<select ng-init="selcntry = 0" ng-model="selcntry" name="country" id="country" class="form-control">
								        <option value="0">- Choose Country -</option>
								        <option ng-repeat="country in countries" value="{{country.countryId}}">{{country.countryName}}</option>
								    </select>
								</div>
								<div class="col-md-6">
									<select ng-model="selstate" name="state" id="state"	class="form-control" required>
									</select>
								</div>
							</div>
							<div class="row">
								<div class="col-md-4">
									<select ng-model="selcity" name="city" id="city" class="form-control" ng-change="getLoc(selcity)" required>
									</select>
								</div>
								<div class="col-md-6">
								    <ui-select multiple ng-model="multi.locations" theme="select2" ng-disabled="disabled" style="width: 299px;">
									    <ui-select-match placeholder="Select Locations...">{{$item.locName}} </ui-select-match>
									    <ui-select-choices repeat="place in places | propsFilter: {locName: $select.search, locCode: $select.search}">
									      <div ng-bind-html="place.locName | highlight: $select.search"></div>
									      <small>
									        Code: <span ng-bind-html="''+place.locCode | highlight: $select.search"></span>
									      </small>
									    </ui-select-choices>
									  </ui-select>
								</div>
							</div>
				 
			