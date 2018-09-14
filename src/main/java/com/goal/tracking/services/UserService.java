package com.goal.tracking.services;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import com.goal.tracking.beans.UserRole;
import com.goal.tracking.beans.Users;
import com.goal.tracking.intf.IUserService;

@Component
public class UserService extends AbstractService implements IUserService {

	public Users getUser(int userId) {
		return getUser(userId, getSessionFactory().openSession());
	}
	
	public Users getUser(int userId, Session session) {
		Query<Users> anUserQuery = session.createQuery("from Users where userId = :userId", Users.class);
		anUserQuery.setParameter("userId", userId);
		return anUserQuery.getSingleResult();
	}

	public List<Users> getAllUsers() {
		Session session = getSessionFactory().openSession();
		Query<Users> allUsers = session.createQuery("from Users u order by u.firstName", Users.class);
		return allUsers.getResultList();
	}

	public boolean addNewUser(Users user) {
		Session session = getSessionFactory().openSession();
		try {
			session.save(user);
			return true;
		} catch (HibernateException hibEx) {
			hibEx.printStackTrace();
			return false;
		} finally {
			session.close();	
		}
	}

	public boolean updateUser(Users user) {
		Session session = getSessionFactory().openSession();
		try {
			session.saveOrUpdate(user);
			return true;
		} catch (HibernateException hibEx) {
			hibEx.printStackTrace();
			return false;
		} finally {
			session.close();	
		}
	}

	public boolean deleteUser(int userId) {
		Session session = getSessionFactory().openSession();
		try {
			Users user = getUser(userId, session);
			if (user != null) {
				session.delete(user);
				return true;
			}
			
			return false;
		} catch (HibernateException hibEx) {
			hibEx.printStackTrace();
			return false;
		} finally {
			session.close();
		}
	}

	public List<UserRole> getAllRoles() {
		Session session = getSessionFactory().openSession();
		Query<UserRole> allRoles = session.createQuery("from UserRole u order by u.roleName", UserRole.class);
		return allRoles.getResultList();
	}

	public UserRole getRole(int roleId) {
		return getRole(roleId, getSessionFactory().openSession());
	}
	
	public UserRole getRole(int roleId, Session session) {
		Query<UserRole> anUserRoleQuery = session.createQuery("from UserRole where roleId = :roleId", UserRole.class);
		anUserRoleQuery.setParameter("roleId", roleId);
		return anUserRoleQuery.getSingleResult();
	}

	public boolean addNewRole(UserRole role) {
		Session session = getSessionFactory().openSession();
		try {
			session.save(role);
			return true;
		} catch (HibernateException hibEx) {
			hibEx.printStackTrace();
			return false;
		} finally {
			session.close();	
		}
	}

	public boolean updateRole(UserRole role) {
		Session session = getSessionFactory().openSession();
		try {
			session.saveOrUpdate(role);
			return true;
		} catch (HibernateException hibEx) {
			hibEx.printStackTrace();
			return false;
		} finally {
			session.close();	
		}
	}

	public boolean deleteRole(int roleId) {
		Session session = getSessionFactory().openSession();
		try {
			UserRole userRole = getRole(roleId, session);
			if (userRole != null) {
				session.delete(userRole);
				return true;
			}
			
			return false;
		} catch (HibernateException hibEx) {
			hibEx.printStackTrace();
			return false;
		} finally {
			session.close();
		}
	}

}
