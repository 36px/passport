package net36px.passport.api.body;

import net36px.passport.api.element.SecretKeyElement;

public class UnlockBody {

	private SecretKeyElement secretKey; // down

	public SecretKeyElement getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(SecretKeyElement secretKey) {
		this.secretKey = secretKey;
	}

}
