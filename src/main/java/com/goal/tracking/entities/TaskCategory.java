package com.goal.tracking.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity(name = "Categories")
@Table(name = "task_categories")
public class TaskCategory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "category_id")
	private int categoryId;
	
	@Column (name = "category", nullable = false, unique = true, length = 15)
	private String categoryName;
	
	@Transient
	@OneToMany(mappedBy = "taskCategory", fetch = FetchType.LAZY)
	private List<Task> tasks;
	
	public TaskCategory() {
		
	}
	
	public TaskCategory(String categoryName) {
		this.categoryName = categoryName;
	}
	
	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	public int getCategoryId() {
		return categoryId;
	}
	
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	
	public String getCategoryName() {
		return categoryName;
	}
	
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	@Override
	public String toString() {
		return "TaskCategory [categoryId=" + categoryId + ", categoryName=" + categoryName + "]";
	}
	
}
