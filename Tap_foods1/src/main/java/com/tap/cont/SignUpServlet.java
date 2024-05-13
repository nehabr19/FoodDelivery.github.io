package com.tap.cont;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tap.daoImp.UserDaoI;
import com.tap.model.User;

@WebServlet("/SignUpServlet")
public class SignUpServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Extracting form parameters
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phoneStr = request.getParameter("phone");
        long phone = Long.parseLong(phoneStr); // Convert phone number to long
        String address = request.getParameter("address"); // Assuming there's an "address" field in the form
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String role = "Customer"; // Assuming default role is "Customer"

        // Creating a User object
        User user = new User(0, name, email, phone, address, username, password, role,
                new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()));

        // Adding user
        UserDaoI userDao = new UserDaoI();
        userDao.addUser(user);

        // Redirecting to a success page or displaying a message
        response.sendRedirect("login.jsp");
    }
}
