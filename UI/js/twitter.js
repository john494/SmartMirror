$(document).ready(function() {

  function getTweets() {
    $.get("http://jarvis.cse.buffalo.edu/mine/twitterinfo", function(body) {
      body = body.split("\n");

      // pull the data from the first tweet
      document.getElementById('username1').innerHTML = body[0]
      document.getElementById('fave1').innerHTML = body[1]
      document.getElementById('rt1').innerHTML = body[2]
      document.getElementById('text1').innerHTML = body[3]

      // pull the data from the second tweet
      document.getElementById('username2').innerHTML = body[6]
      document.getElementById('fave2').innerHTML = body[7]
      document.getElementById('rt2').innerHTML = body[8]
      document.getElementById('text2').innerHTML = body[9]

      // pull the data from the third tweet
      document.getElementById('username3').innerHTML = body[12]
      document.getElementById('fave3').innerHTML = body[13]
      document.getElementById('rt3').innerHTML = body[14]
      document.getElementById('text3').innerHTML = body[15]

    });
  }

  getTweets();
  setInterval(getTweets, 20000); // 20 seconds
});
