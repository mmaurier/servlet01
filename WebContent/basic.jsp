<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Basic JSP</title>
</head>
<body>
	<h1>
	<% 
		System.out.println("before break point...");
		String name = request.getParameter("name");
		System.out.println("after break point...");
		if (name != null) { out.print("hello " + name + " start coding"); }
		else { out.print("hello world"); }
	%>
	</h1>
</body>
</html>