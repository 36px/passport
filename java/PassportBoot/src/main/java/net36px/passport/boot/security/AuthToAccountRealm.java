package net36px.passport.boot.security;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.realm.Realm;
import org.springframework.beans.factory.annotation.Autowired;

import net36px.passport.boot.data.entity.Account;
import net36px.passport.boot.data.service.AccountService;

public class AuthToAccountRealm implements Realm {

	@Autowired
	private AccountService accountService;

	@Override
	public String getName() {
		return "auth-to-account";
	}

	@Override
	public boolean supports(AuthenticationToken token) {
		return (token instanceof AuthToAccountToken);
	}

	@Override
	public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

		final AuthToAccountToken token2 = (AuthToAccountToken) token;
		final Account account = this.accountService.get(token2.getAccountId());

		String account_token_1 = account.getToken();
		String account_token_2 = token2.getAccountToken();

		if (!account_token_1.equals(account_token_2)) {
			throw new AuthenticationException("bad token");
		}

		String user = account.getId() + "";
		String secret = account.getToken();
		String realm = this.getName();
		return new SimpleAuthenticationInfo(user, secret, realm);
	}

}
