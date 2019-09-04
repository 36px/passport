package net36px.passport.api.document;

import net36px.passport.api.BaseRestDocument;
import net36px.passport.api.body.UnlockBody;

public class UnlockDoc extends BaseRestDocument {

	private UnlockBody unlock;

	public UnlockBody getUnlock() {
		return unlock;
	}

	public void setUnlock(UnlockBody unlock) {
		this.unlock = unlock;
	}

}
