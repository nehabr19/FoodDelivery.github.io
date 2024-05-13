package com.tap.cont;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/PaymentServlet")
public class PaymentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve parameters sent from cart.jsp for multiple items
        String[] itemIds = request.getParameterValues("itemId");
        String[] restIds = request.getParameterValues("restId");
        String[] names = request.getParameterValues("name");
        String[] prices = request.getParameterValues("price");
        String[] quantities = request.getParameterValues("quantity");

        // Initialize a HashMap to store item details
        Map<String, Object> itemDetailsMap = new HashMap<>();

        // Loop through each item and store its details in the HashMap
        if (itemIds != null) {
            for (int i = 0; i < itemIds.length; i++) {
                Map<String, Object> itemDetails = new HashMap<>();
                itemDetails.put("itemId", itemIds[i]);
                itemDetails.put("restId", restIds[i]);
                itemDetails.put("name", names[i]);
                itemDetails.put("price", prices[i]);
                itemDetails.put("quantity", quantities[i]);
                itemDetailsMap.put("item" + i, itemDetails);
            }
        }

        // Set the HashMap as an attribute in the request object
        request.setAttribute("itemDetailsMap", itemDetailsMap);

        // Forward the request to checkout.jsp
        request.getRequestDispatcher("checkout.jsp").forward(request, response);
    }
}
