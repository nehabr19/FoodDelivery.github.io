<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Map" %>
<%@ page import="com.tap.model.CartItems" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Shopping Cart</title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom CSS -->
    <style>
        /* Add your custom styles here */
    </style>
</head>
<body>
    <div class="container mt-4">
        <h1 class="text-center">Shopping Cart</h1>
        <div class="row">
            <div class="col">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">Cart Items</h5>
                        <form action="PaymentServlet" method="post"> <%-- Form action points to PaymentServlet --%>
                            <ul>
                                <%-- Iterate over cart items and display them --%>
                                <%
                                    Map<Integer, CartItems> cartItems = (Map<Integer, CartItems>) request.getAttribute("cartItems");
                                    if (cartItems != null && !cartItems.isEmpty()) {
                                        for (Map.Entry<Integer, CartItems> entry : cartItems.entrySet()) {
                                            CartItems item = entry.getValue();
                                %>
                                <li class="menu-item" data-menu-id="<%= item.getItemId() %>">
                                    <p><strong>Item Name:</strong> <%= item.getName() %></p>
                                    <p><strong>Price:</strong> ₹<%= item.getPrice() %></p>
                                    <label for="quantity">Quantity:</label>
                                    <input type="number" id="quantity_<%= item.getItemId() %>" name="quantity" value="<%= item.getQuantity() %>">
                                    <input type="hidden" name="itemId" value="<%= item.getItemId() %>"> <%-- Hidden input for item ID --%>
                                    <input type="hidden" name="restId" value="<%= item.getRestId() %>"> <%-- Hidden input for restaurant ID --%>
                                    <input type="hidden" name="name" value="<%= item.getName() %>"> <%-- Hidden input for item name --%>
                                    <input type="hidden" name="price" value="<%= item.getPrice() %>"> <%-- Hidden input for item price --%>
                                </li>
                                <%
                                        }
                                    } else {
                                %>
                                <li>No items in the cart.</li>
                                <% } %>
                            </ul>
                            <div class="text-center">
                                <button type="submit" class="btn btn-primary">Proceed to Checkout</button> <%-- Submit button to proceed to checkout --%>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- JavaScript for dynamic quantity -->
    <script>
    // Add event listeners to increase and decrease quantity buttons
    document.querySelectorAll('input[name="quantity"]').forEach(input => {
        input.addEventListener('change', (event) => {
            const quantity = parseInt(event.target.value);
            const menuId = event.target.parentElement.querySelector('input[name="itemId"]').value;
            updateQuantity(menuId, quantity); // Update quantity
        });
    });

    function updateQuantity(menuId, quantity) {
        // Perform AJAX request to update quantity on the server
        fetch('CartUpdateServlet', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            body: `itemId=${menuId}&quantity=${quantity}`
        }).then(response => {
            if (response.ok) {
                console.log('Quantity updated successfully');
            } else {
                console.error('Failed to update quantity');
            }
        }).catch(error => {
            console.error('Error:', error);
        });
    }
</script>

</body>
</html>
