<%@page import="com.rave.visiit.entity.Country"%>
<%@page import="java.util.List"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ include file="Header.jsp"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<form action="searchUser" role="form" novalidate >
	<div class="panel-body">
		<div class="form-group">
			<div class="row">
				<div class="col-md-8">
					<input type="text" name="vendor" placeholder="- Choose Role -"
						class="form-control" ng-model="Role" required>
				</div>
				<div class="form-actions text-middle">
					<input type="submit" value="search" class="btn btn-danger">
				</div>
			</div>
		</div>
	</div>
</form>
</br>
<form action="#" role="form" id="roleFrm">
	<div class="panel panel-default">
		<div class="panel-heading">
			<h6 class="panel-title">
				<i class="icon-pencil3"></i> Data Grid
			</h6>
		</div>
		<div class="panel-body">
			<div class="block">
				<div class="">
					<table class="table table-bordered">
						<thead>
							<tr>
								<th class="">Title</th>
								<th class="">Description</th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td><div class="col-md-12">
										<input type="text" id="roleTitle" name="roleTitle"
											placeholder="- Add Role Title-" class="form-control "> <input
											type="hidden" id="roleId" name="roleId" />
									</div></td>
								<td><div class="col-md-12">
										<input type="text" id="roleDesc" name="roleDescription"
											placeholder="- Add Role Description -" class="form-control">
								<td><span ng-click="roleCheck=!roleCheck"
									ng-hide="roleCheck">
										<div class="form-actions text-middle">
											<input type="button" value="  +   " class="btn btn-primary" onclick="saveCountry();">
										</div>
								</span></td>
							</tr>
							<c:if test="${roleList != null}">
								<c:forEach items="${roleList}" var="role">
									<tr id="results">
										<td>${role.roleTitle}</td>
										<td>${role.roleDiscription}</td>
										<td>
											<p>
												<a href="javascript:return false;" id="deleteBtn"
													onclick="deleteRole('${role.roleId}');"><i
													style="color: red;" class="icon-close"></i></a>
												&nbsp;&nbsp;&nbsp; <a href="javascript:return false;"
													id="editBtn" onclick="editRole('${role.roleId}')"><i
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
	</div>
</form>
<script type="text/javascript">
function saveCountry(){
	var id = $("#roleId").val();
	var title = $("#roleTitle").val();
	var desc = $("#roleDesc").val();
	if(title=='' || desc==''){
		alert("Role title and Role description are required fields!");
		return true;
	}
	var role ={};
	role.roleTitle = title;
	role.roleDiscription = desc;
	role.roleId = id;
	var roleStr = JSON.stringify(role);  
	$.ajax({
		url:"saveRole",
		type:"POST",
		data :{"roleStr":roleStr},
		success: function(json){
			clearFields();
			alert(json);
			$("#roleFrm").attr("action","roleMaster");
			$("#roleFrm").submit();
		},
		error: function (xhr, ajaxOptions, thrownError){alert(xhr.statusText);},
		statusCode: {404: function() {alert("This page has been moved.");	}}
	}).done(function(){});
}
function deleteRole(id){
	$.post("deleteRole",{"params":id},
		function(data,status){
			if(status=="success"){
				clearFields();
				alert(data);
				$("#roleFrm").attr("action","roleMaster");
				$("#roleFrm").submit();
			}
	});
};
function editRole(id){
	$.post("editRole",{"params":id},
		function(data,status){
			if(status=="success"){
				$("#roleTitle").val(data.roleTitle);
				$("#roleDesc").val(data.roleDiscription);
				$("#roleId").val(data.roleId);
			}
	});
};

function clearFields(){
	$("#roleId").val("");
	$("#roleTitle").val("");
	$("#roleDesc").val("");
}
</script>