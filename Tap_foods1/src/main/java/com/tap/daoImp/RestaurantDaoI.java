package com.tap.daoImp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tap.dao.RestaurantDao;
import com.tap.model.Restaurant;

public class RestaurantDaoI implements RestaurantDao {
    private Connection connection = null;
    final static String INSERT_QUERY = "INSERT INTO restaurant (name, imagePath, ratings, eta, cuisineType, Address, restOwnerId) VALUES (?, ?, ?, ?, ?, ?, ?)";
    final static String SELECT_QUERY = "SELECT * FROM restaurant WHERE restaurantId = ?";
    final static String UPDATE_QUERY = "UPDATE restaurant SET name = ?, imagePath = ?, ratings = ?, eta = ?, cuisineType = ?, Address = ?, restOwnerId = ? WHERE restaurantId = ?";
    final static String DELETE_QUERY = "DELETE FROM restaurant WHERE restaurantId = ?";
    final static String SELECT_ALL_QUERY = "SELECT * FROM restaurant";

    public RestaurantDaoI() {
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
    public void addRestaurant(Restaurant restaurant) {
        try (PreparedStatement prepareStatement = connection.prepareStatement(INSERT_QUERY)) {
            prepareStatement.setString(1, restaurant.getName());
            prepareStatement.setString(2, restaurant.getImagePath());
            prepareStatement.setString(3, restaurant.getRatings());
            prepareStatement.setString(4, restaurant.getEta());
            prepareStatement.setString(5, restaurant.getCuisineType());
            prepareStatement.setString(6, restaurant.getAddress());
            prepareStatement.setInt(7, restaurant.getRestOwnerId());

            prepareStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Restaurant getRestaurant(int restaurantId) {
        PreparedStatement prepareStatement = null;
        ResultSet res = null;
        Restaurant restaurant = null;
        try {
            prepareStatement = connection.prepareStatement(SELECT_QUERY);
            prepareStatement.setInt(1, restaurantId);
            res = prepareStatement.executeQuery();

            if (res.next()) {
                String name = res.getString("name");
                String imagePath = res.getString("imagePath");
                String ratings = res.getString("ratings");
                String eta = res.getString("eta");
                String cuisineType = res.getString("cuisineType");
                String address = res.getString("Address");
                int restOwnerId = res.getInt("restOwnerId");
                restaurant = new Restaurant(restaurantId, name, imagePath, ratings, eta, cuisineType, address, restOwnerId);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return restaurant;
    }

    @Override
    public void updateRestaurant(Restaurant restaurant) {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)) {
            statement.setString(1, restaurant.getName());
            statement.setString(2, restaurant.getImagePath());
            statement.setString(3, restaurant.getRatings());
            statement.setString(4, restaurant.getEta());
            statement.setString(5, restaurant.getCuisineType());
            statement.setString(6, restaurant.getAddress());
            statement.setInt(7, restaurant.getRestOwnerId());
            statement.setInt(8, restaurant.getRestaurantId());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteRestaurant(int restaurantId) {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_QUERY)) {
            statement.setInt(1, restaurantId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Restaurant> getAllRestaurants() {
        PreparedStatement statement = null;
        Restaurant restaurant = null;

        ArrayList<Restaurant> restaurantsList = new ArrayList<>();
        try {
            statement = connection.prepareStatement(SELECT_ALL_QUERY);
            ResultSet res = statement.executeQuery();
            while (res.next()) {
                int restaurantId = res.getInt("restaurantId");
                String name = res.getString("name");
                String imagePath = res.getString("imagePath");
                String ratings = res.getString("ratings");
                String eta = res.getString("eta");
                String cuisineType = res.getString("cuisineType");
                String address = res.getString("Address");
                int restOwnerId = res.getInt("restOwnerId");
                restaurant = new Restaurant(restaurantId, name, imagePath, ratings, eta                , cuisineType, address, restOwnerId);
                restaurantsList.add(restaurant);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return restaurantsList;
    }
    
}