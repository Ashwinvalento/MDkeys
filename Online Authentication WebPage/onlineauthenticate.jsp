<%-- 
		Import the library [MDkeys] and also keep the library in the WEB-INF/lib folder for tomcat server --%>
<%@page import="java.io.PrintWriter,Server.ServerAuthentication"%>
<html>
<head>
<title>Online Application Authentication Demo</title>
</head>
<body>

	<%!String License=""; %>
   
   <%-- 
		Get The Token from client Application --%>
   <%    String token = request.getParameter("token");   %>
   
	<%-- 
		Create a object of server from the MDkeys library, Make sure you import the library
		also send the same passPhrase as in the client Application --%>
	<% ServerAuthentication sa = new ServerAuthentication("YourPassPhraseHere"); %>
	
	<%--
		Generate the license key for the recieved token
		Before this step payment options will be done	--%>
	<% License = sa.generateLicense(token); %>
	
	<%--
		Send the license key back to the client by setting the header   
		**** DO NOT CHANGE THE SETHEADER METHOD		--%>
	<% response.setHeader("License", License); %>
	
	
<center>
<h1>Online Application Authentication Demo</h1>
<br/>
<h2> USER INFORMATION </h2>
<ul>
<li><p><b>Firse Name :</b>
	<%= request.getParameter("firstname")%>
</p></li>
<li><p><b>Last Name:</b>
	<%= request.getParameter("lastname")%>
</p></li>
<br/>
<br/>
<h2> LICENSE INFORMATION </h2>
<li><p><b>Token Recieved from Client Application:</b>
	<%= request.getParameter("token")%>
</p></li>
<li><p><b>License Key Generated :</b>
	<%=License %> </li>
 <li><p>Please Keep the license key safe</p></li>
</ul>
</body>
</html>