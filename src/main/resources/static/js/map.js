var mymap, radFloat, popup, Popup;

function initMap(rad, list) {

  radFloat = Number(rad) * 1000;

  mymap = L.map('mapid',{
    minZoom: 0,
    maxZoom: 20,
    closePopupOnClick: false,
    scrollWheelZoom: false
  }).locate({setView: true, maxZoom: 20});

  L.tileLayer('https://api.mapbox.com/styles/v1/{id}/tiles/{z}/{x}/{y}?access_token={accessToken}', {
    attribution: 'Map data &copy; <a href="https://www.openstreetmap.org/">OpenStreetMap</a> contributors, <a href="https://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, Imagery © <a href="https://www.mapbox.com/">Mapbox</a>',
    id: 'mapbox/streets-v11',
    tileSize: 512,
    minZoom: 0, 
    maxZoom: 20,
    zoomOffset: -1,
    accessToken: key_leaflet
  }).addTo(mymap);

  function onLocationFound(e) {
    L.marker(e.latlng).addTo(mymap);
    L.circle(e.latlng, radFloat).addTo(mymap);
  }
  mymap.on('locationfound', onLocationFound);
  console.log(mymap.getZoom()); 
  showStations(list);
}

function showStations(list) {

  for (var i = 0; i < list[0].length; i++) 
  {
    var gs = list[0][i];
    var lat = gs.lat;
    var lon = gs.lng;

    var favButton = ""; 
    var favList = Cookies.get("cfavourites");
    if (favList == undefined) favList = new String("");
    if (!favList.includes(gs.id)) favButton= "<button id=\"favourite" + gs.id + "\" class=\"btn btn-primary btn-sm\" onclick=\"toggleFav(this.id) \"><i data-toggle=\"tooltip\" data-placement=\"right\" title=\"zu Favoriten hinzufügen\" class=\"material-icons align-middle\"> favorite </i></button >"; 
    else favButton = "<button id=\"\'favourite" + gs.id + "\" class=\"btn btn-primary btn-sm\" onclick=\"toggleFav(this.id) \"><i data-toggle=\"tooltip\" data-placement=\"right\" title=\"von Favoriten entfernen\" class= \"material-icons align-middle\" > clear </i ></button >"; 
    
    var contentString = "<div class='row'> <div class='col-9'><h6><b>" + gs.name + "</b></h6></div> <div class='col-3'>" + favButton + "</div> </div>"
      + "<p style='font-size: 14px'>"+ gs.street + " " + gs.houseNumber + " <br>" + gs.postCode + " "+ gs.place +"</p>"
      + "<p style='font-size: 14px'> <b> Entfernung: </b>"+ " " + gs.dist + " km </p>";
    
    var fuelType = fuel;
    
    var priceTableHeader = "<tr>"; 
    if(fuel == "diesel" || fuel == "all") priceTableHeader += "<th>Diesel</th>"; 
    if(fuel == "e5" || fuel == "all") priceTableHeader += "<th>E5</th>"; 
    if(fuel == "e10" || fuel == "all") priceTableHeader += "<th>E10</th>"; 
    priceTableHeader += "</tr>";

    var priceTableBody = "<tr>"; 
    if(fuel == "diesel" || fuel == "all")
    {
      priceTableBody += "<td>" + gs.diesel + "€" + "</td>"; 
    }
    if(fuel == "e5" || fuel == "all")
    {
      priceTableBody += "<td>" + gs.e5 + "€" + "</td>"; 
    }
    if(fuel == "e10" || fuel == "all")
    {
      priceTableBody += "<td>" + gs.e10 + "€" + "</td>"; 
    }
    priceTableBody += "</tr>"; 

    var tableString = "<table style='width: 100%; font-size: 19px;'>" + priceTableHeader + priceTableBody + "</table>";

    contentString += tableString; 

    var popup = L.popup({
      className: 'custom',
      minWidth: 250, 
      maxWidth: 250, 
      maxHeight: 350,
      closeOnClick: false,
      autoClose: false,
      closeButton: false,
    })
      .setLatLng([lat, lon])
      .setContent(contentString)
      .addTo(mymap);
  }
}
