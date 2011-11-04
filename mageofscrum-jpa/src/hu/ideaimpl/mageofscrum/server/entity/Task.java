package hu.ideaimpl.mageofscrum.server.entity;

import hu.ideaimpl.mageofscrum.shared.TaskDO;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

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
	private Date created;
	@OneToOne
	private TaskStatus status;
	@ManyToOne
	@JoinColumn(name = "PROJECT_FK")
	private Project project;
	@ManyToOne
	@JoinColumn(name = "SPRINT_FK")
	private Sprint sprint;

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
	
	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Sprint getSprint() {
		return sprint;
	}

	public void setSprint(Sprint sprint) {
		this.sprint = sprint;
	}

	public TaskDO getTaskDO(){
		TaskDO result = new TaskDO();
		result.setId(id);
		result.setName(name);
		result.setDescription(description);
		result.setEstimateTime(estimateTime);
		result.setPriority(priority);
		result.setCreated(created);
		return result;
	}

}
