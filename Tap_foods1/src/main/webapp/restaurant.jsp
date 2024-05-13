<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.tap.model.Restaurant" %>


<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Restaurant List</title>
    
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    
    <style>
        .restaurant-card {
            margin-bottom: 20px; 
        }
    </style>
</head>
<body>
    <div class="container">
        <h1 class="text-center mt-4 mb-4">Restaurant List</h1>
        <div class="row row-cols-1 row-cols-md-3 row-cols-sm-3 g-4 restaurant-list mt-5 mb-4 ">
            <% 
                List<Restaurant> restaurants = (List<Restaurant>) request.getAttribute("restaurants");
                if (restaurants != null && !restaurants.isEmpty()) {
                    for (Restaurant restaurant : restaurants) {
            %>
            <div class="col mb-5" >
                <div class="card h-100 shadow-sm restaurant-card">
                    <a href="MenuServlet?restaurantId=<%= restaurant.getRestaurantId() %>" class="card-link">
                        <img src="<%= restaurant.getImagePath() %>" class="bd-placeholder-img card-img-top" width="100%" height="225" role="img" aria-label="Placeholder: Thumbnail" preserveAspectRatio="xMidYMid slice" focusable="false">
                        <div class="card-body">
                            <h5 class="card-title"><%= restaurant.getName() %></h5>
                            <p class="card-text"><%= restaurant.getAddress() %></p>
                            <div class="d-flex justify-content-between align-items-center">
                                <div>
                                    <span class="card-text"><strong>Rating:</strong> <%= restaurant.getRatings() %></span>
                                    <span class="card-text"> <strong><span>     </span> <%= restaurant.getCuisineType() %></strong> </span>
                                </div>
                            </div>
                            <small class="text-muted"><%= restaurant.getEta() %> minutes</small>
                        </div>
                    </a>
                </div>
            </div>
            <% 
                    }
                } else {
            %>
            <div class="col">
                <div class="alert alert-warning" role="alert">No restaurants found.</div>
            </div>
            <% } %>
        </div>
    </div>
    <!-- Bootstrap JS (optional if you need JavaScript features) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
