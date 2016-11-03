$(document).ready(function() {
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

  setInterval(getStock("AAPL", 1),10000);
  setInterval(getStock("FB", 2),  10000);
  setInterval(getStock("NKE", 3), 10000);
  setInterval(getStock("TSLA", 4),10000);

});
