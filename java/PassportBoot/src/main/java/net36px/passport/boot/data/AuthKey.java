package net36px.passport.boot.data;

import java.io.Serializable;

public class AuthKey implements Serializable {

	private static final long serialVersionUID = 2483755022740852535L;

	private String mechanism;
	private String name;

	public AuthKey() {
	}

	public String getMechanism() {
		return mechanism;
	}

	public void setMechanism(String mechanism) {
		this.mechanism = mechanism;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
