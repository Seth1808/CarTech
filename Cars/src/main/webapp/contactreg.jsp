<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="ContactregServlet" method="post">
 Name: <input type="text" name="name">
 Position: <input type="text" name="position">
 Phone Number: <input type="text" name="phonenumber">
 Email: <input type="text" name="email">

 
 <input type="submit" value="Call Servlet" />
 </form>
</body>
</html>