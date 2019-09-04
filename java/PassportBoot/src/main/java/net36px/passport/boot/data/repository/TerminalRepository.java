package net36px.passport.boot.data.repository;

import org.springframework.data.repository.CrudRepository;

import net36px.passport.boot.data.TerminalKey;
import net36px.passport.boot.data.entity.Terminal;

public interface TerminalRepository extends CrudRepository<Terminal, TerminalKey> {

}
