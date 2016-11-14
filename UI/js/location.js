$(document).ready(function() {
  var zipcode = 14228
  var lat, lng
  // now make a request to see what the user inputted in the app
  function getLocation() {
    $.get("http://maps.googleapis.com/maps/api/geocode/json?address="+zipcode, function(loca){
      lat = loca['results'][0]['geometry']['location']['lat'];
      lng = loca['results'][0]['geometry']['location']['lng'];
      $.get("http://maps.googleapis.com/maps/api/geocode/json?latlng="+ lat + "," + lng + "&sensor=true", function(body) {
        // KEY for 'address_components'
        // [1] Street name, [2] City Name, [3] Town, [4] County, [5] State, [6] Country, [7] Zip Code
        var town = body['results'][0]['address_components'][3]['long_name'];
        var state = body['results'][0]['address_components'][5]['short_name']; // long_name is full name
        document.getElementById('location').innerHTML = town +", " + state;
      });
    });
  }

  getLocation();

});
