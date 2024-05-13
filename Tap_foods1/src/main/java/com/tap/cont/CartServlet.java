package com.tap.cont;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tap.daoImp.CartCreation;
import com.tap.model.CartItems;

@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
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
        String itemIdParam = request.getParameter("menuId");
        String restIdParam = request.getParameter("restId");
        String name = request.getParameter("itemName");
        String priceParam = request.getParameter("price");
        String quantityParam = request.getParameter("quantity");

        if (itemIdParam != null && restIdParam != null && name != null && priceParam != null && quantityParam != null) {
            int itemId = Integer.parseInt(itemIdParam);
            int restId = Integer.parseInt(restIdParam);
            int price = Integer.parseInt(priceParam);
            int quantity = Integer.parseInt(quantityParam);

            // Create a new CartItems object
            CartItems cartItem = new CartItems(itemId, restId, name, price, quantity);

            // Add the cart item to the cart
            cartCreation.addCartItem(cartItem);

            // Set the cart items as an attribute in the request object
            request.setAttribute("cartItems", cartCreation.getCart());

            // Forward the request to the cart.jsp for rendering
            request.getRequestDispatcher("cart.jsp").forward(request, response);

            

        } else {
            // Handle the case where any of the parameters are missing or null
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing or invalid parameters");
        }
    }



}
