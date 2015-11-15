<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib prefix="sec"
  uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/static/css/main.css"
  rel="stylesheet" type="text/css">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
  href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<!-- Optional: Include the jQuery library -->
<script
  src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<!-- Optional: Incorporate the Bootstrap JavaScript plugins -->

<link rel="stylesheet"
  href="http://cdn.datatables.net/1.10.5/css/jquery.dataTables.min.css">
</style>
<link rel="stylesheet"
  href="http://cdn.datatables.net/tabletools/2.2.3/css/dataTables.tableTools.css">
</style>
<script type="text/javascript"
  src="http://cdn.datatables.net/1.10.5/js/jquery.dataTables.min.js"></script>
<script type="text/javascript"
  src="http://cdn.datatables.net/tabletools/2.2.3/js/dataTables.tableTools.min.js">
  </script>

<script
  src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/static/Scripts/admin.js"></script>
<link rel="shortcut icon" type="image/x-icon"
  href="${pageContext.request.contextPath}/static/images/favicon.ico" />

<title>Quiz Admin Page</title>

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
            href="${pageContext.request.contextPath}/home">Quiz</a>
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
          <h3>Administration Page</h3>
        </div>
      </div>
      <div class="row" id="centered-row">
        <div class="col-xs-5"></div>
        <div class="col-xs-2">
          <a class="btn btn-primary"
            href="${pageContext.request.contextPath}/users"
            role="button">Users</a>
        </div>
      </div>
    </div>
  </div>
</body>
</html>