<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<jsp:useBean id="num1" class="org.beans.math" scope="page" />
<jsp:useBean id="num2" class="org.beans.math" scope="page"/>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>use java beans</title>
</head>
<body>
	The value of num1 is : <jsp:getProperty property="number" name="num1"/><br>
	The value of num2 is : <jsp:getProperty property="number" name="num2"/><br>
	<jsp:setProperty property="number" name="num2" value="12"/><br>
	The value of num2 is : <jsp:getProperty property="number" name="num2"/><br>
	<% num2.doubleNumber(); %>
	The value of num2 is : <jsp:getProperty property="number" name="num2"/>
</body>
</html>