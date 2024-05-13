package com.tap.cont;



import com.tap.daoImp.OrderItemsDaoI;
import com.tap.model.OrderItems;

public class demo {

    public static void main(String[] args) {
        OrderItemsDaoI orderItemsDao = new OrderItemsDaoI();

        // Creating a new order item
        OrderItems orderItemToAdd = new OrderItems(1, 14, 1, "Burger", 5, 150, 2);

        // Adding the order item
        orderItemsDao.addOrderItems(orderItemToAdd);
        System.out.println("Order item added successfully.");
    }
}
