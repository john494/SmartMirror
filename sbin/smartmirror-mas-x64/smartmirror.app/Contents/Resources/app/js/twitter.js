$(document).ready(function() {

  function getTweets() {
    $.get("http://jarvis.cse.buffalo.edu/mine/twitterinfo", function(body) {
      body = body.split("\n")

      var first_tweet = body[4].split(" ")
      first_tweet = highlightText(first_tweet)
      var second_tweet = body[11].split(" ")
      second_tweet = highlightText(second_tweet)
      var third_tweet = body[18].split(" ")
      third_tweet = highlightText(third_tweet)


      function highlightText(tweet){
        var highlighted_tweet = ""
        tweet.forEach(function(element) {
          if (element[0] == '@' || element[0] == '#' || element[0] == ".") {
            if (element[element.length - 1] == ':') {
              highlighted_tweet += '<span class="twitblue">' + element.substring(0, element.length - 1) + '</span>' + ": "
              if (element[1] == "@"){
                highlighted_tweet = '.<span class="twitblue">' + element.substring(1, element.length - 1) + '</span>'+ ": "
              }
            } else {
              highlighted_tweet += '<span class="twitblue">' + element + "</span> "
            }
          } else if (element.substring(0, 4) == 'http') {
            highlighted_tweet += '<span class="twitblue">' + element.substring(0,13) + "...</span> "
          } else {
            highlighted_tweet += element + ' '
          }
        });
        return highlighted_tweet;
      }

      // pull the data from the first tweet
      document.getElementById('twitimg1').innerHTML = '<img src="https://twitter.com/'+body[1]+'/profile_image?size=normal" /><br>'
      document.getElementById('username1').innerHTML = "<b>" + body[0] + "</b>"
      document.getElementById('screenname1').innerHTML = "@"+body[1]
      document.getElementById('fave1').innerHTML = body[2]
      document.getElementById('rt1').innerHTML = body[3]
      document.getElementById('text1').innerHTML = first_tweet

      // pull the data from the second tweet
      document.getElementById('twitimg2').innerHTML = '<img src="https://twitter.com/'+body[8]+'/profile_image?size=normal" /><br>'
      document.getElementById('username2').innerHTML = "<b>" + body[7] + "</b>"
      document.getElementById('screenname2').innerHTML = "@"+body[8]
      document.getElementById('fave2').innerHTML = body[9]
      document.getElementById('rt2').innerHTML = body[10]
      document.getElementById('text2').innerHTML = second_tweet

      // pull the data from the third tweet
      document.getElementById('twitimg3').innerHTML = '<img src="https://twitter.com/'+body[15]+'/profile_image?size=normal" /><br>'
      document.getElementById('username3').innerHTML = "<b>" + body[14] + "</b>"
      document.getElementById('screenname3').innerHTML = "@"+body[15]
      document.getElementById('fave3').innerHTML = body[16]
      document.getElementById('rt3').innerHTML = body[17]
      document.getElementById('text3').innerHTML = third_tweet

    });
  }

  getTweets();
  setInterval(getTweets, 20000); // 20 seconds
});
