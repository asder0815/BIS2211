<%@page import="API.ApiData"%>
<%@page import="web.WebController"%>
<%@page import="com.google.*"%>


<!-- Bootstrap -->
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" 
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

<!--  Google Material Icons-->
	<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
      rel="stylesheet">

<!--  Cookies -->
	<script src="https://cdn.jsdelivr.net/npm/js-cookie@rc/dist/js.cookie.min.js"></script>


<html>
<title> BIS2211 - Team B - Tank App </title>
	<head> 
		<link rel="stylesheet" type="text/css" href="style.css">
		<script src="https://cdn.jsdelivr.net/npm/js-cookie@rc/dist/js.cookie.min.js"></script>
	

	<h1> BIS2211 - Team B - Tank App</h1>

		<a href=index.jsp> <button type= "button"  class="btn btn-primary">Tankstelle finden</button></a>
		<a href=preise.jsp> <button type= "button"  class="btn btn-primary">Preise und Statistik</button></a>
		<a href=leaderboard.jsp> <button type= "button"  class="btn btn-primary">Leaderboard</button></a> <br>

	</head>

		<body>
	
			<% out.println("Datum: "+java.util.Calendar.getInstance().getTime()); %> <br>
		
			<form action = "index.jsp" method = "GET">
		
		
<!-- Input Felder -->	
		
			<div class="input-group mb-3">
 			 <div class="input-group-prepend">
  				</div>
 			 <input type="text" id = "usernameField" class="form-control" placeholder="Username" aria-describedby="basic-addon1">
			</div>
	
			<div class="input-group mb-3">
 			 <input type="text" id="latField" name="latField" value="<% out.print(request.getParameter("latField")); %>" class="form-control" placeholder="Längengrad" aria-label="Recipient's username" aria-describedby="basic-addon2">
  			<div class="input-group-append">
 			 </div>
				</div>
	
			<div class="input-group mb-3">
 				 <input type="text" id="lonField" name="lonField" value="<% out.print(request.getParameter("lonField")); %>" class="form-control" placeholder="Breitengrad" aria-label="Recipient's username" aria-describedby="basic-addon2">
  					<div class="input-group-append">
 								 </div>
									</div>
	
		<button type="button" onclick="showPosition()">Position bestimmen</button> 
		<button type="submit" onclick="submitUsername()"value = "Submit" >Bestätigen</button>
				</form> <br/>
		
		<table id="gsTable"> 
		<tbody> 
		<tr> <th>Name:</th> <th>Straße:</th> <th>Geöffnet:</th><th>Diesel:</th><th>E5:</th><th>E10:</th></tr>
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

	function submitUsername(){
		 var username = document.getElementById("usernameField").value;  
		 Cookies.set("cookie_user", username, {expires: 2});
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
	
</html>