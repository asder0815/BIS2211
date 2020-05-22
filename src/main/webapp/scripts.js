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

function checkForUserNameCookie() 
{
	var myCookie = Cookies.get("username");
	if (myCookie != null) 
	{
		window.location.href = "index.jsp";
	}
	else 
	{
	       //hier bleiben
	}
}

function submitUsername()
{
	var username = document.getElementById("usernameField").value;  
	Cookies.set("username", username, {expires: 2});
	window.location.href = "index.jsp";
}

function sortTable(n, id) 
{
	var table, rows, switching, i, x, y, shouldSwitch, dir, switchcount = 0;
	table = document.getElementById(id);
	switching = true;
	dir = "asc";
	while (switching) 
	{
		switching = false;
		rows = table.rows;
		for (i = 1; i < (rows.length - 1); i++) 
		{
			shouldSwitch = false;
			x = rows[i].getElementsByTagName("TD")[n];
			y = rows[i + 1].getElementsByTagName("TD")[n];
			if (dir == "asc") 
			{
				if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) 
				{
					shouldSwitch = true;
					break;
				}
			} 
			else if (dir == "desc") 
			{
				if (x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()) 
				{
					shouldSwitch = true;
					break;
				}
			}
		}
		if (shouldSwitch) 
		{
			rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
			switching = true;
			switchcount ++;
		} 
		else 
		{
			if (switchcount == 0 && dir == "asc") 
			{
				dir = "desc";
				switching = true;
			}
		}
	}
}