var mymap, radFloat, popup, Popup;

function initMap(rad, list) {

  radFloat = Number(rad) * 1000;

  mymap = L.map('mapid',{
    minZoom: 0,
    maxZoom: 20,
    closePopupOnClick: false,
    scrollWheelZoom: false
  });

  L.tileLayer('https://api.mapbox.com/styles/v1/{id}/tiles/{z}/{x}/{y}?access_token={accessToken}', {
    attribution: 'Map data &copy; <a href="https://www.openstreetmap.org/">OpenStreetMap</a> contributors, <a href="https://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, Imagery © <a href="https://www.mapbox.com/">Mapbox</a>',
    id: 'mapbox/streets-v11',
    tileSize: 512,
    minZoom: 0, 
    maxZoom: 20,
    zoomOffset: -1,
    accessToken: key_leaflet
  }).addTo(mymap);

  L.marker(L.latLng(latCoord, lonCoord)).addTo(mymap);
  L.circle(L.latLng(latCoord, lonCoord), radFloat).addTo(mymap);

  showStations(list);

  mymap.setView(L.latLng(latCoord, lonCoord), 17); 
}
/**
 * Erstellt Pop-ups für jede Tankstelle im Umkreis der ausgewählten Position.
 * @param {*} list 
 */
function showStations(list) {

  for (var i = 0; i < list[0].length; i++) 
  {
    var gs = list[0][i];
    var lat = gs.lat;
    var lon = gs.lng;

    if(gs.open == true)
    {
      var favButton = ""; 
      var favList = Cookies.get("cfavourites");
      if (favList == undefined) favList = new String("");
      if (!favList.includes(gs.id)) favButton= "<button id=\"favourite" + gs.id + "\" class=\"btn btn-primary btn-sm\" onclick=\"toggleFav(this.id) \"><i data-toggle=\"tooltip\" data-placement=\"right\" title=\"zu Favoriten hinzufügen\" class=\"material-icons align-middle\"> favorite </i></button >"; 
      else favButton = "<button id=\"favourite" + gs.id + "\" class=\"btn btn-primary btn-sm\" onclick=\"toggleFav(this.id) \"><i data-toggle=\"tooltip\" data-placement=\"right\" title=\"von Favoriten entfernen\" class= \"material-icons align-middle\" > clear </i ></button >"; 
      
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
        if(gs.predictionDiesel == 0) priceTableBody += "<td><button id=\"showGraph" + gs.id + "\" class=\"btn btn-danger\" onclick=\"getDataArray(this.id, 'diesel')\"><span>" + gs.dieselPrice + "</span></button></td>"
        if(gs.predictionDiesel == 1) priceTableBody += "<td><button id=\"showGraph" + gs.id + "\" class=\"btn btn-success\" onclick=\"getDataArray(this.id, 'diesel')\"><span>" + gs.dieselPrice + "</span></button></td>"
        if(gs.predictionDiesel == 2) priceTableBody += "<td><button id=\"showGraph" + gs.id + "\" class=\"btn btn-warning\" onclick=\"getDataArray(this.id, 'diesel')\"><span>" + gs.dieselPrice + "</span></button></td>"
        if(gs.predictionDiesel == -1) priceTableBody += "<td><button id=\"showGraph" + gs.id + "\" class=\"btn btn-secondary\"><span>" + gs.dieselPrice + "</span></button></td>"
      }
      if(fuel == "e5" || fuel == "all")
      {
        if(gs.predictionE5 == 0) priceTableBody += "<td><button id=\"showGraph" + gs.id + "\" class=\"btn btn-danger\" onclick=\"getDataArray(this.id, 'e5')\"><span>" + gs.e5Price + "</span></button></td>"
        if(gs.predictionE5 == 1) priceTableBody += "<td><button id=\"showGraph" + gs.id + "\" class=\"btn btn-success\" onclick=\"getDataArray(this.id, 'e5')\"><span>" + gs.e5Price + "</span></button></td>"
        if(gs.predictionE5 == 2) priceTableBody += "<td><button id=\"showGraph" + gs.id + "\" class=\"btn btn-warning\" onclick=\"getDataArray(this.id, 'e5')\"><span>" + gs.e5Price + "</span></button></td>"
        if(gs.predictionE5 == -1) priceTableBody += "<td><button id=\"showGraph" + gs.id + "\" class=\"btn btn-secondary\"><span>" + gs.e5Price + "</span></button></td>"
      }
      if(fuel == "e10" || fuel == "all")
      {
        if(gs.predictionE10 == 0) priceTableBody += "<td><button id=\"showGraph" + gs.id + "\" class=\"btn btn-danger\" onclick=\"getDataArray(this.id, 'e10')\"><span>" + gs.e10Price + "</span></button></td>"
        if(gs.predictionE10 == 1) priceTableBody += "<td><button id=\"showGraph" + gs.id + "\" class=\"btn btn-success\" onclick=\"getDataArray(this.id, 'e10')\"><span>" + gs.e10Price + "</span></button></td>"
        if(gs.predictionE10 == 2) priceTableBody += "<td><button id=\"showGraph" + gs.id + "\" class=\"btn btn-warning\" onclick=\"getDataArray(this.id, 'e10')\"><span>" + gs.e10Price + "</span></button></td>"
        if(gs.predictionE10 == -1) priceTableBody += "<td><button id=\"showGraph" + gs.id + "\" class=\"btn btn-secondary\"><span>" + gs.e10Price + "</span></button></td>"
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
        autoPan: false
      })
        .setLatLng([lat, lon])
        .setContent(contentString)
        .addTo(mymap);
    }
  }
}
