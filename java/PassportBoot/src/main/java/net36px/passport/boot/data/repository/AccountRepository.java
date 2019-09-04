package net36px.passport.boot.data.repository;

import org.springframework.data.repository.CrudRepository;

import net36px.passport.boot.data.entity.Account;

public interface AccountRepository extends CrudRepository<Account, Long> {

}
