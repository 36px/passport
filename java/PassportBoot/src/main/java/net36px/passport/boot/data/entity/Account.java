package net36px.passport.boot.data.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity

public class Account implements Serializable {

	private static final long serialVersionUID = 3101116601053492641L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id; // final

	private String token;

	private String displayName;

	private String email; // final

	private long signUpDate; // final

	private boolean enable;

	private String ossPath;

	public Account() {
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getSignUpDate() {
		return signUpDate;
	}

	public void setSignUpDate(long signUpDate) {
		this.signUpDate = signUpDate;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public String getOssPath() {
		return ossPath;
	}

	public void setOssPath(String ossPath) {
		this.ossPath = ossPath;
	}

}
