package net36px.passport.boot.data.service;

import net36px.passport.boot.data.entity.Account;
import net36px.passport.boot.data.entity.Auth;

public interface AccountService {

	Account insert(Account account);

	Account get(long id);

	Account get(Auth auth);

	Account update(long id, Account account);

	boolean delete(long id);

	String createNewToken(Account account);

}
