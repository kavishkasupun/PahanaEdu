<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <!-- [Your existing head with stylesheets and CSS animations] -->
  <title>Login Page - Book Management</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/fontawesome.min.css" />
  
  
    <style>
    /* Add these animation styles */
    .form-control {
      transition: all 0.3s ease;
    }
    
    .form-control:focus {
      box-shadow: 0 0 5px rgba(0, 110, 255, 0.5),
                 inset 0 0 5px rgba(0, 110, 255, 0.5);
      animation: glow 0.8s ease-out infinite alternate;
    }
    
    @keyframes glow {
      0% {
        border-color: #2a81ad;
        box-shadow: 0 0 5px rgba(0, 110, 255, 0.5),
                   inset 0 0 5px rgba(0, 110, 255, 0.5);
      }
      100% {
        border-color: #6f6;
        box-shadow: 0 0 20px rgba(0, 110, 255, 0.5),
                   inset 0 0 10px rgba(0, 110, 255, 0.5);
      }
    }
    
    .btn-primary {
      transition: all 0.3s ease;
    }
    
    .btn-primary:hover {
      box-shadow: 0 0 5px rgba(0, 110, 255, 0.5),
                 0 0 10px rgba(0, 110, 255, 0.3),
                 0 0 15px rgba(0, 255, 0, 0.1),
                 0 2px 0 rgba(0, 0, 0, 0.1);
    }
  </style>
  
</head>

<body>

   <div>
      <nav class="navbar navbar-expand-xl">
        <div class="container h-100">
          <a class="navbar-brand" href="index.html">
            <h1 class="tm-site-title mb-0">Pahana Edu</h1>
          </a>
        </div>
      </nav>
    </div>

  <div class="container tm-mt-big tm-mb-big">
    <div class="row">
      <div class="col-12 mx-auto tm-login-col">
        <div class="tm-bg-primary-dark tm-block tm-block-h-auto">
          <div class="row">
            <div class="col-12 text-center">
              <h2 class="tm-block-title mb-4">Login</h2>
            </div>
          </div>
          <div class="row mt-2">
            <div class="col-12">
              <form action="${pageContext.request.contextPath}/LoginServlet" method="post" class="tm-login-form">
                <div class="form-group">
                  <label for="username">Username</label>
                  <input name="username" type="text" class="form-control validate" id="username" required />
                </div>
                <div class="form-group mt-3">
                  <label for="password">Password</label>
                  <input name="password" type="password" class="form-control validate" id="password" required />
                </div>
                <div class="form-group mt-4">
                  <button type="submit" class="btn btn-primary btn-block text-uppercase">Login</button>
                </div>
                <a href="${pageContext.request.contextPath}/Auth/forgot_password.jsp" class="mt-3 btn btn-secondary btn-block text-uppercase">Forgot Password?</a>
              </form>
              <%
                String error = (String) request.getAttribute("error");
                if (error != null) {
              %>
                <div class="alert alert-danger mt-3"><%= error %></div>
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
