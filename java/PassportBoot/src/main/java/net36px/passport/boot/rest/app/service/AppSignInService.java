package net36px.passport.boot.rest.app.service;

import net36px.passport.api.document.SignInDoc;

public interface AppSignInService {

	SignInDoc startSignCommit(SignInDoc doc);

	SignInDoc updateSignCommit(String id, SignInDoc doc);

}
