package com.tap.daoImp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tap.dao.OrderDao;
import com.tap.model.Order;

public class OrderDaoI implements OrderDao {
    private Connection connection = null;
    final static String INSERT_QUERY = "INSERT INTO `order` (`restaurantId`, `userId`, `totalAmount`, `modeOfPayment`, `status`) VALUES (?, ?, ?, ?, ?)";
    final static String SELECT_QUERY = "SELECT * FROM `order` WHERE `orderId` = ?";
    final static String UPDATE_QUERY = "UPDATE `order` SET `restaurantId` = ?, `userId` = ?, `totalAmount` = ?, `modeOfPayment` = ?, `status` = ? WHERE `orderId` = ?";
    final static String DELETE_QUERY = "DELETE FROM `order` WHERE `orderId` = ?";
    final static String SELECT_ALL_QUERY = "SELECT * FROM `order`";

    public OrderDaoI() {
        String url = "jdbc:mysql://localhost:3306/tap_food";
        String username = "root";
        String password = "root";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addOrder(Order order) {
        try (PreparedStatement prepareStatement = connection.prepareStatement(INSERT_QUERY)) {
            prepareStatement.setInt(1, order.getRestaurantId());
            prepareStatement.setInt(2, order.getUserId());
            prepareStatement.setInt(3, order.getTotalAmount());
            prepareStatement.setString(4, order.getModeOfPayment());
            prepareStatement.setString(5, order.getStatus());

            prepareStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Order getOrder(int orderId) {
        PreparedStatement prepareStatement = null;
        ResultSet res = null;
        Order order = null;
        try {
            prepareStatement = connection.prepareStatement(SELECT_QUERY);
            prepareStatement.setInt(1, orderId);
            res = prepareStatement.executeQuery();

            if (res.next()) {
                int userId = res.getInt("userId");
                int restaurantId = res.getInt("restaurantId");
                int totalAmount = res.getInt("totalAmount");
                String modeOfPayment = res.getString("modeOfPayment");
                String status = res.getString("status");
                order = new Order(orderId, userId, restaurantId, totalAmount, modeOfPayment, status);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return order;
    }

    @Override
    public void updateOrder(Order order) {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)) {
            statement.setInt(1, order.getRestaurantId());
            statement.setInt(2, order.getUserId());
            statement.setInt(3, order.getTotalAmount());
            statement.setString(4, order.getModeOfPayment());
            statement.setString(5, order.getStatus());
            statement.setInt(6, order.getOrderId());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteOrder(int orderId) {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_QUERY)) {
            statement.setInt(1, orderId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Order> getAllOrders() {
        PreparedStatement statement = null;
        Order order = null;

        ArrayList<Order> ordersList = new ArrayList<>();
        try {
            statement = connection.prepareStatement(SELECT_ALL_QUERY);
            ResultSet res = statement.executeQuery();
            while (res.next()) {
                int orderId = res.getInt("orderId");
                int userId = res.getInt("userId");
                int restaurantId = res.getInt("restaurantId");
                int totalAmount = res.getInt("totalAmount");
                String modeOfPayment = res.getString("modeOfPayment");
                String status = res.getString("status");
                order = new Order(orderId, userId, restaurantId, totalAmount, modeOfPayment, status);
                ordersList.add(order);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ordersList;
    }
}
