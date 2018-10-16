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
import com.goal.tracking.entities.Task;
import com.goal.tracking.exceptions.SystemException;
import com.goal.tracking.intf.ITaskService;
import com.goal.tracking.utils.StringUtils;

@Component
public class TaskService extends AbstractService implements ITaskService {

	private final Logger logger = LoggerFactory.getLogger(TaskService.class);
	
	public Task getTaskById(int taskId) throws SystemException {
		logger.info("getTask(int) invoked with taskId = " + taskId);
		Session session = getSessionFactory().openSession();
		Task task = getTaskById(taskId, session);
		
		if (task == null) {
			session.close();
			throw new SystemException("No task data available with the given id", HttpStatus.NOT_FOUND);
		}
		
		return task;
	}
	
	private Task getTaskById(int taskId, Session session) throws SystemException {
		logger.info("getTask(int, session) invoked with taskId = " + taskId);
		try {
			Query<Task> anTaskQuery = session.createQuery("from Task where taskId = :taskId", Task.class);
			anTaskQuery.setParameter("taskId", taskId);
			Task task = anTaskQuery.getSingleResult();
			return task;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean isEmptyTask(Task task) {
		if (task == null) {
			return true;
		}
		
		return false;
	}
	
	public boolean checkIfValidTask(Task task) throws SystemException {
		String msg = null;
		
		if (task == null) {
			msg = "Task object is empty";
		} else if (StringUtils.isEmpty(task.getTaskDesc())) {
			msg = "Description is missing";
		} else if (StringUtils.isEmpty(task.getSchedule())) {
			msg = "Schedule is missing";
		} else if (StringUtils.isEmpty(task.getTaskCategory().getCategoryName())) {
			msg = "Task category is missing";
		}

		if (msg != null) {
			throw new SystemException(msg, HttpStatus.BAD_REQUEST);
		}
		
		return true;
	}
	
	public List<Task> getAllTasks() throws SystemException {
		logger.info("getAllTasks() invoked...");
		
		Session session = getSessionFactory().openSession();
		try {
			Query<Task> allTask = session.createQuery("from Task t order by t.createdTime", Task.class);
			List<Task> tasksList = allTask.getResultList();
			
			if (tasksList.isEmpty()) {
				throw new SystemException("No task data available", HttpStatus.NOT_FOUND);
			}
			
			return tasksList;
		} catch (Exception e) {
			e.printStackTrace();
			throw new SystemException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		} finally {
			session.close();
		}
	}

	public Task addNewTask(Task task) throws SystemException {
		logger.info("addNewTask(Task) invoked for task: " + task.getTaskDesc());
		
		checkIfValidTask(task);
		
		Session session = getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		
		try {
			session.save(task);
			tx.commit();
			return task;
		} catch (HibernateException hibEx) {
			tx.rollback();
			hibEx.printStackTrace();
			throw new SystemException(hibEx.getMessage(), HttpStatus.NOT_MODIFIED);
		} finally {
			session.close();	
		}
	}

	public Task updateTask(Task task) throws SystemException {
		logger.info("updateTask(Task) invoked for taskId: " + task.getTaskId());
		
		if (getTaskById(task.getTaskId()) == null) {
			throw new SystemException("Task is not available to modify", HttpStatus.NOT_FOUND);
		}
		
		Session session = getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			session.update(task);
			tx.commit();
			return task;
		} catch (HibernateException hibEx) {
			tx.rollback();
			hibEx.printStackTrace();
			throw new SystemException(hibEx.getMessage(), HttpStatus.NOT_MODIFIED);
		} finally {
			session.close();	
		}
	}

	public Task deleteTaskById(int taskId) throws SystemException {
		logger.info("deleteTaskById(Task) invoked for roleNames: " + taskId);
		
		Session session = getSessionFactory().openSession();
		Task task = getTaskById(taskId, session);
		
		if (task == null) {
			throw new SystemException("Task is not available to delete", HttpStatus.NOT_FOUND);
		}

		Transaction tx = session.beginTransaction();
		try {
			task = getTaskById(taskId, session);
			session.delete(task);
			tx.commit();
			return task;
		} catch (HibernateException hibEx) {
			tx.rollback();
			hibEx.printStackTrace();
			throw new SystemException(hibEx.getMessage(), HttpStatus.NOT_MODIFIED);
		} finally {
			session.close();
		}
	}
	
	public List<Task> getTasksForCategories(int[] categoryIds) {
		logger.info("getTasksForCategories(int[]) invoked for categoryIds: " + categoryIds);
		
		Session session = getSessionFactory().openSession();
		try {
			Query<Task> query = session.createQuery("From Tasks where categoryId IN (:categoryIds)", Task.class);
			query.setParameter("categoryIds", categoryIds);
			return query.list();
		} catch (HibernateException hibEx) {
			hibEx.printStackTrace();
			throw new SystemException("Unable to fetch Tasks associated for given categories", HttpStatus.INTERNAL_SERVER_ERROR);
		} finally {
			session.close();
		}
	}
	
	public List<Task> getTasksForCategories(String[] categoryNames) {
		logger.info("getTasksForCategories(String[]) invoked for categoryNames: " + categoryNames);
		
		Session session = getSessionFactory().openSession();
		try {
			Query<Task> query = session.createQuery("", Task.class);
			query.setParameter("categoryNames", categoryNames);
			return query.list();
		} catch (HibernateException hibEx) {
			hibEx.printStackTrace();
			throw new SystemException("Unable to fetch Tasks associated for given categories", HttpStatus.INTERNAL_SERVER_ERROR);
		} finally {
			session.close();
		}
	}

	public List<Task> getTasksForUsers(int[] userIds) {
		logger.info("getTasksForUsers(int[]) invoked for userIds: " + userIds);
		
		Session session = getSessionFactory().openSession();
		try {
			Query<Task> query = session.createQuery("From Tasks where userId IN (:userIds)", Task.class);
			query.setParameter("userIds", userIds);
			return query.list();
		} catch (HibernateException hibEx) {
			hibEx.printStackTrace();
			throw new SystemException("Unable to fetch Tasks associated for given users", HttpStatus.INTERNAL_SERVER_ERROR);
		} finally {
			session.close();
		}
	}
	
	public List<Task> getTasksForUsers(String[] userNames) {
		logger.info("getTasksForUsers(String[]) invoked for userNames: " + userNames);
		
		Session session = getSessionFactory().openSession();
		try {
			Query<Task> query = session.createQuery("From Tasks where userId IN (select userId from Users where emailId IN (:userNames))", Task.class);
			query.setParameter("userNames", userNames);
			return query.list();
		} catch (HibernateException hibEx) {
			hibEx.printStackTrace();
			throw new SystemException("Unable to fetch Tasks associated for given users", HttpStatus.INTERNAL_SERVER_ERROR);
		} finally {
			session.close();
		}
	}
	
	public List<Task> getTasksForGoals(int[] goalIds) {
		logger.info("getTasksForGoals(int[]) invoked for goalIds: " + goalIds);
		
		Session session = getSessionFactory().openSession();
		try {
			Query<Task> query = session.createQuery("From Tasks where goalId IN (:goalIds)", Task.class);
			query.setParameter("goalIds", goalIds);
			return query.list();
		} catch (HibernateException hibEx) {
			hibEx.printStackTrace();
			throw new SystemException("Unable to fetch Tasks associated for given users", HttpStatus.INTERNAL_SERVER_ERROR);
		} finally {
			session.close();
		}
	}

}
