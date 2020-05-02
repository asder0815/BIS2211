<%@page import="API.ApiData"%>
<%@page import="com.google.*"%>

<html>
	<body>
		<h2>BIS2211 - Team B - Tank App</h2>
		<% out.println("Datum: "+java.util.Calendar.getInstance().getTime()); %> <br/>
		<% out.println("Tankstellen in 10 km um die HS:"); %> <br/>
		<% out.println(ApiData.test()); %> <br/>
	</body>
</html>