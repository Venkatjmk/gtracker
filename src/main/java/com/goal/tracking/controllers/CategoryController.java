package com.goal.tracking.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.goal.tracking.entities.TaskCategory;
import com.goal.tracking.exceptions.SystemException;
import com.goal.tracking.intf.ICategoryService;

@RestController
@RequestMapping("/categories")
public class CategoryController {
	
	private final Logger logger = LoggerFactory.getLogger(CategoryController.class);
	
	@Autowired
	private ICategoryService categoryService;
	
	@GetMapping
	public List<TaskCategory> getAllcategories() throws SystemException {
		logger.info("getAllcategories() invoked ...");
		return categoryService.getAllCategories();
	}
	
	@GetMapping("/categoryId/{categoryId}")
	public TaskCategory getCategoryById(@PathVariable int categoryId) throws SystemException {
		logger.info("getCategoryById(int) invoked ...");
		return categoryService.getCategoryById(categoryId);
	}
	
	@GetMapping("/categoryName/{categoryName}")
	public TaskCategory getCategoryByName(@PathVariable String categoryName) throws SystemException {
		logger.info("getCategoryByName(String) invoked ...");
		return categoryService.getCategoryByName(categoryName);
	}
	
	@PostMapping("/{categoryName}")
	public TaskCategory addNewCategory(@PathVariable String categoryName) throws SystemException {
		logger.info("addNewCategory(String) invoked ...");
		return categoryService.addNewCategory(categoryName);
	}
	
	@PutMapping
	public TaskCategory updateCategory(@RequestBody TaskCategory category) throws SystemException {
		logger.info("updateCategory(TaskCategory) invoked ...");
		return categoryService.updateCategory(category);
	}
	
	@PutMapping("/{oldCategoryName}/{newCategoryName}")
	public TaskCategory updateCategory(@PathVariable String oldCategoryName, @PathVariable String newCategoryName) throws SystemException {
		logger.info("updateCategory(String, String) invoked ...");
		
		if (StringUtils.isEmpty(oldCategoryName)) {
			throw new SystemException("Existing category name is empty", HttpStatus.PRECONDITION_FAILED);
		}
		
		if (StringUtils.isEmpty(newCategoryName)) {
			throw new SystemException("New category name is empty", HttpStatus.PRECONDITION_FAILED);
		}
		
		return categoryService.updateCategory(oldCategoryName, newCategoryName);
	}
	
	@DeleteMapping("/{categoryId}")
	public TaskCategory deleteCategoryById(@PathVariable int categoryId) throws SystemException {
		logger.info("deleteCategoryById(int) invoked ...");
		return categoryService.deleteCategoryById(categoryId);
	}

}
