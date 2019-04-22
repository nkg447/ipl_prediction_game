<html>
  <head>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link
      rel="stylesheet"
      href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
    />
    <link rel="stylesheet" href="styles/index.css" />
  </head>
  <body>
    <form class="form" action="/register" method="POST">
      <h3>Registration Form</h3>

      <input required type="text" name="name" placeholder="Name" />

      <input required type="email" name="email" placeholder="Email" />

      <input required type="password" name="pwd" placeholder="Password" />

      <span>
      <button type="submit" class="btn btn-primary">
        Register
      </button>

      <p class="message">Already registered? <a href="index.jsp">Login</a></p>
    </form>
  </body>
</html>
