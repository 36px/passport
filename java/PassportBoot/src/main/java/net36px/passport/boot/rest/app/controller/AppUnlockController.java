package net36px.passport.boot.rest.app.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net36px.passport.api.document.UnlockDoc;
import net36px.passport.boot.rest.BaseController;

@RestController

@RequestMapping("/app.api/Unlock")

public class AppUnlockController extends BaseController<UnlockDoc> {

	@Override
	protected String getRestType() {
		return "Unlock";
	}

	@Override
	protected UnlockDoc httpGet(String type, String id, UnlockDoc doc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected UnlockDoc httpPut(String type, String id, UnlockDoc doc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected UnlockDoc httpPost(String type, String id, UnlockDoc doc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected UnlockDoc httpDelete(String type, String id, UnlockDoc doc) {
		// TODO Auto-generated method stub
		return null;
	}

}
