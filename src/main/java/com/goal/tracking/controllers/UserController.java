package com.goal.tracking.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.goal.tracking.beans.UserRole;
import com.goal.tracking.beans.Users;
import com.goal.tracking.intf.IUserService;

@RestController
//@RequestMapping("/users")
public class UserController {

	@Autowired
	private IUserService userService;
	
	@GetMapping("/users")
	public List<Users> getAllUsers() {
		return userService.getAllUsers();
	}
	
	@GetMapping("/users/{userId}")
	public Users getUser(@PathVariable int userId) {
		return userService.getUser(userId);
	}
	
	@PostMapping("/users")
	public boolean addNewUser(@RequestBody Users user) {
		return userService.addNewUser(user);
	}
	
	@PutMapping("/users")
	public boolean updateUser(@RequestBody Users user) {
		return userService.updateUser(user);
	}
	
	@DeleteMapping("/users/{userId}")
	public boolean deleteUser(@PathVariable int userId) {
		return userService.deleteUser(userId);
	}
	
	
	// APIs for managing user roles
	@GetMapping("/roles")
	public List<UserRole> getAllRoles() {
		return userService.getAllRoles();
	}
	
	@GetMapping("/roles/{roleId}")
	public UserRole getRole(@PathVariable int roleId) {
		return userService.getRole(roleId);
	}
	
	@PostMapping("/roles")
	public boolean addNewRole(@RequestBody UserRole role) {
		return userService.addNewRole(role);
	}
	
	@PutMapping("/roles")
	public boolean updateRole(@RequestBody UserRole role) {
		return userService.updateRole(role);
	}
	
	@DeleteMapping("/roles/{roleId}")
	public boolean deleteRole(@PathVariable int roleId) {
		return userService.deleteRole(roleId);
	}
}
