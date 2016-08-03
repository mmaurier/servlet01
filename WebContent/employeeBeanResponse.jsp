<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" errorPage="error.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>EmployeeBeanResponse</title>
</head>
<body>
<jsp:useBean id="Employee" class="org.beans.Employee">
	<jsp:setProperty name="Employee" property="*" />
</jsp:useBean>

<%
	final java.util.List missingFields = new java.util.ArrayList();
	if (Employee.getLastName() == null || Employee.getLastName().length() == 0) {
	    missingFields.add("Last Name");
	}
	if (Employee.getEmployeeNo() == null || 
	    Employee.getEmployeeNo().length() == 0) {
	    missingFields.add("Employee Number");
	}
	if (missingFields.isEmpty() == false) {
	    throw new org.beans.MissingInputException("Fields were missing: " 
	        + missingFields.toString());
	}
%>

<P>
First name: <jsp:getProperty name="Employee" property="firstName"/><BR>
Last name: <jsp:getProperty name="Employee" property="lastName"/><BR>
Middle name: <jsp:getProperty name="Employee" property="middleName"/><BR>
Employee No.: <jsp:getProperty name="Employee" property="employeeNo"/><BR>
</P>
</body>
</html>