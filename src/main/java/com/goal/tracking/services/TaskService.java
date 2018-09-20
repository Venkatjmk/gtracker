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

}
