package com.user.services;

import java.util.List;

import com.user.entities.User;

public interface UserService {
	public User createUser(User user);
	public List<User> findUsers();
	public User findUserById(Integer userId);
	public User updateUserById(Integer userId, User user);
	public void deleteUserById(Integer userId);
	
	public User checkUserEmail(User user);
	public User checkUserPhone(User user);
}
