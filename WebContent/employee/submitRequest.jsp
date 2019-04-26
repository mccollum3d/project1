<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>

<head>
	<meta charset="ISO-8859-1">
	<title>Submit Reimbursement Request</title>
	<link type="text/css" rel="stylesheet" href="../css/style.css">
	<link type="text/css" rel="stylesheet" href="../css/add-student-style.css">
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
		<li><a class="active" href="/newProject01/employee/submitRequest.jsp">Submit a Request</a></li>
		<li><a href="/newProject01/ReimbEmpViewPendServlet">View my Pending Request</a></li>
		<li><a href="/newProject01/ReimbEmpViewResolvedServlet"">View my Resolved Request</a></li>
		<li><a href="/newProject01/EmpMyInfoServlet">View My Info</a></li>
		<li><a href="/newProject01/LogoutServlet" class="btn-danger">Log Out</a></li>
	</ul>
	<br>
	<div id="wrapper">
		<div id="header">
			<h2> Request a Reimbursement</h2>
		</div>
	</div>
	
	<div id="container">
		<h3 style="text-align: center">Submit Request</h3>
		
		<form action="../ReimbEmpViewPendServlet" method="GET">
		
			<input type="hidden" name="command" value="ADD" />
			
			<table>
				<tbody>
					<tr>
						<td><label>First Name:</label></td>
						<td><input type="text" name="firstName" /></td>
					</tr>
					<tr>
						<td><label>Last Name:</label></td>
						<td><input type="text" name="lastName" /></td>
					</tr>
					<tr>
						<td><label>Comment:</label></td>
						<td><input type="text" name="comment" /></td>
					</tr>
					<tr>
						<td><label>Amount:</label></td>
						<td><input type="text" name="amount" /></td>
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
			<!-- <a href="../index.html">Back to Login</a> -->   
		</p>	
	</div>
</body>

</html>