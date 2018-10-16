package com.goal.tracking.services;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.goal.tracking.entities.Goal;
import com.goal.tracking.entities.Users;
import com.goal.tracking.exceptions.SystemException;
import com.goal.tracking.intf.IUserService;
import com.goal.tracking.utils.StringUtils;

@Component
public class UserService extends AbstractService implements IUserService {

	private final Logger logger = LoggerFactory.getLogger(UserService.class);
	
	public Users getUserById(int userId) throws SystemException {
		logger.info("getUser(int) invoked with userId = " + userId);
		Session session = getSessionFactory().openSession();
		Users user = getUserById(userId, session);
		
		if (user == null) {
			session.close();
			throw new SystemException("No user data available with the given id", HttpStatus.NOT_FOUND);
		}
		
		return user;
	}
	
	private Users getUserById(int userId, Session session) throws SystemException {
		logger.info("getUser(int, session) invoked with userId = " + userId);
		try {
			Query<Users> anUserQuery = session.createQuery("from Users where userId = :userId", Users.class);
			anUserQuery.setParameter("userId", userId);
			return anUserQuery.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean isEmptyUser(Users user) {
		if (user == null) {
			return true;
		}
		
		return false;
	}
	
	public boolean checkIfValidUser(Users user) throws SystemException {
		String msg = null;
		
		if (user == null) {
			msg = "User object is empty";
		} else if (StringUtils.isEmpty(user.getEmailId())) {
			msg = "Email Id is missing";
		} else if (StringUtils.isEmpty(user.getFirstName())) {
			msg = "Firstname is missing";
		} else if(StringUtils.isEmpty(user.getLastName())) {
			msg = "Lastname is missing";
		} else if (StringUtils.isEmpty(user.getUserRole().getRoleName())) {
			msg = "User role is missing";
		}

		if (msg != null) {
			throw new SystemException(msg, HttpStatus.BAD_REQUEST);
		}
		
		return true;
	}
	
	public List<Users> getAllUsers() throws SystemException {
		logger.info("getAllUsers() invoked");
		Session session = getSessionFactory().openSession();
		try {
			Query<Users> allUsers = session.createQuery("from Users u order by u.firstName", Users.class);
			List<Users> usersList = allUsers.getResultList();
			
			if (usersList.isEmpty()) {
				throw new SystemException("No user data available", HttpStatus.NOT_FOUND);
			}
			
			return usersList;
		} catch (Exception e) {
			e.printStackTrace();
			throw new SystemException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		} finally {
			session.close();
		}
	}

	public Users addNewUser(Users user) throws SystemException {
		logger.info("addNewUser(user) invoked for username: " + user.getEmailId());
		checkIfValidUser(user);
		
		Session session = getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		
		try {
			session.save(user);
			tx.commit();
			return user;
		} catch (HibernateException hibEx) {
			tx.rollback();
			hibEx.printStackTrace();
			throw new SystemException(hibEx.getMessage(), HttpStatus.NOT_MODIFIED);
		} finally {
			session.close();	
		}
	}

	public Users updateUser(Users user) throws SystemException {
		logger.info("updateUser(user) invoked for username: " + user.getEmailId());
		if (getUserById(user.getUserId()) == null) {
			throw new SystemException("User is not available to modify", HttpStatus.NOT_FOUND);
		}
		
		Session session = getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			session.update(user);
			tx.commit();
			return user;
		} catch (HibernateException hibEx) {
			tx.rollback();
			hibEx.printStackTrace();
			throw new SystemException(hibEx.getMessage(), HttpStatus.NOT_MODIFIED);
		} finally {
			session.close();	
		}
	}

	public Users deleteUserById(int userId) throws SystemException {
		logger.info("deleteUser(int) invoked for userId: " + userId);
		Session session = getSessionFactory().openSession();
		Users user = getUserById(userId, session);
		
		if (user == null) {
			throw new SystemException("User is not available to delete", HttpStatus.NOT_FOUND);
		}

		Transaction tx = session.beginTransaction();
		try {
			user = getUserById(userId, session);
			session.delete(user);
			tx.commit();
			return user;
		} catch (HibernateException hibEx) {
			tx.rollback();
			hibEx.printStackTrace();
			throw new SystemException(hibEx.getMessage(), HttpStatus.NOT_MODIFIED);
		} finally {
			session.close();
		}
	}

	public List<Users> getUsersForRoleIds(int[] roleIds) {
		logger.info("getUsersForRoleIds(int[]) invoked for roleIds: " + roleIds);
		Session session = getSessionFactory().openSession();
		try {
			Query<Users> anUserRoleQuery = session.createQuery("from Users where roleId IN (:roleIds)", Users.class);
			anUserRoleQuery.setParameter("roleIds", roleIds);
			return anUserRoleQuery.list();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			session.close();
		}
	}
	
	public List<Users> getUsersForRoleNames(String[] roleNames) {
		logger.info("getUsersForRoleNames(String[]) invoked for roleNames: " + roleNames);
		Session session = getSessionFactory().openSession();
		try {
			Query<Users> anUserRoleQuery = session.createQuery("from Users where roleId IN (select roleId from Roles where roleName IN (:roleNames))", Users.class);
			anUserRoleQuery.setParameter("roleIds", roleNames);
			return anUserRoleQuery.list();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			session.close();
		}
	}

}
