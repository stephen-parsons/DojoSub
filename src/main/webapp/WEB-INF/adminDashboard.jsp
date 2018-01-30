<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<jsp:useBean id="format" class="com.project.dojosub.services.DateFormatterService"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Admin Dashboard</title>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ" crossorigin="anonymous">
		<script src="https://code.jquery.com/jquery-3.1.1.slim.min.js" integrity="sha384-A7FZj7v+d/sdmMqp/nOQwliLvUsJfDHW+k9Omg/a/EheAdgtzNs3hpfag6Ed950n" crossorigin="anonymous"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js" integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb" crossorigin="anonymous"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js" integrity="sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn" crossorigin="anonymous"></script>
	</head>

	<body>
		<div class="container">
		<form action="/users/logout" method="get">
			<p>
				<input type="submit" value="Logout">
			</p>
		</form>

		<h1>Admin Dashboard</h1>

		<hr>

		<!-- CUSTOMERS TABLE -->

		<table class="table">
			<tr>
				<th>Name</th>
				<th>Next Due Date</th>
				<th>Amount Due</th>
				<th>Package Type</th>
			</tr>
			<c:forEach items="${users}" var="user">
				<tr>
					<td>${user.firstName} ${user.lastName}</td>
					<td>${format.findNextDueDate(user)}</td>
					<td>$${user.getSub().cost}</td>
					<td>${user.getSub().name}</td>
				</tr>	
			</c:forEach>
		</table>

		<!-- PACKAGES TABLE -->

		<table class="table">
			<tr>
				<th>Package Name</th>
				<th>Package Cost</th>
				<th>Available</th>
				<th>Users</th>
				<th>Actions</th>
			</tr>
			<c:forEach items="${subs}" var="sub">
				<tr>
					<td>${sub.name}</td>
					<td>$${sub.cost}</td>
					<td>
						<c:if test="${sub.available == true}">
							Available
						</c:if>
						<c:if test="${sub.available == false}">
							Unavailable
						</c:if>
					</td>
					<td>${sub.getUsers().size()}</td>
					<td>
						<c:if test="${sub.available == true}">
							<a href="admin/sub/deactivate/${sub.id}">Deactivate</a>
						</c:if>
						<c:if test="${sub.available == false}">
							<a href="admin/sub/activate/${sub.id}">Activate</a>
						</c:if>
						<c:if test="${sub.getUsers().size() == 0}">
							<a href="admin/sub/delete/${sub.id}">Delete</a>
						</c:if>
					</td>
				</tr>	
			</c:forEach>
		</table>

		<!-- FORM FOR ADDING PACKAGE -->

		<h3>Add a new package</h3>

		<p><form:errors path="sub.*"/></p>

		<c:if test="${errors != null}">
	    	<c:forEach items="${errors}" var="err">
	    		<p style="color:red;">${err.defaultMessage}</p>
	    	</c:forEach>
	    </c:if>

		<form:form method="POST" action="admin/sub/create" modelAttribute="sub">
                <p>
                    <form:label path="name">Package Name:</form:label>
                    <form:input path="name"/>
                </p>
                <p>
                    <form:label path="cost">Cost:</form:label>
                    <form:input path="cost"/>
                </p>
                <input type="submit" value="Create"/>
            </form:form>

		
		</div>
	</body>
</html>