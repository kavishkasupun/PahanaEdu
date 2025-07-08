<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>Forgot Password - Book Management</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/fontawesome.min.css" />

  <style>
    /* Your existing styles remain the same */
  </style>
</head>

<body>
  <div class="container tm-mt-big tm-mb-big">
    <div class="row">
      <div class="col-12 mx-auto tm-login-col">
        <div class="tm-bg-primary-dark tm-block tm-block-h-auto">
          <div class="row">
            <div class="col-12 text-center">
              <h2 class="tm-block-title mb-4">Forgot Password</h2>
            </div>
          </div>
          <div class="row mt-2">
            <div class="col-12">
              <form action="${pageContext.request.contextPath}/ForgotPasswordServlet" method="post" class="tm-login-form">
                <div class="form-group">
                  <label for="email">Enter your Email</label>
                  <input name="email" type="email" class="form-control validate" id="email" required />
                </div>
                <div class="form-group mt-4">
                  <button type="submit" class="btn btn-primary btn-block text-uppercase">Send Reset Link</button>
                </div>
                <a href="${pageContext.request.contextPath}/Auth/index.jsp" class="mt-3 btn btn-secondary btn-block text-uppercase">Back to Login</a>
              </form>

              <% String message = (String) request.getAttribute("message"); 
                 if (message != null) { %>
                <div class="alert alert-info mt-3"><%= message %></div>
              <% } %>

            </div>
          </div>
        </div>
      </div>
    </div>
  </div>

  <script src="../js/bootstrap.min.js"></script>
</body>
</html>