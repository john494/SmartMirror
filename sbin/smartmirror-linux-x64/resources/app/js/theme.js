$(document).ready(function() {

  function getLayout() {
    $.get("http://jarvis.cse.buffalo.edu/mine/layout", function (choice) {

      var setTo = window.location.pathname.split('/')
      setTo = setTo[setTo.length - 1]

      if (choice.slice(0,-1).toLowerCase() + '.html' != setTo) {
        window.location.replace(choice.slice(0,-1).toLowerCase() + '.html')
      }
    });
  }

  getLayout();
  setInterval(getLayout,3000);
});
