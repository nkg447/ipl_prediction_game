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
    <form class="form" action="/login" method="POST">
      <div class="logo">
        <img src="/assets/logo.png" />
      </div>

      <h3>Login</h3>

      <input required type="text" name="email" placeholder="Email" />

      <input required type="password" name="pwd" placeholder="Password" />

      <span>
        Forgot Password?
      </span>

      <button type="submit" class="btn btn-primary">
        Login
      </button>

      <p class="message">
        Not registered? <a href="register.jsp">Create an account</a>
      </p>
    </form>
  </body>
</html>
