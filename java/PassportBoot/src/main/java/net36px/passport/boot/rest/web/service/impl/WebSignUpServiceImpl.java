package net36px.passport.boot.rest.web.service.impl;

import java.net.URI;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;

import net36px.passport.boot.data.entity.Account;
import net36px.passport.boot.data.entity.Auth;
import net36px.passport.boot.data.service.AccountService;
import net36px.passport.boot.data.service.AuthService;
import net36px.passport.boot.data.service.MailToService;
import net36px.passport.boot.data.service.TicketService;
import net36px.passport.boot.rest.web.service.WebSignUpService;
import net36px.passport.boot.ticket.Ticket;
import net36px.passport.boot.ticket.TicketInfo;

public class WebSignUpServiceImpl implements WebSignUpService {

	@Autowired
	private MailToService mailtoService;

	@Autowired
	private TicketService ticketService;

	@Autowired
	private AccountService accountService;

	@Autowired
	private AuthService authService;

	@Override
	public String signUp1(String email) {

		long now = System.currentTimeMillis();
		String verification = this.generateVerificationCode(now);
		email = this.normalizeEmailAddress(email);

		// check account exists
		if (this.isAccountExists(email)) {
			throw new RuntimeException("account exists");
		}

		// transactor
		SignUpTicketInfo sign_up_tran = new SignUpTicketInfo();
		sign_up_tran.verification1 = verification;
		sign_up_tran.email = email;
		sign_up_tran.setTimeFrom(now);
		sign_up_tran.setTimeTo(now + (300 * 1000)); // verify in 5 minutes
		sign_up_tran.setRetry(3);
		sign_up_tran.setOneshot(true);
		sign_up_tran.setRunnable(new SignUpRunnable(sign_up_tran));
		Ticket transactor = this.ticketService.open(sign_up_tran);

		// send mail
		String title = "Your sign-up verification code.";
		String content = "verification: " + verification;
		URI email_address = URI.create("mailto://" + email);
		this.mailtoService.send(title, content, email_address);

		return transactor.getId();
	}

	@Override
	public void signUp2(String ticketId, String verification) {

		Ticket ticket = this.ticketService.get(ticketId);
		SignUpTicketInfo sign_up_tran = (SignUpTicketInfo) ticket.getInfo();
		sign_up_tran.verification2 = verification;

		// check account exists
		if (this.isAccountExists(sign_up_tran.email)) {
			throw new RuntimeException("account exists");
		}

		if (ticket.execute()) {
			this.createNewAccount(sign_up_tran.email);
		} else {
			throw new RuntimeException("error");
		}

	}

	// private

	private boolean isAccountExists(String email) {
		try {
			Auth auth = this.authService.get(Auth.Mechanisms.EMAIL, email);
			Account account = this.accountService.get(auth);
			return (account != null);
		} catch (Exception e) {
			return false;
		}
	}

	private void createNewAccount(String email) {

		// add account

		Account account = new Account();
		account.setEmail(email);
		account.setSignUpDate(System.currentTimeMillis());
		account.setDisplayName(this.getUserOfEmailAddress(email));
		account.setToken(this.accountService.createNewToken(account));
		account = this.accountService.insert(account);

		// add auth

		Auth auth = new Auth();
		auth.setAccount(account.getId());
		auth.setToken(account.getToken());
		auth.setName(email);
		auth.setMechanism(Auth.Mechanisms.EMAIL);
		this.authService.insert(auth);

	}

	private String getUserOfEmailAddress(String email) {
		int index = email.indexOf('@');
		return email.substring(0, index);
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

	private static class SignUpRunnable implements Runnable {

		private final SignUpTicketInfo transaction;

		public SignUpRunnable(SignUpTicketInfo tran) {
			this.transaction = tran;
		}

		@Override
		public void run() {

			String ver1 = this.transaction.verification1;
			String ver2 = this.transaction.verification2;

			if (ver1.equals(ver2)) {
				return;
			} else {
				throw new RuntimeException("bad verification");
			}

		}

	}

	private static class SignUpTicketInfo extends TicketInfo {

		String verification1; // generate by service
		String verification2; // commit by user
		String email;

	}

}
