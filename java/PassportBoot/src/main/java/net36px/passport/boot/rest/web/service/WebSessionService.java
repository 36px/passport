package net36px.passport.boot.rest.web.service;

import net36px.passport.api.body.SessionBody;

public interface WebSessionService {

	SessionBody getSessionInfo();

	void logout();

}
