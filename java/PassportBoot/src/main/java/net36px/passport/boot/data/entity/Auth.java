package net36px.passport.boot.data.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

import net36px.passport.boot.data.AuthKey;

@Entity

@IdClass(AuthKey.class)

public class Auth implements Serializable {

	private static final long serialVersionUID = -3423169305630502661L;

	@Id
	private String mechanism;

	@Id
	private String name;

	/*****************************
	 * equals to account.id
	 */

	private long account;

	/*****************************
	 * equals to account.token
	 */

	private String token;

	private String secret;

	private boolean enable;

	public Auth() {
	}

	public static interface Mechanisms {

		String EMAIL = "email";
		String WEIXIN = "weixin";

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

	public long getAccount() {
		return account;
	}

	public void setAccount(long account) {
		this.account = account;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

}
