<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Manager Homepage!</title>
	<link rel="stylesheet" type="text/css" href="../css/style.css">
</head>
<body>

<%
Cookie[] theCookies = request.getCookies();
if (theCookies != null) {
	for (Cookie temp : theCookies) {
		if("loggedIn".equals(temp.getName())) {
			System.out.println("current user id via cookie = "+temp.getValue());
		}
	}
}
%>

<!--  NAVBAR -->
	<ul>
		<li><a class="active" href="http://localhost:8088/newProject01/manager/mhome.html">Home</a></li>
		<li><a href="http://localhost:8088/newProject01/ReimbMgrViewPendServlet">View Pending Request</a></li>
		<li><a href="http://localhost:8088/newProject01/ReimbMgrViewResolvedServlet">View Resolved Request</a></li>
		<li><a href="http://localhost:8088/newProject01/EmployeeControllerServlet">View Employee Info</a></li>
		<li><a href="http://localhost:8088/newProject01/mgrMyInfoServlet">View My Info</a></li>
		<li><a href="http://localhost:8088/newProject01/index.html" class="btn-danger">Log Out</a></li>
	</ul>
	
	<h1>Manager Homepage!</h1>
	<h5> [Your company notice here!]</h5>
	<p> Important announcements.</p>
</body>
</html>











