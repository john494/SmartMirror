$(document).ready(function() {
  function stocks(){
    getStock("AAPL", 1);
    getStock("FB", 2);
    getStock("NKE", 3);
    getStock("TSLA", 4);

    function getStock(symbol, i) {
      $.get("http://finance.google.com/finance/info?client=iq&q=" + symbol, function(body) {
        body = body.slice(4);
        body = JSON.parse(body);

        // Current share price
        var companyname = body[0]['t'];
        var exchange = body[0]['e'];
        var price = body[0]['l'];
        var pointchange = body[0]['c'];
        var percentchange = body[0]['cp'];

        document.getElementById('stockname'+i).innerHTML = companyname;
        document.getElementById('exchange'+i).innerHTML = "("+exchange+")";
        document.getElementById('curprice'+i).innerHTML  = price;

        // choose appropriate color
        if (pointchange < 0) {
          document.getElementById('shift'+i).innerHTML = "<span style='color:red;'>▼ "+ pointchange + " (" + percentchange + "%)</span>";
        } else {
          document.getElementById('shift'+i).innerHTML = "<span style='color:green;'>▲ "+ pointchange + " (" + percentchange + "%)</span>";
        };

      });

    }
  }
  stocks();
  var stockInt = setInterval(stocks, 10000); //10 seconds

  var d, c, ampm;
  updateStock();
  function updateStock() {
      d = new Date()
      c = d.toString("h:mm")
      ampm = d.toString("tt")
      var t = setTimeout(updateStock, 500)

      var mydate = new Date()
      var day   = mydate.getDay()
      var daym  = mydate.getDate()
      if (daym < 10)
          daym = "0" + daym
      var dayarray = new Array("Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday")

      if("9:00AM" == (c+ampm) && (dayarray[day] != "Saturday" && dayarray[day] != "Sunday")){
        stocks();
        stockInt = setInterval(stocks, 10000)
      }
      if("4:30PM" == (c+ampm)){
        clearInterval(stockInt)
        console.log("closed for the day");
      }
  };
});
