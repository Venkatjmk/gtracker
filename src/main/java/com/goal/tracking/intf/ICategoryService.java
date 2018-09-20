package com.goal.tracking.intf;

import java.util.List;

import com.goal.tracking.entities.TaskCategory;
import com.goal.tracking.exceptions.SystemException;

public interface ICategoryService {

	public List<TaskCategory> getAllCategories() throws SystemException;
	
	public TaskCategory getCategoryById(int CategoryId) throws SystemException;
	
	public TaskCategory getCategoryByName(String CategoryName) throws SystemException;
	
	public TaskCategory addNewCategory(String Category) throws SystemException;

	public TaskCategory updateCategory(String oldCategoryName, String newCategoryName) throws SystemException;
	
	public TaskCategory updateCategory(TaskCategory Category) throws SystemException;

	public TaskCategory deleteCategoryById(int CategoryId) throws SystemException;
	
	public boolean isValidCategory(String CategoryName) throws SystemException;	
}
