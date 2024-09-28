package com.example.userscrud.service;

import java.util.List;

import com.example.userscrud.entity.User;

public interface UserService {
	
	List<User> getAllUsers();
	User createUser(User user);
	User getUser(String email);
	void deleteUser(String email);

	List<User> getUserByName(String name);
	void deleteUserByName(String name);

}
