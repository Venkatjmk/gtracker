package com.goal.tracking.intf;

import java.util.List;

import com.goal.tracking.beans.Goal;

public interface IGoalService {
	
	public boolean deleteGoal(int goalId);

	public boolean addNewGoal(Goal goal);

	public boolean updateGoal(Goal goal);

	public Goal getGoal(int goalId);

	public List<Goal> getAllGoals();
	
}
