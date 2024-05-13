<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Map" %>
<html>
<head>
    <title>Checkout</title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        /* Custom styles */
        body {
            padding-top: 20px;
            padding-bottom: 20px;
        }
        .container {
            max-width: 600px;
        }
        .item-details {
            margin-bottom: 20px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Items in the Cart:</h2>
        <div class="item-details">
            <% 
                Map<String, Object> itemDetailsMap = (Map<String, Object>) request.getAttribute("itemDetailsMap");
                if (itemDetailsMap != null && !itemDetailsMap.isEmpty()) {
                    double totalPrice = 0.0;
                    for (Map.Entry<String, Object> entry : itemDetailsMap.entrySet()) {
                        Map<String, Object> itemDetails = (Map<String, Object>) entry.getValue();
                        String itemId = (String) itemDetails.get("itemId");
                        String restId = (String) itemDetails.get("restId");
                        String name = (String) itemDetails.get("name");
                        String price = (String) itemDetails.get("price");
                        String quantity = (String) itemDetails.get("quantity");
                        double subtotal = Double.parseDouble(price) * Integer.parseInt(quantity);
                        totalPrice += subtotal;
            %>
                        <div class="card">
                            <div class="card-body">
                                <h5 class="card-title">Item: <%= name %></h5>
                                <p class="card-text">Item ID: <%= itemId %></p>
                                <p class="card-text">Price: ₹<%= price %></p>
                                <p class="card-text">Quantity: <%= quantity %></p>
                                <p class="card-text">Subtotal: ₹<%= subtotal %></p>
                            </div>
                        </div>
            <%
                    }
            %>
                    <h3>Total Price: ₹<%= totalPrice %></h3>
                    <p>Payment Options:</p>
                    <label><input type="radio" name="paymentOption" value="creditCard"> Credit Card</label><br>
                    <label><input type="radio" name="paymentOption" value="debitCard"> Debit Card</label><br>
                    <label><input type="radio" name="paymentOption" value="cashOnDelivery"> Cash on Delivery</label><br>
                    <form action="orderConfirmed.jsp" method="post">
                        <input type="submit" value="Confirm Order" class="btn btn-primary">
                    </form>
            <%
                } else {
            %>
                    <p>No items in the cart.</p>
            <%
                }
            %>
        </div>
    </div>
</body>
</html>
