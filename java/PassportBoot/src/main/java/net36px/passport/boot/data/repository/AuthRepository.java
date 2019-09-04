package net36px.passport.boot.data.repository;

import org.springframework.data.repository.CrudRepository;

import net36px.passport.boot.data.AuthKey;
import net36px.passport.boot.data.entity.Auth;

public interface AuthRepository extends CrudRepository<Auth, AuthKey> {

}
