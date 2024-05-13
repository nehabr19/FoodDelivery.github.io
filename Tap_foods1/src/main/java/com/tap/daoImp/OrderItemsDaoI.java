package com.tap.daoImp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tap.dao.OrderItemsDao;
import com.tap.model.OrderItems;

public class OrderItemsDaoI implements OrderItemsDao {
    private Connection connection = null;
    final static String INSERT_QUERY = "INSERT INTO `orderitems` (`orderId`, `userId`, `menuId`, `itemName`, `rating`, `price`, `quantity`) VALUES (?, ?, ?, ?, ?, ?, ?)";
    final static String SELECT_QUERY = "SELECT * FROM `orderitems` WHERE `orderId` = ?";
    final static String SELECT_QUERY1 = "SELECT * FROM `orderitems` WHERE `userId` = ?";
    final static String SELECT_QUERY2 = "SELECT * FROM `orderitems` WHERE `orderId` = ? AND `menuId` = ?";
    final static String UPDATE_QUERY = "UPDATE `orderitems` SET `userId` = ?, `menuId` = ?, `itemName` = ?, `rating` = ?, `price` = ?, `quantity` = ? WHERE `orderId` = ?";
    final static String DELETE_QUERY = "DELETE FROM `orderitems` WHERE `orderId` = ?";
    final static String SELECT_ALL_QUERY = "SELECT * FROM `orderitems`";
    private static final String SELECT_QUERY3 = "SELECT * FROM orderitems WHERE userId = ? AND menuId = ?";
    private static final String UPDATE_QUERY1 = "UPDATE orderitems SET quantity = ? WHERE userId = ? AND menuId = ?";

