package com.tap.daoImp;

import java.util.List;

import com.tap.daoImp.UserDaoI;
import com.tap.model.Restaurant;
import com.tap.daoImp.RestaurantDaoI;

public class demo {

	public static void main(String[] args) {
		RestaurantDaoI userDao = new RestaurantDaoI();

        // Getting all users
        List<Restaurant> restaurantList = userDao.getAllRestaurants();
        if (!restaurantList.isEmpty()) {
            System.out.println("All users:");
            for (Restaurant restaurant : restaurantList) {
                System.out.println(restaurant.getName());
            }
        } else {
            System.out.println("No users found.");
        }

	}

}
