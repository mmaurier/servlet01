<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>declare scriptlet comments</title>
</head>
<body>
<h2>declarations</h2>
<%! 
	String name = "marco"; 
	private double getRandomNumber() { return Math.random(); }	
%>

<h2>scriptlet</h2>
<%
	out.println(name + ", software developer");
	out.println("number: " + getRandomNumber());
%>
5 * 2 = <%=5*2%>
<h2>output comment</h2>
<!--  this  is a comment -->
<!--  the user name <%=name%> -->
<h2>hidden comment</h2>
<%-- this is a hidden comment --%>
</body>
</html>