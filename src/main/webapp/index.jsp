<%@page import="API.ApiData"%>
<%@page import="web.WebController"%>
<%@page import="com.google.*"%>

<html>

	<title> BIS2211 - Team B - Tank App </title>
	
	<head>
		<script src="https://cdn.jsdelivr.net/npm/js-cookie@rc/dist/js.cookie.min.js"></script>
		<!-- Bootstrap -->
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" 
		integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
		<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
		<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
		<!--  Google Material Icons-->
		<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
		<!--  Cookies -->
		<script src="https://cdn.jsdelivr.net/npm/js-cookie@rc/dist/js.cookie.min.js"></script>
		<!--  CSS -->
		<link rel="stylesheet" type="text/css" href="style.css"></link>
		<!--  JS -->
		<script src="scripts.js"></script>
	</head> 
		
	<body>
		<header>
		<nav class="navbar fixed-top navbar-expand-sm navbar-dark bg-dark">
			<a href="index.jsp" class= "navbar-brand">Tankstelle finden</a> 
				<button class="navbar-toggler" data-toggle="collapse" data-target="#navbarMenu">
					<span class="navbar-toggler-icon"></span></button>
			<div class="collapse navbar-collapse" id="navbarMenu">
			<ul class="navbar-nav ">
				<li class= "nav-item">
					<a href="preise.jsp" class="nav-link">Statistik</a>
				</li>
				<li class= "nav-item">
					<a href="preise.jsp" class="nav-link">Leaderboard</a>
				</li>
				<li class= "nav-item">
					<a href="preise.jsp" class="nav-link">Test</a>
							</li>
						</ul>
					</div>
				</nav>
			
		</header>
	<section class="banner"></section>
	
		<form action = "index.jsp" method = "GET">		
 			<input type="text" id="latField" name="latField" value="<% out.print(request.getParameter("latField")); %>" class="form-control" placeholder="Längengrad" aria-label="Recipient's username" aria-describedby="basic-addon2">
 			<input type="text" id="lonField" name="lonField" value="<% out.print(request.getParameter("lonField")); %>" class="form-control" placeholder="Breitengrad" aria-label="Recipient's username" aria-describedby="basic-addon2">			
		<br>	
			<button class="btn btn-warning" type="button" onclick="showPosition()">Position bestimmen</button> 
			<input class="btn btn-warning" type="submit" value = "Bestätigen" ></input>				
		</form> 
		
		<br>
		
		<table id="gsTable"> 
			<tbody> 
			<tr> 
				<th onclick="sortTable(0)">Name:</th> 
				<th onclick="sortTable(1)">Diesel:</th> 
				<th onclick="sortTable(2)">E5:</th> 
				<th onclick="sortTable(3)">E10:</th> 
				<th onclick="sortTable(4)">Straße:</th> 
				<th onclick="sortTable(5)">Geöffnet:</th> 
			</tr>
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
		</table> 
		
		<br/>
		
	</body>
	
</html>