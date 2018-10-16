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
import org.springframework.util.StringUtils;

import com.goal.tracking.entities.Goal;
import com.goal.tracking.entities.UserRole;
import com.goal.tracking.entities.Users;
import com.goal.tracking.exceptions.SystemException;
import com.goal.tracking.intf.IRoleService;

@Component
public class RoleService extends AbstractService implements IRoleService {

	private final Logger logger = LoggerFactory.getLogger(RoleService.class);
	
	public List<UserRole> getAllRoles() throws SystemException {
		logger.info("getAllRoles() invoked...");
		try {
			Session session = getSessionFactory().openSession();
			Query<UserRole> allRoles = session.createQuery("from Roles u order by u.roleName", UserRole.class);
			return allRoles.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public UserRole getRoleById(int roleId) throws SystemException {
		logger.info("getRoleById(int) invoked...");
		
		Session session = getSessionFactory().openSession();
		UserRole role = getRoleById(roleId, session);
		
		if (role == null) {
			session.close();
			throw new SystemException("No role exists for given id", HttpStatus.NOT_FOUND);
		}
		
		return role;
	}
	
	public UserRole getRoleById(int roleId, Session session) throws SystemException {
		logger.info("getRoleById(int, Session) invoked...");
		
		try {
			Query<UserRole> anUserRoleQuery = session.createQuery("from Roles where roleId = :roleId", UserRole.class);
			anUserRoleQuery.setParameter("roleId", roleId);
			return anUserRoleQuery.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public UserRole getRoleByName(String roleName) throws SystemException {
		logger.info("getRoleByName(String) invoked...");
		
		Session session = getSessionFactory().openSession();
		UserRole role = getRoleByName(roleName, session);
		
		if(role == null) {
			session.close();
			throw new SystemException("No role exists with given rolename", HttpStatus.NOT_FOUND);
		}
		
		return role;
	}
	
	public UserRole getRoleByName(String roleName, Session session) throws SystemException {
		logger.info("getRoleByName(String, Session) invoked...");
		
		try {
			Query<UserRole> anUserRoleQuery = session.createQuery("from Roles where roleName = :roleName", UserRole.class);
			anUserRoleQuery.setParameter("roleName", roleName);
			return anUserRoleQuery.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public UserRole addNewRole(String roleName) throws SystemException {
		logger.info("addNewRole(String) invoked...");
		
		Session session = getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			UserRole role = new UserRole(roleName);
			session.save(role);
			tx.commit();
			return role;
		} catch (HibernateException hibEx) {
			tx.rollback();
			hibEx.printStackTrace();
			throw new SystemException(hibEx.getMessage(), HttpStatus.NOT_MODIFIED);
		} finally {
			session.close();	
		}
	}
	
	public UserRole updateRole(String oldRoleName, String newRoleName) throws SystemException {
		logger.info("updateRole(String, String) invoked...");
		
		Session session = getSessionFactory().openSession();
		UserRole existingRole = getRoleByName(oldRoleName, session);
		
		if (existingRole == null) {
			throw new SystemException("Given role name not exists", HttpStatus.NOT_FOUND);
		}

		Transaction tx = session.beginTransaction();
		try {
			existingRole.setRoleName(newRoleName);
			session.update(existingRole);
			tx.commit();
			return existingRole;
		} catch (HibernateException hibEx) {
			tx.rollback();
			hibEx.printStackTrace();
			throw new SystemException(hibEx.getMessage(), HttpStatus.NOT_MODIFIED);
		} finally {
			session.close();
		}
	}

	public UserRole updateRole(UserRole role) throws SystemException {
		logger.info("updateRole(UserRole) invoked...");
		
		Session session = getSessionFactory().openSession();
		UserRole existingRole = getRoleById(role.getRoleId(), session);
		
		if (existingRole == null) {
			throw new SystemException("Role with given id is exists", HttpStatus.NOT_FOUND);
		}
		
		logger.info("venki: new role name: " + role.getRoleName());
		logger.info("venki: old role name: " + existingRole.getRoleName());
		
		Transaction tx = session.beginTransaction();
		try {
			existingRole.setRoleName(role.getRoleName());
			session.update(existingRole);
			tx.commit();
			return role;
		} catch (HibernateException hibEx) {
			tx.rollback();
			hibEx.printStackTrace();
			throw new SystemException(hibEx.getMessage(), HttpStatus.NOT_MODIFIED);
		} finally {
			session.close();	
		}
	}

	public UserRole deleteRoleById(int roleId) throws SystemException {
		logger.info("deleteRoleById(int) invoked...");
		
		Session session = getSessionFactory().openSession();
		UserRole userRole = getRoleById(roleId, session);
		
		if (userRole == null) {
			throw new SystemException("No role found for the given id", HttpStatus.NOT_FOUND);
		}

		Transaction tx = session.beginTransaction();
		try {
			session.delete(userRole);
			tx.commit();
			return userRole;
		} catch (HibernateException hibEx) {
			tx.rollback();
			hibEx.printStackTrace();
			throw new SystemException(hibEx.getMessage(), HttpStatus.NOT_MODIFIED);
		} finally {
			session.close();
		}
	}

	@Override
	public boolean isValidRole(String roleName) {
		return !StringUtils.isEmpty(roleName);
	}

}
