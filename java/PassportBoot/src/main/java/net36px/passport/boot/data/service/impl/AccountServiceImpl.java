package net36px.passport.boot.data.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import net36px.passport.boot.data.entity.Account;
import net36px.passport.boot.data.entity.Auth;
import net36px.passport.boot.data.repository.AccountRepository;
import net36px.passport.boot.data.service.AccountService;
import net36px.passport.boot.data.service.RandomStringService;

public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository accountRepo;

	@Autowired
	private RandomStringService randomStringService;

	@Override
	public Account insert(Account account) {
		return this.accountRepo.save(account);
	}

	@Override
	public Account get(long id) {
		return this.accountRepo.findById(id).get();
	}

	@Override
	public Account update(long id, Account account) {
		Account old = this.get(id);
		account.setId(id);
		account.setEmail(old.getEmail());
		account.setSignUpDate(old.getSignUpDate());
		return this.accountRepo.save(account);
	}

	@Override
	public boolean delete(long id) {
		this.accountRepo.deleteById(id);
		return true;
	}

	@Override
	public String createNewToken(Account account) {
		int length = 64;
		List<String> salt = new ArrayList<>();
		salt.add(account.getId() + "");
		salt.add(account.getEmail() + "");
		return this.randomStringService.nextString(length, salt);
	}

	@Override
	public Account get(Auth auth) {
		long id = auth.getAccount();
		Account account = this.accountRepo.findById(id).get();
		String token1 = auth.getToken();
		String token2 = account.getToken();
		if (token1.trim().length() > 10) {
			if (token1.equals(token2)) {
				return account;
			}
		}
		throw new RuntimeException("no account");
	}

}
