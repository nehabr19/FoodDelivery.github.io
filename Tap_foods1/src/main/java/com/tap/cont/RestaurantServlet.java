package com.tap.cont;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tap.daoImp.RestaurantDaoI;
import com.tap.daoImp.UserDaoI;
import com.tap.model.Restaurant;
import com.tap.model.User;

@WebServlet("/RestaurantServlet")
public class RestaurantServlet extends HttpServlet {
    private UserDaoI userDao = new UserDaoI(); // Create an instance of UserDaoI

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Get username and password from request parameters
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        
        // Authenticate user
        User user = userDao.getUser(username);
        if (user != null && user.getPassword().equals(password)) {
        	
        	req.getSession().setAttribute("userId", user.getUserId());
            // If authentication successful, proceed to fetch restaurants
            RestaurantDaoI restaurantDao = new RestaurantDaoI();
            List<Restaurant> restaurants = restaurantDao.getAllRestaurants();
            req.setAttribute("restaurants", restaurants);
            req.getRequestDispatcher("restaurant.jsp").forward(req, resp);
        } else {
        	
            // If authentication fails, redirect back to login page with an error message
            req.setAttribute("error", "Wrong username or password, please try again!");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        
        }
    }
}
