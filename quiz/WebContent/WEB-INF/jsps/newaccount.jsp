<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec"
  uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script
  src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script
  src="${pageContext.request.contextPath}/static/Scripts/newaccount.js"></script>
<script type="text/javascript">
	var pass_match = "Passwords match";
	var pass_no_match = "Passwords do not match";
</script>
<script
  src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<link rel="stylesheet"
  href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<link href="${pageContext.request.contextPath}/static/css/main.css"
  rel="stylesheet" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="shortcut icon" type="image/x-icon"
  href="${pageContext.request.contextPath}/static/images/favicon.ico" />

<title>Create User Account</title>

</head>

<body>
  <div id="wrap">
    <div class="container">
      <nav class="navbar navbar-default navbar-static-top">
      <div class="container-fluid">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed"
            data-toggle="collapse"
            data-target="#bs-example-navbar-collapse-1">
            <span class="sr-only">Toggle navigation</span> <span
              class="icon-bar"></span> <span class="icon-bar"></span> <span
              class="icon-bar"></span>
          </button>
          <a class="navbar-brand"
            href="${pageContext.request.contextPath}/home">Laplace
            Systems - PV Hub</a>
        </div>
        <div class="collapse navbar-collapse"
          id="bs-example-navbar-collapse-1">
          <ul class="nav navbar-nav navbar-right">
            <sec:authorize access="hasRole('ROLE_ADMIN')">
              <li><a href="<c:url value='/admin'/>">Admin</a></li>
            </sec:authorize>
            <sec:authorize access="isAuthenticated()">
              <li><a
                href="<c:url value='/j_spring_security_logout'/>">Log
                  Out</a></li>
            </sec:authorize>
          </ul>
        </div>
      </div>
      </nav>
      <div class="row" id="centered-row">
        <div class="col-xs-4"></div>
        <div class="col-xs-4">
          <h3>Create Account</h3>
        </div>
      </div>
      <div class="row" id="centered-row">
        <div class="col-xs-3"></div>
        <div class="col-xs-5">
          <sf:form id="details" method="post" class="form-horizontal"
            action="${pageContext.request.contextPath}/createaccount"
            commandName="user">

            <!-- the commandName attribute above (user) is for -->
            <!-- connecting the form to the model for validation -->
            <!-- See LoginController.java -->

            <div class="form-group">
              <label for="inputUsername2" class="col-sm-5 control-label">Username</label>
              <div class="col-sm-7">
                <sf:input type="text" path="username" name="username"
                  class="form-control" id="inputUsername2"
                  placeholder="Username" />
                <div id="error-text">
                  <sf:errors path="username"></sf:errors>
                </div>
              </div>
            </div>

            <div class="form-group">
              <label for="inputName2" class="col-sm-5 control-label">Full
                Name</label>
              <div class="col-sm-7">
                <sf:input type="text" path="name" name="name"
                  class="form-control" id="inputName2"
                  placeholder="Full Name" />
                <div id="error-text">
                  <sf:errors path="name"></sf:errors>
                </div>
              </div>
            </div>

            <div class="form-group">
              <label for="inputEmail2" class="col-sm-5 control-label">Email</label>
              <div class="col-sm-7">
                <sf:input type="text" path="email" name="email"
                  class="form-control" id="inputEmail2"
                  placeholder="Email" />
                <div id="error-text">
                  <sf:errors path="email"></sf:errors>
                </div>
              </div>
            </div>
            
            <div class="form-group">
              <label for="inputPassword2" class="col-sm-5 control-label">Password</label>
              <div class="col-sm-7">
                <sf:input type="password" path="password"
                  name="password" class="form-control"
                  id="inputPassword2" placeholder="Password" />
                <div id="error-text">
                  <sf:errors path="password"></sf:errors>
                </div>
              </div>
            </div>

            <div class="form-group">
              <label for="inputConfirmPassword2"
                class="col-sm-5 control-label">Confirm Password</label>
              <div class="col-sm-7">
                <input type="password"
                  name="confirmpass" class="form-control"
                  id="inputConfirmPassword2"
                  placeholder="Confirm Password" />
                <div id="matchpass"></div>
              </div>
            </div>

            <div class="form-group">
             <label for="inputUserAuthority2" class="col-sm-5 control-label">User Role</label>
              <div class="col-sm-7">
                <sf:select path="authority" class="form-control" id="inputUserAuthority2"
                        multiple="false">
                    <option>ROLE_USER</option>
                    <option>ROLE_ADMIN</option>
                </sf:select>
                <div id="error-text">
                  <sf:errors path="authority"></sf:errors>
                </div>
              </div>
            </div>
            
            <div class="form-group">
              <div class="col-sm-offset-1 col-sm-11" id="create-button">
                <button type="submit" name="submit"
                  class="btn btn-default">Create Account</button>
              </div>
            </div>
          </sf:form>
        </div>
      </div>
    </div>
  </div>
</body>
</html>