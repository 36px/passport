package net36px.passport.boot.data.repository;

import org.springframework.data.repository.CrudRepository;

import net36px.passport.boot.data.entity.SecretKeyPem;

public interface SecretKeyPemRepository extends CrudRepository<SecretKeyPem, String> {

}
