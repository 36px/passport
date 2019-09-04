package net36px.passport.boot.rest.app.service.impl;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import net36px.passport.api.body.SignInBody;
import net36px.passport.api.document.SignInDoc;
import net36px.passport.boot.data.entity.Account;
import net36px.passport.boot.data.entity.Auth;
import net36px.passport.boot.data.service.AccountService;
import net36px.passport.boot.data.service.AuthService;
import net36px.passport.boot.data.service.MailToService;
import net36px.passport.boot.data.service.RandomStringService;
import net36px.passport.boot.data.service.TicketService;
import net36px.passport.boot.rest.app.service.AppSignInService;
import net36px.passport.boot.ticket.Ticket;
import net36px.passport.boot.ticket.TicketInfo;

public class AppSignInServiceImpl implements AppSignInService {

	@Autowired
	private MailToService mailToService;

	@Autowired
	private TicketService ticketService;

	@Autowired
	private RandomStringService randomStringService;

	@Autowired
	private AccountService accountService;

	@Autowired
	private AuthService authService;

	@Override
	public SignInDoc startSignCommit(SignInDoc doc) {

		// check account
		String email = doc.getSignIn().getName();
		this.checkAccount(email);

		// create verification
		int length = 6;
		List<String> salt = this.makeSalt(doc);
		String verification = this.randomStringService.nextString(length, salt);

		// send mail
		String title = "The verification for sign-in.";
		String content = "verification: " + verification;
		URI email_address = URI.create("mailto://" + email);
		this.mailToService.send(title, content, email_address);

		// create ticket
		final long now = System.currentTimeMillis();
		MyTicketInfo info = new MyTicketInfo(verification);
		info.setRetry(3);
		info.setOneshot(true);
		info.setRunnable(new MyTicketExe(info));
		info.setTimeFrom(now);
		info.setTimeTo(now + (300 * 1000)); // in 5 minutes
		Ticket ticket = this.ticketService.open(info);

		// result
		doc = new SignInDoc();
		SignInBody body = new SignInBody();
		body.setTicket(ticket.getId());
		doc.setSignIn(body);
		return doc;
	}

	@Override
	public SignInDoc updateSignCommit(String id, SignInDoc doc) {
		Ticket ticket = this.ticketService.get(id);
		MyTicketInfo info = (MyTicketInfo) ticket.getInfo();
		info.verification2 = doc.getSignIn().getVerification();
		if (ticket.execute()) {
			doc = new SignInDoc();
			SignInBody body = new SignInBody();
			body.setTicket(ticket.getId());
			doc.setSignIn(body);
			return doc;
		}
		throw new RuntimeException("error");
	}

	// private

	private void checkAccount(String email) {
		Auth auth = this.authService.get(Auth.Mechanisms.EMAIL, email);
		Account account = this.accountService.get(auth);
		if (auth.isEnable() && account.isEnable()) {
			return; // OK
		} else {
			throw new RuntimeException("account is disable");
		}
	}

	private List<String> makeSalt(SignInDoc doc) {
		List<String> list = new ArrayList<>();
		list.add(doc.getSignIn().getName());
		return list;
	}

	private class MyTicketExe implements Runnable {

		private final MyTicketInfo info;

		public MyTicketExe(MyTicketInfo info) {
			this.info = info;
		}

		@Override
		public void run() {
			final int min_len = 5;
			final String v1 = info.verification1.trim();
			final String v2 = info.verification2.trim();
			if (v1.length() < min_len || v2.length() < min_len) {
				// error
			} else if (v1.equals(v2)) {
				return;
			} else {
				// error
			}
			throw new RuntimeException("bad verification");
		}

	}

	private static class MyTicketInfo extends TicketInfo {

		private String verification1;
		private String verification2;

		public MyTicketInfo(String verification) {
			this.verification1 = verification;
		}
	}

}
