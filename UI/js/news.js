$(document).ready(function() {
  function getNews() {
    var apiKey = "7322bfbd152e4050b6054c1bd4601106"
    // Top CNN headline
    $.get("https://newsapi.org/v1/articles?source=cnn&sortBy=top&apiKey=" + apiKey, function(headlines) {
      document.getElementById('CNN').innerHTML = "CNN: " + headlines['articles'][0]['title']
    });

    // Top USA Today headline
    $.get("https://newsapi.org/v1/articles?source=usa-today&sortBy=top&apiKey=" + apiKey, function(headlines) {
      document.getElementById('USA').innerHTML = "USA Today: " + headlines['articles'][0]['title']
    });

    // Top ESPN headline
    $.get("https://newsapi.org/v1/articles?source=espn&sortBy=top&apiKey=" + apiKey, function(headlines) {
      document.getElementById('ESPN').innerHTML = "ESPN: " + headlines['articles'][0]['title']
    });

    // Top Blumberg headline
    $.get("https://newsapi.org/v1/articles?source=bloomberg&sortBy=top&apiKey=" + apiKey, function(headlines) {
      document.getElementById('BLUM').innerHTML = "Bloomberg: " + headlines['articles'][0]['title']
    });
  }
  getNews();
  setInterval(getNews, 900000); //15 min
});
