package hu.ideaimpl.mageofscrum.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

public class SprintDO implements IsSerializable {

	private Long id;
	private String interval;

	public SprintDO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getInterval() {
		return interval;
	}

	public void setInterval(String interval) {
		this.interval = interval;
	}

}
