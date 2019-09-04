package net36px.passport.api.document;

import net36px.passport.api.BaseRestDocument;
import net36px.passport.api.body.SessionBody;

public class SessionDoc extends BaseRestDocument {

	private SessionBody session;

	public SessionBody getSession() {
		return session;
	}

	public void setSession(SessionBody session) {
		this.session = session;
	}

}
