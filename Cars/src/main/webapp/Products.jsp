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
     Name<input type="text" name="name">
     Description<input type="text" name="description">
     Price<input type="text" name="price">
     Specs<input type="text" name="specs">
     <input type="submit" value="Add Product"/>
</form>
</body>
</html>