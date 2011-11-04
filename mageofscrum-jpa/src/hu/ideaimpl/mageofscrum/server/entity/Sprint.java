package hu.ideaimpl.mageofscrum.server.entity;

import hu.ideaimpl.mageofscrum.shared.SprintDO;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Sprint implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Date startDate;
	private Date endDate;
	@OneToMany(mappedBy = "sprint")
	private List<Task> tasks;
	@OneToMany(mappedBy = "sprint")
	private List<History> history;
	@ManyToOne
	@JoinColumn(name = "PROJECT_FK")
	private Project project;

	public Sprint() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}
	
	public void addTask(Task task){
		if(tasks == null){
			tasks = new ArrayList<Task>();
		}
		tasks.add(task);
	}

	public List<History> getHistory() {
		return history;
	}

	public void setHistory(List<History> history) {
		this.history = history;
	}
	
	public void addHistory(History history){
		if(this.history == null){
			this.history = new ArrayList<History>();
		}
		this.history.add(history);
	}
	
	public SprintDO getSprintDO(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SprintDO sprintDO = new SprintDO();
		sprintDO.setId(this.id);
		String interval = sdf.format(this.startDate);
		if(this.endDate != null){
			interval += ", "+sdf.format(this.endDate);
		}else{
			interval += ", running";
		}
		sprintDO.setInterval(interval);
		return sprintDO;
	}

}
