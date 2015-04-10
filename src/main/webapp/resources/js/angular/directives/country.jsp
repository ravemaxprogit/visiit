
<script type="text/javascript" src="resources/js/views/cntrypop.js"></script>			
				<div class="panel panel-default">
					<div class="panel-heading">
						<h6 class="panel-title">
							<i class="icon-pencil3"></i>COUNTRY & CITY MASTER
						</h6>
					</div>
					<input type="hidden" name="popCntry" value="pop">
					<form action="#" role="form" novalidate id="onChangeFrm" name="onChangeFrm" method="post">
					<div class="panel-body">
						<div class="form-group">
							<div class="row">
								<div class="col-md-4">Country</div>
								<div ng-controller="countryCtrl" class="col-md-8">
									<select ng-model="selectedcntry" name="country" id="country" class="form-control">
								        <option>- Choose Country -</option>
								        <option ng-repeat="country in countries" value="{{country.countryId}}">{{country.countryName}}</option>
								    </select>
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="row">
								<div class="col-md-4">State</div>
								<div class="col-md-8">
									<!-- <input type="text" name="country" placeholder="- Choose State -" class="form-control" ng-model="country" required> -->
									<select name="state" id="state" placeholder="- Choose state -"
										class="form-control" required>
									</select>
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="row">
								<div class="col-md-4">City</div>
								<div class="col-md-8">
									<!-- <input type="text" name="country" placeholder="- Choose City -" class="form-control" ng-model="country" required> -->
									<select name="city" id="city" placeholder="- Choose city -"
										class="form-control" required>
									</select>
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="row">
								<div class="col-md-4">Location</div>
								<div class="col-md-8">
									<!-- <input type="text" name="country" placeholder="- Choose Location -" class="form-control" ng-model="country" required> -->
									<select name="location" id="location"
										placeholder="- Choose Location -" class="form-control"
										required>
									</select>
								</div>
							</div>
						</div>
					</div>
					</form>
					<form action="#" role="form" id="cntForm" method="post">
					<input type="hidden" id="onChangeId" name="onChangeId">
					<div class="panel-heading">
						<h6 class="panel-title">
							<i class="icon-pencil3"></i> <label id="catId">Country</label>&nbsp;Grid
						</h6>
					</div>
					<div class="block">
							<table border="0" class="table table-bordered" id="paneltbl">
								<tbody border="0" id="tb_body">
									<tr border="0">
										<td>
											<input type="text" id="code" name="code" placeholder="-Add Code-" class="form-control ">
											<input type="hidden" id="id" name="countryId" />
									    </td>
										<td>
											<input type="text" id="name" name="name" placeholder="-Add Name-" class="form-control">
										</td>
										<td>
											<input type="text" id="desc" name="desc" placeholder="-Add Desc-" class="form-control">
										</td>
										<td>
										   <input type="button" value="  +  " class="btn btn-primary" id="saveBtn">
										</td>
									</tr>
								</tbody>
							</table>
						</div>
						</form>
				</div>
				 
			