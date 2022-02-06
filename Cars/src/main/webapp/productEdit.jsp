<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title>Contact Management Application</title>
 <link rel="stylesheet"
href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
crossorigin="anonymous">
<meta charset="ISO-8859-1">
</head>
<body>
<nav class="navbar navbar-expand-md navbar-light">
<div>
<a class="navbar-brand"> ProductList Management Application </a>
</div>
<ul class="navbar-nav">
<li><a href="<%=request.getContextPath()%>/ProductListServlet/dashboard"
class="nav-link">Back to Dashboard</a></li>
</ul>
</nav>
<div class="container col-md-6">
<div class="card">
<div class="card-body">
<c:if test="${ProductList != null}">
<form action="update" method="post">
</c:if>
<c:if test="${ProductList == null}">
<form action="insert" method="post">
</c:if>
<caption>
<h2>
<c:if test="${ProductList != null}">
Edit contact
</c:if>
<c:if test="${ProductList == null}">
Add New contact
</c:if>
</h2>
</caption>
<c:if test="${ProductList != null}">
<input type="hidden" name="oriName" value="<c:out
value='${ProductList.name}' />" />
</c:if>
<fieldset class="form-group">
<label>Name</label> <input type="text" value="<c:out
value='${ProductList.name}' />" class="form-control" name="name" required="required">
</fieldset>
<fieldset class="form-group">
<label>Position</label> <input type="text" value="<c:out
value='${ProductList.description}' />" class="form-control" name="description">
</fieldset>
<fieldset class="form-group">
<label>Phone Number</label> <input type="text" value="<c:out
value='${ProductList.price}' />" class="form-control" name="price">
</fieldset>
<fieldset class="form-group">
<label>Email</label> <input type="text" value="<c:out
value='${ProductList.specs}' />" class="form-control" name="specs">
</fieldset>
<button type="submit" class="btn btn-success">Save</button>
</form>
</div>
</div>
</div>
</body>
</html>