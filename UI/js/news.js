$(document).ready(function() {

  function getNews(pref) {


    $.get("http://jarvis.cse.buffalo.edu/mine/news", function(pref) {

      var apiKey = "7322bfbd152e4050b6054c1bd4601106"

        if (pref == 'Default\n') {
          try {
            // Top USA Today headline
            $.get("https://newsapi.org/v1/articles?source=cnn&sortBy=top&apiKey=" + apiKey, function(headlines) {
              document.getElementById('news1').innerHTML = "<b>CNN:</b> " + headlines['articles'][0]['title']
            });
          } finally {
            document.getElementById('news1').innerHTML = 'Headline not available'
          }

          try {
            // Top USA Today headline
            $.get("https://newsapi.org/v1/articles?source=usa-today&sortBy=top&apiKey=" + apiKey, function(headlines) {
              document.getElementById('news2').innerHTML = "<b>USA Today:</b> " + headlines['articles'][0]['title']
            });
          } finally {
            document.getElementById('news2').innerHTML = 'Headline not available'
          }

          try {
            // Top ESPN headline
            $.get("https://newsapi.org/v1/articles?source=espn&sortBy=top&apiKey=" + apiKey, function(headlines) {
              document.getElementById('news3').innerHTML = "<b>ESPN:</b> " + headlines['articles'][0]['title']
            });
          } finally {
            document.getElementById('news3').innerHTML = 'Headline not available'
          }

          try {
            // Top Blumberg headline
            $.get("https://newsapi.org/v1/articles?source=bloomberg&sortBy=top&apiKey=" + apiKey, function(headlines) {
              document.getElementById('news4').innerHTML = "<b>Bloomberg:</b> " + headlines['articles'][0]['title']
            });
          } finally {
            document.getElementById('news4').innerHTML = 'Headline not available'
          }
      }

      if (pref == 'Sports\n') {
        try {
          // Top ESPN headline
          $.get("https://newsapi.org/v1/articles?source=espn&sortBy=top&apiKey=" + apiKey, function(headlines) {
            document.getElementById('news1').innerHTML = "<b>ESPN:</b> " + headlines['articles'][0]['title']
          });
        } finally {
          document.getElementById('news1').innerHTML = 'Headline not available'
        }

        try {
          // Top NFL headline
          $.get("https://newsapi.org/v1/articles?source=nfl-news&sortBy=top&apiKey=" + apiKey, function(headlines) {
            document.getElementById('news2').innerHTML = "<b>NFL:</b> " + headlines['articles'][0]['title']
          });
        } finally {
          document.getElementById('news2').innerHTML = 'Headline not available'
        }

        try {
          // Top Fox Sports Today headline
          $.get("https://newsapi.org/v1/articles?source=fox-sports&sortBy=top&apiKey=" + apiKey, function(headlines) {
            document.getElementById('news3').innerHTML = "<b>Fox Sports:</b> " + headlines['articles'][0]['title']
          });
        } finally {
          document.getElementById('news3').innerHTML = 'Headline not available'
        }

        try {
          // Top BBC Sport headline
          $.get("https://newsapi.org/v1/articles?source=bbc-sport&sortBy=top&apiKey=" + apiKey, function(headlines) {
            document.getElementById('news4').innerHTML = "<b>BBC Sports:</b> " + headlines['articles'][0]['title']
          });
        } finally {
          document.getElementById('news4').innerHTML = 'Headline not available'
        }
      }

      if (pref == 'Politics\n') {
        try {
          // Top CNN headline
          $.get("https://newsapi.org/v1/articles?source=cnn&sortBy=top&apiKey=" + apiKey, function(headlines) {
            document.getElementById('news1').innerHTML = "<b>CNN:</b> " + headlines['articles'][0]['title']
          });
        } finally {
          document.getElementById('news1').innerHTML = 'Headline not available'
        }

        try {
          // Top BBC headline
          $.get("https://newsapi.org/v1/articles?source=bbc-news&sortBy=top&apiKey=" + apiKey, function(headlines) {
            document.getElementById('news2').innerHTML = "<b>BBC:</b> " + headlines['articles'][0]['title']
          });
        } finally {
          document.getElementById('news2').innerHTML = 'Headline not available'
        }

        try {
          // Top USA Today headline
          $.get("https://newsapi.org/v1/articles?source=usa-today&sortBy=top&apiKey=" + apiKey, function(headlines) {
            document.getElementById('news3').innerHTML = "<b>USA Today:</b> " + headlines['articles'][0]['title']
          });
        } finally {
          document.getElementById('news3').innerHTML = 'Headline not available'
        }

        try {
          // Top Huffinton Post headline
          $.get("https://newsapi.org/v1/articles?source=the-huffington-post&sortBy=top&apiKey=" + apiKey, function(headlines) {
            document.getElementById('news4').innerHTML = "<b>Huffington Post:</b> " + headlines['articles'][0]['title']
          });
        } finally {
          document.getElementById('news4').innerHTML = 'Headline not available'
        }
      }

      if (pref == 'Technology\n') {
        try {
          // Top Techradar Today headline
          $.get("https://newsapi.org/v1/articles?source=techradar&sortBy=top&apiKey=" + apiKey, function(headlines) {
            document.getElementById('news1').innerHTML = "<b>Techradar:</b> " + headlines['articles'][0]['title']
          });
        } finally {
          document.getElementById('news1').innerHTML = 'Headline not available'
        }

        try {
          // Top Techcrunch headline
          $.get("https://newsapi.org/v1/articles?source=techcrunch&sortBy=top&apiKey=" + apiKey, function(headlines) {
            document.getElementById('news2').innerHTML = "<b>Techcrunch:</b> " + headlines['articles'][0]['title']
          });
        } finally {
          document.getElementById('news2').innerHTML = 'Headline not available'
        }

        try {
          // Top Polygon headline
          $.get("https://newsapi.org/v1/articles?source=polygon&sortBy=top&apiKey=" + apiKey, function(headlines) {
            document.getElementById('news3').innerHTML = "<b>Polygon:</b> " + headlines['articles'][0]['title']
          });
        } finally {
          document.getElementById('news3').innerHTML = 'Headline not available'
        }

        try {
          // Top IGN headline
          $.get("https://newsapi.org/v1/articles?source=ign&sortBy=top&apiKey=" + apiKey, function(headlines) {
            document.getElementById('news4').innerHTML = "<b>IGN:</b> " + headlines['articles'][0]['title']
          });
        } finally {
          document.getElementById('news4').innerHTML = 'Headline not available'
        }
      }
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
