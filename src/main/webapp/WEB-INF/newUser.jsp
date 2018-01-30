<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Login/Registration</title>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ" crossorigin="anonymous">
		<script src="https://code.jquery.com/jquery-3.1.1.slim.min.js" integrity="sha384-A7FZj7v+d/sdmMqp/nOQwliLvUsJfDHW+k9Omg/a/EheAdgtzNs3hpfag6Ed950n" crossorigin="anonymous"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js" integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb" crossorigin="anonymous"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js" integrity="sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn" crossorigin="anonymous"></script>
	</head>

	<body>
		<div class="container">
			<h1>Dojoscriptions</h1>
			<h3>A Subscriptions platform</h3>
			<br>
		    <c:if test="${errors != null}">
		    	<c:forEach items="${errors}" var="err">
		    		<p style="color: red;">${err.defaultMessage}</p>
		    	</c:forEach>
		    </c:if>

			<form:form action="/users/new" method="post" modelAttribute="user">
				<p>
					<form:label path="firstName">First Name:
						<form:errors path="firstName"></form:errors>
						<form:input type="text" path="firstName"></form:input>
					</form:label>
				</p>
				<p>
					<form:label path="lastName">Last Name:
						<form:errors path="lastName"></form:errors>
						<form:input type="text" path="lastName"></form:input>
					</form:label>
				</p>
				<p>
					<form:label path="email">Email:
						<form:errors path="email"></form:errors>
						<form:input type="text" path="email"></form:input>
					</form:label>
				</p>
				<p>
					<c:if test="${pwerror != null}">
	    				<p style="color: red;">${pwerror}</p>
		    		</c:if>
					<form:label path="password">Password:
						<form:errors path="password"></form:errors>
						<form:input type="password" path="password"></form:input>
					</form:label>
				</p>
				<p>
					<form:label path="passwordConfirmation">Confirm Password:
						<form:errors path="passwordConfirmation"></form:errors>
						<form:input type="password" path="passwordConfirmation"></form:input>
					</form:label>
				</p>

				<input type="submit" value="Register">
			</form:form>
			<br>

		    <c:if test="${error != null}">
	    		<p style="color: red;">${error}</p>
		    </c:if>

			<form action="/users/login" method="post">
				<p>
					<label>Email:
						<input type="text" name="email">
					</label>
				</p>
				<p>
					<label>Password:
						<input type="password" name="password">
					</label>
				</p>

				<input type="submit" value="Login">
			</form>
		</div>
	</body>
</html>