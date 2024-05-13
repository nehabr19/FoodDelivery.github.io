package com.tap.model;

public class Restaurant {
	
	private int restaurantId;
	private String name;
	private String imagePath;
	private String ratings;
	private String eta;
	private String cuisineType;
	private String Address;
	private int restOwnerId;
	
	public Restaurant(int restaurantId, String name, String imagePath, float ratings, int est, String cuisineType, String address, boolean isActive, int ownerId) {
		
	}

	public Restaurant(int restaurantId, String name, String imagePath, String ratings, String eta, String cuisineType,
			String address, int restOwnerId) {
		super();
		this.restaurantId = restaurantId;
		this.name = name;
		this.imagePath = imagePath;
		this.ratings = ratings;
		this.eta = eta;
		this.cuisineType = cuisineType;
		this.Address = address;
		this.restOwnerId = restOwnerId;
	}

	public int getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getRatings() {
		return ratings;
	}

	public void setRatings(String ratings) {
		this.ratings = ratings;
	}

	public String getEta() {
		return eta;
	}

	public void setEta(String eta) {
		this.eta = eta;
	}

	public String getCuisineType() {
		return cuisineType;
	}

	public void setCuisineType(String cuisineType) {
		this.cuisineType = cuisineType;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		this.Address = address;
	}

	public int getRestOwnerId() {
		return restOwnerId;
	}

	public void setRestOwnerId(int restOwnerId) {
		this.restOwnerId = restOwnerId;
	}
	

}
