package com.tap.cont;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tap.daoImp.MenuDaoI;
import com.tap.model.Menu;

@WebServlet("/MenuServlet")
public class MenuServlet extends HttpServlet {
    private MenuDaoI menuDao = new MenuDaoI(); // Create an instance of MenuDaoI

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Get restaurant ID from request parameter
        int restaurantId = Integer.parseInt(req.getParameter("restaurantId"));
        
        // Fetch menu items by restaurant ID
        List<Menu> menuItems = menuDao.getMenuItemsByRestaurantId(restaurantId);
        
        // Set menu items as request attribute
        req.setAttribute("menuItems", menuItems);
        
        // Forward the request to the menu.jsp page for display
        req.getRequestDispatcher("menu.jsp").forward(req, resp);
    }
}
