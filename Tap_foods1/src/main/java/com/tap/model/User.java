package com.tap.model;

import java.sql.Date;
import java.sql.Timestamp;

public class User {
    private int userId;
    private String name;
    private String email;
    private long phoneNo;
    private String address;
    private String username;
    private String password;
    private String role;
    private Timestamp createDate;
    private Timestamp lastLoginDate;
    
    // Constructor with all fields except createDate and lastLoginDate
    public User(int userId, String name, String email, long phone, String address, String username, String password, String role) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.phoneNo = phone;
        this.address = address;
        this.username = username;
        this.password = password;
        this.role = role;
    }
    
    
    public User(String name, String email, long phoneNo, String address, String password, String role,
			Timestamp createDate, Timestamp lastLoginDate) {
		super();
		this.name = name;
		this.email = email;
		this.phoneNo = phoneNo;
		this.address = address;
		this.password = password;
		this.role = role;
		this.createDate = createDate;
		this.lastLoginDate = lastLoginDate;
	}


	// Constructor with all fields including createDate and lastLoginDate
    public User(int userId, String name, String email, long phone, String address, String username, String password, String role, Date createDate, Date lastLoginDate) {
        this(userId, name, email, phone, address, username, password, role); // Call the other constructor
        this.createDate = new Timestamp(createDate.getTime()); // Convert java.sql.Date to java.sql.Timestamp
        this.lastLoginDate = new Timestamp(lastLoginDate.getTime()); // Convert java.sql.Date to java.sql.Timestamp
    }

    // Getters and setters...





	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(int phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public Timestamp getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(Timestamp lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}




	public Timestamp getLastLoginTime() {
		return lastLoginDate;
	}




	public void setLastLoginTime(Timestamp lastLoginTime) {
		this.lastLoginDate = lastLoginTime;
	}
	
}
