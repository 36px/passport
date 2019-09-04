package net36px.passport.api.document;

import net36px.passport.api.BaseRestDocument;
import net36px.passport.api.body.SignInBody;

public class SignInDoc extends BaseRestDocument {

	private SignInBody signIn;

	public SignInDoc() {
	}

	public SignInBody getSignIn() {
		return signIn;
	}

	public void setSignIn(SignInBody signIn) {
		this.signIn = signIn;
	}

}
