<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec"
  uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
		"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/static/css/main.css"
  rel="stylesheet" type="text/css">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
  href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<link href="${pageContext.request.contextPath}/static/css/main.css"
  rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/static/css/styles.css"
  rel="stylesheet" type="text/css">
<!-- Optional: Include the jQuery library -->
<script
  src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<!-- Optional: Incorporate the Bootstrap JavaScript plugins -->

<script
  src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/static/Scripts/home.js"></script>

<script type="text/javascript">
	var context_path = "${pageContext.request.contextPath}";
</script>
<link rel="shortcut icon" type="image/x-icon"
  href="${pageContext.request.contextPath}/static/images/favicon.ico" />

<title>Solar Design Fundamentals Home Page</title>
</head>
<body>
  <!--
<body BACKGROUND="${pageContext.request.contextPath}/static/images/Cover.JPG">
  -->
  <div id="wrap">
    <div class="container">

      <!-- BEGIN NAVBAR -->

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
            href="${pageContext.request.contextPath}/home">
            Solar Design Fundamentals</a>
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

      <!-- END NAVBAR -->
      <div id="home-page">

        <h1>Solar Design Fundamentals</h1>

        <div class="row" id="centered-row">
          <div class="col-xs-10" id="choose_source">
            <form class="form-inline" role="form">
              <div class="form-group" id="learning_modules_form_group">
                <label for="inputSource" class="col-sm-3 control-label">Choose
                  Module</label>
                <div class="col-sm-7">
                  <sf:select path="learningModules" class="form-control"
                    id="selectSource" multiple="false">
                    <c:forEach items="${question_counts}" var="item"
                      varStatus="count">
                      <option value="${item.source.id}">
                            ${item.source.description}</option>
                    </c:forEach>
                  </sf:select>
                  <div id="error-text">
                    <sf:errors path="learningModules"></sf:errors>
                  </div>
                </div>
            </form>
          </div>
        </div>

        <div class="row" id="centered-row">
          <div class="col-xs-4"></div>
          <div id="home-buttons">
            <sec:authorize access="isAuthenticated()">
              <div class="col-xs-2">
                <a class="btn btn-primary" id="newquiz-button"
                  role="button">Go To Module</a>
              </div>
              <div class="col-xs-2">
                <a class="btn btn-primary" id="reset_stats-button"
                  role="button">Reset Quiz Stats</a>
              </div>
            </sec:authorize>
          </div>
        </div>


        <div class="row" id="row">
          <H4>Module Quiz Stats</H4>
          <div class="col-xs-2"></div>
          <div class="col-xs-8" id="home-stats">
            <table id="stats-table" class="table table-bordered"
              align="left">
              <tr>
                <th>Module</th>
                <th>Question Count</th>
              </tr>
              <c:forEach var="question_stats" items="${question_counts}"
                varStatus="loop">
                <tr>
                  <td id="source_id">
                    <div> ${question_stats.source.description} </div>
                    <div class="progress" id="progress-${loop.index}">
                      <div class="progress-bar progress-bar-success"
                          role="progressbar" aria-valuenow
                          ="${questions_stats.done_pct}" aria-valuemin="0"
                          aria-valuemax="100"
                          style="width: ${question_stats.done_pct}%;">
                          ${question_stats.done_pct}% Done
                          ( ${question_stats.done_count} of 
                          ${question_stats.count} )
                          </div>
                    </div>
                  </td>
                  <td id="count_id">${question_stats.count}</td>
                </tr>
              </c:forEach>
            </table>
          </div>
        </div>


      </div>
    </div>
  </div>
  </div>
  <!--
  <div id="cover-image">
    <img src="static/images/Cover.JPG">
  </div 
  -->
  >
</body>
</html>
