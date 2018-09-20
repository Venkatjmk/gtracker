package com.goal.tracking.entities;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity(name = "Goals")
@Table(name = "goals")
public class Goal {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name = "goal_id")
	private int goalId;
	
	@Column(name = "goal_name", nullable = false, length = 30)
	private String name;
	
	@Column(name = "description", length = 100)
	private String description;
	
	@Column(name = "goal_type", nullable = false)
	private int goalType;
	
	@Column(name = "status", nullable = false, length = 15)
	private String status;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private Users user;
	
	@Transient
	@OneToMany (mappedBy = "goal", fetch = FetchType.EAGER)
	private List<Task> subGoals;
	
	@Column(name = "created_by", nullable = false, length = 15)
	private String createdBy;
	
	@Column(name = "created_time", nullable = false)
	private Timestamp createdTime;
	
	@Column(name = "last_modified_by", nullable = false, length = 15)
	private String lastModifiedBy;
	
	@Column(name = "last_modified_time", nullable = false)
	private Timestamp lastModifiedTime;

	public int getGoalId() {
		return goalId;
	}

	public void setGoalId(int goalId) {
		this.goalId = goalId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getGoalType() {
		return goalType;
	}

	public void setGoalType(int goalType) {
		this.goalType = goalType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public List<Task> getTasks() {
		return subGoals;
	}

	public void setTasks(List<Task> tasks) {
		this.subGoals = tasks;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}

	public String getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public Timestamp getLastModifiedTime() {
		return lastModifiedTime;
	}

	public void setLastModifiedTime(Timestamp lastModifiedTime) {
		this.lastModifiedTime = lastModifiedTime;
	}
	
	@Override
	public String toString() {
		return "Goal [goalId=" + goalId + ", name=" + name + ", description=" + description + ", goalType=" + goalType
				+ ", status=" + status + ", user=" + user + ", subGoals=" + subGoals + ", createdBy=" + createdBy
				+ ", createdTime=" + createdTime + ", lastModifiedBy=" + lastModifiedBy + ", lastModifiedTime="
				+ lastModifiedTime + ", getGoalId()=" + getGoalId() + ", getName()=" + getName() + ", getDescription()="
				+ getDescription() + ", getGoalType()=" + getGoalType() + ", getStatus()=" + getStatus()
				+ ", getUser()=" + getUser() + ", getTasks()=" + getTasks() + ", getCreatedBy()=" + getCreatedBy()
				+ ", getCreatedTime()=" + getCreatedTime() + ", getLastModifiedBy()=" + getLastModifiedBy()
				+ ", getLastModifiedTime()=" + getLastModifiedTime() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}

}
