package com.tap.daoImp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tap.dao.MenuDao;
import com.tap.model.Menu;

public class MenuDaoI implements MenuDao {
    private Connection connection = null;
    final static String INSERT_QUERY = "INSERT INTO `menu` (`name`, `price`, `description`, `imagePath`, `isAvailable`, `restaurantId`) VALUES (?, ?, ?, ?, ?, ?)";
    final static String SELECT_QUERY = "SELECT * FROM `menu` WHERE `menuId` = ?";
    final static String UPDATE_QUERY = "UPDATE `menu` SET `name` = ?, `price` = ?, `description` = ?, `imagePath` = ?, `isAvailable` = ?, `restaurantId` = ? WHERE `menuId` = ?";
    final static String DELETE_QUERY = "DELETE FROM `menu` WHERE `menuId` = ?";
    final static String SELECT_ALL_QUERY = "SELECT * FROM `menu`";
    final static String SELECT_QUERY1 = "SELECT * FROM `menu` WHERE `restaurantId` = ?";
    

    public MenuDaoI() {
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
    public void addMenu(Menu menu) {
        try (PreparedStatement prepareStatement = connection.prepareStatement(INSERT_QUERY)) {
            prepareStatement.setString(1, menu.getName());
            prepareStatement.setInt(2, menu.getPrice());
            prepareStatement.setString(3, menu.getDescription());
            prepareStatement.setString(4, menu.getImagePath());
            prepareStatement.setString(5, menu.getIsAvailable());
            prepareStatement.setInt(6, menu.getRestaurantId());

            prepareStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Menu getMenu(int menuId) {
        PreparedStatement prepareStatement = null;
        ResultSet res = null;
        Menu menu = null;
        try {
            prepareStatement = connection.prepareStatement(SELECT_QUERY);
            prepareStatement.setInt(1, menuId);
            res = prepareStatement.executeQuery();

            if (res.next()) {
                String name = res.getString("name");
                int price = res.getInt("price");
                String description = res.getString("description");
                String imagePath = res.getString("imagePath");
                String isAvailable = res.getString("isAvailable");
                int restaurantId = res.getInt("restaurantId");
                menu = new Menu(menuId, name, price, description, imagePath, isAvailable, restaurantId);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return menu;
    }

    @Override
    public void updateMenu(Menu menu) {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)) {
            statement.setString(1, menu.getName());
            statement.setInt(2, menu.getPrice());
            statement.setString(3, menu.getDescription());
            statement.setString(4, menu.getImagePath());
            statement.setString(5, menu.getIsAvailable());
            statement.setInt(6, menu.getRestaurantId());
            statement.setInt(7, menu.getMenuId());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteMenu(int menuId) {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_QUERY)) {
            statement.setInt(1, menuId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Menu> getAllMenus() {
        PreparedStatement statement = null;
        Menu menu = null;

        ArrayList<Menu> menusList = new ArrayList<>();
        try {
            statement = connection.prepareStatement(SELECT_ALL_QUERY);
            ResultSet res = statement.executeQuery();
            while (res.next()) {
                int menuId = res.getInt("menuId");
                String name = res.getString("name");
                int price = res.getInt("price");
                String description = res.getString("description");
                String imagePath = res.getString("imagePath");
                String isAvailable = res.getString("isAvailable");
                int restaurantId = res.getInt("restaurantId");
                menu = new Menu(menuId, name, price, description, imagePath, isAvailable, restaurantId);
                menusList.add(menu);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return menusList;
    }

	@Override
	public List<Menu> getMenuItemsByRestaurantId(int restaurantId) {
	    List<Menu> menuItems = new ArrayList<>();
	    PreparedStatement prepareStatement = null;
	    ResultSet res = null;
	    try {
	        prepareStatement = connection.prepareStatement(SELECT_QUERY1);
	        prepareStatement.setInt(1, restaurantId);
	        res = prepareStatement.executeQuery();

	        while (res.next()) {
	            String name = res.getString("name");
	            int price = res.getInt("price");
	            String description = res.getString("description");
	            String imagePath = res.getString("imagePath");
	            String isAvailable = res.getString("isAvailable");
	            int menuId = res.getInt("menuId");
	            Menu menuItem = new Menu(menuId, name, price, description, imagePath, isAvailable);
	            menuItems.add(menuItem);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        // Close resources (PreparedStatement, ResultSet, etc.) here
	    }

	    return menuItems;
	}

	

}
