<%@page import="API.ApiData"%>
<%@page import="web.WebController"%>
<%@page import="com.google.*"%>

<html>
<title> Leaderboard </title>
	<head>
	<!-- Bootstrap -->
	<!-- Bootstrap -->
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" 
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
	<!--  Google Material Icons-->
	<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
      rel="stylesheet">
	<!--  Cookies -->
	<script src="https://cdn.jsdelivr.net/npm/js-cookie@rc/dist/js.cookie.min.js"></script>
	
	<link rel="stylesheet" type="text/css" href="style.css"></link>
	
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
</body>




</html>