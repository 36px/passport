package net36px.passport.boot.rest.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net36px.passport.api.body.SignInBody;
import net36px.passport.api.document.SignInDoc;
import net36px.passport.boot.rest.WebBaseController;
import net36px.passport.boot.rest.web.service.WebSignInService;

@RestController

@RequestMapping("/web.api/SignIn")

public class WebSignInController extends WebBaseController<SignInDoc> {

	@Autowired
	private WebSignInService signInService;

	@Override
	protected String getRestType() {
		return "SignIn";
	}

	@Override
	protected SignInDoc httpGet(String type, String id, SignInDoc doc) {
		throw new RuntimeException("unsupported");
	}

	@Override
	protected SignInDoc httpPost(String type, String id, SignInDoc doc) {
		String email = doc.getSignIn().getName();
		String ticket = this.signInService.signIn1(email);
		// result
		doc = new SignInDoc();
		doc.setSignIn(new SignInBody());
		doc.getSignIn().setTicket(ticket);
		return doc;
	}

	@Override
	protected SignInDoc httpPut(String type, String id, SignInDoc doc) {
		String transactionId = id;
		String verification = doc.getSignIn().getVerification();
		this.signInService.signIn2(transactionId, verification);
		// result
		doc = new SignInDoc();
		doc.setSignIn(new SignInBody());
		return doc;
	}

	@Override
	protected SignInDoc httpDelete(String type, String id, SignInDoc doc) {
		throw new RuntimeException("unsupported");
	}

}
