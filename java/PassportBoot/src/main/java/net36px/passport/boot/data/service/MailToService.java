package net36px.passport.boot.data.service;

import java.net.URI;

public interface MailToService {

	void send(String title, String content, URI email_address);

}
