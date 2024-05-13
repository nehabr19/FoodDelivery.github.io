package com.tap.dao;

import java.util.ArrayList;
import java.util.List;

import com.tap.model.User;

public interface UserDao {
	void addUser(User user);
	User getUser(String username);
	void updateUser(User user);
	void deleteUser(int userId);
	List<User> getAllUsers();
	ArrayList<User> getUserByUsername(String username);

}
