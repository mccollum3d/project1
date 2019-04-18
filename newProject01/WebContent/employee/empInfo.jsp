<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>

<head>
<title>Your Information Page</title>
<link type="text/css" rel="stylesheet" href="css/style.css">
</head>

<body>
<!-- NAVBAR -->
	<ul>
		<li><a href="http://localhost:8088/newProject01/employee/ehome.jsp">Home</a></li>
		<li><a href="http://localhost:8088/newProject01/employee/submitRequest.html">Submit a Request</a></li>
		<li><a href="http://localhost:8088/newProject01/ReimbEmpViewPendServlet">View my Pending Request</a></li>
		<li><a href="http://localhost:8088/newProject01/ReimbEmpViewResolvedServlet">View my Resolved Request</a></li>
		<li><a class="active" href="http://localhost:8088/newProject01/EmpMyInfoServlet">View My Info</a></li>
		<li><a href="http://localhost:8088/newProject01/index.html" class="btn-danger">Log Out</a></li>
	</ul>

	<div id="wrapper">
		<div id="header">
			<h2>Your Info</h2>
		</div>
	</div>

	<div id="fancy">
		<div id="content">
			<table>

				<tr>
					<th>Emp. ID</th>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Login Name</th>
					<th>Job Title</th>
					<th>Actions:</th>
				</tr>

				<c:forEach var="tempEmployee" items="${EMPLOYEE_LIST}">
				
				<c:url var="tempLink" value="http://localhost:8088/newProject01/EmpMyInfoServlet">
					<c:param name="command" value="LOAD" />
					<c:param name="employeeId" value="${tempEmployee.id}" />
				
				</c:url>
					<tr>
						<td>${tempEmployee.id}</td>
						<td>${tempEmployee.firstName}</td>
						<td>${tempEmployee.lastName}</td>
						<td>${tempEmployee.loginName}</td>
						<td>
							<c:if test="${tempEmployee.manager == 0}">Employee</c:if>
							<c:if test="${tempEmployee.manager == 1}">Manager</c:if>
						</td>
						<td> <a href="${tempLink}">Update</a></td>
					</tr>

				</c:forEach>

			</table>
		</div>
	</div>

</body>

</html>