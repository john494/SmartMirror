$(document).ready(function() {

  function getTweets() {
    $.get("http://jarvis.cse.buffalo.edu/mine/facebookinfo", function(body) {
      // console.log(body);
    });
  }

  getTweets();
  setInterval(getTweets, 20000); // 20 seconds
});
