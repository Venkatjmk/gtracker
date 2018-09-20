package com.goal.tracking.intf;

import java.util.List;

import com.goal.tracking.entities.Task;
import com.goal.tracking.exceptions.SystemException;

public interface ITaskService {

	public List<Task> getAllTasks() throws SystemException;
	
	public Task getTaskById(int taskId) throws SystemException;
	
	public Task addNewTask(Task task) throws SystemException;
	
	public Task updateTask(Task task) throws SystemException;
	
	public Task deleteTaskById(int taskId) throws SystemException;
	
	public boolean checkIfValidTask(Task task) throws SystemException;
	
	public boolean isEmptyTask(Task task);
	
}
