<%@page import="API.ApiData"%>
<%@page import="com.google.*"%>

<html>
	<body>
		<h2>BIS2211 - Team B - Tank App</h2>
		<% out.println("Datum: "+java.util.Calendar.getInstance().getTime()); %> 
		<% out.println("Tankstellen in 10 km um die HS:"); %>
		<% 
		out.println("Request URL: " + ApiData.buildRequestString("48.878550", "8.717204", 5));
		out.println(ApiData.test()); 
		%>
	</body>
</html>