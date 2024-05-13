package com.tap.dao;

import java.util.List;

import com.tap.model.OrderItems;

public interface OrderItemsDao {
	void addOrderItems(OrderItems orderItems);
	OrderItems getOrderItems(int orderItems);
	void updateOrderItems(OrderItems orderItems);
	void deleteOrderItems(int orderItemsId);
	List<OrderItems> getAllOrderItems();
	List<OrderItems> getOrderItemsByUserId(int userId);
	void addOrUpdateOrderItems(OrderItems orderItems);
    List<OrderItems> getOrderItemsByUserIdAndMenuId(int userId, int menuId);
    void updateOrderItemQuantity(int userId, int menuId, int quantity);

}
