<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>

<head>
<title>Your Information Page</title>
<link type="text/css" rel="stylesheet" href="css/style.css">
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
   response.sendRedirect("/newProject01/index.html");
}
%>
	<!-- NAVBAR -->
	<ul>
		<li><a href="/newProject01/manager/mhome.jsp">Home</a></li>
		<li><a href="/newProject01/ReimbMgrViewPendServlet">View Pending Request</a></li>
		<li><a href="/newProject01/ReimbMgrViewResolvedServlet">View Resolved Request</a></li>
		<li><a href="/newProject01/EmployeeControllerServlet">View Employee Info</a></li>
		<li><a class="active" href="/newProject01/mgrMyInfoServlet">View My Info</a></li>
		<li><a href="/newProject01/LogoutServlet" class="btn-danger">Log Out</a></li>
	</ul>
	<br>
	<div id="wrapper">
		<div id="header">
			<h2>Your Info</h2>
		</div>
	</div>
	<br>
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
				
				<c:url var="tempLink" value="/mgrMyInfoServlet">
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