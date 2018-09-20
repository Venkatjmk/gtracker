package com.goal.tracking.entities;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name="Tasks")
@Table(name="tasks")
public class Task {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name = "task_id")
	private int taskId;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private Users user;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "goal_id")
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
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "category_id")
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

	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
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

	@Override
	public String toString() {
		return "Task [taskId=" + taskId + ", user=" + user + ", goal=" + goal + ", taskDesc=" + taskDesc
				+ ", oneTimeTask=" + oneTimeTask + ", schedule=" + schedule + ", createdTime=" + createdTime
				+ ", modifiedTime=" + modifiedTime + ", createdBy=" + createdBy + ", modifiedBy=" + modifiedBy
				+ ", taskCategory=" + taskCategory + ", taskType=" + taskType + ", status=" + status + ", deadLine="
				+ deadLine + ", isPostponed=" + isPostponed + ", postponedCount=" + postponedCount + ", getTaskId()="
				+ getTaskId() + ", getTaskDesc()=" + getTaskDesc() + ", getCreatedTime()=" + getCreatedTime()
				+ ", getModifiedTime()=" + getModifiedTime() + ", getTaskCategory()=" + getTaskCategory()
				+ ", getTaskType()=" + getTaskType() + ", getStatus()=" + getStatus() + ", getDeadLine()="
				+ getDeadLine() + ", isPostponed()=" + isPostponed() + ", getPostponedCount()=" + getPostponedCount()
				+ ", getUser()=" + getUser() + ", getGoal()=" + getGoal() + ", isOneTimeTask()=" + isOneTimeTask()
				+ ", getSchedule()=" + getSchedule() + ", getCreatedBy()=" + getCreatedBy() + ", getModifiedBy()="
				+ getModifiedBy() + "]";
	}
	
}
