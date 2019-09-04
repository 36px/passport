package net36px.passport.boot.data.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

import net36px.passport.boot.data.TerminalKey;

@Entity

@IdClass(TerminalKey.class)

public class Terminal implements Serializable {

	private static final long serialVersionUID = 1535888961242080012L;

	@Id
	private long account;
	@Id
	private String device;

	private String publicKeyFingerprint;
	private String secretKeyFingerprint;
	private String pin; // pin 码
	private boolean active; // 激活标志

	public Terminal() {
	}

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

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public String getPublicKeyFingerprint() {
		return publicKeyFingerprint;
	}

	public void setPublicKeyFingerprint(String publicKeyFingerprint) {
		this.publicKeyFingerprint = publicKeyFingerprint;
	}

	public String getSecretKeyFingerprint() {
		return secretKeyFingerprint;
	}

	public void setSecretKeyFingerprint(String secretKeyFingerprint) {
		this.secretKeyFingerprint = secretKeyFingerprint;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}
