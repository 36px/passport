package net36px.passport.boot.data.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import net36px.passport.boot.data.AuthKey;
import net36px.passport.boot.data.entity.Auth;
import net36px.passport.boot.data.repository.AuthRepository;
import net36px.passport.boot.data.service.AuthService;

public class AuthServiceImpl implements AuthService {

	@Autowired
	private AuthRepository authRepo;

	@Override
	public Auth insert(Auth auth) {
		return this.authRepo.save(auth);
	}

	@Override
	public Auth update(Auth auth) {
		return this.authRepo.save(auth);
	}

	@Override
	public Auth get(AuthKey key) {
		return this.authRepo.findById(key).get();
	}

	@Override
	public boolean delete(Auth auth) {
		this.authRepo.delete(auth);
		return true;
	}

	@Override
	public boolean delete(AuthKey key) {
		this.authRepo.deleteById(key);
		return true;
	}

	@Override
	public boolean exists(AuthKey key) {
		return this.authRepo.existsById(key);
	}

	@Override
	public Auth get(String mechanism, String name) {
		AuthKey key = new AuthKey();
		key.setMechanism(mechanism);
		key.setName(name);
		return this.authRepo.findById(key).get();
	}

}
