package com.tap.daoImp;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tap.dao.UserDao;
import com.tap.model.User;

public class UserDaoI implements UserDao {
    private Connection connection = null;
    final static String INSERT_QUERY = "INSERT INTO `user` (`name`, `email`, `phoneNo`, `Address`, `username`, `password`, `Role`) VALUES (?, ?, ?, ?, ?, ?, ?)";
    final static String SELECT_QUERY = "SELECT * FROM `user` WHERE `username` = ?";
    final static String UPDATE_QUERY = "UPDATE `user` SET `name` = ?, `email` = ?, `phoneNo` = ?, `Address` = ?, `username` = ?, `password` = ?, `Role` = ? WHERE `UserId` = ?";
    final static String DELETE_QUERY = "DELETE FROM `user` WHERE `userId` = ?";
    final static String SELECT_ALL_QUERY = "SELECT * FROM `user`";

    public UserDaoI() {
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
    public void addUser(User user) {
        try (PreparedStatement prepareStatement = connection.prepareStatement(INSERT_QUERY)) {
            prepareStatement.setString(1, user.getName());
            prepareStatement.setString(2, user.getEmail());
            prepareStatement.setLong(3, user.getPhoneNo());
            prepareStatement.setString(4, user.getAddress());
            prepareStatement.setString(5, user.getUsername());
            prepareStatement.setString(6, user.getPassword());
            prepareStatement.setString(7, user.getRole());

            prepareStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User getUser(String username) {
        PreparedStatement prepareStatement = null;
        ResultSet res = null;
        User user = null;
        try {
            prepareStatement = connection.prepareStatement(SELECT_QUERY);
            prepareStatement.setString(1, username);
            res = prepareStatement.executeQuery();

            if (res.next()) {
            	int userId=res.getInt("userId");
                String name = res.getString("name");
                String email = res.getString("email");
                long phoneNo = res.getInt("phoneNo");
                String address = res.getString("Address");
                String username1 = res.getString("username");
                String password = res.getString("password");
                String role = res.getString("Role");
                Date createDate = res.getDate("CreateDate");
                Date lastLoginTime = res.getDate("LastLoginTime");
                user = new User(userId, name, email, phoneNo, address, username1, password, role, createDate, lastLoginTime);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    @Override
    public void updateUser(User user) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(UPDATE_QUERY);
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setLong(3, user.getPhoneNo());
            statement.setString(4, user.getAddress());
            statement.setString(5, user.getUsername());
            statement.setString(6, user.getPassword());
            statement.setString(7, user.getRole());
            statement.setInt(8, user.getUserId());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteUser(int userId) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(DELETE_QUERY);
            statement.setInt(1, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public ArrayList<User> getUserByUsername(String usernameParam) {
        PreparedStatement statement = null;
        User user = null;

        ArrayList<User> usersList = new ArrayList<User>();
        try {
            statement = connection.prepareStatement(SELECT_ALL_QUERY);
            ResultSet res = statement.executeQuery();
            while (res.next()) {
                int userId = res.getInt("userId");
                String name = res.getString("name");
                String email = res.getString("email");
                long phoneNo = res.getInt("phoneNo");
                String address = res.getString("Address");
                String retrievedUsername = res.getString("username");
                String password = res.getString("password");
                String role = res.getString("Role");
                Date createDate = res.getDate("CreateDate");
                Date lastLoginTime = res.getDate("LastLoginTime");
                user = new User(userId, name, email, phoneNo, address, retrievedUsername, password, role, createDate, lastLoginTime);
                usersList.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usersList;
    }
    public List<User> getAllUsers() {
        PreparedStatement statement = null;
        User user = null;

        ArrayList<User> usersList = new ArrayList<User>();
        try {
            statement = connection.prepareStatement(SELECT_ALL_QUERY);
            ResultSet res = statement.executeQuery();
            while (res.next()) {
                int userId = res.getInt("userId");
                String name = res.getString("name");
                String email = res.getString("email");
                long phoneNo = res.getInt("phoneNo");
                String address = res.getString("Address");
                String username = res.getString("username");
                String password = res.getString("password");
                String role = res.getString("Role");
                Date createDate = res.getDate("CreateDate");
                Date lastLoginTime = res.getDate("LastLoginTime");
                user = new User(userId, name, email, phoneNo, address, username, password, role, createDate, lastLoginTime);
                usersList.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usersList;
    }

}
