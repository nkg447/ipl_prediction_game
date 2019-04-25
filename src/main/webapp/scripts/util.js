function sendPost(data, url) {
  var xhr = new XMLHttpRequest();
  xhr.withCredentials = true;

  xhr.addEventListener("readystatechange", function() {
    if (this.readyState === 4) {
      console.log(this.responseText);
    }
  });

  xhr.open("POST", url);
  xhr.setRequestHeader("Content-Type", "application/json");

  xhr.send(data);
}
