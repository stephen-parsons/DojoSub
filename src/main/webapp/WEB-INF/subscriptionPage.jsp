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
		<title>Subscription Page</title>
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

			<h1>Welcome to Dojo Subscriptions, ${user.firstName}!</h1>

			<h3>Please choose a subscription and start date:</h3>

			<form id="sub_select" action="/users/sub" method="POST">
				<p>
					Start Date: <select name="duedatelist" form="sub_select">
						<c:forEach var="counter" begin="1" end="31">
						    <option value="${counter}">${counter}</option>
						</c:forEach>
					</select>
				</p>
				<p>
					<c:if test="${subs.size() > 0}">
					Subscription: <select name="sublist" form="sub_select">
						<c:forEach items="${subs}" var="sub">
							<option value="${sub.id}">${sub.name} ($${sub.cost})</option>	
						</c:forEach>
					</select>
					</c:if>
					<c:if test="${subs.size() == 0}">
					No subscriptions currently available.
					</c:if>
				</p>
				<p>
					<input type="submit" value="Sign up!">
				</p>
			</form>
		</div>
	</body>
</html>