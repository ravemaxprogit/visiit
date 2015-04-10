<%@page import="com.rave.visiit.entity.Country"%>
<%@page import="java.util.List"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ include file="Header.jsp"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript" src="resources/js/views/country.js"></script>
<body ng-app="content">
	<!-- Page container -->
	<!-- Breadcrumbs line -->
	<br />
	<div class="breadcrumb-line">
		<ul class="breadcrumb">
			<li><a href="index-2.html">Home</a></li>
			<li class="act
                    ive">Dashboard</li>
		</ul>
	</div>
	<!-- /breadcrumbs line -->
	<div class="page-container">
		<%@ include file="Navigator.jsp"%>
		<!-- Page content -->
		<div class="page-content">
			<form action="#" role="form" novalidate id="onChangeFrm" name="onChangeFrm" method="post">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h6 class="panel-title">
							<i class="icon-pencil3"></i>COUNTRY & CITY MASTER
						</h6>
					</div>
					<div class="panel-body">
						<div class="form-group">
							<div class="row">
								<div class="col-md-4">Country</div>
								<div class="col-md-8">
									<!-- <input type="text" name="country" placeholder="- Choose Country -" class="form-control" ng-model="country" required> -->
									<select name="country" id="country" class="form-control"
										required>
										<option value="">- Choose Country -</option>
										<c:if test="${!empty cntryList}">
											<c:forEach items="${cntryList}" var="cntry">
												<option value="${cntry.countryId}">${cntry.countryName}</option>
											</c:forEach>
										</c:if>
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
				</div>
				<input type="hidden" id="onChangeId" name="onChangeId"> 
			</form>
			<form action="#" role="form" id="cntForm" method="post">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h6 class="panel-title">
							<i class="icon-pencil3"></i> <label id="catId">Country</label>&nbsp;Grid
						</h6>
					</div>
					<div class="panel-body" >
						<div class="block">
							<table class="table table-bordered" id="paneltbl">
								<thead>
									<tr>
										<th class="">Code</th>
										<th class="">Name</th>
										<th class="">Description</th>
										<th class="" colspan="2">Active</th>
									</tr>
								</thead>
								<tbody id="tb_body">
									<tr>
										<td><div class="col-md-12">
												<input type="text" id="code" name="code"
													placeholder="- Add Code-" class="form-control ">
												<input type="hidden" id="id" name="countryId" />
											</div></td>
										<td><div class="col-md-12">
												<input type="text" id="name" name="name"
													placeholder="- Add Name-" class="form-control">
											</div></td>
										<td><div class="col-md-12">
												<input type="text" id="desc" name="desc"
													placeholder="- Add Description-"
													class="form-control">
											</div></td>
										<td><div class="col-md-12">
												<input type="radio" id="isActive" name="isActive" 
													class="form-control">
											</div></td>
										<td><div class="form-actions text-middle">
												<input type="button" value="  +   " class="btn btn-primary"
													id="saveBtn">
											</div></td>
									</tr>
									<c:if test="${cntryList != null}">
										<c:forEach items="${cntryList}" var="cntry">
											<tr id="results">
												<td>${cntry.countryCode}</td>
												<td>${cntry.countryName}</td>
												<td>${cntry.countryDescription}</td>
												<td>${cntry.countryIsactive}</td>
												<td>
													<p>
														<a href="javascript:return false;" id="deleteBtn"
															onclick="deleteCountry('${cntry.countryId}');"><i
															style="color: red;" class="fa fa-trash"></i></a>
														&nbsp;&nbsp;&nbsp; <a href="javascript:return false;" id="editBtn"
															onclick="editCntry('${cntry.countryId}','${cntry.countryCode}','${cntry.countryName}','${cntry.countryDescription}','${cntry.countryIsactive}')"><i
															style="color: #808080;" class="icon-pencil2"></i></a>
													</p>
												</td>
											</tr>
										</c:forEach>
									</c:if>
								</tbody>
							</table>
						</div>
					</div>
				</div>
				<script>
$(document).ready(function(){
   var tog = 0;
    $("#isActive").click(function(){
        if(tog%2==0) $("#isActive").prop('checked', true);
        else $("#isActive").prop('checked', false); 
        tog++;
    });
});
</script>
			</form>
			<!-- /tasks table -->
			<br />
			<br />
			<br />
			<br />
			<br />
			<br />
			<br />
			<%@ include file="Footer.jsp"%>
		</div>
		<!-- /page content -->
	</div>
	<!-- /page container -->
</body>