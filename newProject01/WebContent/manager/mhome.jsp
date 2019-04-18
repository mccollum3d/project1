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

<%
response.setHeader("Cache-Control","no-cache"); //Forces caches to obtain a new copy of the page from the origin server
response.setHeader("Cache-Control","no-store"); //Directs caches not to store the page under any circumstance
response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
response.setHeader("Pragma","no-cache"); //HTTP 1.0 backward compatibility
String userName = (String) session.getAttribute("User");
if (null == userName) {
   request.setAttribute("Error", "Session has ended.  Please login.");
   response.sendRedirect("http://localhost:8088/newProject01/index.html");
}
%>

<!--  NAVBAR -->
	<ul>
		<li><a class="active" href="http://localhost:8088/newProject01/manager/mhome.jsp">Home</a></li>
		<li><a href="http://localhost:8088/newProject01/ReimbMgrViewPendServlet">View Pending Request</a></li>
		<li><a href="http://localhost:8088/newProject01/ReimbMgrViewResolvedServlet">View Resolved Request</a></li>
		<li><a href="http://localhost:8088/newProject01/EmployeeControllerServlet">View Employee Info</a></li>
		<li><a href="http://localhost:8088/newProject01/mgrMyInfoServlet">View My Info</a></li>
		<li><a href="http://localhost:8088/newProject01/LogoutServlet" class="btn-danger">Log Out</a></li>
	</ul>
	
	<h1>Manager Homepage!</h1>
	<h5> [Your company notice here!]</h5>
	<p> Important announcements.</p>
</body>
</html>











