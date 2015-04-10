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
					<i class="icon-pencil3"></i>Registration
				</h6>
			</div>
			<div class="panel-body">

				<div class="form-group">
					<!-- <div class="row">
						<div class="col-md-4">
							<label>Name:</label> <input type="text" name="name"
								placeholder="- Name -" class="form-control" ng-model="fname"
								required>
						</div>
					</div> -->
					<div class="row">
						<div class="col-md-4">
							<label>Email Id:</label> <input type="email" name="custEmail" id="custEmail"
								placeholder="- Email -" class="form-control" ng-model="email"
								required>
						</div>
					</div>
					<div class="row">
						<div class="col-md-4">
							<label>Password:</label> <input type="password" name="custPwd" id="custPwd"
								placeholder="- Password -" class="form-control"
								ng-model="password" required>
						</div>
					</div>
					<div class="row">
						<div class="col-md-4">
							<label>Mobile Number:</label> <input type="tel" name="mobile" id="custMobile"
								placeholder="- Mobile No -" class="form-control"
								ng-model="mobile" required>
						</div>
					</div><br/>
					<div class="form-actions text-left">
						<input type="checkbox">&nbsp;&nbsp;Send me offers & news by email.&nbsp;&nbsp;
					</div><br/>
					<div class="form-actions text-left" >
						<input type="button" id="submit" name="create" value="Create" onclick="saveCustReg();" 
							class="btn btn-danger"> <input type="reset"
							value="Clear" class="btn btn-primary">
					</div>
				</div>
			</div>
		</div>
	</form>
	<br />
	<script type="text/javascript">
	function saveCustReg(){
		var email= $("#custEmail").val();
		var pwd= $("#custPwd").val();
		var mobile= $("#custMobile").val();
			var custReg ={};
			custReg.customerEmail = email;
			custReg.password = pwd;
			custReg.phone = mobile;
			var custRegStr = JSON.stringify(custReg);  
			$.ajax({
				url:"saveCustReg",
				type:"POST",
				data :{"custRegStr":custRegStr},
				success: function(json){
					alert(json);
					$("#custEmail").val("");
					$("#custPwd").val("");
					$("#custMobile").val("");
				},
				error: function (xhr, ajaxOptions, thrownError){alert(xhr.statusText);},
				statusCode: {404: function() {alert("This page has been moved.");	}}
			}).done(function(){});
		}
	</script>
</body>