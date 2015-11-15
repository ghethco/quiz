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

      <div id="question-panel">
        <div class="panel panel-primary">
          <div class="panel-heading">
            <h3 class="panel-title">Question: (correct answers:
              ${num_correct} incorrect: ${num_incorrect})</h3>
          </div>
          <div class="panel-body">${question_obj.question}</div>
        </div>
      </div>

      <!-- invisible divs to store question values -->
      <div class="q-invis" id="question-id">${question_obj.id}</div>
      <div class="q-invis" id="question-source">${question_obj.source.id}</div>
      <div class="q-invis" id="question-correct">${question_obj.correct}</div>
      <div class="q-invis" id="question-text">${question_obj.question}</div>
      <div class="q-invis" id="question-img-path">${pageContext.request.contextPath}/static/images/</div>
      <div class="q-invis" id="question-before">${question_obj.before_image}</div>
      <div class="q-invis" id="question-after">${question_obj.after_image}</div>
      <div class="q-invis" id="question-after">${question_obj.after_image}</div>
      <div class="q-invis" id="question-type">${question_obj.type}</div>
      <div class="q-invis" id="question-answer1">${question_obj.answer1}</div>
      <div class="q-invis" id="question-answer2">${question_obj.answer2}</div>
      <div class="q-invis" id="question-answer3">${question_obj.answer3}</div>
      <div class="q-invis" id="question-answer4">${question_obj.answer4}</div>
      <div class="q-invis" id="question-answer5">${question_obj.answer5}</div>
      <div class="q-invis" id="question-answer6">${question_obj.answer6}</div>

      <sec:authorize access="isAuthenticated()">
        <form class="form-horizontal" id="main-form" action="#">

          <div id="form-elements"></div>

          <div class="form-group">
            <div class="col-sm-offset-2 col-sm-6">
              <button type="button" class="btn btn-primary"
                id="submit-answer-button">Submit Answer</button>
              <button type="button" class="btn btn-primary"
                id="see-answer-button">Show Answer</button>
              <button type="button" class="btn btn-primary"
                id="mark-correct-button">Mark Correct</button>
              <button type="button" class="btn btn-primary"
                id="mark-incorrect-button">Mark Incorrect</button>
            </div>
          </div>

          <div class="form-group">
            <div class="col-sm-offset-2 col-sm-6">
              <button type="button" class="btn btn-primary"
                id="next-question-button">Next Question</button>
            </div>
          </div>

        </form>
        <div id="illustration">
          <div class="col-sm-6" id="image_div"></div>
        </div>
      </sec:authorize>

    </div>
  </div>
</body>
</html>