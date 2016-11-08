$(document).ready(function() {
  startClock();
  getDate();

  function startClock() {
      var d = new Date()
      var c = d.toString("h:mm");
      var mm = d.toString("mm");
      var ampm = d.toString("tt");
      document.getElementById('time').innerHTML = c +" <sup style='font-size:20px;top:0.0em'>"+ ampm + "</sup>";
      var t = setTimeout(startClock, 500);
      if(mm == "00"){
        getDate();
      }
  };

  function getDate() {
    var mydate = new Date()
    var year = mydate.getYear()
    if (year < 1000)
        year+=1900
    var day   = mydate.getDay()
    var month = mydate.getMonth()
    var daym  = mydate.getDate()
    if (daym < 10)
        daym = "0" + daym
    var dayarray = new Array("Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday")
    var montharray = new Array("January","February","March","April","May","June","July","August","September","October","November","December")
    document.getElementById('when').innerHTML = "<p id='day'>"+dayarray[day]+"</p><p id='date'>"+montharray[month]+" "+daym+", "+year+"</p>";
  }
});
