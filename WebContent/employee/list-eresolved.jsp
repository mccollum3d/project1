<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>

<head>
<title>Employee requests Page</title>
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

	<!--  NAVBAR -->
	<ul>
		<li><a href="/newProject01/employee/ehome.jsp">Home</a></li>
		<li><a href="/newProject01/employee/submitRequest.jsp">Submit a Request</a></li>
		<li><a href="/newProject01/ReimbEmpViewPendServlet">View my Pending Request</a></li>
		<li><a class="active" href="/newProject01/ReimbEmpViewResolvedServlet"">View my Resolved Request</a></li>
		<li><a href="/newProject01/EmpMyInfoServlet">View My Info</a></li>
		<li><a href="/newProject01/LogoutServlet"
			class="btn-danger">Log Out</a></li>
	</ul>
	<br>
	<div id="wrapper">
		<div id="header">
			<h2 style="text-align: center">Employee Pending Requests</h2>
		</div>
	</div>

	<div id="container">
		<div id="content">
			<table>

				<tr>
					<th>Request ID</th>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Comment</th>
					<th>Amount</th>
					<th>Status</th>
				</tr>
				
				<c:forEach var="tempReimbursement" items="${EPENDING_LIST}">

					<c:if test="${tempReimbursement.status !=  'pending'}">
						<tr>
							<td>${tempReimbursement.id}</td>
							<td>${tempReimbursement.firstName}</td>
							<td>${tempReimbursement.lastName}</td>
							<td>${tempReimbursement.comment}</td>
							<td>${tempReimbursement.amount}</td>
							<td>${tempReimbursement.status}</td>
						</tr>
					</c:if>
				</c:forEach>
			</table>
		</div>
	</div>

</body>
</html>