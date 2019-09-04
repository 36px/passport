package net36px.passport.api.body;

import net36px.passport.api.element.PublicKeyElement;
import net36px.passport.api.element.SecretKeyElement;

public class SignInBody {

	private String name; // up , the user name or id
	private String verification; // up
	private PublicKeyElement publicKey; // up

	private long account; // down
	private String ticket; // down
	private SecretKeyElement secretKey; // down

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVerification() {
		return verification;
	}

	public void setVerification(String verification) {
		this.verification = verification;
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public PublicKeyElement getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(PublicKeyElement publicKey) {
		this.publicKey = publicKey;
	}

	public SecretKeyElement getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(SecretKeyElement secretKey) {
		this.secretKey = secretKey;
	}

	public long getAccount() {
		return account;
	}

	public void setAccount(long account) {
		this.account = account;
	}

}
