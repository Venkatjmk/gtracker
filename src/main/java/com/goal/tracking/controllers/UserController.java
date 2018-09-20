package com.goal.tracking.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.goal.tracking.entities.Users;
import com.goal.tracking.exceptions.SystemException;
import com.goal.tracking.intf.IUserService;

@RestController
@RequestMapping("/users")
public class UserController {

	private final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired private IUserService userService;
	
	@GetMapping
	public List<Users> getAllUsers() throws SystemException {
		logger.info("getAllUsers() is invoked...");
		return userService.getAllUsers();
	}
	
	@GetMapping("/{userId}")
	public Users getUserById(@PathVariable int userId) throws SystemException {
		logger.info("getUserById(int) is invoked...");
		
		if (userId <= 0) {
			throw new SystemException("Not an valid user id", HttpStatus.BAD_REQUEST);
		}
		
		return userService.getUserById(userId);
	}
	
	@PostMapping
	public Users addNewUser(@RequestBody Users user) throws SystemException {
		logger.info("addNewUser(Users) is invoked...");
		return userService.addNewUser(user);
	}
	
	@PutMapping
	public Users updateUser(@RequestBody Users user) throws SystemException {
		logger.info("updateUser(Users) is invoked...");
		return userService.updateUser(user);
	}
	
	@DeleteMapping("/{userId}")
	public Users deleteUserById(@PathVariable int userId) throws SystemException {
		logger.info("deleteUserById(int) is invoked...");
		return userService.deleteUserById(userId);
	}
	
}
