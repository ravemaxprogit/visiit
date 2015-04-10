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
					<i class="icon-pencil3"></i>FORGOT PASSWORD
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
						<div class="col-md-4">
							<input type="button" id="resetPwd" name="resetPwd"
								value="Reset & Send Password"
								onclick='sendPwdDetails();' class="btn btn-danger" />
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
	<br />
<script type="text/javascript">
	function sendPwdDetails() {
		var email = $("#custEmail").val();
		 $.ajax({	url : "resetPassword",
					type : "POST",
					data : {"email" : email},
					success : function(json) {
						alert(json);
						$("#custEmail").val("")
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