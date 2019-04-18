<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>

<head>
<title>Employee Information Page</title>
<link type="text/css" rel="stylesheet" href="css/style.css">
</head>
<body>

	<!--  NAVBAR -->
	<ul>
		<li><a href="http://localhost:8088/newProject01/employee/ehome.jsp">Home</a></li>
		<li><a href="http://localhost:8088/newProject01/employee/submitRequest.html">Submit a Request</a></li>
		<li><a class="active"  href="http://localhost:8088/newProject01/ReimbEmpViewPendServlet">View my Pending Request</a></li>
		<li><a href="http://localhost:8088/newProject01/ReimbEmpViewResolvedServlet">View my Resolved Request</a></li>
		<li><a href="http://localhost:8088/newProject01/EmpMyInfoServlet">View My Info</a></li>
		<li><a href="http://localhost:8088/newProject01/index.html"
			class="btn-danger">Log Out</a></li>
	</ul>

	<div id="wrapper">
		<div id="header">
			<h2>Employee Pending Requests</h2>
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

					<c:if test="${tempReimbursement.status ==  'pending'}">
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






	<!--  
	<button type="button" onclick="pending()">View Pending</button>
	<button type="button" onclick="denied()">View Denied</button>
	<button type="button" onclick="approved()">View Approved</button>
	<button type="button" onclick="all()">View All</button>



	<script>
		function pending() {
			console.log("Pending() activated!");
			var xhttp = new XMLHttpRequest();
			xhttp.onreadystatechange = function() {
				if (this.readyState == 4 && this.status == 200) {
					var x = document.getElementById("pending");
					x.style.display = "block";
					
				}
			};
			xhttp
					.open(
							"GET",
							"http://localhost:8088/newProject01/ReimbEmpViewPendServlet",
							true);
			xhttp.send();
		}
	</script>
 -->


</body>
</html>