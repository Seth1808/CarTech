<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<meta charset="ISO-8859-1">
<title>Cars</title>
</head>
<body>
<div class="row">
		<div class="container">
			<h3 class="text-center">List of Users</h3>
			<hr>
			<div class="container text-left">
				<!-- Add new user button redirects to the register.jsp page -->
				<a href="<%=request.getContextPath()%>/CreateRegCars.jsp"
					class="btn btn-success">Add New Car</a>
			</div>
			<br>
			<!-- Create a table to list out all current users information -->
			<table class="table">
				<thead>
					<tr>
						<th>Car Model</th>
						<th>License Plate</th>
						<th>Warranty</th>
						<th>Actions</th>
					</tr>
				</thead>
				<!-- Pass in the list of cars receive via the Servlet’s response to a loop -->
				<tbody>
					<c:forEach var="car" items="${listCars}">
						<!-- For each user in the database, display their information accordingly -->
						<tr>
							<td><c:out value="${car.carModel}" /></td>
							<td><c:out value="${car.licensePlate}" /></td>
							<td><c:out value="${car.warranty}" /></td>
							<!-- For each user in the database, Edit/Delete buttons which invokes the edit/delete functions -->
							<td><a href="editCar?car_model=<c:out value='${car.carModel}' />">Edit</a>
								&nbsp;&nbsp;&nbsp;&nbsp; <a
								href="deleteCar?car_model=<c:out value='${car.carModel}' />">Delete</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>