$(document).ready(function() {
  var zipcode = 0
  var lat, lng

  // sets the initial location based on user input
  function getLocation() {
    $.get("http://jarvis.cse.buffalo.edu/mine/zipcode", function(zip){
      zipcode = zip;
      if(zipcode == 0){zipcode = 14086;}
      $.get("http://maps.googleapis.com/maps/api/geocode/json?address="+zipcode, function(loca){
        lat = loca['results'][0]['geometry']['location']['lat'];
        lng = loca['results'][0]['geometry']['location']['lng'];
        $.get("http://maps.googleapis.com/maps/api/geocode/json?latlng="+ lat + "," + lng + "&sensor=true", function(body) {
          // KEY for 'address_components'
          // [1] Street name, [2] City Name, [3] Town, [4] County, [5] State, [6] Country, [7] Zip Code
          var town = body['results'][0]['address_components'][3]['long_name'];
          var state = body['results'][0]['address_components'][5]['short_name']; // long_name is full name
          var location = town+", "+state;
          document.getElementById('location').innerHTML = location;
        });
      });
    });
  }

  getLocation();
  updateLocation();
  setInterval(updateLocation, 5000);

  // looks for a change in zipcode from user input
  function updateLocation() {
    $.get("http://jarvis.cse.buffalo.edu/mine/zipcode", function(zip){
      zipcode = zip;
      if(zipcode == 0){zipcode = 14086;}
      $.get("http://maps.googleapis.com/maps/api/geocode/json?address="+zipcode, function(loca){
        lat = loca['results'][0]['geometry']['location']['lat'];
        lng = loca['results'][0]['geometry']['location']['lng'];
        $.get("http://maps.googleapis.com/maps/api/geocode/json?latlng="+ lat + "," + lng + "&sensor=true", function(body) {
          // KEY for 'address_components'
          // [1] Street name, [2] City Name, [3] Town, [4] County, [5] State, [6] Country, [7] Zip Code
          var town = body['results'][0]['address_components'][3]['long_name'];
          var state = body['results'][0]['address_components'][5]['short_name']; // long_name is full name

          var node = document.getElementById('location')
          var htmlContent = node.innerHTML
          var textContent = node.textContent
          var location = town+", "+state;
          if(textContent != location){
            // console.log("yes equal");
            document.getElementById('location').innerHTML = location;
          }
        });
      });
    });
  }

});
