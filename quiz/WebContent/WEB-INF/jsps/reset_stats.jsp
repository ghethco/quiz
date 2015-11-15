<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec"
  uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script
  src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script
  src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<script
  src="${pageContext.request.contextPath}/static/Scripts/newquestion.js"></script>
<link rel="stylesheet"
  href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<link href="${pageContext.request.contextPath}/static/css/main.css"
  rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/static/css/styles.css"
  rel="stylesheet" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="shortcut icon" type="image/x-icon"
  href="${pageContext.request.contextPath}/static/images/favicon.ico" />

<script type="text/javascript">
	var context_path = "${pageContext.request.contextPath}";
</script>

<title>Glider Quiz</title>

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
            href="${pageContext.request.contextPath}/home">Glider
            Quiz</a>
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

      <!--  END NAVBAR -->

      <h1>Glider Quiz</h1>

      <h2>Answer statistics have been reset for source: ${reset_source}</h2>

    </div>
  </div>
</body>
</html>