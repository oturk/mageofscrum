package hu.ideaimpl.mageofscrum.server.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
public class Task implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String description;
	private int estimateTime;
	private int reportTime;
	private int priority;
	@ManyToOne
	@JoinTable(name="PROJECT_TASKS",joinColumns = @JoinColumn(name="TASK_ID"), inverseJoinColumns = @JoinColumn(name="PROJECT_ID"))
	private Project project;
	@OneToOne
	@PrimaryKeyJoinColumn
	private TaskStatus status;

	public Task() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public int getEstimateTime() {
		return estimateTime;
	}

	public void setEstimateTime(int estimateTime) {
		this.estimateTime = estimateTime;
	}

	public int getReportTime() {
		return reportTime;
	}

	public void setReportTime(int reportTime) {
		this.reportTime = reportTime;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public TaskStatus getStatus() {
		return status;
	}

	public void setStatus(TaskStatus status) {
		this.status = status;
	}

}
