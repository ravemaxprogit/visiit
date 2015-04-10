<%@page import="com.rave.visiit.entity.Country"%>
<%@page import="java.util.List"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="styles.jsp"%>
<%@ include file="scripts.jsp"%>
<script type="text/javascript" src="resources/js/views/country.js"></script>
<body ng-app="content">
	<div class="page-content">
		<form action="#" role="form" novalidate id="onChangeFrm"
			name="onChangeFrm" method="post">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h6 class="panel-title">
						<i class="icon-pencil3"></i>Forgot Password
					</h6>
				</div>
				<div class="panel-body">
					<div class="form-group">
						<div class="row">
							<div class="col-md-4">Email</div>
							<div class="col-md-4">
								<input type="text" name="custEmail" id="custEmail"
									placeholder="- Email Address -" class="form-control"
									ng-model="email" required>
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="row">
							<div class="col-md-4">Password</div>
							<div class="col-md-4">
								<input type="password" name="newPwd" id="newPwd"
									placeholder="- Password -" class="form-control"
									ng-model="newPwd" required>
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="row">
							<div class="col-md-4">Confirm Password</div>
							<div class="col-md-4">
								<input type="password" name="cnfPwd" id="cnfPwd"
									placeholder="- Confirm Password -" class="form-control"
									ng-model="cnfPwd" required>
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="row">
							<div class="col-md-4">
								<input type="button" id="change" name="change" value="Change"
									onclick='changePwd();' class="btn btn-danger" />
							</div>
						</div>
					</div>
				</div>
			</div>
			<input type="hidden" id="onChangeId" name="onChangeId">
		</form>
	</div>

</body>