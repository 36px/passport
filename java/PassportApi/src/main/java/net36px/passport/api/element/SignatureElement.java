package net36px.passport.api.element;

public class SignatureElement {

	private long timestamp;
	private long account;
	private String device;
	private String nonce;
	private String pin;

	private String publicKeyFingerprint;
	private String signatureData;

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
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

	public String getNonce() {
		return nonce;
	}

	public void setNonce(String nonce) {
		this.nonce = nonce;
	}

	public String getPublicKeyFingerprint() {
		return publicKeyFingerprint;
	}

	public void setPublicKeyFingerprint(String publicKeyFingerprint) {
		this.publicKeyFingerprint = publicKeyFingerprint;
	}

	public String getSignatureData() {
		return signatureData;
	}

	public void setSignatureData(String signatureData) {
		this.signatureData = signatureData;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

}
