<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Login</title>
    <!-- Include Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <!-- Include custom CSS -->
    <style>
        /* Center the login form */
        .container {
            margin-top: 50px;
            position: relative;
        }

        /* Style the form */
        .login-form {
            background-color: rgba(255, 255, 255, 0.9);
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
            margin-right: auto; /* Move form to the left */
        }

        /* Add background image */
        body {
            background-image: url('pngtree-black-meat-western-food-banner-background-picture-image_1013905.jpg');
            background-size: 1450px;
            background-repeat: no-repeat;
            margin-bottom: 30px;
        }

        /* Style the login text */
        .login-text {
            font-family: 'Dancing Script', fantasy; /* Use Dancing Script font */
            font-size: 72px; /* Adjust the font size as needed */
            font-weight: bold; /* Make the font bold */
            color: purple; /* Text color */
            text-align: center;
            margin-bottom: 20px;
        }
    </style>
</head>
<body>

<div class="container">
    <h1 class="login-text">TAP FOODS</h1>
    <!-- Login form -->
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="login-form">
            <h2 class="mb-4">Sign In</h2>
                <!-- Display error message if present -->
                <p class="text-danger" id="error-message">
                    <%-- Check if error message is present and display it --%>
                    <% if (request.getAttribute("error") != null) { %>
                        <%= request.getAttribute("error") %>
                    <% } %>
                </p>
                <form action="RestaurantServlet" method="post">
                    <div class="form-group">
                        <label for="username">Username</label>
                        <input type="text" id="username" name="username" class="form-control" required>
                    </div>
                    <br>
                    <div class="form-group">
                        <label for="password">Password</label>
                        <input type="password" id="password" name="password" class="form-control" required>
                    </div>
                    <br><br>
                    <button type="submit" class="btn btn-primary btn-block">Login</button><br><br>
                    <p class="text-center">Don't have an account? <a href="signUp.jsp">Click here</a> to create one.</p>
                </form>
            </div>
            
            
        </div>
    </div>
</div>
<!-- Include Bootstrap JavaScript (optional) -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>
