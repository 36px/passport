package net36px.passport.boot.rest.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net36px.passport.api.body.SignUpBody;
import net36px.passport.api.document.SignUpDoc;
import net36px.passport.boot.rest.WebBaseController;
import net36px.passport.boot.rest.web.service.WebSignUpService;

@RestController

@RequestMapping("/web.api/SignUp")

public class WebSignUpController extends WebBaseController<SignUpDoc> {

	@Autowired
	private WebSignUpService signUpService;

	@Override
	protected String getRestType() {
		return "SignUp";
	}

	@Override
	protected SignUpDoc httpGet(String type, String id, SignUpDoc doc) {
		throw new RuntimeException("unsupported");
	}

	@Override
	protected SignUpDoc httpPost(String type, String id, SignUpDoc doc) {
		String email = doc.getSignUp().getEmail();
		String ticket = this.signUpService.signUp1(email);
		// result
		doc = new SignUpDoc();
		doc.setSignUp(new SignUpBody());
		doc.getSignUp().setTicket(ticket);
		return doc;
	}

	@Override
	protected SignUpDoc httpPut(String type, String id, SignUpDoc doc) {
		String transactionId = id;
		String verification = doc.getSignUp().getVerification();
		this.signUpService.signUp2(transactionId, verification);
		// result
		doc = new SignUpDoc();
		doc.setSignUp(new SignUpBody());
		return doc;
	}

	@Override
	protected SignUpDoc httpDelete(String type, String id, SignUpDoc doc) {
		throw new RuntimeException("unsupported");
	}

}
