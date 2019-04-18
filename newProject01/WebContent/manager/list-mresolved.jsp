<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>

<head>
<title>Manager requests Page</title>
<link type="text/css" rel="stylesheet" href="css/style.css">
</head>
<body>

	<!--  NAVBAR -->
	<ul>
		<li><a href="http://localhost:8088/newProject01/manager/mhome.jsp">Home</a></li>
		<li><a href="http://localhost:8088/newProject01/ReimbMgrViewPendServlet">View Pending Request</a></li>
		<li><a class="active" href="http://localhost:8088/newProject01/ReimbMgrViewResolvedServlet">View Resolved Request</a></li>
		<li><a href="http://localhost:8088/newProject01/EmployeeControllerServlet">View Employee Info</a></li>
		<li><a href="http://localhost:8088/newProject01/mgrMyInfoServlet">View My Info</a></li>
		<li><a href="http://localhost:8088/newProject01/index.html" class="btn-danger">Log Out</a></li>
	</ul>

	<div id="wrapper">
		<div id="header">
			<h2>Manager: View resolved Requests</h2>
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