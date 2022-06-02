package com.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.user.Error;
import com.user.entities.User;
import com.user.services.UserService;

@RestController
public class UserController{
	private Error err = new Error();
	@Autowired
	private UserService userService;
	
	@PostMapping("/users")
	public Error userCreate(@RequestBody User user) {
		if(userService.checkUserEmail(user)!=null) {err.setMessage("Email Already Exist");err.setStatus("Bad Request");}
		if(userService.checkUserPhone(user)!=null) {err.setMessage("Phone Number Already Exist");err.setStatus("Bad Request");}
		if (userService.checkUserEmail(user)==null && userService.checkUserPhone(user)==null) {
			userService.createUser(user);
			err.setMessage("User Created");
			err.setStatus("OK");
		}
		return err;
	}
	
	@PostMapping("/users/emailAndPhone")
	public User checkUser(@RequestBody User user) {
		return userService.checkUserEmail(user);
	}
	
	@GetMapping("/users")
	public List<User> getUsers() {
		return userService.findUsers();
	}
	@GetMapping("/users/{id}")
	public User getUserById(@PathVariable("id") Integer userId) {
		return userService.findUserById(userId);
	}
	@PutMapping("/users/{id}")
	public User updateUserById(@PathVariable("id") Integer userId, @RequestBody User user) {
		return userService.updateUserById(userId, user);
	}
	@DeleteMapping("/users/{id}")
	public String deleteUserById(@PathVariable("id") Integer userId) {
		userService.deleteUserById(userId);
		return "Delete Successfully";
	}
	
}
