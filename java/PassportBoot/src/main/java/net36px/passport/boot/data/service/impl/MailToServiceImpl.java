package net36px.passport.boot.data.service.impl;

import java.net.URI;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;

import net36px.passport.boot.data.service.MailToService;
import net36px.passport.boot.data.service.SettingService;
import net36px.passport.boot.utils.Settings;

public class MailToServiceImpl implements MailToService {

	@Autowired
	private SettingService settingService;

	@Override
	public void send(String title, String content, URI email_address) {

		String to_addr = email_address.getUserInfo() + "@" + email_address.getHost();

		try {
			this.send(title, content, to_addr);
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}

	}

	private void send(String title, String content, String to_addr) throws MessagingException {

		Settings settings = this.settingService.getSettings();
		String from_addr = settings.getMailSenderAddress();
		String password = settings.getMailSenderPassword();
		String user = settings.getMailSenderUser();
		String smtp_host = settings.getMailSmtpHost();

		Properties props = new Properties();
		props.put("mail.smtp.host", smtp_host);
		props.put("mail.smtp.auth", "true");
		props.put("mail.transport.protocol", "smtp");

		Session session = Session.getInstance(props);
		// session.setProtocolForAddress(addresstype, protocol);

		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress(from_addr));
		message.setRecipient(Message.RecipientType.TO, new InternetAddress(to_addr));
		message.setSubject(title);
		// message.setText(mailContent);
		message.setContent(content, "text/html;charset=utf-8");
		message.setSentDate(new Date());
		message.saveChanges();

		Transport transport = null;

		try {
			transport = session.getTransport();
			transport.connect(user, password);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		} finally {
			this.close(transport);
		}

	}

	private void close(Transport transport) {
		if (transport == null) {
			return;
		}
		try {
			transport.close();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

}
