$(document).ready(function() {

  function getQuote() {
    $.get("http://quotes.rest/qod.json?category=funny", function(quote) {
      document.getElementById('quote').innerHTML = '<i>" ' + quote['contents']['quotes'][0]['quote'] +' "</i>'
      document.getElementById('author').innerHTML = "<b>- " + quote['contents']['quotes'][0]['author']+'</b>'

    });
  }

  getQuote();
  // setInterval(getQuote, 900000);
  var d, c, ampm;
  updateQuote();
  function updateQuote() {
      d = new Date()
      mm = d.toString("h:mm:ss")
      var t = setTimeout(updateQuote, 500)
      if(mm == "12:00:00"){
        getQuote();
      }
  };
});
