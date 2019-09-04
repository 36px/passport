package net36px.passport.boot.security;

import org.apache.shiro.authc.AuthenticationToken;

public class AuthToAccountToken implements AuthenticationToken {

	private static final long serialVersionUID = 2227365009440352138L;

	private long accountId;
	private String accountToken;

	@Override
	public Object getCredentials() {
		return this.accountToken;
	}

	@Override
	public Object getPrincipal() {
		return this.accountId;
	}

	public long getAccountId() {
		return accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

	public String getAccountToken() {
		return accountToken;
	}

	public void setAccountToken(String accountToken) {
		this.accountToken = accountToken;
	}

}
