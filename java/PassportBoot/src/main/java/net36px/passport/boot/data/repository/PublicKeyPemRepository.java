package net36px.passport.boot.data.repository;

import org.springframework.data.repository.CrudRepository;

import net36px.passport.boot.data.entity.PublicKeyPem;

public interface PublicKeyPemRepository extends CrudRepository<PublicKeyPem, String> {

}
