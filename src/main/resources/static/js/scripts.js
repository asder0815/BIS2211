function toggleFav(clicked_id)
{
	var id = clicked_id.substr(9); 
	var favList = Cookies.get("cfavourites");
	if (favList == null) 
	{
		Cookies.set("cfavourites", id + "/", {expires: 2});
		location.reload(); 
	}
	else 
	{
		if(!favList.includes(id))
		{
			Cookies.set("cfavourites", favList + id + "/", {expires: 2});
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
				Cookies.set("cfavourites", newList, {expires: 2});
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