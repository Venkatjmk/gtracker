package com.goal.tracking.services;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import com.goal.tracking.beans.Goal;
import com.goal.tracking.beans.Users;
import com.goal.tracking.intf.IGoalService;

@Component
public class GoalService extends AbstractService implements IGoalService {

	public Goal getGoal(int goalId) {
		return getGoal(goalId, getSessionFactory().openSession());
	}
	
	public Goal getGoal(int goalId, Session session) {
		Query<Goal> anGoalQuery = session.createQuery("from Goal where id = :goalId", Goal.class);
		anGoalQuery.setParameter("goalId", goalId);
		return anGoalQuery.getSingleResult();
	}

	public List<Goal> getAllGoals() {
		Session session = getSessionFactory().openSession();
		Query<Goal> allGoals = session.createQuery("from Goal g order by u.createdTime", Goal.class);
		return allGoals.getResultList();
	}

	public boolean addNewGoal(Goal goal) {
		Session session = getSessionFactory().openSession();
		try {
			session.save(goal);
			return true;
		} catch (HibernateException hibEx) {
			hibEx.printStackTrace();
			return false;
		} finally {
			session.close();	
		}
	}

	public boolean updateGoal(Goal goal) {
		Session session = getSessionFactory().openSession();
		try {
			session.saveOrUpdate(goal);
			return true;
		} catch (HibernateException hibEx) {
			hibEx.printStackTrace();
			return false;
		} finally {
			session.close();	
		}
	}

	public boolean deleteGoal(int goalId) {
		Session session = getSessionFactory().openSession();
		try {
			Goal goal = getGoal(goalId, session);
			if (goal != null) {
				session.delete(goal);
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
