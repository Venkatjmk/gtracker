package com.goal.tracking.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.goal.tracking.beans.Task;
import com.goal.tracking.beans.TaskCategory;
import com.goal.tracking.services.TaskService;

@RestController
@RequestMapping("/tasks")
public class TaskController {

	@Autowired
	private TaskService taskService;
	
	@GetMapping
	public List<Task> getAllTasks() {
		return taskService.getAllTasks();
	}
	
	@GetMapping("/{taskId}")
	public Task getTask(@PathVariable int categoryId) {
		return taskService.getTask(categoryId);
	}
	
	@PostMapping
	public boolean addNewTask(@RequestBody Task task) {
		return taskService.addNewTask(task);
	}
	
	@PutMapping
	public boolean updateTask(@RequestBody Task task) {
		return taskService.updateTask(task);
	}
	
	@DeleteMapping("/{taskId}")
	public boolean deleteTask(@PathVariable int taskId) {
		return taskService.deleteCategory(taskId);
	}
}
