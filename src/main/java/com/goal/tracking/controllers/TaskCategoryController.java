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

import com.goal.tracking.beans.TaskCategory;
import com.goal.tracking.services.TaskService;

@RestController
@RequestMapping("/categories")
public class TaskCategoryController {

	@Autowired
	private TaskService taskService;
	
	@GetMapping
	public List<TaskCategory> getAllCategories() {
		return taskService.getAllCategories();
	}
	
	@GetMapping("/{categoryId}")
	public TaskCategory getCategory(@PathVariable int categoryId) {
		return taskService.getTaskCategory(categoryId);
	}
	
	@PostMapping
	public boolean addNewCategory(@RequestBody TaskCategory taskCategory) {
		return taskService.addNewCategory(taskCategory);
	}
	
	@PutMapping
	public boolean updateCategory(@RequestBody TaskCategory taskCategory) {
		return taskService.updateCategory(taskCategory);
	}
	
	@DeleteMapping("/{categoryId}")
	public boolean deleteCategory(@PathVariable int categoryId) {
		return taskService.deleteCategory(categoryId);
	}
	
}
