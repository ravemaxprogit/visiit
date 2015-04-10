<%@page import="com.rave.visiit.entity.Vendor"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="styles.jsp"%>
<%@ include file="scripts.jsp"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<body ng-app="content">
	<form action="${pageContext.request.contextPath}/save" role="form"
		method="POST" novalidate>
		<div class="panel panel-default">
			<div class="panel-heading">
				<h6 class="panel-title">
					<i class="icon-pencil3"></i>CHANGE PASSWORD
				</h6>
			</div>
			<div class="panel-body">
				<div class="form-group">
					<div class="row">
						<div class="col-md-4">
							<input type="email" name="custEmail" id="custEmail"
								placeholder="- Email Address -" class="form-control"
								ng-model="email" required>
						</div>
					</div>
					<div class="row">
						<div class="col-md-4">
							<input type="password" name="newPwd" id="newPwd"
								placeholder="- New Password -" class="form-control"
								ng-model="newPassword" required>
						</div>
					</div>
					<div class="row">
						<div class="col-md-4">
							<input type="password" name="cnfPwd" id="cnfPwd"
								placeholder="- Confirm Password -" class="form-control"
								ng-model="cnfPassword" required>
							<input type="button" id="change" name="change"
								value="Change"
								onclick='changePwd();' class="btn btn-danger" />
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
	<br/>
<script type="text/javascript">
	function changePwd() {
		var email = $("#custEmail").val();
		var newPwd = $("#newPwd").val();
		var cnfPwd = $("#cnfPwd").val();
		if(newPwd!=cnfPwd){
			alert("!New password and confirm password are not equal.");
			return false;	
		}
		 $.ajax({	url : "changePassword",
					type : "POST",
					data : {"email" : email,"pwd" : newPwd},
					success : function(json) {
						alert(json);
						$("#custEmail").val("")
						$("#newPwd").val("")
						$("#cnfPwd").val("")
					},
					error : function(xhr, ajaxOptions, thrownError) {
						alert(xhr.statusText);
					},
					statusCode : {
						404 : function() {
							alert("This page has been moved.");
						}
					}
				}).done(function() {
		});
	}
</script>
</body>