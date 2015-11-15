<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Login Page</title>

<link href="${pageContext.request.contextPath}/static/css/main.css"
	rel="stylesheet" type="text/css">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<!-- Optional: Include the jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<!-- Optional: Incorporate the Bootstrap JavaScript plugins -->
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<link rel="shortcut icon" type="image/x-icon"
		href="${pageContext.request.contextPath}/static/images/favicon.ico" />
</head>
<body onload='document.f.j_username.focus();'>
	<div id="wrap">
		<div class="container">
			<div class="row" id="centered-row">
				<div class="col-xs-4"></div>
				<div class="col-xs-4" id="login-header">
					<h3>Login</h3>
				</div>
			</div>
			<div class="row" id="centered-row">
				<div class="col-xs-4"></div>
				<div class="col-xs-4">
					<c:if test="${param.error != null}">
						<p class="login-error">
                            Login failed. Check that your user name and password are correct.
						</p>
					</c:if>

					<form class="form-horizontal" name='f'
						action='${pageContext.request.contextPath}/j_spring_security_check'
						method='POST'>
						<div class="form-group">
							<label for="inputUsername2" class="col-sm-4 control-label">
                                Username
							</label>
							<div class="col-sm-8">
								<input type="text" name='j_username' value=''
									class="form-control" id="inputUsername2" placeholder="Username">
							</div>
						</div>
						<div class="form-group">
							<label for="inputPassword2" class="col-sm-4 control-label">
                                Password
							</label>
							<div class="col-sm-8">
								<input type="password" name='j_password' class="form-control"
									id="inputPassword2" placeholder="Password">
							</div>

						</div>
						<div class="form-group">
							<div class="col-sm-offset-1 col-sm-11">
								<div class="checkbox">
									<label> <input type="checkbox"> 
                                        Remember Me
									</label>
								</div>
							</div>

						</div>
						<div class="form-group">
							<div class="col-sm-offset-1 col-sm-11">
								<button type="submit" name="submit" class="btn btn-default">
									Sign In
								</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>