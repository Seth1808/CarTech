<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>
	<div class="row">
		<div class="container">
			<h3 class="text-center">List of Product</h3>
			<hr>
			<div class="container text-left">
				<a href="<%=request.getContextPath()%>/Products.jsp" class="btn btn-success">Add New Product</a>
			</div>
			<br>
			<table class="table">
				<thead>
					<tr>
						<th>Name</th>
						<th>Description</th>
						<th>Price</th>
						<th>Specs</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="ProductList" items="${listProducts}">
						<tr>
							<td><c:out value="${ProductList.name}" /></td>
							<td><c:out value="${ProductList.description}" /></td>
							<td><c:out value="${ProductList.price}" /></td>
							<td><c:out value="${ProductList.specs}" /></td>
							<td><a href="edit?name=<c:out value='${products.name}'/>">Edit</a>
								&nbsp;&nbsp;&nbsp;&nbsp; 
						     <a href="delete?name=<c:out value='${products.name}' />">Delete</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>