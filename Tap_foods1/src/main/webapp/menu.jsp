<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.tap.model.Menu" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Menu</title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom CSS -->
    <style>
        /* Add your custom styles here */
    </style>
</head>
<body>
    <!-- Main content -->
    <div class="container mt-4 mb-4">
        <h1 class="text-center mb-4">Menu</h1>
        <div class="row">
            <% 
                List<Menu> menuItems = (List<Menu>) request.getAttribute("menuItems");
                if (menuItems != null && !menuItems.isEmpty()) {
                    for (Menu menuItem : menuItems) {
            %>
            <div class="col-md-12">
                <div class="card menu-item-card">
                    <div class="row">
                        <div class="col-md-4">
                            <img src="<%= menuItem.getImagePath() %>" class="card-img-top menu-item-img" alt="Menu Item Image">
                        </div>
                        <div class="col-md-8">
                            <div class="card-body">
                                <h5 class="card-title menu-item-name"><%= menuItem.getName() %></h5>
                                <p class="card-text menu-item-description"><%= menuItem.getDescription() %></p>
                                <p class="card-text menu-item-price">₹<%= menuItem.getPrice() %></p>
                                <div class="align-right">
                                    <!-- Form for adding items to the cart -->
                                    <form action="CartServlet" method="post">
                                        <input type="hidden" name="userId" value="<%= session.getAttribute("userId") %>">
                                        <input type="hidden" name="menuId" value="<%= menuItem.getMenuId() %>">
                                        <input type="hidden" name="itemName" value="<%= menuItem.getName() %>">
                                        <input type="hidden" name="price" value="<%= menuItem.getPrice() %>">
                                        <input type="hidden" name="restId" value="<%= menuItem.getRestaurantId() %>">
    									<input type="hidden" name="quantity" value="1">
                                        <button type="submit" class="btn btn-success btn-sm add-to-cart-btn">Add</button>
                                        
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <% 
                    }
                } else {
            %>
            <div class="col">
                <div class="alert alert-warning" role="alert">No menu items found.</div>
            </div>
            <% } %>
        </div>
    </div>
</body>
</html>
