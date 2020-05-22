<%@page import="API.ApiData"%>
<%@page import="web.WebController"%>
<%@page import="com.google.*"%>
<%@page import="java.util.*"%>

<html>

<title>BIS2211 - Team B - Tank App</title>

<head>
<!-- jquery -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/js-cookie@rc/dist/js.cookie.min.js"></script>
<!-- Bootstrap -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
	integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
	integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
	integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI"
	crossorigin="anonymous"></script>
<!--  Google Material Icons-->
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<!--  Cookies -->
<script
	src="https://cdn.jsdelivr.net/npm/js-cookie@rc/dist/js.cookie.min.js"></script>
<!--  CSS -->
<link rel="stylesheet" type="text/css" href="style.css"></link>
<!--  JS -->
<script src="scripts.js"></script>
</head>

<body>


	<header>
		<nav class="navbar fixed-top navbar-expand-sm navbar-dark bg-dark">
			<a href="index.jsp" class="navbar-brand">Tankstelle finden</a>
			<button class="navbar-toggler" data-toggle="collapse"
				data-target="#navbarMenu">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarMenu">
				<ul class="navbar-nav ">
					<li class="nav-item"><a href="preise.jsp" class="nav-link">Statistik</a>
					</li>
					<li class="nav-item"><a href="leaderboard.jsp"
						class="nav-link">Leaderboard</a></li>
					<li class="nav-item"><a href="leaderboard.jsp"
						class="nav-link">Test</a></li>
				</ul>
			</div>
		</nav>

	</header>
	<section class="banner"></section>

	<form action="index.jsp" method="GET">
		<input type="text" id="latField" name="latField" class="form-control"
			placeholder="Längengrad"> <input type="text" id="lonField"
			name="lonField" class="form-control" placeholder="Breitengrad">
		<div class="container">
			<div class="form-group">
				<label for="radField">Im Umkreis von</label><br> <select
					name="radField" id="radField" class="selectpicker"
					onchange="radSelect()">
					<option value="5">5km</option>
					<option value="10">10km</option>
					<option value="15">15km</option>
					<option value="20">20km</option>
				</select>
			</div>
		</div>
		<br>
		<button class="btn btn-warning" type="button" onclick="showPosition()">Position
			bestimmen</button>
		<input class="btn btn-warning" type="submit" value="Bestätigen"></input>

	</form>

	<br>
	<%
		Cookie[] ourCookies = request.getCookies();
		Cookie favList = null;
		String[] favorites = null;
		if (ourCookies == null) {
			System.out.println("Keine Cookies vorhanden");

		} else {
			for (Cookie c : ourCookies) {
				if (c.getName().equals("cfavourites")) {
					favList = c;
					favorites = favList.getValue().split("/");
				System.out.println("Wir sind vorm Break");
					break;
				}
			}
		}
		if (favorites != null) {
			System.out.println("Sind im If");
			out.println("<table id=\"favTable\">");
			out.println("<tbody>");
			out.println("<tr>");
			out.println("<th>Name:</th>");
			out.println("<th>Diesel:</th>");
			out.println("<th>E5:</th>");
			out.println("<th>E10:</th>");
			out.println("<th>Straße:</th>");
			out.println("<th>Geöffnet:</th>");
			out.println("</tr>");
			out.println(WebController.printGSTable(favorites));
			out.println("</tbody>");
			out.println("</table>");
		}
	%>
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
				float rad = 5f;

				lat = WebController.convertReqestParameter(request.getParameter("latField"));
				lon = WebController.convertReqestParameter(request.getParameter("lonField"));
				rad = WebController.convertReqestParameter(request.getParameter("radField"));

				if (lat == 0 && lon == 0) {
					lat = 48.878708f;
					lon = 8.717344f;
				}
				if (rad == 0f) {
					rad = 5f;
				}

				out.println(WebController.printGSTable(lat, lon, rad));
			%>
		</tbody>
	</table>

	<br />

	<script>
		
	</script>

</body>



</html>