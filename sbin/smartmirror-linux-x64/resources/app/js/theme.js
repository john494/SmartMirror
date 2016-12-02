$(document).ready(function() {
  function getLayout(){
    $.get("http://jarvis.cse.buffalo.edu/mine/layout", function (choice) {
      window.location.replace(choice.slice(0,-1).toLowerCase() + '.html')
    });
  }

getLayout();
setInterval(getLayout,3000);
});
