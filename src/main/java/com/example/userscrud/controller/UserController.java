package com.example.userscrud.controller;

import    java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.userscrud.entity.Post;
import com.example.userscrud.entity.User;
import com.example.userscrud.repository.UserRepository;
import com.example.userscrud.service.PostService;
import com.example.userscrud.service.UserService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/users")
public class UserController {
	
	private UserService userService;
	private PostService postService;
	
	public UserController(UserService userService, PostService postService) {
		this.userService = userService;
		this.postService = postService;
	}
	
	
	@GetMapping("")
	public List<User> getAllUsers(){
		return userService.getAllUsers();
	}
	
	@GetMapping("/{email}")
	public User retrieveUser(@PathVariable String email) {
		System.out.println(email);
		return userService.getUser(email);
	}
	
	@DeleteMapping("/{email}")
	public void deleteUser(@PathVariable String email) {
		userService.deleteUser(email);
	}


	//Delete user by name
	@DeleteMapping("/deleteByName/{name}")
	public ResponseEntity<?> deleteUserByName(@PathVariable String name) {
		List<User> users = userService.getUserByName(name);

		if (users.size() > 1) {
			return new ResponseEntity<>("Multiple users found with the name " + name + "!", HttpStatus.BAD_REQUEST);
		} else if (users.isEmpty()) {
			return new ResponseEntity<>("User not found with the name " + name, HttpStatus.NOT_FOUND);
		} else {
			System.out.println("username: " + name);
			userService.deleteUserByName(name);
			return new ResponseEntity<>("User with name " + name + " was deleted", HttpStatus.OK);
		}
	}
	
	@PostMapping("/create")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user){
		System.out.println("Creating new user 2" + user.getName());
		User savedUser = userService.createUser(user);
		System.out.println(user.getEmail());
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{email}")
				.buildAndExpand(savedUser.getEmail()).toUri();
		// returning URI
		
		return ResponseEntity.created(location).build();
	}
	
	
	// To retrieve posts of User
	@GetMapping("/{email}/posts")
	public List<Post> retrievePosts(@PathVariable String email) {
		User user = userService.getUser(email);
		return user.getPosts();
	}
	
	@PostMapping("/{email}/posts")
	public ResponseEntity<Post> createPost(@RequestBody Post post, @PathVariable String email) {
		User postuser = userService.getUser(email);
		post.setUser(postuser);
		
		Post savedPost = postService.createPost(post);
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest().path("")
				.buildAndExpand(savedPost.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	

}
