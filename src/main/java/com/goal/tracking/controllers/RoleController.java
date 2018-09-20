package com.goal.tracking.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.goal.tracking.entities.UserRole;
import com.goal.tracking.exceptions.SystemException;
import com.goal.tracking.intf.IRoleService;

@RestController
@RequestMapping("/roles")
public class RoleController {
	
	private final Logger logger = LoggerFactory.getLogger(RoleController.class);
	
	@Autowired
	private IRoleService roleService;
	
	@GetMapping
	public List<UserRole> getAllRoles() throws SystemException {
		logger.info("getAllGoals() invoked ...");
		return roleService.getAllRoles();
	}
	
	@GetMapping("/roleId/{roleId}")
	public UserRole getRoleById(@PathVariable int roleId) throws SystemException {
		logger.info("getRoleById(int) invoked ...");
		return roleService.getRoleById(roleId);
	}
	
	@GetMapping("/roleName/{roleName}")
	public UserRole getRoleByName(@PathVariable String roleName) throws SystemException {
		logger.info("getRoleByName(String) invoked ...");
		return roleService.getRoleByName(roleName);
	}
	
	@PostMapping("/{roleName}")
	public UserRole addNewRole(@PathVariable String roleName) throws SystemException {
		logger.info("addNewRole(String) invoked ...");
		return roleService.addNewRole(roleName);
	}
	
	@PutMapping
	public UserRole updateRole(@RequestBody UserRole role) throws SystemException {
		logger.info("updateRole(UserRole) invoked ...");
		logger.info("new role name: " + role.getRoleName());
		return roleService.updateRole(role);
	}
	
	@PutMapping("/{oldRoleName}/{newRoleName}")
	public UserRole updateRole(@PathVariable String oldRoleName, @PathVariable String newRoleName) throws SystemException {
		logger.info("updateRole(String, String) invoked ...");
		logger.info("oldRoleName: " + oldRoleName);
		logger.info("newRoleName: " + newRoleName);
		
		if (StringUtils.isEmpty(oldRoleName)) {
			throw new SystemException("Existing role name is empty", HttpStatus.PRECONDITION_FAILED);
		}
		
		if (StringUtils.isEmpty(newRoleName)) {
			throw new SystemException("New role name is empty", HttpStatus.PRECONDITION_FAILED);
		}
		
		return roleService.updateRole(oldRoleName, newRoleName);
	}
	
	@DeleteMapping("/{roleId}")
	public UserRole deleteRoleById(@PathVariable int roleId) throws SystemException {
		logger.info("deleteRoleById(int) invoked ...");
		return roleService.deleteRoleById(roleId);
	}

}
