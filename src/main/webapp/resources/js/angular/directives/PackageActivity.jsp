		<form name="packageAct" id="packageAct" role="form" method="POST" novalidate>
                    <div class="panel-body"> 
                   <div class="row">
                        <div data-ng-show="message.success" class="sucess_message">{{packageActivityResponse.message}}</div>
			       	   <div data-ng-show="message.error" class="sucess_error">{{packageActivityResponse.message}}</div>
			       </div>
                   <div class="col-md-6">
                   <div class="row mrgin-row">
                           <div class="col-md-2  label-styl">Day:</div>
                            <div class="col-md-10">
                                <input type="number" name="day" id="day" class="form-control margin-zero" ng-focus="hideeerormsg();" ng-model="day" data-validator="required,validateNumber" data-required-error-message="Enter the no of day" min="1" max="100">                                                         
                               </div>                           
                       </div>                       
                       <div class="row mrgin-row">
                          <div class="col-md-2  label-styl">Activities:</div>
                          <div class="col-md-10">
                             <textarea class="form-control margin-zero" rows="2" name="actDesc" id="actDesc" ng-focus="hideeerormsg();" maxlength="300" ng-model="actDesc" data-validator="required" data-required-error-message="Enter the activity details"></textarea>
                          </div>                          
                       </div>                                       
                       <div class="row mrgin-row">
                       	  <div class="col-md-2">
                          </div>
                          <div class="col-md-2">
                          <input type="button" data-validation-submit="packageAct" data-ng-click="addAct('packageAct');" value="Save" class="btn btn-danger">                           
                          </div>
                       </div>
                       </div>
                       <div class="col-md-6">
               		<div class="col-md-7">
                       <div class="block">
                       	<div >	
					    	<input type="number" ng-model="packagefeaturesCount" max="7" name="packagefeaturesCount" id="packagefeaturesCount" style="display:none">
					    	<span class="errorSpan" ng-show="packageAct.packagefeaturesCount.$error.max">You can select maximum 7 features only.</span>						
						</div>
                           <table class="table table-bordered">
                              <tbody>                               
                                 <tr>
                                 <td>Airport Pickup</td>
                                  <td>
                                   <span ng-click="aircheck=!aircheck;packagefeaturesCount=packagefeaturesCount-1" ng-show="aircheck">
                                     <a href=""><img
												 ng-src="{{appIcons.airportStationActive}}" /></a>
                                   </span>
                                   <span ng-click="aircheck=!aircheck;packagefeaturesCount=packagefeaturesCount+1" ng-show="!aircheck">
                                     <a href=""><img
												 ng-src="{{appIcons.airportStationInactive}}" /></a>
                                   </span>
                                  </td>
                                 </tr>                                
                                  <tr>
                                 <td>Bus</td>
                                  <td>
                                   <span ng-click="buscheck=!buscheck;packagefeaturesCount=packagefeaturesCount-1" ng-show="buscheck">
                                     <a href=""><img
												 ng-src="{{appIcons.busActive}}" /></a>
                                   </span>
                                   <span ng-click="buscheck=!buscheck;packagefeaturesCount=packagefeaturesCount+1" ng-show="!buscheck">
                                     <a href=""><img
												 ng-src="{{appIcons.busInactive}}" /></a>
                                   </span>
                                  </td>
                                 </tr>
                                  <tr>
                                 <td>Campfire</td>
                                  <td>
                                   <span ng-click="campcheck=!campcheck;packagefeaturesCount=packagefeaturesCount-1" ng-show="campcheck">
                                     <a href=""><img
												 ng-src="{{appIcons.campfireActive}}" /></a>
                                   </span>
                                   <span ng-click="campcheck=!campcheck;packagefeaturesCount=packagefeaturesCount+1" ng-show="!campcheck">
                                     <a href=""><img
												 ng-src="{{appIcons.campfireInactive}}" /></a>
                                   </span>
                                  </td>
                                 </tr>
                                 <tr>
                                 <td>Ferry</td>
                                  <td>
                                   <span ng-click="ferrycheck=!ferrycheck;packagefeaturesCount=packagefeaturesCount-1" ng-show="ferrycheck">
                                     <a href=""><img
												 ng-src="resources/images/icons/packageEnable/Ferry.png" /></a>
                                   </span>
                                   <span ng-click="ferrycheck=!ferrycheck;packagefeaturesCount=packagefeaturesCount+1" ng-show="!ferrycheck">
                                     <a href=""><img
												 ng-src="{{appIcons.ferryInactive}}" /></a>
                                   </span>
                                  </td>
                                 </tr>
                                  <tr>
                                  <td>Flight</td>
                                   <td>
                                   <span ng-click="planecheck=!planecheck;packagefeaturesCount=packagefeaturesCount-1" ng-show="planecheck">
                                      <a href=""><img
												 ng-src="{{appIcons.flightActive}}" /></a>
                                   </span>
                                   <span ng-click="planecheck=!planecheck;packagefeaturesCount=packagefeaturesCount+1" ng-show="!planecheck">
                                     <a href=""><img
												 ng-src="{{appIcons.flightInactive}}" /></a>
                                   </span>
                                   </td>
                                 </tr>
                                 <tr>
                                  <td>Food</td>
                                  <td>
                                   <span ng-click="foodcheck=!foodcheck;packagefeaturesCount=packagefeaturesCount-1" ng-show="foodcheck">
                                     <a href=""><img
												 ng-src="{{appIcons.breakfastActive}}" /></a>
                                   </span>
                                   <span ng-click="foodcheck=!foodcheck;packagefeaturesCount=packagefeaturesCount+1" ng-show="!foodcheck">
                                     <a href=""><img
												 ng-src="{{appIcons.breakfastInactive}}" /></a>
                                   </span>
                                  </td>
                                 </tr>
                                  <tr>
                                  <td>Games</td>
                                  <td>
                                   <span ng-click="gamescheck=!gamescheck;packagefeaturesCount=packagefeaturesCount-1" ng-show="gamescheck">
                                     <a href=""><img
												 ng-src="{{appIcons.gamesActive}}" /></a>
                                   </span>
                                   <span ng-click="gamescheck=!gamescheck;packagefeaturesCount=packagefeaturesCount+1" ng-show="!gamescheck">
                                     <a href=""><img
												 ng-src="{{appIcons.gamesInactive}}" /></a>
                                   </span>
                                  </td>
                                 </tr>
                                  <tr>
                                  <td>Guide</td>
                                  <td>
                                   <span ng-click="guidecheck=!guidecheck;packagefeaturesCount=packagefeaturesCount-1" ng-show="guidecheck">
                                     <a href=""><img
												 ng-src="{{appIcons.guideActive}}" /></a>
                                   </span>
                                   <span ng-click="guidecheck=!guidecheck;packagefeaturesCount=packagefeaturesCount+1" ng-show="!guidecheck">
                                     <a href=""><img
												 ng-src="{{appIcons.guideInactive}}" /></a>
                                   </span>
                                  </td>
                                 </tr>
                                 <tr>
                                  <td>Hotel</td>
                                  <td>
                                   <span ng-click="hotelchck=!hotelchck;packagefeaturesCount=packagefeaturesCount-1" ng-show="hotelchck">
                                     <a href=""><img
												 ng-src="{{appIcons.hotelActive}}" /></a>
                                   </span>
                                   <span ng-click="hotelchck=!hotelchck;packagefeaturesCount=packagefeaturesCount+1" ng-show="!hotelchck">
                                     <a href=""><img
												 ng-src="{{appIcons.hotelInactive}}" /></a>
                                   </span>
                                  </td>
                                 </tr> 
                                  <tr>
                                  <td>Sea</td>
                                  <td>
                                   <span ng-click="seacheck=!seacheck;packagefeaturesCount=packagefeaturesCount-1" ng-show="seacheck">
                                     <a href=""><img
												 ng-src="{{appIcons.seaActive}}" /></a>
                                   </span>
                                   <span ng-click="seacheck=!seacheck;packagefeaturesCount=packagefeaturesCount+1" ng-show="!seacheck">
                                     <a href=""><img
												 ng-src="{{appIcons.seaInactive}}" /></a>
                                   </span>
                                  </td>
                                 </tr> 
                                 <tr>
                                  <td>Shows</td>
                                  <td>
                                   <span ng-click="showscheck=!showscheck;packagefeaturesCount=packagefeaturesCount-1" ng-show="showscheck">
                                     <a href=""><img
												 ng-src="{{appIcons.showActive}}" /></a>
                                   </span>
                                   <span ng-click="showscheck=!showscheck;packagefeaturesCount=packagefeaturesCount+1" ng-show="!showscheck">
                                     <a href=""><img
												 ng-src="{{appIcons.showInactive}}" /></a>
                                   </span>
                                  </td>
                                 </tr>                                     
                                 <tr>
                                  <td>Train</td>
                                  <td>
                                   <span ng-click="traincheck=!traincheck;packagefeaturesCount=packagefeaturesCount-1" ng-show="traincheck">
                                     <a href=""><img
												 ng-src="{{appIcons.trainActive}}" /></a>
                                   </span>
                                   <span ng-click="traincheck=!traincheck;packagefeaturesCount=packagefeaturesCount+1" ng-show="!traincheck">
                                     <a href=""><img
												 ng-src="{{appIcons.trainInactive}}" /></a>
                                   </span>
                                  </td>
                                 </tr> 
                                 <tr>
                                  <td>Transfer</td>
                                  <td>
                                   <span ng-click="transfercheck=!transfercheck;packagefeaturesCount=packagefeaturesCount-1" ng-show="transfercheck">
                                     <a href=""><img
												 ng-src="{{appIcons.transferActive}}" /></a>
                                   </span>
                                   <span ng-click="transfercheck=!transfercheck;packagefeaturesCount=packagefeaturesCount+1" ng-show="!transfercheck">
                                     <a href=""><img
												 ng-src="{{appIcons.transferInactive}}" /></a>
                                   </span>
                                  </td>
                                 </tr> 
                                 <tr>
                                  <td>Visa</td>
                                  <td>
                                   <span ng-click="visacheck=!visacheck;packagefeaturesCount=packagefeaturesCount-1" ng-show="visacheck">
                                     <a href=""><img
												 ng-src="{{appIcons.visaActive}}" /></a>
                                   </span>
                                   <span ng-click="visacheck=!visacheck;packagefeaturesCount=packagefeaturesCount+1" ng-show="!visacheck">
                                     <a href=""><img
												 ng-src="{{appIcons.visaInactive}}" /></a>
                                   </span>
                                  </td>
                                 </tr> 
                                 <tr>
                                  <td>Zoo</td>
                                  <td>
                                   <span ng-click="zoocheck=!zoocheck;packagefeaturesCount=packagefeaturesCount-1" ng-show="zoocheck">
                                     <a href=""><img
												 ng-src="{{appIcons.zooActive}}" /></a>
                                   </span>
                                   <span ng-click="zoocheck=!zoocheck;packagefeaturesCount=packagefeaturesCount+1" ng-show="!zoocheck">
                                     <a href=""><img
												 ng-src="{{appIcons.zooInactive}}" /></a>
                                   </span>
                                  </td>
                                 </tr>
                                 
                               </tbody>
                            </table>
                        </div>
                   </div><br>
                   <BR><BR>				
                       <div ng-show="pkactUploadshow">
                            <!--  <div class="row" >
                                  <div class="panel-heading block col-md-8"><h6 class="panel-title"><i class="fa fa-image"></i>Day Icon Images{{imgIconList.length}}</h6></div>
                             </div>  -->                            
                             <div class="row" ng-show="dayIconList.length < 1 || dayIconList.length == null">
                                <div class="col-md-6">
                                   <img data-ng-show="hideImageSaveBtn" ng-src="{{iconDayImage}}" class="img-responsive" width="300" height="200">
                                </div>
                                 <div class="col-md-6">
                                      <div class="row">
                                      <div class="col-md-10">
                                         <button  type="file" ng-file-select ng-model="dayIconImg" ng-file-change="fileIconSelected($files, $event)" ng-multiple="true" accept="image/*,*.pdf" resetOnClick="true" class="btn btn-primary">Upload</button>
                                      </div></div><br>
                                      <div class="row">
                                     <div class="col-md-10">
                                        <input type="button" data-ng-show="hideImageSaveBtn" value="Save" ng-click="saveDayIconImg()" class="btn btn-danger">
                                    </div></div>
                                 </div>
                                 <div class="row">
										<span class="errorSpan" ng-show="fileError">{{fileError}}</span>
								</div>
                           </div><br><br>
                   </div>
                       </div>
                     
                     <div class="block">
                            <div class="col-md-12">
                                <table class="table table-bordered">
                                    <thead>
                                        <tr>
                                            <th class="">Day</th>
                                            <th class="">Activity</th>
                                            <th class="">Add/Remove Image</th>
                                             <th class="">Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr ng-repeat = "act in activity">
                                            <td>{{act.pkiDay}}</td>
                                            <td>{{act.pkiItinerary}}</td>
                                            <td ng-show="!act.imageUrl" ng-click="packageActiveImg(act.pkiSeqId)"><img width="50" ng-src="resources/images/no-imag.png"></td>
                                             <td ng-show ="act.imageUrl" ng-click="packageActiveImg(act.pkiSeqId,act.imageUrl)"><img width="50" ng-src="{{imagePath+act.imageUrl}}"></td>
                                            <td>
                                               <a href="" ng-really-message="Are you sure?" ng-really-click="removeAct(act.pkiSeqId)"><i style="color: red;" class="icon-close"></i></a>
                                            </td>
                                        </tr>
                                        
                                    </tbody>
                                </table>
                            </div>
                        </div>     
                     <input type="hidden" ng-model="pkiSeqId">
                  </div>
                    </form>