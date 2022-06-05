package com.user.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.entities.User;
import com.user.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User createUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public List<User> findUsers() {
		return userRepository.findAll();
	}

	@Override
	public User findUserById(Integer userId) {
		return userRepository.findById(userId).get();
	}

	@Override
	public User updateUserById(Integer userId, User user) {
		User updatedUser = userRepository.findById(userId).get();
		updatedUser.setEmail(user.getEmail());
		updatedUser.setFirstname(user.getFirstname());
		updatedUser.setLastname(user.getLastname());
		updatedUser.setPhone(user.getPhone());
		
		return userRepository.save(updatedUser);
	}

	@Override
	public void deleteUserById(Integer userId) {
		userRepository.deleteById(userId);
	}
	
	@Override
	public User checkUserEmail(User user) {
		return userRepository.findByEmail(user.getEmail());
	}

	@Override
	public User checkUserPhone(User user) {
		return userRepository.findByPhone(user.getPhone());
	}
}
