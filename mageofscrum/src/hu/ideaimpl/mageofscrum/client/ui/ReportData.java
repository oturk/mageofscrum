package hu.ideaimpl.mageofscrum.client.ui;

import java.util.Date;

public class ReportData {
	private int time;
	private Date date;
	private String desc;

	public ReportData() {
	}

	public ReportData(int time, Date date, String desc) {
		super();
		this.time = time;
		this.date = date;
		this.desc = desc;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
