package net36px.passport.api.document;

import net36px.passport.api.BaseRestDocument;
import net36px.passport.api.body.SignUpBody;

public class SignUpDoc extends BaseRestDocument {

	private SignUpBody signUp;

	public SignUpDoc() {
	}

	public SignUpBody getSignUp() {
		return signUp;
	}

	public void setSignUp(SignUpBody signUp) {
		this.signUp = signUp;
	}

}
