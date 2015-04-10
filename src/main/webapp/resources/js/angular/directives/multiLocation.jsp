 <div class="panel-body"> 
							<div class="row">
								<div class="col-md-4" ng-click="getCountry()">
								 <div class="col-md-4 label-styl" style="padding-left:0px;">Contry:</div>
									<ui-select multiple ng-model="multi.country" theme="select2" ng-disabled="disabled" style="padding-left:0px;width: 199px;" ng-change="getState();multiValid();">
									    <ui-select-match placeholder="Select Countries...">{{$item.countryName}} </ui-select-match>
									    <ui-select-choices repeat="con in countries | propsFilter: {countryName: $select.search, countryCode: $select.search}">
									      <div ng-bind-html="con.countryName | highlight: $select.search"></div>
									      <small>
									        Code: <span ng-bind-html="''+con.countryCode | highlight: $select.search"></span>
									      </small>
									    </ui-select-choices>
									  </ui-select>
									  <div data-ng-show="county_mutli_error" class="sucess_error1">Select Countries</div>
								</div>
								<div class="col-md-6" ng-click="checkLoc()">
								<div class="col-md-4 label-styl" style="padding-left:0px;">State:</div>
								    <ui-select multiple ng-model="multi.state" theme="select2" ng-disabled="disabled" style="width: 299px;" ng-change="getCity();multiValid();">
									    <ui-select-match placeholder="Select States...">{{$item.stateName}} </ui-select-match>
									    <ui-select-choices repeat="st in States | propsFilter: {stateName: $select.search, stateCode: $select.search}">
									      <div ng-bind-html="st.stateName | highlight: $select.search"></div>
									      <small>
									        Code: <span ng-bind-html="''+st.stateCode | highlight: $select.search"></span>
									      </small>
									    </ui-select-choices>
									  </ui-select>
									  <div data-ng-show="state_mutli_error" class="sucess_error1">Select States</div>
								</div>
							</div>
							 <div class="row" ng-click="checkLoc()">
							 <div class="col-md-6 label-styl">City:</div>
							 <div class="col-md-12 col-sm-12" style="margin-left:12px;padding: 0px 0px;">
								<ui-select multiple ng-model="multi.city" theme="select2" ng-disabled="disabled" style="width: 516px;" ng-change="getLocation();multiValid();">
									    <ui-select-match placeholder="Select Cities...">{{$item.cityName}} </ui-select-match>
									    <ui-select-choices repeat="c in cities | propsFilter: {cityName: $select.search, cityCode: $select.search}">
									      <div ng-bind-html="c.cityName | highlight: $select.search"></div>
									      <small>
									        Code: <span ng-bind-html="''+c.cityCode | highlight: $select.search"></span>
									      </small>
									    </ui-select-choices>
									  </ui-select>
									  <div data-ng-show="city_mutli_error" class="sucess_error1">Select Cities</div>
								</div>	  
							</div>
							 <div class="row">
							 <div class="col-md-6 label-styl">Location:</div>
							 <div class="col-md-12 col-sm-12" style="margin-left:12px;padding: 0px 0px;">
							 	<ui-select multiple ng-model="multi.locations" theme="select2" ng-disabled="disabled" style="width: 516px;" ng-change="multiValid();">
									    <ui-select-match placeholder="Select Locations...">{{$item.locName}} </ui-select-match>
									    <ui-select-choices repeat="place in places | propsFilter: {locName: $select.search, locCode: $select.search}">
									      <div ng-bind-html="place.locName | highlight: $select.search"></div>
									      <small>
									        Code: <span ng-bind-html="''+place.locCode | highlight: $select.search"></span>
									      </small>
									    </ui-select-choices>
									  </ui-select>
									  <div data-ng-show="locations_mutli_error" class="sucess_error1">Select Locations</div>
							</div>
							</div>
							</div>