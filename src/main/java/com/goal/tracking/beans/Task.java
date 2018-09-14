package com.goal.tracking.beans;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Task {
	
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "userId")
	private Users user;
	
	@ManyToOne
	@JoinColumn(name = "goalId")
	private Goal goal;
	
	@Column (name = "task_desc", nullable = false, length = 100)
	private String taskDesc;
	
	@Column(name = "one_time_task")
	private boolean oneTimeTask;
	
	@Column(name = "schedule", length = 25)
	private String schedule;
	
	@Column (name = "created_time", nullable = false)
	private Timestamp createdTime;
	
	@Column (name = "modified_time", nullable = false)
	private Timestamp modifiedTime;
	
	@Column (name = "created_by", nullable = false, length = 15)
	private String createdBy;
	
	@Column (name = "modified_by", nullable = false, length = 15)
	private String modifiedBy;
	
	@ManyToOne
	@JoinColumn(name = "categoryId")
	private TaskCategory taskCategory;
	
	@Column (name = "task_type", nullable = false)
	private int taskType;
	
	@Column (name = "status", nullable = false, length = 15)
	private String status;
	
	@Column (name = "dead_line", nullable = false)
	private Timestamp deadLine;
	
	@Column (name = "is_postponed", nullable = false)
	private boolean isPostponed;
	
	@Column (name = "postponed_count", nullable = false)
	private int postponedCount;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTaskDesc() {
		return taskDesc;
	}

	public void setTaskDesc(String taskDesc) {
		this.taskDesc = taskDesc;
	}

	public Timestamp getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}

	public Timestamp getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(Timestamp modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	public TaskCategory getTaskCategory() {
		return taskCategory;
	}

	public void setTaskCategory(TaskCategory taskCategory) {
		this.taskCategory = taskCategory;
	}

	public int getTaskType() {
		return taskType;
	}

	public void setTaskType(int taskType) {
		this.taskType = taskType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Timestamp getDeadLine() {
		return deadLine;
	}

	public void setDeadLine(Timestamp deadLine) {
		this.deadLine = deadLine;
	}

	public boolean isPostponed() {
		return isPostponed;
	}

	public void setPostponed(boolean isPostponed) {
		this.isPostponed = isPostponed;
	}

	public int getPostponedCount() {
		return postponedCount;
	}

	public void setPostponedCount(int postponedCount) {
		this.postponedCount = postponedCount;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public Goal getGoal() {
		return goal;
	}

	public void setGoal(Goal goal) {
		this.goal = goal;
	}

	public boolean isOneTimeTask() {
		return oneTimeTask;
	}

	public void setOneTimeTask(boolean oneTimeTask) {
		this.oneTimeTask = oneTimeTask;
	}

	public String getSchedule() {
		return schedule;
	}

	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	
}
