<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="ISO-8859-1">
	<title>Update Employee Info</title>
	<link type="text/css" rel="stylesheet" href="css/style.css">
	<link type="text/css" rel="stylesheet" href="css/add-student-style.css">
</head>

<body>
<%
response.setHeader("Cache-Control","no-cache"); //Forces caches to obtain a new copy of the page from the origin server
response.setHeader("Cache-Control","no-store"); //Directs caches not to store the page under any circumstance
response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
response.setHeader("Pragma","no-cache"); //HTTP 1.0 backward compatibility
String userName = (String) session.getAttribute("User");
if (null == userName) {
   request.setAttribute("Error", "Session has ended.  Please login.");
   response.sendRedirect("http://localhost:8088/newProject01/index.html");
}
%>
	<div id="wrapper">
		<div id="header">
			<h2 style="text-align: center"> Update Employee's Info</h2>
		</div>
	</div>
	
	<div id="container">
		<h3 style="text-align: center">Update Info</h3>
		
		<form action="http://localhost:8088/newProject01/EmpMyInfoServlet" method="GET">
		
			<input type="hidden" name="command" value="UPDATE" />
			<input type="hidden" name="employeeId" value="${THE_EMPLOYEE.id}" />
			
			<table>
				<tbody>
					<tr>
						<td><label>First Name:</label></td>
						<td><input type="text" name="firstName" value="${THE_EMPLOYEE.firstName}" /></td>
					</tr>
					<tr>
						<td><label>Last Name:</label></td>
						<td><input type="text" name="lastName" value="${THE_EMPLOYEE.lastName}" /></td>
					</tr>
					<tr>
						<td><label>Login Name:</label></td>
						<td><input type="text" name="loginName" value="${THE_EMPLOYEE.loginName}" /></td>
					</tr>
					<tr>
						<td><label>Password:</label></td>
						<td><input type="password" name="password" value="${THE_EMPLOYEE.password}" /></td>
					</tr>
					
					<!--  All requests will start off as pending -->
					
					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Save" class="save" /></td>
					</tr>
					
				</tbody>
			</table>	
		</form>
		
		<div style="clear: both;">
		</div>	
		<p style="text-align: center">
			<a href="http://localhost:8088/newProject01/EmpMyInfoServlet">Back to Employee Info</a>
		</p>
		
	</div>	
</body>
</html>