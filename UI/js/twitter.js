$(document).ready(function() {

  function getTweets() {
    $.get("http://jarvis.cse.buffalo.edu/mine/twitter.php", function(body) {
      // console.log(body);
    });
  }

  getTweets();
  setInterval(getTweets, 20000); // 20 seconds
});
