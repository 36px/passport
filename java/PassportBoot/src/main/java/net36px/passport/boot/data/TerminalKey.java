package net36px.passport.boot.data;

import java.io.Serializable;

public class TerminalKey implements Serializable {

	private static final long serialVersionUID = -7635881323856707144L;

	private long account;
	private String device;

	public long getAccount() {
		return account;
	}

	public void setAccount(long account) {
		this.account = account;
	}

	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

}
