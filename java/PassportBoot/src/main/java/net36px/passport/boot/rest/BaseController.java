package net36px.passport.boot.rest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public abstract class BaseController<DOC> {

	protected abstract String getRestType();

	protected abstract DOC httpGet(String type, String id, DOC doc);

	protected abstract DOC httpPut(String type, String id, DOC doc);

	protected abstract DOC httpPost(String type, String id, DOC doc);

	protected abstract DOC httpDelete(String type, String id, DOC doc);

	@RequestMapping(method = RequestMethod.POST)
	public DOC doPost(@RequestBody DOC doc) {
		String type = this.getRestType();
		String id = null;
		return this.httpPost(type, id, doc);
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public DOC doPost2(@RequestBody DOC doc) {
		String type = this.getRestType();
		String id = null;
		return this.httpPost(type, id, doc);
	}

	@RequestMapping(value = "/{id:.+}", method = RequestMethod.PUT)
	public DOC doPut(@PathVariable String id, @RequestBody DOC doc) {
		String type = this.getRestType();
		return this.httpPut(type, id, doc);
	}

	@RequestMapping(value = "/{id:.+}", method = RequestMethod.DELETE)
	public DOC doDelete(@PathVariable String id) {
		String type = this.getRestType();
		DOC doc = null;
		return this.httpDelete(type, id, doc);
	}

	@RequestMapping(value = "/{id:.+}", method = RequestMethod.GET)
	public DOC doGet(@PathVariable String id) {
		String type = this.getRestType();
		DOC doc = null;
		return this.httpGet(type, id, doc);
	}

	@RequestMapping(method = RequestMethod.GET)
	public DOC doGetList() {
		String type = this.getRestType();
		String id = null;
		DOC doc = null;
		return this.httpGet(type, id, doc);
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public DOC doGetList2() {
		String type = this.getRestType();
		String id = null;
		DOC doc = null;
		return this.httpGet(type, id, doc);
	}

}
