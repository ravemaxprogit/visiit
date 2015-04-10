<form name="savePackage" role="form" method="POST" novalidate>
                <div class="panel-body" ng-init="getPackLoc(pkId)">    
                     
                     <div class="col-md-8">
                      <div class="row mrgin-row">
                            <div class="col-md-4 label-styl">Name:</div>
                            <div class="col-md-6">
                              <input type="text" name="packageName" placeholder="Package name" class="form-control margin-zero" ng-model="packName" ng-change="getpackageName();">
                              <div data-ng-show="package_name_error" class="sucess_error1" ng-init="nameError='Enter the package name'">{{nameError}}</div>
                            </div>                                                     
                       </div>                        
                        <div class="row mrgin-row">
                            <div class="col-md-4 label-styl">Code:</div>
                            <div class="col-md-6">
                               <input type="text" name="packageCode" placeholder="" class="form-control margin-zero" ng-model="packCode" readonly="readonly">
                            </div>
                       </div>
                       <!-- <multi-location></multi-location> -->
                       
                         <div class="row mrgin-row">
                            <div class="col-md-4 label-styl">Contry:</div>
                            <div class="col-md-6" ng-click="getCountry()">
                                    <ui-select multiple ng-model="multi.country" theme="select2" ng-disabled="disabled" style="padding-left:0px;width: 295px;" ng-change="getState();multiValid();" >
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
                        </div>
                        <div class="row mrgin-row">
                            <div class="col-md-4 label-styl">State:</div>
                            <div class="col-md-6" ng-click="checkLoc()">
									<ui-select multiple ng-model="multi.state" theme="select2" ng-disabled="disabled" style="width: 295px;" ng-change="getCity();multiValid();">
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
							 <div class="col-md-12 col-sm-12" style="margin-left:12px;padding-bottom: 13px;">
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
							 <div class="col-md-12 col-sm-12" style="margin-left:12px;">
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
                         <div class="row mrgin-row">
                            <div class="col-md-4 label-styl">Category:</div>
                            <div class="col-md-6" ng-click="getCategory()">
									<ui-select multiple ng-model="multi.category" theme="select2" ng-disabled="disabled" style="padding-left:0px;width: 295px;">
									    <ui-select-match placeholder="Select category...">{{$item.catTitle}} </ui-select-match>
									    <ui-select-choices repeat="con in categories | propsFilter: {catTitle: $select.search, catId: $select.search}">
									      <div ng-bind-html="con.catTitle | highlight: $select.search"></div>
									      <small>
									        Code: <span ng-bind-html="''+con.catId | highlight: $select.search"></span>
									      </small>
									    </ui-select-choices>
									  </ui-select>
									  <div data-ng-show="category_mutli_error" class="sucess_error1">Select Category</div>
								</div>
                       </div>
                   </div>
                   
                   <div class="col-md-4">
                        <div class="row mrgin-row">
                           <div class="col-md-4 label-styl">Type</div>
                           <div class="col-md-6">
                                <select ng-init="selpacktype=1" name="packageType" ng-model="selpacktype" name="packtype" id="packtype" class="form-control">
								        <option ng-repeat="typ in packtype" ng-selected="pkType == typ.id" value="{{typ.id}}">{{typ.name}}</option>
								</select>
                           </div>                          
                        </div>
                        <div class="row mrgin-row">
                           <div class="col-md-4 label-styl">Special</div>
                           <div class="col-md-6">
                                <select ng-init="selpackSpecial=1" name="packageSpecial" ng-model="selpackSpecial" name="packpkspecial" id="packpkspecial" class="form-control" data-ng-change="changeSpecial()">
								        <option ng-repeat="spc in packspecial" ng-selected="pkSpecial == spc.id" value="{{spc.name}}">{{spc.name}}</option>
								</select>
                           </div>                           
                        </div>
                         <div class="row mrgin-row">
                           <div class="col-md-4 label-styl">Order</div>
                           <div class="col-md-6">
                                <select name="packageOrder" ng-model="selpackOrder" name="packpkOrder" id="packpkOrder" class="form-control">
								        <option ng-repeat="ord in packorder" ng-selected="pkOrder == ord.id" value="{{ord.name}}">{{ord.name}}</option>
								</select>
                           </div>                           
                        </div> 
                        <div class="row mrgin-row">
                            <div class="col-md-4 label-styl">Vendor:</div>
                            <div class="col-md-6" ng-click="getVend();multiValid();">
                               <ctypeahead items="items" name="packageVendorname"  prompt="Start typing a Vendor" title="viVendorName" model="vendName" modobj="vendObj" modby="viVendorName" on-select="onItemSelected()" />
                               <div data-ng-show="package_vendorname_error" class="sucess_error1">Start typing a vendor name</div>
                            </div>
                       </div>
                        
                        <div class="row mrgin-row">
                            <div class="col-md-4 label-styl">Days:</div>
                            <div class="col-md-6">
                               <input type="number" name="packageDays" placeholder="No. of days" class="form-control margin-zero" ng-model="packDays" min="1" max="99"  ng-maxlength="2" maxlength="2" ng-change="checkDayNight()">
                               <div data-ng-show="package_day_error" class="sucess_error1">Enter no of days</div>                             
                               <span class="error" style="color:red" ng-show="savePackage.packageDays.$error.number">Please enter numbers only</span>
                               <span class="error" style="color:red" ng-show="savePackage.packageDays.$error.min">Please enter valid number</span>
                               <span class="error" style="color:red" ng-show="savePackage.packageDays.$error.maxlength">Please enter maximum two numbers</span>                                                                                         
                            </div>
                       </div>
                       
                       <div class="row mrgin-row">
                            <div class="col-md-4 label-styl">Nights:</div>
                            <div class="col-md-6">                              
                               <input type="number" name="packageNights" placeholder="No. of nights" class="form-control margin-zero" ng-model="packNights" min="1" max="{{packDays}}" ng-maxlength="2" maxlength="2" ng-change="checkDayNight()">
                               <div data-ng-show="package_night_error" class="sucess_error1">Enter no of days</div>                             
                               <span class="error" style="color:red" ng-show="savePackage.packageNights.$error.number">Please enter numbers only</span>
                               <span class="error" style="color:red" ng-show="savePackage.packageNights.$error.min">Please enter valid number</span>
                               <span class="error" style="color:red" ng-show="savePackage.packageNights.$error.maxlength">Please enter maximum two numbers</span>
                               <span class="error" style="color:red" ng-show="savePackage.packageNights.$error.max || dayError">Night should be less than days</span>                               
                            </div>
                       </div>
                       
                       <div class="row mrgin-row">
                            <div class="col-md-10">
                                <textarea class="form-control margin-zero" name="packageOverview" id="packageOverview" rows="2" ng-model="packDesc" ng-change="getpackageDesc();" placeholder="Over View"></textarea>
                               <div data-ng-show="package_overview_error" class="sucess_error1">Enter the package description</div>
                            </div>
                       </div> 
                     </div>   
                 </div>                
                   <br>
                   <div class="panel panel-default">
                       <div class="panel-body">
                       	<div>	
					    	<input type="number" ng-model="packagefeaturesCount" max="7" name="packagefeaturesCount" id="packagefeaturesCount" style="display:none">
					    	<span class="errorSpan" ng-show="savePackage.packagefeaturesCount.$error.max">You can select maximum 7 features only.</span>						
						</div>
						<div class="row">
					<div class="col-md-2">Airport Pickup</div>
					<div class="col-md-1">
					  <span ng-click="aircheck=!aircheck;packagefeaturesCount=packagefeaturesCount-1" ng-show="aircheck">
                                     <a href=""><img
												 ng-src="{{appIcons.airportStationActive}}" /></a>
                                   </span>
                                   <span ng-click="aircheck=!aircheck;packagefeaturesCount=packagefeaturesCount+1" ng-show="!aircheck">
                                     <a href=""><img
												 ng-src="{{appIcons.airportStationInactive}}" /></a>
                                   </span>
				   </div>
					<div class="col-md-2">Bus</div>
					<div class="col-md-1">
					   <span ng-click="buscheck=!buscheck;packagefeaturesCount=packagefeaturesCount-1" ng-show="buscheck">
                                     <a href=""><img
												 ng-src="{{appIcons.busActive}}" /></a>
                                   </span>
                                   <span ng-click="buscheck=!buscheck;packagefeaturesCount=packagefeaturesCount+1" ng-show="!buscheck">
                                     <a href=""><img
												 ng-src="{{appIcons.busInactive}}" /></a>
                                   </span>
				   </div>
					<div class="col-md-2">Campfire</div>
					<div class="col-md-1">
					   <span ng-click="campcheck=!campcheck;packagefeaturesCount=packagefeaturesCount-1" ng-show="campcheck">
                                     <a href=""><img
												 ng-src="{{appIcons.campfireActive}}" /></a>
                                   </span>
                                   <span ng-click="campcheck=!campcheck;packagefeaturesCount=packagefeaturesCount+1" ng-show="!campcheck">
                                     <a href=""><img
												 ng-src="{{appIcons.campfireInactive}}" /></a>
                                   </span>
				   </div>
					<div class="col-md-2">Ferry</div>
					<div class="col-md-1">
					  <span ng-click="ferrycheck=!ferrycheck;packagefeaturesCount=packagefeaturesCount-1" ng-show="ferrycheck">
                                     <a href=""><img
												 ng-src="resources/images/icons/packageEnable/Ferry.png" /></a>
                                   </span>
                                   <span ng-click="ferrycheck=!ferrycheck;packagefeaturesCount=packagefeaturesCount+1" ng-show="!ferrycheck">
                                     <a href=""><img
												 ng-src="{{appIcons.ferryInactive}}" /></a>
                                   </span>
				   </div>
			   </div>
			     <div class="row">
					<div class="col-md-2">Flight</div>
					<div class="col-md-1">
					    <span ng-click="planecheck=!planecheck;packagefeaturesCount=packagefeaturesCount-1" ng-show="planecheck">
                                      <a href=""><img
												 ng-src="{{appIcons.flightActive}}" /></a>
                                   </span>
                                   <span ng-click="planecheck=!planecheck;packagefeaturesCount=packagefeaturesCount+1" ng-show="!planecheck">
                                     <a href=""><img
												 ng-src="{{appIcons.flightInactive}}" /></a>
                                   </span>
				   </div>
					<div class="col-md-2">Food</div>
					<div class="col-md-1">
					  <span ng-click="foodcheck=!foodcheck;packagefeaturesCount=packagefeaturesCount-1" ng-show="foodcheck">
                                     <a href=""><img
												 ng-src="{{appIcons.breakfastActive}}" /></a>
                                   </span>
                                   <span ng-click="foodcheck=!foodcheck;packagefeaturesCount=packagefeaturesCount+1" ng-show="!foodcheck">
                                     <a href=""><img
												 ng-src="{{appIcons.breakfastInactive}}" /></a>
                                   </span>
				   </div>
					<div class="col-md-2">Games</div>
					<div class="col-md-1">
					   <span ng-click="gamescheck=!gamescheck;packagefeaturesCount=packagefeaturesCount-1" ng-show="gamescheck">
                                     <a href=""><img
												 ng-src="{{appIcons.gamesActive}}" /></a>
                                   </span>
                                   <span ng-click="gamescheck=!gamescheck;packagefeaturesCount=packagefeaturesCount+1" ng-show="!gamescheck">
                                     <a href=""><img
												 ng-src="{{appIcons.gamesInactive}}" /></a>
                                   </span>
				   </div>
					<div class="col-md-2">Guide</div>
					<div class="col-md-1">
					   <span ng-click="guidecheck=!guidecheck;packagefeaturesCount=packagefeaturesCount-1" ng-show="guidecheck">
                                     <a href=""><img
												 ng-src="{{appIcons.guideActive}}" /></a>
                                   </span>
                                   <span ng-click="guidecheck=!guidecheck;packagefeaturesCount=packagefeaturesCount+1" ng-show="!guidecheck">
                                     <a href=""><img
												 ng-src="{{appIcons.guideInactive}}" /></a>
                                   </span>
				   </div>
			   </div>
			   <div class="row">
					<div class="col-md-2">Hotel</div>
					<div class="col-md-1">
					   <span ng-click="hotelchck=!hotelchck;packagefeaturesCount=packagefeaturesCount-1" ng-show="hotelchck">
                                     <a href=""><img
												 ng-src="{{appIcons.hotelActive}}" /></a>
                                   </span>
                                   <span ng-click="hotelchck=!hotelchck;packagefeaturesCount=packagefeaturesCount+1" ng-show="!hotelchck">
                                     <a href=""><img
												 ng-src="{{appIcons.hotelInactive}}" /></a>
                                   </span>
				   </div>
					<div class="col-md-2">Sea</div>
					<div class="col-md-1">
					   <span ng-click="seacheck=!seacheck;packagefeaturesCount=packagefeaturesCount-1" ng-show="seacheck">
                                     <a href=""><img
												 ng-src="{{appIcons.seaActive}}" /></a>
                                   </span>
                                   <span ng-click="seacheck=!seacheck;packagefeaturesCount=packagefeaturesCount+1" ng-show="!seacheck">
                                     <a href=""><img
												 ng-src="{{appIcons.seaInactive}}" /></a>
                                   </span>
				   </div>
					<div class="col-md-2">Shows</div>
					<div class="col-md-1">
					   <span ng-click="showscheck=!showscheck;packagefeaturesCount=packagefeaturesCount-1" ng-show="showscheck">
                                     <a href=""><img
												 ng-src="{{appIcons.showActive}}" /></a>
                                   </span>
                                   <span ng-click="showscheck=!showscheck;packagefeaturesCount=packagefeaturesCount+1" ng-show="!showscheck">
                                     <a href=""><img
												 ng-src="{{appIcons.showInactive}}" /></a>
                                   </span>
				   </div>
					<div class="col-md-2">Train</div>
					<div class="col-md-1">
					  <span ng-click="traincheck=!traincheck;packagefeaturesCount=packagefeaturesCount-1" ng-show="traincheck">
                                     <a href=""><img
												 ng-src="{{appIcons.trainActive}}" /></a>
                                   </span>
                                   <span ng-click="traincheck=!traincheck;packagefeaturesCount=packagefeaturesCount+1" ng-show="!traincheck">
                                     <a href=""><img
												 ng-src="{{appIcons.trainInactive}}" /></a>
                                   </span>
				   </div>
			   </div>
			   <div class="row">
					<div class="col-md-2">Transfer</div>
					<div class="col-md-1">
					    <span ng-click="transfercheck=!transfercheck;packagefeaturesCount=packagefeaturesCount-1" ng-show="transfercheck">
                                     <a href=""><img
												 ng-src="{{appIcons.transferActive}}" /></a>
                                   </span>
                                   <span ng-click="transfercheck=!transfercheck;packagefeaturesCount=packagefeaturesCount+1" ng-show="!transfercheck">
                                     <a href=""><img
												 ng-src="{{appIcons.transferInactive}}" /></a>
                                   </span>
				   </div>
					<div class="col-md-2">Visa</div>
					<div class="col-md-1">
					   <span ng-click="visacheck=!visacheck;packagefeaturesCount=packagefeaturesCount-1" ng-show="visacheck">
                                     <a href=""><img
												 ng-src="{{appIcons.visaActive}}" /></a>
                                   </span>
                                   <span ng-click="visacheck=!visacheck;packagefeaturesCount=packagefeaturesCount+1" ng-show="!visacheck">
                                     <a href=""><img
												 ng-src="{{appIcons.visaInactive}}" /></a>
                                   </span>
				   </div>
					<div class="col-md-2">Zoo</div>
					<div class="col-md-1">
					    <span ng-click="zoocheck=!zoocheck;packagefeaturesCount=packagefeaturesCount-1" ng-show="zoocheck">
                                     <a href=""><img
												 ng-src="{{appIcons.zooActive}}" /></a>
                                   </span>
                                   <span ng-click="zoocheck=!zoocheck;packagefeaturesCount=packagefeaturesCount+1" ng-show="!zoocheck">
                                     <a href=""><img
												 ng-src="{{appIcons.zooInactive}}" /></a>
                                   </span>
				   </div>
			   </div>
                        </div>
                   </div>
              <div class="form-actions text-right" >
						<input type="button" value="Save" data-validation-submit="savePackage" ng-click="savePack();" class="btn btn-danger"> 
						<input type="reset" value="Clear" class="btn btn-primary">
						<input type="reset" value="Cancel" ng-click="cancel()" class="btn btn-default">
		      </div>
           </form>       
<style>

input[type=number] {
    -moz-appearance:textfield;
}

input[type=number]::-webkit-inner-spin-button,
input[type=number]::-webkit-outer-spin-button {
  display:none;
 -webkit-appearance: none; 
  margin: 0; 
}

</style>