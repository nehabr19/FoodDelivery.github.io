package com.tap.cont;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tap.daoImp.OrderItemsDaoI;

@WebServlet("/UpdateQuantityServlet")
public class UpdateQuantityServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final OrderItemsDaoI orderItemsDao = new OrderItemsDaoI();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get the user ID, menu ID, and quantity from the request
        int userId = Integer.parseInt(request.getParameter("userId"));
        int menuId = Integer.parseInt(request.getParameter("menuId"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        // Update the quantity in the database
        orderItemsDao.updateOrderItemQuantity(userId, menuId, quantity);

        // Send a response indicating success
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        out.print("Quantity updated successfully");
        out.flush();
    }
}
