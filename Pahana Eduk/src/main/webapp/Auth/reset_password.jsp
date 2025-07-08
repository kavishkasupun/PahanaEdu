<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>Reset Password - Book Management</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/fontawesome.min.css" />
</head>

<body>
  <div class="container tm-mt-big tm-mb-big">
    <div class="row">
      <div class="col-12 mx-auto tm-login-col">
        <div class="tm-bg-primary-dark tm-block tm-block-h-auto">
          <div class="row">
            <div class="col-12 text-center">
              <h2 class="tm-block-title mb-4">Reset Your Password</h2>
            </div>
          </div>
          <div class="row mt-2">
            <div class="col-12">
              <form action="${pageContext.request.contextPath}/ResetPasswordServlet" method="post" class="tm-login-form">
                <input type="hidden" name="token" value="${param.token}">
                <div class="form-group">
                  <label for="password">New Password</label>
                  <input name="password" type="password" class="form-control validate" id="password" required />
                </div>
                <div class="form-group">
                  <label for="confirmPassword">Confirm New Password</label>
                  <input name="confirmPassword" type="password" class="form-control validate" id="confirmPassword" required />
                </div>
                <div class="form-group mt-4">
                  <button type="submit" class="btn btn-primary btn-block text-uppercase">Reset Password</button>
                </div>
              </form>

              <% String error = (String) request.getAttribute("error"); 
                 if (error != null) { %>
                <div class="alert alert-danger mt-3"><%= error %></div>
              <% } %>
              
              <% String message = (String) request.getAttribute("message"); 
                 if (message != null) { %>
                <div class="alert alert-success mt-3"><%= message %></div>
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