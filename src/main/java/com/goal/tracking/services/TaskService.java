package com.goal.tracking.services;

import java.util.List;

import org.springframework.stereotype.Component;

import com.goal.tracking.beans.Task;
import com.goal.tracking.beans.TaskCategory;
import com.goal.tracking.intf.ITaskService;

@Component
public class TaskService extends AbstractService implements ITaskService {

	public List<TaskCategory> getAllCategories() {
		return null;
	}

	public TaskCategory getTaskCategory(int categoryId) {
		return null;
	}

	public boolean addNewCategory(TaskCategory taskCategory) {
		return false;
	}

	public boolean updateCategory(TaskCategory taskCategory) {
		return false;
	}

	public boolean deleteCategory(int categoryId) {
		return false;
	}
	
	public List<Task> getAllTasks() {
		return null;
	}

	public Task getTask(int taskId) {
		return null;
	}

	public boolean addNewTask(Task task) {
		return false;
	}

	public boolean updateTask(Task task) {
		return false;
	}

	public boolean deleteTask(int taskId) {
		return false;
	}

}
