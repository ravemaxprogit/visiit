<!-- <script type="text/ng-template" id="custom-datepicker.html">
    <div class="enhanced-datepicker">
        <div class="proxied-field-wrap">
            <input type="text" class="form-control margin-zero" minDate="2015-02-26" ui-date-format="yy-mm-dd" ng-model="ngModel" ui-date="dateOptions"/>
        </div>
           </div>
</script> -->
			
   <div class="panel-body">
    <form name="packagePrice" role="form" method="POST" novalidate>
    	   <div class="row">
               <div data-ng-show="message.success" class="sucess_message">{{packagePriceResponse.message}}</div>
    	       <div data-ng-show="message.error" class="sucess_error">{{packagePriceResponse.message}}</div>
			</div>
            <div class="row mrgin-row">
                     <div class="col-md-2  label-styl"> Price:</div>
                     <div class="col-md-4">
                        <input type="text" placeholder="Price" name="price" class="form-control margin-zero" ng-focus="hideeerormsg();" ng-model="price" ng-minlength="1" ng-maxlength="7" ng-maxlength-error-message="Enter the correct price" data-validator="required,validateNumber" data-required-error-message="Enter the price" data-number-error-message="Enter the correct price">
                     </div>
                     <div class="col-md-2  label-styl">Valid From:</div>
                     <div class="col-md-4">                                                       
                        <!-- <input type="text" class="form-control margin-zero selector"name="validfrom1" ng-model="validfrom" date-options="{minDate:0}" custom-datepicker > -->
                       <input type="text" class="form-control margin-zero date-control" ng-focus="hideeerormsg();" name="validfrom1" id="validfrom1" ng-model="validfrom" data-validator="required" valid-date data-required-error-message="Please select From Date">
                       <!-- <div data-ng-show="package_date_error" class="sucess_error1">Select Only from current date</div> -->
                    </div>
            </div>
            
            <div class="row mrgin-row">
                    <div class="col-md-2  label-styl"> Description:</div>
                    <div class="col-md-4">
                       <input type="text" placeholder="" name="description" maxlength="300" ng-focus="hideeerormsg();" class="form-control margin-zero" ng-model="priceDesc" data-validator="required" data-required-error-message="Enter the Description">
                    </div>
                    <div class="col-md-2  label-styl">Valid To:</div>
                    <div class="col-md-4">
                      <!--<datepicker>
                          <input type="text" name="validto"  ng-model="validto" date-options="{minDate:0}" custom-datepicker >
                       </datepicker>-->
                       
                       <input type="text" class="form-control margin-zero date-control" ng-focus="hideeerormsg();" name="validto" id="validto" ng-model="validto" data-validator="required" valid-date valid-date data-required-error-message="Please select To Date">
                    </div>
            </div>
            <div class="row mrgin-row">
                    <div class="col-md-2  label-styl"> Offer Price:</div>
                    <div class="col-md-4">
                       <input type="number" placeholder="Offer Price" name="offerprice" ng-focus="hideeerormsg();" class="form-control margin-zero" ng-model="priceOffer" minlength="1" maxlength="7">
                    </div>                           
            </div>
            <div class="row mrgin-row">
                    <div class="col-md-2"></div>
                    <div class="col-md-4">
                       <input data-validation-submit="packagePrice" type="button" ng-click="addPrice(packagePrice);" value="Save" class="btn btn-danger">
                    </div>                           
            </div>
            <br>
            </form>
            <div class="block">
                    <div class="">
                        <table class="table table-bordered">
                            <thead>
                                <tr>
                                    <th class="">Price</th>
                                    <th class="">Description</th>
                                    <th class="">From</th>
                                    <th class="">To</th>
                                    <th class="">Offer Price</th>
                                    <th class="">Valid</th>
                                    <th class="">Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr ng-repeat = "pp in packPrice" style="cursor:pointer" ng-click="getPrice(pp)">
                                    <td>{{pp.pkcCost}}</td>
                                    <td>{{pp.pkcDescription}}</td>
                                     <td>{{pp.validFrom | date:'dd/MM/yyyy'}}</td>
                                      <td>{{pp.validTo | date:'dd/MM/yyyy'}}</td>
                                       <td>{{pp.offerPrice}}</td>
                                    <td>{{pp.validDays}}</td>
                                    <td>
                                       <!-- <a href="" ng-really-message="Are you sure?" ng-really-click="removePrice(pp.pkcId)"><i style="color: red;" class="icon-close"></i></a>-->
                                        
                                        <p>										
		<a
			ng-really-click="getPrice(pp.pkcId)"><i
			style="color: #808080;" class="icon-pencil2"></i></a>
			&nbsp;&nbsp;&nbsp; <a ng-really-message="Are you sure?"  ng-really-click="removePrice(pp.pkcId)"><i
			style="color: red;" class="icon-close"></i></a>
	</p>
                                    </td>
                                </tr>  
                            </tbody>
                        </table>
                    </div>
                </div>
             <input type="hidden" ng-model="pkcId">
       </div>
      <script>       
 		$( ".date-control" ).datepicker({minDate:0,dateFormat: 'dd/mm/yy'});
</script>