package net36px.passport.boot.data;

import java.io.Serializable;

import javax.persistence.Id;

// @Entity

public class BaseKeyPem implements Serializable {

	private static final long serialVersionUID = 3018225262368126671L;

	@Id
	private String fingerprint;

	private String format;
	private String algorithm;
	private boolean encrypted;

	private String content;

	public String getFingerprint() {
		return fingerprint;
	}

	public void setFingerprint(String fingerprint) {
		this.fingerprint = fingerprint;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getAlgorithm() {
		return algorithm;
	}

	public void setAlgorithm(String algorithm) {
		this.algorithm = algorithm;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public boolean isEncrypted() {
		return encrypted;
	}

	public void setEncrypted(boolean encrypted) {
		this.encrypted = encrypted;
	}

}
