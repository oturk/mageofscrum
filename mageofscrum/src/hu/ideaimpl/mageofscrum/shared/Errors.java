package hu.ideaimpl.mageofscrum.shared;

public enum Errors {
	UNKNOWN("Something goes wrong"), AUTHENTICATION("You're not admitted!"), SERVICE_UNAVAILABLE("Servers temporarly unavailable");

	private String msg;

	private Errors(String msg) {
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}

}
