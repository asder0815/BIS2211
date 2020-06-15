var mymap, radFloat, popup, Popup;

function initMap(rad, list) {

  radFloat = Number(rad) * 1000;

  mymap = L.map('mapid').locate({ setView: true, maxZoom: 18 });

  L.tileLayer('https://api.mapbox.com/styles/v1/{id}/tiles/{z}/{x}/{y}?access_token={accessToken}', {
    attribution: 'Map data &copy; <a href="https://www.openstreetmap.org/">OpenStreetMap</a> contributors, <a href="https://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, Imagery © <a href="https://www.mapbox.com/">Mapbox</a>',
    id: 'mapbox/streets-v11',
    tileSize: 512,
    zoomOffset: -1,
    accessToken: 'pk.eyJ1Ijoia2FyYWRlbmIiLCJhIjoiY2tiZjNyMWRuMDVrcDJ5bWxqd2xkMmlrayJ9.9dfIhA52TTDorCgtuLrAsg'
  }).addTo(mymap);

  function onLocationFound(e) {


    L.marker(e.latlng).addTo(mymap)
      .bindPopup("Dein").openPopup();

    L.circle(e.latlng, radFloat).addTo(mymap);
  }
  mymap.on('locationfound', onLocationFound);

  showStations(list);
}


function showStations(list) {

  for (var i = 0; i < list[0].length; i++) {
    var gs = list[0][i];

    var lat = gs.lat;
    var lon = gs.lon;

    console.log(lat, lon);

    L.circle([lat, lon], radFloat).addTo(mymap);

    var popup = L.popup()
      .setLatLng([lat, lon])
      .setContent('<p>Hello world!<br />This is a nice popup.</p>')
      .addTo(mymap);
  }

  list[0].forEach(element => {

  })

}





function updateMap() {
  // Try HTML5 geolocation.
  if (navigator.geolocation) {
    navigator.geolocation.getCurrentPosition(function (position) {
      var pos = {
        lat: position.coords.latitude,
        lng: position.coords.longitude
      };
      var marker = new google.maps.Marker({ position: pos, map: map });

      showStations();

      map.setCenter(pos);
    });
  }
}


