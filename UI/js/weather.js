$(document).ready(function() {
  var key = "85f6ad9b4d64ca03f1e00f6775de0259";
  // Temporary values, make these dynamic later
  var buff_lat = "42.998854";
  var buff_lon = "-78.800475";
  var current;

  function weather() {
    $.get("https://api.darksky.net/forecast/" + key + "/" + buff_lat + "," + buff_lon, function(data) {
      current = data['currently'];
      hourly = data['hourly'];
      var currIcon = current['icon'];
      currIcon = checkIcon(currIcon);

      document.getElementById('temp').innerHTML          = Math.round(current['apparentTemperature']);
      document.getElementById('description').innerHTML   = (hourly['summary']).slice(0,-1);
      document.getElementById('precipitation').innerHTML = (current['precipProbability'] * 100) + "%";
      document.getElementById('currIcon').innerHTML      = "<div class='cond " + currIcon + "'></div>";

      // Getting AM or PM
      var d = new Date();
      var ampm = d.toString("tt");

      // Icon per hour
      for(i = 1; i <= 4; i ++){
        var icon = hourly['data'][i]['icon'];
        icon = checkIcon(icon);
        document.getElementById('icon'+i).innerHTML = "<div class='cond " + icon + "'></div>";
      }

      // Each actual hour
      for(i = 1; i <= 4; i++){
        var hour = (new Date(hourly['data'][i]['time'] * 1000)).getHours();
        if (hour > 12) {
          hour = hour - 12;
          ampm = 'PM';
        } else if (hour == 12) {
          ampm = 'PM';
        } else if (hour == 0) {
          hour = 12;
          ampm = 'AM';
        }
        document.getElementById('hour'+i).innerHTML = hour +" "+ ampm;
      }

      // Temperature/Precip each hour
      for(i = 1; i <= 4; i ++){
        document.getElementById('temp'+i).innerHTML   = Math.round(hourly['data'][i]['apparentTemperature'])+"°";
        document.getElementById('precip'+i).innerHTML = Math.round(hourly['data'][i]['precipProbability'] * 100) + "%";
      }

    });
  }

  // will translate the icon from DarkSky to what we have locally
  // consider giphy for current
  function checkIcon(darkskyname) {
    var d = new Date();
    var hour = d.toString("h");
    var nightHour = 6;
    if(darkskyname == "cloudy"){ if(nightHour <= hour){ return "cloudy-night";} return darkskyname;}
    if(darkskyname == "partly-cloudy-day"){  return "partlycloudy";}
    if(darkskyname == "partly-cloudy-night"){return "cloudy-night";}
    if(darkskyname == "clear-day"){          return darkskyname;}
    if(darkskyname == "clear-night"){        return darkskyname;}
    if(darkskyname == "rain"){               if(nightHour <= hour){ return "rainy-night";}   return darkskyname;}
    if(darkskyname == "wind"){               if(nightHour <= hour){ return "windy-night";}   return darkskyname;}
    if(darkskyname == "fog") {               if(nightHour <= hour){ return "foggy-night";}   return darkskyname;}
    if(darkskyname == "thunderstorms"){      if(nightHour <= hour){ return "thunder-night";} return darkskyname;}
    if(darkskyname == "snow"){               if(nightHour <= hour){ return "snow-night";}    return darkskyname;}
    if(darkskyname == "sleet"){              if(nightHour <= hour){ return "sleet-night";}   return darkskyname;}
  }

  weather();
  setInterval(weather,90000); // 15 min

  // each hour the weather forcefully updates
  var d, c, ampm;
  updateWeather();
  function updateWeather() {
      d = new Date()
      mm = d.toString("mm")
      var t = setTimeout(updateWeather, 500)
      if(mm == "00"){
        weather(current);
        console.log("new hour update")
      }
  };

});
