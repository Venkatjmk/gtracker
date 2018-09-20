package com.goal.tracking.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.goal.tracking.entities.Goal;
import com.goal.tracking.services.GoalService;

@RestController
@RequestMapping("/goals")
public class GoalController {
	
	private final Logger logger = LoggerFactory.getLogger(GoalController.class);

	@Autowired
	private GoalService goalService;
	
	@GetMapping
	public List<Goal> getAllGoals() {
		logger.info("getAllGoals() invoked ...");
		return goalService.getAllGoals();
	}
	
	@GetMapping("/{goalId}")
	public Goal getGoalById(@PathVariable int goalId) {
		logger.info("getGoalById(int) invoked ...");
		return goalService.getGoalById(goalId);
	}
	
	@PostMapping
	public Goal addNewGoal(@RequestBody Goal goal) {
		logger.info("addNewGoal(Goal) invoked ...");
		return goalService.addNewGoal(goal);
	}
	
	@PutMapping
	public Goal updateGoal(@RequestBody Goal goal) {
		logger.info("updateGoal(Goal) invoked ...");
		return goalService.updateGoal(goal);
	}
	
	@DeleteMapping("/{goalId}")
	public Goal deleteGoal(@PathVariable int goalId) {
		logger.info("deleteGoal(int) invoked ...");
		return goalService.deleteGoalById(goalId);
	}
}
