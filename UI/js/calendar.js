$(document).ready(function() {


  function getEvent(){
    for(i = 1; i <=3; i++){
      document.getElementById('events').innerHTML += '<div class="event">\n \
        \t<div class="Gevent"><span class="Gblock" style="background-color:'+random_hex_color()+'"></span><span id="Gtime"><b>12:00 PM: </b></span><span id="Gtitle">JS test</span></div>\n \
      </div>'
    }
  }
  getEvent();

  // gets random color for the events
  function random_hex_color(){
    /* get random red, green, and blue from 0 to 255 */
    var randomred = Math.floor(Math.random() * 255);
    var randomgreen = Math.floor(Math.random() * 255);
    var randomblue = Math.floor(Math.random() * 255);

    /* convert each decimal number to hexadecimal */
    var hred = new String(randomred.toString(16));
    var hgreen = new String(randomgreen.toString(16));
    var hblue = new String(randomblue.toString(16));

    /* pad with 0 if necessary
        (e.g. make sure to output 05 instead of just 5) */
    hred = String('00'+hred).slice(-2);
    hgreen = String('00'+hgreen).slice(-2);
    hblue = String('00'+hblue).slice(-2);

    return '#'+hred+hgreen+hblue;
  }

  // each 30 min mark the app will update events
  var d, mm
  function updateEvents() {
      d = new Date()
      mm = d.toString("mm")
      var t = setTimeout(updateEvents, 500)
      if(mm == "00" || mm == "30"){
        getEvent();
      }
  };

});
