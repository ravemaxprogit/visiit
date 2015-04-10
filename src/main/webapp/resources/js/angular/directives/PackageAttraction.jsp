<div class="panel-body">
	<form name="packageAct" role="form" method="POST" novalidate>

		<div class="col-md-6">
		<div data-ng-show="message.success" class="sucess_message">{{packageAttractionResponse.message}}</div>
		<div data-ng-show="message.error" class="sucess_error">{{packageAttractionResponse.message}}</div>
			<div class="col-md-12">
				<div class="col-md-3  label-styl">Attraction:</div>
				<div class="col-md-9">
					<select data-required-error-message="Select Attraction"
						data-valid-method="watch" data-validator="required" ng-focus="hideeerormsg();"
						name="attraction" data-ng-change="changeAttraction()"
						data-ng-model="attrac"
						class="form-control"
						id="select-1">
						<option value="" selected="selected">Select..</option>
						<option value="inclus">Inclusions</option>
						<option value="exclus">Exclusions</option>
					</select><span class="mLeft0"></span>
				</div>
			</div>
			<div class="row">&nbsp;</div>
			<div class="row">&nbsp;</div>
			<div class="col-md-12">
				<div class="col-md-3  label-styl">Description:</div>
				<div class="col-md-9">
					<textarea data-required-error-message="Enter Description"
						data-validator="required" name="attrdesc" id="attrdesc" maxlength="300" ng-focus="hideeerormsg();" ng-model="attrdesc" rows="3"
						class="form-control ng-pristine ng-untouched ng-isolate-scope ng-valid"></textarea>
					<span class="mLeft0"></span>
				</div>
			</div>
			<div class="row">&nbsp;</div>
			<div class="row">&nbsp;</div>
			<div class="col-md-12">
				<div class="col-md-3"></div>
				<div class="col-md-9" ng-init="saveAttractionBtn=1">
					<input type="button" class="btn btn-danger" value="Save"
						ng-click="saveAttraction(packageAct);" data-validation-submit="packageAct" ng-show="saveAttractionBtn">
				</div>
			</div>
		</div>
		<div class="col-md-6">

			<div ng-app="content" ng-show="pkattUploadshow">
				<div class="row" ng-show="attractIconList.length < 1 || attractIconList.length == null">
					<div class="col-md-6">
						<div class="col-md-12">
							<img ng-show="hideImageSaveBtnAttraction" ng-src="{{iconAttractImage}}" alt="Attraction Image"
								width="{{cropperWidth}}" height="{{cropperHeight}}">
						</div>
						<div class="col-md-10">
							<div ng-show="imageDataURI" class="cropArea">
								<img-crop image="imageDataURI" result-image="iconAttractImage"
									change-on-fly="changeOnFly" area-type="square"
									area-min-size="cropperWidth" result-image-format="fileType"
									result-image-size="cropperWidth"></img-crop>
							</div>
						</div>
						
						</div>
						<div class="col-md-6">
						<!-- <div class="panel-heading block col-md-6">
							<h6 class="panel-title">
								<i class="fa fa-image"></i>Attraction Images
							</h6>
						</div> -->
						<div class="col-md-12">
							<button type="file" ng-file-select
								ng-model="attractIconImg"
								ng-file-change="fileIconSelected($event,'packageExclusion')"
								ng-multiple="true" accept="image/*,*.pdf" resetOnClick="true"
								class="btn btn-primary">Upload</button>
						</div><br/><br/><br/>
						<div>
						<div class="col-md-12">
								<input type="button" value="Save"
									ng-show="hideImageSaveBtnAttraction"
									ng-click="saveDAttractionIconImg(pkId,att.type)"
									class="btn btn-danger">
						</div>
					</div>

				</div>
				<div class="row col-md-12">
							<span class="errorSpan" ng-show="fileError">{{fileError}}</span>
				</div>
						
					</div>
					
			</div>
		</div>
	</form>
	<br />
	<br />
	<div class="block">
		<div class="col-md-12">
			<table class="table table-bordered">
				<thead>
					<tr>
						<th class="">Description</th>
						<th class="">Add/Edit Image</th>
						<th class="">Delete</th>
					</tr>
				</thead>
				<tbody>
					<tr ng-if="packAttractionExclus.length > 0"
						ng-repeat="att in packAttractionExclus" style="cursor: pointer">
						<td
							ng-click="packageAttractionImg(att.pkexSeqId,'','exclusion',att.pkexDescription)">{{att.pkexDescription}}</td>
						<td ng-show="!att.imageUrl"
							ng-click="packageAttractionImg(att.pkexSeqId,'','exclusion',att.pkexDescription,'imageEdit')"><img
							width="50" ng-src="resources/images/no-imag.png"></td>
						<td ng-show="att.imageUrl"
							ng-click="packageAttractionImg(att.pkexSeqId,att.imageUrl,'exclusion',att.pkexDescription,'imageEdit')"><img
							width="50" ng-src="{{imagePath+att.imageUrl}}"></td>
						<td><a href="" ng-really-message="Are you sure?"
							ng-really-click="removeAttract(att.pkexSeqId,'exclus')"><i
								style="color: red;" class="icon-close"></i></a></td>
					</tr>
					<tr ng-if="packAttractionInclus.length>0"
						ng-repeat="attincl in packAttractionInclus"
						style="cursor: pointer">
						<td
							ng-click="packageAttractionImg(attincl.pkinSeqId,'','inclusion',attincl.pkinDescription)">{{attincl.pkinDescription}}</td>
						<td ng-show="!attincl.imageUrl"
							ng-click="packageAttractionImg(attincl.pkinSeqId,'','inclusion',attincl.pkinDescription,'imageEdit')"><img
							width="50" ng-src="resources/images/no-imag.png"></td>
						<td ng-show="attincl.imageUrl"
							ng-click="packageAttractionImg(attincl.pkinSeqId,attincl.imageUrl,'inclusion',attincl.pkinDescription,'imageEdit')"><img
							width="50" ng-src="{{imagePath+attincl.imageUrl}}"></td>
						<td><a href="" ng-really-message="Are you sure?"
							ng-really-click="removeAttract(attincl.pkinSeqId,'inclus')"><i
								style="color: red;" class="icon-close"></i></a></td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	</div>