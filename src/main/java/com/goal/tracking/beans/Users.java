package com.goal.tracking.beans;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Users {

	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private int userId;
	
	@Column (name = "first_name", nullable = false, length = 15)
	private String firstName;
	
	@Column (name = "last_name", nullable = false, length = 15)
	private String lastName;
	
	@Column (name = "email_id", nullable = false, unique = true, length = 50)
	private String emailId;
	
	@Column (name = "created_time", nullable = false)
	private Timestamp createdTime;
	
	@Column (name = "last_modified_time", nullable = false)
	private Timestamp lastModifiedTime;
	
	@Column (name = "status", nullable = false, length = 15)
	private String status;
	
	@ManyToOne
	@JoinColumn(name = "roleId")
	private UserRole userRole;

	@OneToMany (mappedBy = "user")
	private List<Goal> associatedGoals;
	
	@OneToMany (mappedBy = "user")
	private List<Task> associatedTasks;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public Timestamp getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}

	public Timestamp getLastModifiedTime() {
		return lastModifiedTime;
	}

	public void setLastModifiedTime(Timestamp lastModifiedTime) {
		this.lastModifiedTime = lastModifiedTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

	public List<Goal> getAssociatedGoals() {
		return associatedGoals;
	}

	public void setAssociatedGoals(List<Goal> associatedGoals) {
		this.associatedGoals = associatedGoals;
	}

	public List<Task> getAssociatedTasks() {
		return associatedTasks;
	}

	public void setAssociatedTasks(List<Task> associatedTasks) {
		this.associatedTasks = associatedTasks;
	}
	
}
