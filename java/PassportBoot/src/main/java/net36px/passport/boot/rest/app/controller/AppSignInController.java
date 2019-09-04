package net36px.passport.boot.rest.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net36px.passport.api.document.SignInDoc;
import net36px.passport.boot.rest.BaseController;
import net36px.passport.boot.rest.app.service.AppSignInService;

@RestController

@RequestMapping("/app.api/SignIn")

public class AppSignInController extends BaseController<SignInDoc> {

	@Autowired
	private AppSignInService signInService;

	@Override
	protected String getRestType() {
		return "SignIn";
	}

	@Override
	protected SignInDoc httpGet(String type, String id, SignInDoc doc) {
		throw new RuntimeException("unsupported");
	}

	@Override
	protected SignInDoc httpPut(String type, String id, SignInDoc doc) {
		return this.signInService.updateSignCommit(id, doc);
	}

	@Override
	protected SignInDoc httpPost(String type, String id, SignInDoc doc) {
		return this.signInService.startSignCommit(doc);
	}

	@Override
	protected SignInDoc httpDelete(String type, String id, SignInDoc doc) {
		throw new RuntimeException("unsupported");
	}

}
