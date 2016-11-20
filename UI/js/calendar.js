$(document).ready(function() {

  $.get("http://jarvis.cse.buffalo.edu/mine/calanderinfo", function (body) {
    body = body.split('\n')

    var events = []
    var times = []

    events[0] = body[0].split('(')[0]
    events[0] = events[0].replace('Lec', 'Lecture')
    times[0] = body[0].substring(body[0].length - 19, body[0].length - 14)
    times[0] = times[0].replace('T', '')
    times[0] = times[0].replace('.', '')

    events[1] = body[1].split('(')[0]
    events[1] = events[1].replace('Lec', 'Lecture')
    times[1] = body[1].substring(body[1].length - 19, body[1].length - 14)
    times[1] = times[1].replace('T', '')
    times[1] = times[1].replace('.', '')

    events[2] = body[2].split('(')[0]
    events[2] = events[2].replace('Lec', 'Lecture')
    times[2] = body[2].substring(body[2].length - 19, body[2].length - 14)
    times[2] = times[2].replace('T', '')
    times[2] = times[2].replace('.', '')

    function getEvent(){
      for(i = 1; i <=3; i++){
        document.getElementById('events').innerHTML += '<div class="event">\n \
          \t<div class="Gevent"><span class="Gblock" style="background-color:'+random_hex_color()+'"></span><span id="Gtime"><b>' + times[i-1] + ' </b></span><span id="Gtitle">' + events[i-1] + '</span></div>\n \
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

});
