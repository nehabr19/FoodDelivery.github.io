package com.tap.model;

public class OrderItems {
	
	private int orderId;
	private int userId;
	private int menuId;
	private String itemName;
	private int rating;
	private int price;
	private int quantity;
	
	
	public OrderItems() {
		
	}


	public OrderItems(int orderId, int userId, int menuId, String itemName, int rating, int price, int quantity) {
		super();
		this.orderId = orderId;
		this.userId = userId;
		this.menuId = menuId;
		this.itemName = itemName;
		this.rating = rating;
		this.price = price;
		this.quantity = quantity;
	}
	


	public OrderItems(int orderId, int menuId, String itemName, int rating, int price, int quantity) {
		super();
		this.orderId = orderId;
		this.menuId = menuId;
		this.itemName = itemName;
		this.rating = rating;
		this.price = price;
		this.quantity = quantity;
	}
	


	public OrderItems(int orderId, String itemName, int rating, int price, int quantity) {
		super();
		this.orderId = orderId;
		this.itemName = itemName;
		this.rating = rating;
		this.price = price;
		this.quantity = quantity;
	}


	public int getOrderId() {
		return orderId;
	}


	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	public int getMenuId() {
		return menuId;
	}


	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}


	public String getItemName() {
		return itemName;
	}


	public void setItemName(String itemName) {
		this.itemName = itemName;
	}


	public int getRating() {
		return rating;
	}


	public void setRating(int rating) {
		this.rating = rating;
	}


	public int getPrice() {
		return price;
	}


	public void setPrice(int price) {
		this.price = price;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	

}
