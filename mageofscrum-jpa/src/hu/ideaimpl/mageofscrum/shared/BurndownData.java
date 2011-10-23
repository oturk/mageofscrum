package hu.ideaimpl.mageofscrum.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

public class BurndownData implements IsSerializable {

	private int value = 0;
	private Operations operator;

	public BurndownData() {
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public Operations getOperator() {
		return operator;
	}

	public void setOperator(Operations operator) {
		this.operator = operator;
	}

}
