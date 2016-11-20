$(document).ready(function() {
  $.get("http://jarvis.cse.buffalo.edu/mine/layout", function (choice) {
    window.location.replace(choice.slice(0,-1).toLowerCase() + '.html')
  });
});
