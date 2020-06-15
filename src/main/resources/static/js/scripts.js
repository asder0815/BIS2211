function toggleFav(clicked_id)
{
	var id = clicked_id.substr(9); 
	var favList = Cookies.get("cfavourites");
	if (favList == null) 
	{
<<<<<<< Updated upstream
		Cookies.set("cfavourites", id + "/", {expires: -1});
=======
		Cookies.set("cfavourites", id + "/", {expires: 9999});
>>>>>>> Stashed changes
		location.reload(); 
	}
	else 
	{
		if(!favList.includes(id))
		{
<<<<<<< Updated upstream
			Cookies.set("cfavourites", favList + id + "/", {expires: -1});
=======
			Cookies.set("cfavourites", favList + id + "/", {expires: 9999});
>>>>>>> Stashed changes
		}
		else
		{
			var newList = favList.replace(id + "/", ""); 
			if(newList == "")
			{
				Cookies.remove("cfavourites"); 
			}
			else
			{
<<<<<<< Updated upstream
				Cookies.set("cfavourites", newList, {expires: -1});
=======
				Cookies.set("cfavourites", newList, {expires: 9999});
>>>>>>> Stashed changes
			}			
		}
		location.reload();
	}
}

function showPosition()
{
	if(navigator.geolocation)
	{
		navigator.geolocation.getCurrentPosition(showMap, showError);
	} 
	else
	{
		alert("Sorry, your browser does not support HTML5 geolocation.");
	}
}
	  
function showMap(position)
{
    lat = position.coords.latitude;
    lon = position.coords.longitude;
    document.getElementById("latField").value = lat;
    document.getElementById("lonField").value = lon;  
}
	  
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

function submitUsername(name) 
{
<<<<<<< Updated upstream
	Cookies.set("username", name, {expires: -1});
=======
	Cookies.set("username", name, {expires: 9999});
>>>>>>> Stashed changes
	location.reload(); 
}
