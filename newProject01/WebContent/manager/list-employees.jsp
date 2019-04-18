<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>

<head>
<title>Employee Information Page</title>
<link type="text/css" rel="stylesheet" href="css/style.css">
</head>

<body>

	<ul>
		<li><a href="http://localhost:8088/newProject01/manager/mhome.jsp">Home</a></li>
		<li><a href="http://localhost:8088/newProject01/ReimbMgrViewPendServlet">View Pending Request</a></li>
		<li><a href="http://localhost:8088/newProject01/ReimbMgrViewResolvedServlet">View Resolved Request</a></li>
		<li><a class="active" href="http://localhost:8088/newProject01/EmployeeControllerServlet">View Employee Info</a></li>
		<li><a href="http://localhost:8088/newProject01/mgrMyInfoServlet">View My Info</a></li>
		<li><a href="http://localhost:8088/newProject01/index.html" class="btn-danger">Log Out</a></li>
	</ul>
	<!--  I don't think this page is meant to be run directly, but by calling the EmployeeControllerServlet -->
	<!--  which then does RequestDispatcher dispatcher = request.getRequestDispatcher("/list-employees.jsp"); -->

	<div id="wrapper">
		<div id="header">
			<h2>Fakeblock Employee Info</h2>
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
				</tr>

				<c:forEach var="tempEmployee" items="${EMPLOYEE_LIST}">

					<tr>
						<td>${tempEmployee.id}</td>
						<td>${tempEmployee.firstName}</td>
						<td>${tempEmployee.lastName}</td>
						<td>${tempEmployee.loginName}</td>
						<td>
							<c:if test="${tempEmployee.manager == 0}">Employee</c:if>
							<c:if test="${tempEmployee.manager == 1}">Manager</c:if>
						</td>
					</tr>

				</c:forEach>

			</table>
		</div>
	</div>

</body>

</html>