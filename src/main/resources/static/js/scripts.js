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

function submitUsername(name) 
{
	Cookies.set("username", name, {expires: 2});
	location.reload(); 
}

function addLD(name, gsID, amount, type, price)
{
	if(price == false) alert("Bitte eine gültige Treibstoffart auswählen!");
	else
	{
		var myHeaders = new Headers();
		myHeaders.append("Content-Type", "application/json");

		var raw = JSON.stringify({"user":name, "gsID": gsID, "amount":amount,"type":type,"pricePayed":price,"priceDeviation":-69,"timestamp":Date.now()});

		var requestOptions = {
		method: 'POST',
		headers: myHeaders,
		body: raw,
		redirect: 'follow'
		};

		fetch("http://localhost:8080/leaderboardData", requestOptions)
		.then(response => response.text())
		.then(result => console.log(result))
		.catch(error => console.log('error', error));	

		setTimeout(function(){
			location.reload();
		}, 500); 
	}
}

function createTestData()
{
	var yesterday = new Date();
	yesterday.setDate(yesterday.getDate() - 1);

	var lizer = {name:"lizer", gsID:"b4ed695f-2cfc-4688-8ecf-268b10cdb93e", type:"Diesel", price:"1.420", amount:"69", timestamp:yesterday};
	var dog = {name:"dog", gsID:"b4ed695f-2cfc-4688-8ecf-268b10cdb93e", type:"E10", price:"1.253", amount:"75", timestamp:Date.now()};
	var cat = {name:"cat", gsID:"b4ed695f-2cfc-4688-8ecf-268b10cdb93e", type:"E5", price:"1.234", amount:"26", timestamp:yesterday};
	var bird = {name:"bird", gsID:"b4ed695f-2cfc-4688-8ecf-268b10cdb93e", type:"Diesel", price:"1.567", amount:"42", timestamp:Date.now()};
	var snek = {name:"snek", gsID:"b4ed695f-2cfc-4688-8ecf-268b10cdb93e", type:"E5", price:"1.238", amount:"46", timestamp:yesterday};
	var animals = [lizer, dog, cat, bird, snek]; 

	animals.forEach(element => sendHTTPReq(element.name, element.gsID, element.amount, element.type, element.price, element.timestamp)); 
}

function sendHTTPReq(name, gsID, amount, type, price, timestamp)
{
	var myHeaders = new Headers();
	myHeaders.append("Content-Type", "application/json");

	var raw = JSON.stringify({"user":name,"gsID":gsID,"amount":amount,"type":type,"pricePayed":price,"priceDeviation":-69,"timestamp":timestamp});

	var requestOptions = {
	method: 'POST',
	headers: myHeaders,
	body: raw,
	redirect: 'follow'
	};

	fetch("http://localhost:8080/leaderboardData", requestOptions)
	.then(response => response.text())
	.then(result => console.log(result))
	.catch(error => console.log('error', error));
}