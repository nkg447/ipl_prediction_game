function login() {
  var email = document.getElementById("email").value;
  var password = document.getElementById("pwd").value;

  var jsonData = JSON.stringify({
    email: email,
    password: password
  });
  sendPost(jsonData, "http://localhost:8080/login");
}
