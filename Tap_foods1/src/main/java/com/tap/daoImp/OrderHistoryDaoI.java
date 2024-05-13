package com.tap.daoImp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tap.dao.OrderHistoryDao;
import com.tap.model.OrderHistory;

public class OrderHistoryDaoI implements OrderHistoryDao {
    private Connection connection = null;
    final static String INSERT_QUERY = "INSERT INTO `orderhistory` (`orderId`, `userId`) VALUES (?, ?)";
    final static String SELECT_QUERY = "SELECT * FROM `orderhistory` WHERE `orderHistoryId` = ?";
    final static String UPDATE_QUERY = "UPDATE `orderhistory` SET `orderId` = ?, `userId` = ? WHERE `orderHistoryId` = ?";
    final static String DELETE_QUERY = "DELETE FROM `orderhistory` WHERE `orderHistoryId` = ?";
    final static String SELECT_ALL_QUERY = "SELECT * FROM `orderhistory`";

    public OrderHistoryDaoI() {
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
    public void addOrderHistory(OrderHistory orderHistory) {
        try (PreparedStatement prepareStatement = connection.prepareStatement(INSERT_QUERY)) {
            prepareStatement.setInt(1, orderHistory.getOrderId());
            prepareStatement.setInt(2, orderHistory.getUserId());

            prepareStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public OrderHistory getOrderHistory(int orderHistoryId) {
        PreparedStatement prepareStatement = null;
        ResultSet res = null;
        OrderHistory orderHistory = null;
        try {
            prepareStatement = connection.prepareStatement(SELECT_QUERY);
            prepareStatement.setInt(1, orderHistoryId);
            res = prepareStatement.executeQuery();

            if (res.next()) {
                int orderId = res.getInt("orderId");
                int userId = res.getInt("userId");
                orderHistory = new OrderHistory(orderHistoryId, orderId, userId);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orderHistory;
    }

    @Override
    public void updateOrderHistory(OrderHistory orderHistory) {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)) {
            statement.setInt(1, orderHistory.getOrderId());
            statement.setInt(2, orderHistory.getUserId());
            statement.setInt(3, orderHistory.getOrderHistoryId());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteOrderHistory(int orderHistoryId) {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_QUERY)) {
            statement.setInt(1, orderHistoryId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<OrderHistory> getAllOrderHistories() {
        PreparedStatement statement = null;
        OrderHistory orderHistory = null;

        ArrayList<OrderHistory> orderHistoriesList = new ArrayList<>();
        try {
            statement = connection.prepareStatement(SELECT_ALL_QUERY);
            ResultSet res = statement.executeQuery();
            while (res.next()) {
                int orderHistoryId = res.getInt("orderHistoryId");
                int orderId = res.getInt("orderId");
                int userId = res.getInt("userId");
                orderHistory = new OrderHistory(orderHistoryId, orderId, userId);
                orderHistoriesList.add(orderHistory);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orderHistoriesList;
    }
}
