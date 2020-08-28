<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>My Tasks</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
	integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
	crossorigin="anonymous">
<link rel="stylesheet" href="/style.css" />
</head>
<body>
	<%@include file="partials/header.jsp"%>
	<h1>My Tasks</h1>

	<div class="container">

		<div class="card">
			<table class="table">
				<tr>
					<th>Description</th>
					<th>Due Date</th>
					<th>Mark Complete</th>
					<th>Remove</th>
				</tr>
				<c:forEach var="task" items="${tasks}">
					<tr>
						<td>${task.description}</td>
						<td>${task.dueDate}</td>
						<td> 
					 <input class="disabled-checkbox" type="checkbox" ${task.complete==true?'checked':''} ></input>
						<a class="btn ${task.complete==true?'btn-secondary disabled-checkbox':'btn-primary'}"
							href="/updatecompletetask?id=${task.id}">
								${task.complete==true?'Completed':'Complete'} </a> 
						</td>
						
						<td><a class="btn btn-danger"
							href="/deleteTask?id=${task.id}">Remove</a></td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<br> <br>
		<p>
			<a href="/addtask">Add Task</a>
		</p>
	</div>


</body>
</html>