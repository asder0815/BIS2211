var map, infoWindow, popup, Popup;

function initMap() {
        map = new google.maps.Map(document.getElementById('map'), {
          center: {lat: 48.869, lng: 8.636},
          zoom: 13
        });
        infoWindow = new google.maps.InfoWindow;

        // Try HTML5 geolocation.
        if (navigator.geolocation) {
          navigator.geolocation.getCurrentPosition(function(position) {
            var pos = {
              lat: position.coords.latitude,
              lng: position.coords.longitude
            };
            var marker = new google.maps.Marker({position: pos, map: map});

            showStations();

            map.setCenter(pos);
          }, function() {
            handleLocationError(true, infoWindow, map.getCenter());
          });
        } else {
          // Browser doesn't support Geolocation
          handleLocationError(false, infoWindow, map.getCenter());
        }
      }

function updateMap() {
        map = new google.maps.Map(document.getElementById('map'), {
          center: {lat: 48.869, lng: 8.636},
          zoom: 13
        });
        infoWindow = new google.maps.InfoWindow;

        // Try HTML5 geolocation.
        if (navigator.geolocation) {
          navigator.geolocation.getCurrentPosition(function(position) {
            var pos = {
              lat: position.coords.latitude,
              lng: position.coords.longitude
            };
            var marker = new google.maps.Marker({position: pos, map: map});

            showStations();

            map.setCenter(pos);
          }, function() {
            handleLocationError(true, infoWindow, map.getCenter());
          });
        } else {
          // Browser doesn't support Geolocation
          handleLocationError(false, infoWindow, map.getCenter());
        }
      }

      function handleLocationError(browserHasGeolocation, infoWindow, pos) {
        infoWindow.setPosition(pos);
        infoWindow.setContent(browserHasGeolocation ?
                              'Error: The Geolocation service failed.' :
                              'Error: Your browser doesn\'t support geolocation.');
        infoWindow.open(map);
      }


function showStations(){
    var pos = {
        lat: 48.875,
        lng: 8.64
      };
    var contentString = '<div id="content">'+
    '<div id="siteNotice">'+
    '</div>'+
    '<h5 id="firstHeading" class="firstHeading">Tankstelle</h5>'+
    '<div id="bodyContent">'+
    '<p><b>Tankstelle Testergebnis</b>'+
    '<p> <a href="main">Details</a> </p>'+
    '</div>'+
    '</div>';
    infoWindow.setPosition(pos);
    infoWindow.setContent(contentString);
    infoWindow.open(map);
}

