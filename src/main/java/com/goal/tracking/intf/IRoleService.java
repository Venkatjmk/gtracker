package com.goal.tracking.intf;

import java.util.List;

import com.goal.tracking.entities.UserRole;
import com.goal.tracking.exceptions.SystemException;

public interface IRoleService {

	public List<UserRole> getAllRoles() throws SystemException;
	
	public UserRole getRoleById(int roleId) throws SystemException;
	
	public UserRole getRoleByName(String roleName) throws SystemException;
	
	public UserRole addNewRole(String role) throws SystemException;

	public UserRole updateRole(String oldRoleName, String newRoleName) throws SystemException;
	
	public UserRole updateRole(UserRole role) throws SystemException;

	public UserRole deleteRoleById(int roleId) throws SystemException;
	
	public boolean isValidRole(String roleName) throws SystemException;
	
}
