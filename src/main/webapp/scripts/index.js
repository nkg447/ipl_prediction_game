function login() {
  var email = document.getElementById("email").value;
  var password = document.getElementById("pwd").value;

  var jsonData = {
    email: email,
    password: password
  };
  axios.post("login", jsonData).then(response => {
    if (response.data.data.authenticated) {
      window.location.replace("/predict.html");
    }
  });
}
