<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="ProductAddServlet" method="post">
     image<input type="text" name="Image link">
     name<input type="text" name="Propduct name">
     description<input type="text" name="Description">
     price<input type="text" name="Product price">
     <input type="submit" value="Add Product"/>
</form>
</body>
</html>