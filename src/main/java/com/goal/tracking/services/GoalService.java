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

import com.goal.tracking.controllers.TaskController;
import com.goal.tracking.entities.Goal;
import com.goal.tracking.exceptions.SystemException;
import com.goal.tracking.intf.IGoalService;
import com.goal.tracking.utils.StringUtils;

@Component
public class GoalService extends AbstractService implements IGoalService {

	private final Logger logger = LoggerFactory.getLogger(TaskController.class);
	
	public Goal getGoalById(int goalId) throws SystemException {
		logger.info("getGoalById(int) invoked...");
		Session session = getSessionFactory().openSession();
		Goal goal = getGoalById(goalId, session);
		
		if (goal == null) {
			throw new SystemException("No goals found for the given id", HttpStatus.NOT_FOUND);
		}
		
		return goal;
	}
	
	public Goal getGoalById(int goalId, Session session) throws SystemException {
		logger.info("getGoalById(int, session) invoked...");
		try {
			Query<Goal> anGoalQuery = session.createQuery("from Goal where id = :goalId", Goal.class);
			anGoalQuery.setParameter("goalId", goalId);
			return anGoalQuery.getSingleResult();
		} catch (HibernateException hibEx) {
			hibEx.printStackTrace();
			throw new SystemException("No goals found for the given id", HttpStatus.NOT_FOUND); 
		}
	}

	public List<Goal> getAllGoals() throws SystemException {
		logger.info("getAllGoals() invoked...");
		Session session = getSessionFactory().openSession();
		Query<Goal> allGoals = session.createQuery("from Goal g order by u.createdTime", Goal.class);
		return allGoals.getResultList();
	}

	public Goal addNewGoal(Goal goal) throws SystemException {
		logger.info("addNewGoal(Goal) invoked...");
		Session session = getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			session.save(goal);
			tx.commit();
			return goal;
		} catch (HibernateException hibEx) {
			tx.rollback();
			hibEx.printStackTrace();
			throw new SystemException("Given goal is not deleted", HttpStatus.NOT_MODIFIED);
		} finally {
			session.close();	
		}
	}
	
	public boolean checkIfValidGoal(Goal goal) throws SystemException {
		String msg = null;
		
		if (goal == null) {
			msg = "Goal object is empty";
		} else if (StringUtils.isEmpty(goal.getName())) {
			msg = "Goal is missing";
		} else if (StringUtils.isEmpty(goal.getDescription())) {
			msg = "Description is missing";
		}

		if (msg != null) {
			throw new SystemException(msg, HttpStatus.BAD_REQUEST);
		}
		
		return true;
	}
	
	public boolean isEmptyGoal(Goal goal) {
		if (goal == null) {
			return true;
		}
		
		return false;
	}

	public Goal updateGoal(Goal goal) throws SystemException {
		logger.info("updateGoal(Goal) invoked...");
		Session session = getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			session.update(goal);
			tx.commit();
			return goal;
		} catch (HibernateException hibEx) {
			tx.rollback();
			hibEx.printStackTrace();
			throw new SystemException("Given goal is not deleted", HttpStatus.NOT_MODIFIED);
		} finally {
			session.close();	
		}
	}

	public Goal deleteGoalById(int goalId) throws SystemException {
		logger.info("deleteGoalById(int) invoked...");
		Session session = getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			Goal goal = getGoalById(goalId, session);
			
			if (goal == null) {
				throw new SystemException("Given goal id not exists", HttpStatus.NOT_FOUND);
			}
			
			session.delete(goal);
			tx.commit();
			return goal;
		} catch (HibernateException hibEx) {
			tx.rollback();
			hibEx.printStackTrace();
			throw new SystemException("Given goal is not deleted", HttpStatus.NOT_MODIFIED);
		} finally {
			session.close();
		}
	}

}
