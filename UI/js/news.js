$(document).ready(function() {

  function getNews() {
    // 1) First grab what tye of news they want
    // Default is what we have Rn
    // Poltical =
    // Sports =
    // Tech =
    var apiKey = "7322bfbd152e4050b6054c1bd4601106"
    // Top CNN headline
    $.get("https://newsapi.org/v1/articles?source=cnn&sortBy=top&apiKey=" + apiKey, function(headlines) {
      document.getElementById('news1').innerHTML = "<b>CNN:</b> " + headlines['articles'][0]['title']
    });

    // Top USA Today headline
    $.get("https://newsapi.org/v1/articles?source=usa-today&sortBy=top&apiKey=" + apiKey, function(headlines) {
      document.getElementById('news2').innerHTML = "<b>USA Today:</b> " + headlines['articles'][0]['title']
    });

    // Top ESPN headline
    $.get("https://newsapi.org/v1/articles?source=espn&sortBy=top&apiKey=" + apiKey, function(headlines) {
      document.getElementById('news3').innerHTML = "<b>ESPN:</b> " + headlines['articles'][0]['title']
    });

    // Top Blumberg headline
    $.get("https://newsapi.org/v1/articles?source=bloomberg&sortBy=top&apiKey=" + apiKey, function(headlines) {
      document.getElementById('news4').innerHTML = "<b>Bloomberg:</b> " + headlines['articles'][0]['title']
    });
  }
  getNews();
  
  // each 30 min mark the app will update news
  var d, mm
  function updateNews() {
      d = new Date()
      mm = d.toString("mm")
      var t = setTimeout(updateNews, 500)
      if(mm == "00" || mm == "30"){
        getNews();
      }
  };
});
