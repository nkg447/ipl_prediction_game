function register() {
  var name = document.getElementById("name").value;
  var email = document.getElementById("email").value;
  var password = document.getElementById("pwd").value;

  var jsonData = JSON.stringify({
    name: name,
    email: email,
    password: password
  });
  axios.post("register", jsonData).then(response => {
    if (response.data.data.registered) {
      window.location.replace("/index.html");
    }
  });
}
