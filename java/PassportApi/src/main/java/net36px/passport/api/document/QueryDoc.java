package net36px.passport.api.document;

import net36px.passport.api.BaseRestDocument;
import net36px.passport.api.body.QueryBody;

public class QueryDoc extends BaseRestDocument {

	private QueryBody query;

	public QueryBody getQuery() {
		return query;
	}

	public void setQuery(QueryBody query) {
		this.query = query;
	}

}
