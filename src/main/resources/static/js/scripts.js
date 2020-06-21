function toggleFav(clicked_id)
{
	var id = clicked_id.substr(9); 
	var favList = Cookies.get("cfavourites");
	if (favList == null) 
	{
		Cookies.set("cfavourites", id + "/", {expires: 9999});
		location.reload(); 
	}
	else 
	{
		if(!favList.includes(id))
		{
			Cookies.set("cfavourites", favList + id + "/", {expires: 9999});
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
				Cookies.set("cfavourites", newList, {expires: 9999});
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
	var url = "http://localhost:8080/leaderboardData/search/findByUser?name=" + name; 
	var requestOptions = {
		method: 'GET',
		redirect: 'follow'
	  };
	  
	fetch(url, requestOptions)
	.then(response => response.text())
	.then(result => checkUsername(result, name))
	.catch(error => console.log('error', error));
}


function checkUsername(data, name)
{
	var resonse = JSON.parse(data); 
	if(resonse._embedded.leaderboardData.length >= 1) 
	{
		alert("Username already exists. Please choose another one."); 
	}
	else
	{
		Cookies.set("username", name, {expires: 9999});
		location.reload();
	}
}
