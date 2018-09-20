package com.goal.tracking.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.goal.tracking.entities.Task;
import com.goal.tracking.exceptions.SystemException;
import com.goal.tracking.intf.ITaskService;

@RestController
@RequestMapping("/tasks")
public class TaskController {

	private final Logger logger = LoggerFactory.getLogger(TaskController.class);

	@Autowired private ITaskService taskService;
	
	@GetMapping
	public List<Task> getAllTasks() throws SystemException {
		logger.info("getAllTasks() is invoked ...");
		return taskService.getAllTasks();
	}
	
	@GetMapping("/{taskId}")
	public Task getTaskById(@PathVariable int taskId) throws SystemException {
		logger.info("getTaskById(int): taskId = " + taskId);
		
		if (taskId <= 0) {
			throw new SystemException("Not an valid task id", HttpStatus.BAD_REQUEST);
		}
		
		return taskService.getTaskById(taskId);
	}
	
	@PostMapping
	public Task addNewTask(@RequestBody Task task) throws SystemException {
		logger.info("addNewTask(Task) is invoked ...");
		return taskService.addNewTask(task);
	}
	
	@PutMapping
	public Task updateTask(@RequestBody Task task) throws SystemException {
		logger.info("updateTask(Task) is invoked ...");
		return taskService.updateTask(task);
	}
	
	@DeleteMapping("/{taskId}")
	public Task deleteTaskById(@PathVariable int taskId) throws SystemException {
		logger.info("deleteTaskById(int) is invoked ...");
		return taskService.deleteTaskById(taskId);
	}
	
}
