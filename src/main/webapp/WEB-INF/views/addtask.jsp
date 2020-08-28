<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add Task</title>
<link rel="stylesheet" href="/style.css" />
</head>
<body>
	<%@include file="partials/header.jsp" %>
	<h1>Add Task</h1>
	 
	
	<form action="/addtask" method="post"> 
	<p>
			<label for="userId">UserId:</label> <c:out value="${ userId}" />
		</p>
		<p>
			<label for="description">Description:</label> <input id="description" name="description" value="${ param.description }" required minlength="2" />
		</p>
		<p>
			<label for="dueDate">Due Date:</label> <input id="dueDate" type="date" name="dueDate" required  />
		</p>		 
		<p>
			<button>ADD</button>  <a href="/">Never mind</a>
		</p>
	</form>
</body>
</html>