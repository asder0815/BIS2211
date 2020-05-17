
<link rel="stylesheet" type="text/css" href="style.css">
<script src="https://cdn.jsdelivr.net/npm/js-cookie@rc/dist/js.cookie.min.js"></script>
<meta charset="utf-8"/>

<html>
<script type="text/javascript"> 
function doSomething() {
    var myCookie = Cookies.get("cookie_user");
    
    if (myCookie != null) {
       window.location.replace("http://localhost:8080/tank-app/index.jsp");
    }
    else {
       //hier bleiben
    }}
     function submitUsername(){
	 var username = document.getElementById("usernameField").value;  
	 Cookies.set("cookie_user", username, {expires: 2});
	  window.location.replace("http://localhost:8080/tank-app/index.jsp");
	 
	}
</script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome to TankApp BIS2211</title>
</head>
<body onload="doSomething()">
<h2> Please set a username </h2>
<label>Benutzername: </label>
			<input type="text" id= "usernameField" name = "usernameField">
			<button type="submit" onclick="submitUsername()"value = "Submit" >submit</button>
			
</body>
</html>