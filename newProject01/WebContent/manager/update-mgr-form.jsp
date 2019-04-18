<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="ISO-8859-1">
	<title>Update Manager Info</title>
	<link type="text/css" rel="stylesheet" href="css/style.css">
	<link type="text/css" rel="stylesheet" href="css/add-student-style.css">
</head>


<body>
	<div id="wrapper">
		<div id="header">
			<h2> Update Manager's Info</h2>
		</div>
	</div>
	
	<div id="container">
		<h3>Update Info</h3>
		
		<form action="./mgrMyInfoServlet" method="GET">
		
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
						<td><input type="text" name="loginName" value="${THE_EMPLOYEE.loginName}"  /></td>
					</tr>
					<tr>
						<td><label>Password:</label></td>
						<td><input type="password" name="password" value="${THE_EMPLOYEE.password}"  disabled/></td>
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
		
		<p>
			<a href="http://localhost:8088/newProject01/mgrMyInfoServlet">Back to Manager Info</a>
		</p>
		
		
	</div>
	
	
	
	
	
	
</body>

</html>













