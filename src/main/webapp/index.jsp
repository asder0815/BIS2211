<%@page import="API.ApiData"%>
<%@page import="web.WebController"%>
<%@page import="com.google.*"%>

<html>
	<body>
		<h2>BIS2211 - Team B - Tank App</h2>
		<% out.println("Datum: "+java.util.Calendar.getInstance().getTime()); %> <br/>
		<% out.println("Tankstellen in 10 km um die HS:"); %> <br/>
		<% 
			float lat = 48.878708f;
        	float lon = 8.717344f;
			out.println(WebController.printGSTable(lat, lon));
		%> <br/>
	</body>
</html>