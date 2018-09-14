package com.goal.tracking.intf;

import java.util.List;

import com.goal.tracking.beans.UserRole;
import com.goal.tracking.beans.Users;

public interface IUserService {

	public Users getUser(int userId);
	
	public List<Users> getAllUsers();
	
	public boolean addNewUser(Users user);
	
	public boolean updateUser(Users user);
	
	public boolean deleteUser(int userId);
	
	public List<UserRole> getAllRoles();
	
	public UserRole getRole(int roleId);
	
	public boolean addNewRole(UserRole role);

	public boolean updateRole(UserRole role);

	public boolean deleteRole(int roleId);
	
}
