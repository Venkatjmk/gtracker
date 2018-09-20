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

import com.goal.tracking.entities.TaskCategory;
import com.goal.tracking.exceptions.SystemException;
import com.goal.tracking.intf.ICategoryService;

@Component
public class CategoryService extends AbstractService implements ICategoryService {

	private final Logger logger = LoggerFactory.getLogger(CategoryService.class);
	
	public List<TaskCategory> getAllCategories() throws SystemException {
		logger.info("getAllCategories() invoked...");
		try {
			Session session = getSessionFactory().openSession();
			Query<TaskCategory> allCategorys = session.createQuery("from Categories u order by u.categoryName", TaskCategory.class);
			
			List<TaskCategory> categoriesList = allCategorys.getResultList();
			
			if (categoriesList.isEmpty()) {
				throw new SystemException("", HttpStatus.NOT_FOUND);
			}
			
			return categoriesList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public TaskCategory getCategoryById(int CategoryId) throws SystemException {
		logger.info("getCategoryById(int) invoked...");
		Session session = getSessionFactory().openSession();
		TaskCategory Category = getCategoryById(CategoryId, session);
		
		if (Category == null) {
			session.close();
			throw new SystemException("No Category exists for given id", HttpStatus.NOT_FOUND);
		}
		
		return Category;
	}
	
	public TaskCategory getCategoryById(int CategoryId, Session session) throws SystemException {
		logger.info("getCategoryById(int, session) invoked...");
		try {
			Query<TaskCategory> anTaskCategoryQuery = session.createQuery("from Categories where CategoryId = :CategoryId", TaskCategory.class);
			anTaskCategoryQuery.setParameter("CategoryId", CategoryId);
			return anTaskCategoryQuery.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public TaskCategory getCategoryByName(String CategoryName) throws SystemException {
		logger.info("getCategoryByName(String) invoked...");
		Session session = getSessionFactory().openSession();
		TaskCategory Category = getCategoryByName(CategoryName, session);
		
		if(Category == null) {
			session.close();
			throw new SystemException("No Category exists with given Categoryname", HttpStatus.NOT_FOUND);
		}
		
		return Category;
	}
	
	public TaskCategory getCategoryByName(String CategoryName, Session session) throws SystemException {
		logger.info("getCategoryByName(String, Session) invoked...");
		try {
			Query<TaskCategory> anTaskCategoryQuery = session.createQuery("from Categories where CategoryName = :CategoryName", TaskCategory.class);
			anTaskCategoryQuery.setParameter("CategoryName", CategoryName);
			return anTaskCategoryQuery.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public TaskCategory addNewCategory(String CategoryName) throws SystemException {
		logger.info("addNewCategory(String) invoked...");
		
		Session session = getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			TaskCategory Category = new TaskCategory(CategoryName);
			session.save(Category);
			tx.commit();
			return Category;
		} catch (HibernateException hibEx) {
			tx.rollback();
			hibEx.printStackTrace();
			throw new SystemException(hibEx.getMessage(), HttpStatus.NOT_MODIFIED);
		} finally {
			session.close();	
		}
	}
	
	public TaskCategory updateCategory(String oldCategoryName, String newCategoryName) throws SystemException {
		logger.info("updateCategory(String, String) invoked...");
		Session session = getSessionFactory().openSession();
		TaskCategory existingCategory = getCategoryByName(oldCategoryName, session);
		
		if (existingCategory == null) {
			throw new SystemException("Given Category name not exists", HttpStatus.NOT_FOUND);
		}

		Transaction tx = session.beginTransaction();
		try {
			existingCategory.setCategoryName(newCategoryName);
			session.update(existingCategory);
			tx.commit();
			return existingCategory;
		} catch (HibernateException hibEx) {
			tx.rollback();
			hibEx.printStackTrace();
			throw new SystemException(hibEx.getMessage(), HttpStatus.NOT_MODIFIED);
		} finally {
			session.close();
		}
	}

	public TaskCategory updateCategory(TaskCategory Category) throws SystemException {
		logger.info("updateCategory(TaskCategory) invoked...");
		Session session = getSessionFactory().openSession();
//		TaskCategory existingCategory = getCategoryById(Category.getCategoryId(), session);
//		
//		if (existingCategory == null) {
//			throw new SystemException("Category with given id is exists", HttpStatus.NOT_FOUND);
//		}
		
		Transaction tx = session.beginTransaction();
		try {
			session.update(Category);
			tx.commit();
			return Category;
		} catch (HibernateException hibEx) {
			tx.rollback();
			hibEx.printStackTrace();
			throw new SystemException(hibEx.getMessage(), HttpStatus.NOT_MODIFIED);
		} finally {
			session.close();	
		}
	}

	public TaskCategory deleteCategoryById(int CategoryId) throws SystemException {
		logger.info("deleteCategory(int) invoked...");
		
		Session session = getSessionFactory().openSession();
		TaskCategory TaskCategory = getCategoryById(CategoryId, session);
		
		if (TaskCategory == null) {
			throw new SystemException("No Category found for the given id", HttpStatus.NOT_FOUND);
		}
		
		Transaction tx = session.beginTransaction();
		try {
			session.delete(TaskCategory);
			tx.commit();
			return TaskCategory;
		} catch (HibernateException hibEx) {
			tx.rollback();
			hibEx.printStackTrace();
			throw new SystemException(hibEx.getMessage(), HttpStatus.NOT_MODIFIED);
		} finally {
			session.close();
		}
	}

	@Override
	public boolean isValidCategory(String CategoryName) {
		return !StringUtils.isEmpty(CategoryName);
	}

}
