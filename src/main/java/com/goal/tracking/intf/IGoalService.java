package com.goal.tracking.intf;

import java.util.List;

import com.goal.tracking.entities.Goal;
import com.goal.tracking.exceptions.SystemException;

public interface IGoalService {
	
	public List<Goal> getAllGoals() throws SystemException;
	
	public Goal getGoalById(int goalId) throws SystemException;

	public Goal addNewGoal(Goal goal) throws SystemException;

	public Goal updateGoal(Goal goal) throws SystemException;

	public Goal deleteGoalById(int goalId) throws SystemException;
	
	public boolean checkIfValidGoal(Goal goal) throws SystemException;
	
	public boolean isEmptyGoal(Goal goal);
	
}
