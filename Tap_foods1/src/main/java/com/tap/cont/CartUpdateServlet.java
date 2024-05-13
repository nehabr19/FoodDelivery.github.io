package com.tap.cont;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tap.daoImp.CartCreation;
import com.tap.model.CartItems;

@WebServlet("/CartUpdateServlet")
public class CartUpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CartCreation cartCreation;

    @Override
    public void init() throws ServletException {
        // Initialize the CartCreation object in the servlet's init method
        cartCreation = new CartCreation();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve form data
        String itemIdParam = request.getParameter("itemId");
        String quantityParam = request.getParameter("quantity");
        String restIdParam = request.getParameter("restId");
        String name = request.getParameter("name");
        String priceParam = request.getParameter("price");

        if (itemIdParam != null && quantityParam != null && restIdParam != null && name != null && priceParam != null) {
            int itemId = Integer.parseInt(itemIdParam);
            int quantity = Integer.parseInt(quantityParam);
            int restId = Integer.parseInt(restIdParam);
            int price = Integer.parseInt(priceParam);

            // Log received itemId and quantity for debugging
            System.out.println("Received itemId: " + itemId + ", quantity: " + quantity);

            // Create a CartItems object with the received itemId, restId, name, price, and quantity
            CartItems cartItem = new CartItems(itemId, restId, name, price, quantity);

            // Add the cart item to the cart
            cartCreation.addCartItem(cartItem);

            // Send a success response
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            // Send a bad request response if any of the parameters are missing
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing or invalid parameters");
        }
    }

}
