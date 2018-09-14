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

import com.goal.tracking.beans.Goal;
import com.goal.tracking.services.GoalService;

@RestController
@RequestMapping("/goals")
public class GoalController {

	@Autowired
	private GoalService goalService;
	
	@GetMapping
	public List<Goal> getAllGoals() {
		return goalService.getAllGoals();
	}
	
	@GetMapping("/{goalId}")
	public Goal getGoal(@PathVariable int goalId) {
		return goalService.getGoal(goalId);
	}
	
	@PostMapping
	public boolean addNewGoal(@RequestBody Goal goal) {
		return goalService.addNewGoal(goal);
	}
	
	@PutMapping
	public boolean updateGoal(@RequestBody Goal goal) {
		return goalService.updateGoal(goal);
	}
	
	@DeleteMapping("/{goalId}")
	public boolean deleteGoal(@PathVariable int goalId) {
		return goalService.deleteGoal(goalId);
	}
}
