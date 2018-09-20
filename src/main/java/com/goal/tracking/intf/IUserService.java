package com.goal.tracking.intf;

import java.util.List;

import com.goal.tracking.entities.Users;
import com.goal.tracking.exceptions.SystemException;

public interface IUserService {

	// users API
	public Users getUserById(int userId) throws SystemException;
	
	public List<Users> getAllUsers() throws SystemException;
	
	public Users addNewUser(Users user) throws SystemException;
	
	public Users updateUser(Users user) throws SystemException;
	
	public Users deleteUserById(int userId) throws SystemException;
	
	public boolean checkIfValidUser(Users user) throws SystemException;
	
	public boolean isEmptyUser(Users user) throws SystemException;
	
}
