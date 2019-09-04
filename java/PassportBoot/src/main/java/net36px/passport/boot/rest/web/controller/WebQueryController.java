package net36px.passport.boot.rest.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net36px.passport.api.document.QueryDoc;
import net36px.passport.boot.rest.WebBaseController;

@RestController

@RequestMapping("/web.api/Query")

public class WebQueryController extends WebBaseController<QueryDoc> {

	@Override
	protected String getRestType() {
		return "Query";
	}

	@Override
	protected QueryDoc httpGet(String type, String id, QueryDoc doc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected QueryDoc httpPut(String type, String id, QueryDoc doc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected QueryDoc httpPost(String type, String id, QueryDoc doc) {

		return doc;
	}

	@Override
	protected QueryDoc httpDelete(String type, String id, QueryDoc doc) {
		// TODO Auto-generated method stub
		return null;
	}

}
