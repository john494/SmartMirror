$(document).ready(function() {
  var buff_lat = "42.998854";
  var buff_lon = "-78.800475";

  function getLocation() {
    $.get("http://maps.googleapis.com/maps/api/geocode/json?latlng="+ buff_lat + "," + buff_lon + "&sensor=true", function(body) {
      // KEY for 'address_components'
      // [1] Street name, [2] City Name, [3] Town, [4] County, [5] State, [6] Country, [7] Zip Code
      var town = body['results'][0]['address_components'][3]['long_name'];
      var state = body['results'][0]['address_components'][5]['short_name']; // long_name is full name
      document.getElementById('location').innerHTML = town +", " + state;
    });
  }

  getLocation();

});
