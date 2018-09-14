package com.goal.tracking.intf;

import java.util.List;

import com.goal.tracking.beans.Task;
import com.goal.tracking.beans.TaskCategory;

public interface ITaskService {

	public List<TaskCategory> getAllCategories();
	
	public TaskCategory getTaskCategory(int categoryId);
	
	public boolean addNewCategory(TaskCategory taskCategory);
	
	public boolean updateCategory(TaskCategory taskCategory);
	
	public boolean deleteCategory(int categoryId);
	
	public List<Task> getAllTasks();
	
	public Task getTask(int taskId);
	
	public boolean addNewTask(Task task);
	
	public boolean updateTask(Task task);
	
	public boolean deleteTask(int taskId);
	
}
