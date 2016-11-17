$(document).ready(function() {
  var stock1 = "AAPL"
  var stock2 = "FB"
  var stock3 = "NKE"
  var stock4 = "TSLA"
  //make get request to get these names

  function stocks(){
    getStock(stock1, 1);
    getStock(stock2, 2);
    getStock(stock3, 3);
    getStock(stock4, 4);

    function getStock(symbol, i) {
      $.get("http://finance.google.com/finance/info?client=iq&q=" + symbol, function(body) {
        body = body.slice(4);
        body = JSON.parse(body);

        // Current share price
        var tickername = body[0]['t'];
        var price = body[0]['l'];
        var pointchange = body[0]['c'];
        var percentchange = body[0]['cp'];

        document.getElementById('ticker'+i).innerHTML = tickername;
        document.getElementById('curprice'+i).innerHTML  = price +" USD";

        // choose appropriate color
        if (pointchange < 0) {
          document.getElementById('shift'+i).innerHTML = "<span style='color:red;'>▼ "  + pointchange + " (" + percentchange + "%)</span>";
        } else {
          document.getElementById('shift'+i).innerHTML = "<span style='color:green;'>▲ "+ pointchange + " (" + percentchange + "%)</span>";
        };

      });
    }
  }
  stocks();
  var stockInt = setInterval(stocks, 10000); //10 seconds

  //grabs company name from ticker symbol
  function stockName(){
    $.get("https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.quote%20where%20symbol%20in%20( \
      %22"+stock1+"%22%2C%22"+stock2+"%22%2C%22"+stock3+"%22%2C%22"+stock4+"%22)&format=json&diagnostics=true&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys&callback=", function(body) {

        var companyname = "";
        for(i = 0; i < 4; i++){
          companyname = body['query']['results']['quote'][i]['Name'];
          companyname = companyname.substring(0, companyname.indexOf('Inc') + 'Inc'.length); // remove unwanted text
          if(companyname.length >= 17){
            companyname = companyname.substring(0, 13)+"...";
          }
          document.getElementById('stockname'+(i+1)).innerHTML = companyname;
        }
      });
  }
  //see if name updates on new stocks being added in
  stockName();

  var d, c, ampm, h;
  updateStock();
  function updateStock() {
      d    = new Date()
      c    = d.toString("hmm")
      h    = d.toString("h")
      ampm = d.toString("tt")
      var t = setTimeout(updateStock, 500)

      var mydate = new Date()
      var day   = mydate.getDay()
      var daym  = mydate.getDate()
      if (daym < 10)
          daym = "0" + daym
      var dayarray = new Array("Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday")

      if("900AM" == (c+ampm) && (dayarray[day] != "Saturday" && dayarray[day] != "Sunday")){
        stocks();
        stockInt = setInterval(stocks, 10000)
      }

      if("430PM" == (c+ampm) || (("PM" == ampm) && (c >= 430)) || (("AM" == ampm) && (c < 900))){
        clearInterval(stockInt)
      }
  };
});
