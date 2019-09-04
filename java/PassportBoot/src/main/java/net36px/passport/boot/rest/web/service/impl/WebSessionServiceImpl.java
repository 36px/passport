package net36px.passport.boot.rest.web.service.impl;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import net36px.passport.api.body.SessionBody;
import net36px.passport.boot.rest.web.service.WebSessionService;

public class WebSessionServiceImpl implements WebSessionService {

	@Override
	public SessionBody getSessionInfo() {

		SessionBody info = new SessionBody();
		Subject subject = SecurityUtils.getSubject();

		if (subject.isAuthenticated()) {
			Session session = subject.getSession();
			info.setAuthenticated(true);
			info.setStartTimestamp(session.getStartTimestamp().getTime());
			info.setAccount(String.valueOf(subject.getPrincipal()));
			info.setDisplayName(this.getText(session, "displayName"));
			info.setEmail(this.getText(session, "email"));
			info.setAvatar(this.getText(session, "avatar"));
		}

		return info;
	}

	private String getText(Session session, String key) {
		Object value = session.getAttribute(key);
		return String.valueOf(value);
	}

	@Override
	public void logout() {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
	}

}
