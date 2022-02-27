<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<h1>HELLO Testing</h1>


	<form action="RegCarCreateServlet" method="post">
		Car Model: <input type="text" name="carModel"> 
		License Plate: <input type="text" name="licensePlate"> 
		Warranty: <input type="text"name="warranty"> 
		
		<input type="submit" value="Call Servlet" />
	</form>


</body>
</html>