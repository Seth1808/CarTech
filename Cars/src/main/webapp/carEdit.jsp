<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<title>car edit</title>


<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
	
	

<meta charset="ISO-8859-1">
<title>Car</title>
</head>
<body>
	<nav class="navbar navbar-expand-md navbar-light">
		<div>
			<a class="navbar-brand"> Car Management Application </a>
		</div>
		<ul class="navbar-nav">
			<li><a
				href="<%=request.getContextPath()%>/RegCarServlet/dashboardCar"
				class="nav-link">Back to Dashboard</a></li>
		</ul>
	</nav>
	<div class="container col-md-6">
		<div class="card">
			<div class="card-body">
				<c:if test="${car_model != null}">
					<form action="update" method="post"></form>
				</c:if>
				<c:if test="${car_model == null}">
					<form action="insert" method="post"></form>
				</c:if>
				<caption>
					<h2>
						<c:if test="${car_model != null}">
Edit Car
</c:if>
						<c:if test="${car_model == null}">
Add New Car
</c:if>
					</h2>
				</caption>
				<c:if test="${car_model != null}">
					<input type="hidden" name="oricar_model"
						value="<c:out value='${car_model.car_model}' />" />
				</c:if>
				<fieldset class="form-group">
					<label>Car Model</label> <input type="text"
						value="<c:out value='${car_model.car_model}' />" class="form-control"
						name="name" required="required">
				</fieldset>
				<fieldset class="form-group">
					<label>License Plate</label> <input type="text"
						value="<c:out value='${car_model.license_plate}' />" class="form-control"
						name="password">
				</fieldset>
				<fieldset class="form-group">
					<label>Car Warranty</label> <input type="text"
						value="<c:out value='${car_model.warranty}' />" class="form-control"
						name="email">
				</fieldset>
				<button type="submit" class="btn btn-success">Save</button>

			</div>
		</div>
	</div>
</body>
</html>