    public OrderItemsDaoI() {
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
    public void addOrderItems(OrderItems orderItems) {
        try (PreparedStatement prepareStatement = connection.prepareStatement(INSERT_QUERY)) {
            prepareStatement.setInt(1, orderItems.getOrderId());
            prepareStatement.setInt(2, orderItems.getUserId());
            prepareStatement.setInt(3, orderItems.getMenuId());
            prepareStatement.setString(4, orderItems.getItemName());
            prepareStatement.setInt(5, orderItems.getRating());
            prepareStatement.setInt(6, orderItems.getPrice());
            prepareStatement.setInt(7, orderItems.getQuantity());

            prepareStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public OrderItems getOrderItems(int orderId) {
        PreparedStatement prepareStatement = null;
        ResultSet res = null;
        OrderItems orderItems = null;
        try {
            prepareStatement = connection.prepareStatement(SELECT_QUERY);
            prepareStatement.setInt(1, orderId);
            res = prepareStatement.executeQuery();

            if (res.next()) {
                int userId = res.getInt("userId");
                int menuId = res.getInt("menuId");
                String itemName = res.getString("itemName");
                int rating = res.getInt("rating");
                int price = res.getInt("price");
                int quantity = res.getInt("quantity");
                orderItems = new OrderItems(orderId, userId, menuId, itemName, rating, price, quantity);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orderItems;
    }

    @Override
    public void updateOrderItems(OrderItems orderItems) {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)) {
            statement.setInt(1, orderItems.getUserId());
            statement.setInt(2, orderItems.getMenuId());
            statement.setString(3, orderItems.getItemName());
            statement.setInt(4, orderItems.getRating());
            statement.setInt(5, orderItems.getPrice());
            statement.setInt(6, orderItems.getQuantity());
            statement.setInt(7, orderItems.getOrderId());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteOrderItems(int orderId) {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_QUERY)) {
            statement.setInt(1, orderId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<OrderItems> getAllOrderItems() {
        PreparedStatement statement = null;
        OrderItems orderItems = null;

        ArrayList<OrderItems> orderItemsList = new ArrayList<>();
        try {
            statement = connection.prepareStatement(SELECT_ALL_QUERY);
            ResultSet res = statement.executeQuery();
            while (res.next()) {
                int orderId = res.getInt("orderId");
                int userId = res.getInt("userId");
                int menuId = res.getInt("menuId");
                String itemName = res.getString("itemName");
                int rating = res.getInt("rating");
                int price = res.getInt("price");
                int quantity = res.getInt("quantity");
                orderItems = new OrderItems(orderId, userId, menuId, itemName, rating, price, quantity);
                orderItemsList.add(orderItems);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orderItemsList;
    }

	@Override
	public List<OrderItems> getOrderItemsByUserId(int userId) {
	    PreparedStatement prepareStatement = null;
	    ResultSet res = null;
	    List<OrderItems> orderItemsList = new ArrayList<>();
	    try {
	        prepareStatement = connection.prepareStatement(SELECT_QUERY1);
	        prepareStatement.setInt(1, userId);
	        res = prepareStatement.executeQuery();
	        while (res.next()) {
	            int orderId = res.getInt("orderId");
	            int menuId = res.getInt("menuId");
	            String itemName = res.getString("itemName");
	            int rating = res.getInt("rating");
	            int price = res.getInt("price");
	            int quantity = res.getInt("quantity");
	            OrderItems orderItems = new OrderItems(orderId, menuId, itemName, rating, price, quantity);
	            orderItemsList.add(orderItems);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        // Close PreparedStatement and ResultSet
	        try {
	            if (res != null) res.close();
	            if (prepareStatement != null) prepareStatement.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    return orderItemsList;
	}

	
	public List<OrderItems> getOrderItemsByUserIdAndMenuId(int userId, int menuId) {
		PreparedStatement prepareStatement = null;
	    ResultSet res = null;
	    List<OrderItems> orderItemsList = new ArrayList<>();
	    try {
	        prepareStatement = connection.prepareStatement(SELECT_QUERY3);
	        prepareStatement.setInt(1, userId);
	        prepareStatement.setInt(2, menuId);
	        res = prepareStatement.executeQuery();

	        while (res.next()) {
	            int orderId = res.getInt("orderId");
	            String itemName = res.getString("itemName");
	            int rating = res.getInt("rating");
	            int price = res.getInt("price");
	            int quantity = res.getInt("quantity");
	            OrderItems orderItems = new OrderItems(orderId, itemName, rating, price, quantity);
	            orderItemsList.add(orderItems);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        // Close PreparedStatement and ResultSet
	        try {
	            if (res != null) res.close();
	            if (prepareStatement != null) prepareStatement.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    return orderItemsList;
	
	}
	
	@Override
    public void addOrUpdateOrderItems(OrderItems orderItems) {
        PreparedStatement prepareStatement = null;
        try {
            // Check if the item already exists
            List<OrderItems> existingItem = getOrderItemsByUserIdAndMenuId(orderItems.getUserId(), orderItems.getMenuId());
            if (!existingItem.isEmpty()) {
                // Item already exists, update its quantity
                int newQuantity = existingItem.get(0).getQuantity() + orderItems.getQuantity();
                updateOrderItemQuantity(orderItems.getUserId(), orderItems.getMenuId(), newQuantity);
            } else {
                // Item doesn't exist, insert a new record
                prepareStatement = connection.prepareStatement(INSERT_QUERY);
                prepareStatement.setInt(1, orderItems.getOrderId());
                prepareStatement.setInt(2, orderItems.getUserId());
                prepareStatement.setInt(3, orderItems.getMenuId());
                prepareStatement.setString(4, orderItems.getItemName());
                prepareStatement.setInt(5, orderItems.getRating());
                prepareStatement.setInt(6, orderItems.getPrice());
                prepareStatement.setInt(7, orderItems.getQuantity());
                prepareStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close PreparedStatement
            try {
                if (prepareStatement != null) prepareStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

	@Override
	public void updateOrderItemQuantity(int userId, int menuId, int quantity) {
		PreparedStatement prepareStatement = null;
        try {
            prepareStatement = connection.prepareStatement(UPDATE_QUERY1);
            prepareStatement.setInt(1, quantity);
            prepareStatement.setInt(2, userId);
            prepareStatement.setInt(3, menuId);
            prepareStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close PreparedStatement
            try {
                if (prepareStatement != null) prepareStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    
		
	}


}
