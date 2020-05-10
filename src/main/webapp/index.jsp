<%@page import="API.ApiData"%>
<%@page import="web.WebController"%>
<%@page import="com.google.*"%>

<html>
<link rel="stylesheet" type="text/css" href="style.css">

	<script type="text/javascript">
	function showPosition(){
	    if(navigator.geolocation){
	        navigator.geolocation.getCurrentPosition(showMap, showError);
	    } else{
	        alert("Sorry, your browser does not support HTML5 geolocation.");
	    }
	}
	  
	// Define callback function for successful attempt
	function showMap(position)
	{
	    // Get location data
	    lat = position.coords.latitude;
	    lon = position.coords.longitude;

	    console.log(lat);
	    console.log(lon); 
	    document.getElementById("latField").value = lat;
	    document.getElementById("lonField").value = lon;  
	}
	  
	// Define callback function for failed attempt
	function showError(error)
	{
        switch(error.code) 
        {
	        case error.PERMISSION_DENIED:
	        	alert("User denied request for geolocation.");
		        break;
	        case error.POSITION_UNAVAILABLE:
	        	alert("Location data not available.");
		        break;
	        case error.TIMEOUT:
	        	alert("Request for location timed out.");
		        break;
	        case error.UNKNOWN_ERROR:
	        	alert("An error occured.");
		        break;
		}
	}
	</script>

	<head>
		<style>
			/* Chrome, Safari, Edge, Opera */
			input::-webkit-outer-spin-button,
			input::-webkit-inner-spin-button {
			  -webkit-appearance: none;
			  margin: 0;
			}
			
			/* Firefox */
			input[type=number] {
			  -moz-appearance: textfield;
			}
		</style>
	</head>

	<body>
		<h2>BIS2211 - Team B - Tank App</h2>
		<% out.println("Datum: "+java.util.Calendar.getInstance().getTime()); %> <br/>
		
		<form action = "index.jsp" method = "GET">
		
			<input type="number" step= "any" id="latField" name="latField" value="<% out.print(request.getParameter("latField")); %>">
			<input type="number" step = "any" id="lonField" name="lonField" value="<% out.print(request.getParameter("lonField")); %>">
			<button type="button" onclick="showPosition();">Meine Position bestimmen</button> 
		<!-- 	<input type = "submit" value = "Submit" />  -->
			<button type="submit" value = "Submit" >submit</button>
			
		</form>
		<br/>
		
		<table id="gsTable"> 
		<tbody> 
		<tr> <th>Name:</th> <th>Stra�e:</th> <th>Ge�ffnet:</th> </tr>
		<% 
			float lat = 0f;
	    	float lon = 0f;
	    	
	    	lat = WebController.convertReqestParameter(request.getParameter("latField"));
	    	lon = WebController.convertReqestParameter(request.getParameter("lonField"));
	    	
			if(lat == 0 && lon == 0)
			{
				lat = 48.878708f;
				lon = 8.717344f;
			}
			
			out.println(WebController.printGSTable(lat, lon));
		%> 
		</tbody> 
		</table> <br/>
		
	</body>
</html>