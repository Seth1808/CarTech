<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet"
href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
crossorigin="anonymous">
<meta charset="ISO-8859-1">
<title>List of Staff Contacts</title>
</head>

<body>
<div class="row">
<div class="container">
<h3 class="text-center">List of Staff Contacts</h3>
<hr>
<div class="container text-left">
<!-- Add new contact button redirects to the contactreg.jsp page -->
<a href="<%=request.getContextPath()%>/contactreg.jsp" class="btn btn-success">Add New Contact</a>
</div>

<br>
<!-- Create a table to list out all current contacts information -->
<table class="table">
<thead>
<tr>
<th>Name</th>
<th>Position</th>
<th>Phone Number</th>
<th>Email</th>

<th>Actions</th>
</tr>
</thead>

<tbody>
<c:forEach var="contact" items="${listContacts}">
<tr>
<td>
<c:out value="${contact.name}"/>
</td>
<td>
<c:out value="${contact.position}"/>
</td>
<td>
<c:out value="${contact.phonenumber}"/>
</td>
<td>
<c:out value="${contact.email}"/>
</td>

<td>
<a href="edit?name=<c:out value='${contact.name}'/>">Edit</a>
 &nbsp;&nbsp;&nbsp;&nbsp;
<a href="delete?name=<c:out value='${contact.name}'/>">Delete</a>
</td>
</tr>
</c:forEach>
</tbody>
</table>
</div>
</div>
</body>
</html>