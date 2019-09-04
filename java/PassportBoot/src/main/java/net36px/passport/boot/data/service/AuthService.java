package net36px.passport.boot.data.service;

import net36px.passport.boot.data.AuthKey;
import net36px.passport.boot.data.entity.Auth;

public interface AuthService {

	Auth insert(Auth auth);

	Auth update(Auth auth);

	Auth get(AuthKey key);

	Auth get(String mechanism, String name);

	boolean exists(AuthKey key);

	boolean delete(Auth auth);

	boolean delete(AuthKey key);

}
