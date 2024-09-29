package com.example.userscrud.service;

import java.util.List;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import com.example.userscrud.entity.User;
import com.example.userscrud.exception.UserNotFoundException;
import com.example.userscrud.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
	
	
	private UserRepository userRepository;

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public User createUser(User user) {
		System.out.println(user.getEmail());
		return userRepository.save(user);
	}

	@Override
	public User getUser(String email) {
		User user = userRepository.findByEmailAddress(email);
		if(user == null) {
			throw new UserNotFoundException("User with email : "+email+" doesn't exist.");
		}
		
		return user;
	}

	@Override
	public void deleteUser(String email) {
		User user = userRepository.findByEmailAddress(email);
		userRepository.delete(user);

	}

	@Override
	public List<User> getUserByName(String name) {
		return userRepository.findByName(name);
	}

	@Override
	@Transactional
	public void deleteUserByName(String name) {
		userRepository.deleteByName(name);
	}



}
