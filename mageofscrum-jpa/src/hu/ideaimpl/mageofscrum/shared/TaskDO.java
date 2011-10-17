package hu.ideaimpl.mageofscrum.shared;

import java.util.Date;

import com.google.gwt.user.client.rpc.IsSerializable;

public class TaskDO implements IsSerializable{
	private Long id;
	private String name;
	private String description;
	private int estimateTime;
	private int reportTime;
	private int priority;
	private Date created;
	public TaskDO() {
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

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

}
