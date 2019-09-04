package net36px.passport.boot.rest.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net36px.passport.api.document.SessionDoc;
import net36px.passport.boot.rest.WebBaseController;
import net36px.passport.boot.rest.web.service.WebSessionService;

@RestController

@RequestMapping("/web.api/Session")

public class WebSessionController extends WebBaseController<SessionDoc> {

	@Autowired
	private WebSessionService sessionService;

	@Override
	protected String getRestType() {
		return "Session";
	}

	@Override
	protected SessionDoc httpGet(String type, String id, SessionDoc doc) {
		doc = new SessionDoc();
		doc.setSession(this.sessionService.getSessionInfo());
		return doc;
	}

	@Override
	protected SessionDoc httpPut(String type, String id, SessionDoc doc) {
		throw new RuntimeException("unsupported");
	}

	@Override
	protected SessionDoc httpPost(String type, String id, SessionDoc doc) {
		throw new RuntimeException("unsupported");
	}

	@Override
	protected SessionDoc httpDelete(String type, String id, SessionDoc doc) {
		this.sessionService.logout();
		return new SessionDoc();
	}

}
