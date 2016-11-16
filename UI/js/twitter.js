$(document).ready(function() {

  function getTweets() {
    $.get("http://jarvis.cse.buffalo.edu/mine/twitterinfo", function(body) {
      body = body.split("\n")

      var first_text_split = body[3].split(" ")
      var first_text = ""
      first_text_split.forEach(function(element) {
        if (element[0] == '@' || element[0] == '#') {
          if (element[element.length - 1] == ':') {
            first_text += '<span class="twitblue">' + element.substring(0, element.length - 1) + "</span>" + ": "
          } else {
            first_text += '<span class="twitblue">' + element + "</span> "
          }
        } else if (element.substring(0, 4) == 'http') {
          first_text += '<span class="twitblue">' + element + "</span> "
        } else {
          first_text += element + ' '
        }
      });

      var second_text_split = body[9].split(" ")
      var second_text = ""
      second_text_split.forEach(function(element) {
        if (element[0] == '@' || element[0] == '#') {
          if (element[element.length - 1] == ':') {
            second_text += '<span class="twitblue">' + element.substring(0, element.length - 1) + "</span>" + ": "
          } else {
            second_text += '<span class="twitblue">' + element + "</span> "
          }
        } else if (element.substring(0, 4) == 'http') {
          second_text += '<span class="twitblue">' + element + "</span> "
        } else {
          second_text += element + ' '
        }
      });

      var third_text_split = body[15].split(" ")
      var third_text = ""
      third_text_split.forEach(function(element) {
        if (element[0] == '@' || element[0] == '#') {
          if (element[element.length - 1] == ':') {
            third_text += '<span class="twitblue">' + element.substring(0, element.length - 1) + "</span>" + ": "
          } else {
            third_text += '<span class="twitblue">' + element + "</span> "
          }
        } else if (element.substring(0, 4) == 'http') {
          third_text += '<span class="twitblue">' + element + "</span> "
        } else {
          third_text += element + ' '
        }
      });

      // pull the data from the first tweet
      document.getElementById('username1').innerHTML = "<b>" + body[0] + "</b>"
      document.getElementById('fave1').innerHTML = body[1]
      document.getElementById('rt1').innerHTML = body[2]
      document.getElementById('text1').innerHTML = first_text

      // pull the data from the second tweet
      document.getElementById('username2').innerHTML = "<b>" + body[6] + "</b>"
      document.getElementById('fave2').innerHTML = body[7]
      document.getElementById('rt2').innerHTML = body[8]
      document.getElementById('text2').innerHTML = second_text

      // pull the data from the third tweet
      document.getElementById('username3').innerHTML = "<b>" + body[12] + "</b>"
      document.getElementById('fave3').innerHTML = body[13]
      document.getElementById('rt3').innerHTML = body[14]
      document.getElementById('text3').innerHTML = third_text

    });
  }

  getTweets();
  setInterval(getTweets, 20000); // 20 seconds
});
