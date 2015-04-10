   <form role="form" name="packageHotel" id="hotelFrm" novalidate>
              <div class="panel-body">
                   <div class="col-md-6">
                    <div data-ng-show="message.success" class="sucess_message">{{packageHotelResponse.message}}</div>
			       <div data-ng-show="message.error" class="sucess_error">{{packageHotelResponse.message}}</div>
                   <div class="row">
                            <div class="col-md-12">
                             <label>City:</label>
                            	<select data-ng-model="pkhotel.city" ng-focus="hideeerormsg();" name="city" id="city" data-ng-change="changePackageCity()" class="form-control" data-validator="required"  data-valid-method="watch" data-required-error-message="Select your City" >
                            		<option selected="selected" value="">- Choose City -</option>
									<option data-ng-if="packageHotelsCities.Status=='Ok'" data-ng-repeat="packageHotelsCity in packageHotelsCities.cities" value="{{packageHotelsCity.cityId}}">{{packageHotelsCity.cityName}}</option>
								</select>
                            </div>
                            </div>
                            <div class="row">
                            <div class="col-md-12">
                            <label>Hotel:</label>
                               <select data-ng-model="pkhotel.pkhotelId" ng-focus="hideeerormsg();" name="hotel" id="hotel" class="form-control" data-validator="required"  data-valid-method="watch" data-required-error-message="Select Hotel" >
                               		<option selected="selected" value="">- Choose Hotel -</option>
									<option data-ng-if="packageHotelsByCities.Status=='Ok'" data-ng-repeat="packageHotelByCity in packageHotelsByCities.hotel" value="{{packageHotelByCity.hdSeqId}}">{{packageHotelByCity.hdName}}</option>
								</select>
                            </div>
                            </div>
                            <div class="row">
                            <div class="col-md-12">
	                            <label>Description:</label><br/>
	                            	<textarea type="text" class="form-control" ng-focus="hideeerormsg();" name="pkhDesc" maxlength="300" data-ng-model="pkhotel.pkhDesc" data-validator="required" data-required-error-message="Enter the Hotel Description" ></textarea>
	                            </div>
                            </div>
                            <br/>
                            <div class="row">
                             <!-- <div class="col-md-12">
                                <div id="vendor_active_status" style="display:none">{{(pkhotel.status)?1:0}}</div>
                                    <label>Active:&nbsp;&nbsp;&nbsp;
                                    <input style="width:50px;" type="radio" ng-model="pkhotel.status" id="isActive"
										data-ng-click="changeStatusActive()"
										data-ng-checked="pkhotel.status"
										value=1
										class="hotelPackisActive form-control" />    
										</label>                                
                                </div>-->
                                
                                <div class="col-md-12">                                
                                    <label>Active:&nbsp;&nbsp;&nbsp;
                                   <span ng-click="pkhotel.pkhotelIsactiveTrue=!pkhotel.pkhotelIsactiveTrue" ng-hide="!pkhotel.pkhotelIsactiveTrue">
								<a href=""><i style="color: #21b384;"
									class="fa fa-check-square rect-check"></i></a>
							</span> <span ng-click="pkhotel.pkhotelIsactiveTrue=!pkhotel.pkhotelIsactiveTrue" ng-hide="pkhotel.pkhotelIsactiveTrue">
								<a href=""><i style="color: #808080;"
									class="fa fa-check-square rect-check"></i></a>
							</span> 
										</label>                                
                                </div>                                
                               
                                </div>
                            
                  </div>
                  <div class="col-md-6">
                  	         <!--  ng-show="imgIconList.length == 0"-->
                             <div class="row" ng-show="pkhotelUploadshow">
                                <div class="col-md-6">
                                   <img data-ng-show="iconPkHotelImage" ng-src="{{iconPkHotelImage}}" class="img-responsive" alt="Day Icon" width="300" height="200">
                                </div>
                                 <div class="col-md-6">
                                      <div class="row">
                                      <div class="col-md-10">
                                         <button  type="file" ng-file-select ng-model="pkHotelImg" ng-file-change="fileIconSelected($files, $event)" ng-multiple="true" accept="image/*,*.pdf" resetOnClick="true" class="btn btn-primary">Upload</button>
                                      </div></div><br>
                                      <div class="row">
                                     <div class="col-md-10" >
                                        <input type="button" data-ng-show="hideImageHotelSaveBtn" value="Save" ng-click="savePkHotelImg(phId)" class="btn btn-danger">
                                    </div></div>
                                 </div>
                                  <div class="col-md-12"><span style="color:hsl(208, 56%, 53%);font-weight:bold;">Note : Image size should be Width- 150 x Height- 140 pixels</span></div>
                                 <div class="col-md-12 row">
                                 	<span class="errorSpan" ng-show="fileError">{{fileError}}</span>
                                 </div>
                                
                           </div><br/><br/>
                    
                  </div>
                   <div class="col-md-7">
                <div class="row">
                            <div class="col-md-12">
                                <input type="submit" data-validation-submit="packageHotel" data-ng-click="savePackageHotel(packageHotel)" value="Save" class="btn btn-danger">
                            </div>
                            </div>
                </div>
                
                  <br/><br/>
                  <div class="col-md-12">
                   <div class="block"><br/><br/>
                            <div class="col-md-12">
                                <table class="table table-bordered">
                                    <thead>
                                        <tr>
                                            <th class="">Hotel</th>
                                            <th class="">Type</th>
                                            <th class="">City</th>
                                            <th class="">Add/Remove Image</th>
                                            <th class="">Delete</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr data-ng-show="packageHotels.Status=='Ok'" data-ng-repeat="pkHotel in packageHotels.hotelList">
                                            <td>{{pkHotel.hdName}}</td>
                                            <td>{{pkHotel.hdType}}</td>
                                            <td>{{pkHotel.cityName}}</td>
                                            <td ng-show="!pkHotel.imageUrl" ng-click="packageHotelImg(pkHotel.phId)"><img width="50" ng-src="resources/images/no-imag.png"></td>
                                             <td ng-show ="pkHotel.imageUrl" ng-click="packageHotelImg(pkHotel.phId,act.imageUrl)"><img width="50" ng-src="{{imagePath+pkHotel.imageUrl}}"></td>
                                            
                                            <td>
                                              <!--  <a href=""><i style="color:#808080;" class="icon-pencil2"></i></a> &nbsp;&nbsp;|&nbsp;&nbsp; -->
                                               <a href="" data-ng-click="removePackageHotel(pkHotel.phId)"><i style="color: red;" class="icon-close"></i></a>
                                            </td>
                                            
                                        </tr>  
                                         <tr data-ng-show="packageHotels.Status=='Error'">
											<td colspan="5">{{packageHotels.message}}</td>
										</tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                </div>
                </div>
                </form>
               <script>
	//$(document).ready(function() {
			var vendortog = 1;
		$(".hotelPackisActive").on('click', function() {			
			if (vendortog % 2 == 0) {
				$(".hotelPackisActive").prop('checked', true);
				$(".hotelPackisActive").val('y');
			} else {
				$(".hotelPackisActive").prop('checked', false);
				$(".hotelPackisActive").val('y');
			}
			vendortog++;
		});
	//});
</script>