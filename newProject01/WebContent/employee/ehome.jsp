<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Employee Homepage!</title>
	<link rel="stylesheet" type="text/css" href="../css/style.css">
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
   response.sendRedirect("http://localhost:8088/newProject01/index.html");
}
%>

<!--  NAVBAR -->
	<ul>
		<li><a class="active" href="http://localhost:8088/newProject01/employee/ehome.jsp">Home</a></li>
		<li><a href="http://localhost:8088/newProject01/employee/submitRequest.jsp">Submit a Request</a></li>
		<li><a href="http://localhost:8088/newProject01/ReimbEmpViewPendServlet">View my Pending Request</a></li>
		<li><a href="http://localhost:8088/newProject01/ReimbEmpViewResolvedServlet">View my Resolved Request</a></li>
		<li><a href="http://localhost:8088/newProject01/EmpMyInfoServlet">View My Info</a></li>
		<li><a href="http://localhost:8088/newProject01/LogoutServlet" class="btn-danger">Log Out</a></li>
	</ul>
	
	<h1 style="text-align: center">Employee Homepage!</h1>
	<p style="text-align: center"> <b>Important announcements:</b> </p>
	<p style="text-align: center"> Endgame tickets are not reimbursable.</p>
	<p style="text-align: center"> If it feels like management is watching you, it's because <em>we are</em>.</p>
</body>
</html>
