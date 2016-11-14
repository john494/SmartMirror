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
      var winddegree = current['windBearing']
      var currIcon = current['icon'];
      currIcon = checkIcon(currIcon);

      document.getElementById('temp').innerHTML          = Math.round(current['apparentTemperature']);
      document.getElementById('description').innerHTML   = (hourly['summary']).slice(0,-1);
      document.getElementById('precipitation').innerHTML = (current['precipProbability'] * 100) + "%";
      document.getElementById('currIcon').innerHTML      = "<div class='cond " + currIcon + "'></div>";
      if(winddegree == 0){winddegree = "N"}  if(0 < winddegree < 90){winddegree = "NE"}
      if(winddegree == 90){winddegree = "E"} if(90 < winddegree < 180){winddegree = "SE"}
      if(winddegree == 180){winddegree = "S"}if(180 < winddegree < 270){winddegree = "SW"}
      if(winddegree == 270){winddegree = "W"}if(270 < winddegree < 360){winddegree = "NW"}
      if((Math.round(current['windSpeed'])) == 0){windegree == ""}
      
      document.getElementById('windspeed').innerHTML = winddegree +" "+Math.round(current['windSpeed'])+" mph";

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
    var sunset = 17;
    var sunrise = 04
    var d = new Date();
    var hour = d.toString("hh");

    if(darkskyname == "partly-cloudy-day"){  return "partlycloudy";}
    if(darkskyname == "partly-cloudy-night"){return "cloudy-night";}
    if(darkskyname == "clear-day"){          return darkskyname;}
    if(darkskyname == "clear-night"){        return darkskyname;}
    if(darkskyname == "cloudy"){             if(sunset <= hour && hour <= sunrise){ return "cloudy-night";}  return darkskyname;}
    if(darkskyname == "rain"){               if(sunset <= hour && hour <= sunrise){ return "rainy-night";}   return darkskyname;}
    if(darkskyname == "wind"){               if(sunset <= hour && hour <= sunrise){ return "windy-night";}   return darkskyname;}
    if(darkskyname == "fog") {               if(sunset <= hour && hour <= sunrise){ return "foggy-night";}   return darkskyname;}
    if(darkskyname == "thunderstorms"){      if(sunset <= hour && hour <= sunrise){ return "thunder-night";} return darkskyname;}
    if(darkskyname == "snow"){               if(sunset <= hour && hour <= sunrise){ return "snow-night";}    return darkskyname;}
    if(darkskyname == "sleet"){              if(sunset <= hour && hour <= sunrise){ return "sleet-night";}   return darkskyname;}
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
      }
  };

});
