<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Sign Up</title>
    <!-- Include Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <!-- Include custom CSS -->
    <style>
        /* Center the signup form */
        .container {
            margin-top: 50px;
            position: relative;
        }

        /* Style the form */
        .signup-form {
            background-color: rgba(255, 255, 255, 0.9);
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
            margin-right: auto; /* Move form to the left */
        }

        /* Style the picture */
        .picture {
            position: absolute;
            top: 0;
            right: 0;
            height: 100%;
            width: auto;
            z-index: -1;
        }

        /* Add background image */
        body {
            background-image: url('pngtree-black-meat-western-food-banner-background-picture-image_1013905.jpg');
            background-size: 1300px 739px;
            
            background-repeat: no-repeat;
            
        }

        /* Style the creative calligraphy font for TAP FOODS */
        .tap-foods {
            font-family: 'Dancing Script', cursive; /* Use Dancing Script font */
            font-size: 72px; /* Adjust the font size as needed */
            font-weight: bold; /* Make the font bold */
            color: purple; /* Text color */
            position: absolute;
            left: 50%;
            
            transform: translate(-50%, -50%);
            font-family: 'Graffiti font', sans-serif;
        }

        .tap-foods img {
            margin: 0; /* Remove default margin */
            padding: 0; /* Remove default padding */
            height: 60px; /* Set height for images */
        }
    </style>
</head>
<body>

<div class="container">
    <h1 class="tap-foods" style="font-family:fantasy;">
        TAP FOODS
        <!-- Add more images for other letters if needed -->
    </h1>
    
    <!-- TAP FOODS text -->
    <div class="row justify-content-center">
        <div class="col-md-6">
            <br /><br />
            <div class="signup-form">
                <h2 class="mb-4">Sign Up</h2>
                <form action="SignUpServlet" method="post">
    

                    <div class="form-group">
                        <label for="name">Name</label>
                        <input type="text" id="name" name="name" class="form-control" required>
                    </div>
                    <br>
                    <div class="form-group">
                        <label for="email">Email</label>
                        <input type="email" id="email" name="email" class="form-control" required>
                    </div>
                    <br>
                    <div class="form-group">
                        <label for="phone">Phone</label>
                        <input type="number" id="phone" name="phone" class="form-control" required>
                    </div>
                    <br>
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
                    <button type="submit"  class="btn btn-primary">Sign Up</button>
                </form>
            </div>
            <br><br>
        </div>
    </div>
</div>
<!-- Include Bootstrap JavaScript (optional) -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>
