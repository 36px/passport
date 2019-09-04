package net36px.passport.boot.rest.web.service.impl;

import java.net.URI;
import java.util.Random;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import net36px.passport.boot.data.AuthKey;
import net36px.passport.boot.data.entity.Auth;
import net36px.passport.boot.data.service.AuthService;
import net36px.passport.boot.data.service.MailToService;
import net36px.passport.boot.data.service.TicketService;
import net36px.passport.boot.rest.web.service.WebSignInService;
import net36px.passport.boot.security.AuthToAccountToken;
import net36px.passport.boot.ticket.Ticket;
import net36px.passport.boot.ticket.TicketInfo;

public class WebSignInServiceImpl implements WebSignInService {

	@Autowired
	private MailToService mailtoService;

	@Autowired
	private TicketService ticketService;

	// @Autowired
	// private AccountService accountService;

	@Autowired
	private AuthService authService;

	@Override
	public String signIn1(String email) {

		long now = System.currentTimeMillis();
		String verification = this.generateVerificationCode(now);
		email = this.normalizeEmailAddress(email);

		// check email exists
		this.checkExistsOfEmail(email);

		// ticket
		SignInTicketInfo sign_up_tran = new SignInTicketInfo();
		sign_up_tran.verification1 = verification;
		sign_up_tran.email = email;
		sign_up_tran.setTimeFrom(now);
		sign_up_tran.setTimeTo(now + (3000 * 1000)); // verify in 50 minutes
		sign_up_tran.setRetry(3);
		sign_up_tran.setRunnable(new SignInRunnable(sign_up_tran));
		Ticket ticket = this.ticketService.open(sign_up_tran);

		// send mail
		String title = "Your sign-in verification code.";
		String content = "verification: " + verification;
		URI email_address = URI.create("mailto://" + email);
		this.mailtoService.send(title, content, email_address);

		return ticket.getId();
	}

	@Override
	public void signIn2(String ticketId, String verification) {
		Ticket ticket = this.ticketService.get(ticketId);
		SignInTicketInfo sign_in_ticket = (SignInTicketInfo) ticket.getInfo();
		sign_in_ticket.verification2 = verification;
		if (ticket.execute()) {
			this.login(sign_in_ticket.email);
		}
	}

	private void checkExistsOfEmail(String email) {
		AuthKey key = new AuthKey();
		key.setMechanism(Auth.Mechanisms.EMAIL);
		key.setName(email);
		if (!this.authService.exists(key)) {
			throw new RuntimeException("no account");
		}
	}

	private void login(String email) {

		AuthKey key = new AuthKey();
		key.setMechanism(Auth.Mechanisms.EMAIL);
		key.setName(email);
		Auth auth = this.authService.get(key);

		AuthToAccountToken auth_token = new AuthToAccountToken();
		auth_token.setAccountId(auth.getAccount());
		auth_token.setAccountToken(auth.getToken());

		Subject subject = SecurityUtils.getSubject();
		subject.login(auth_token);

	}

	private String normalizeEmailAddress(String email) {
		email = email.trim();
		int index = email.indexOf('@');
		String user = email.substring(0, index);
		String host = email.substring(index + 1);
		if (user.length() > 0 && host.length() > 0) {
			return email;
		} else {
			throw new RuntimeException("bad email address: " + email);
		}
	}

	private String generateVerificationCode(long now) {
		Random rand = new Random(now);
		long n = rand.nextLong() & 0xffffff;
		if (n < 100000) {
			n += 100000;
		}
		String str = String.valueOf(n);
		if (str.length() > 6) {
			return str.substring(0, 6);
		} else {
			return str;
		}
	}

	private static class SignInRunnable implements Runnable {

		private final SignInTicketInfo ticket_info;

		public SignInRunnable(SignInTicketInfo info) {
			this.ticket_info = info;
		}

		@Override
		public void run() {

			String ver1 = this.ticket_info.verification1;
			String ver2 = this.ticket_info.verification2;

			if (ver1.equals(ver2)) {
				return;
			} else {
				throw new RuntimeException("bad verification");
			}

		}

	}

	private static class SignInTicketInfo extends TicketInfo {

		String verification1; // generate by service
		String verification2; // commit by user
		String email;

	}

}
