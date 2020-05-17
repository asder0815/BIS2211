<!--  CSS -->
<link rel="stylesheet" type="text/css" href="style.css">
<!--  JS -->
<script src="scripts.js"></script>
<script src="https://cdn.jsdelivr.net/npm/js-cookie@rc/dist/js.cookie.min.js"></script>

<meta charset="utf-8"/>

<html>

<head>
	<title>Welcome to TankApp BIS2211</title>
</head>

<body onload="checkForUserNameCookie()">
	<h2> Please set a username </h2>
	<label>Benutzername: </label>
	<input type="text" id= "usernameField" name = "usernameField">
	<button type="submit" onclick="submitUsername()"value = "Submit" >submit</button>	
</body>

</html>