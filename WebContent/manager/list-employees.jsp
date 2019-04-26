<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>

<head>
<title>Employee Information Page</title>
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
	<ul>
		<li><a href="/newProject01/manager/mhome.jsp">Home</a></li>
		<li><a href="/newProject01/ReimbMgrViewPendServlet">View Pending Request</a></li>
		<li><a href="/newProject01/ReimbMgrViewResolvedServlet">View Resolved Request</a></li>
		<li><a class="active" href="/newProject01/EmployeeControllerServlet">View Employee Info</a></li>
		<li><a href="/newProject01/mgrMyInfoServlet">View My Info</a></li>
		<li><a href="/newProject01/LogoutServlet" bgcolor="red">Log Out</a></li>
	</ul>
	<br>
	<div id="wrapper">
		<div id="header">
			<h2>Employee Info</h2>
		</div>
	</div>
	<br>
	<div id="fancy">
		<div id="content">
			<table align="center">

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