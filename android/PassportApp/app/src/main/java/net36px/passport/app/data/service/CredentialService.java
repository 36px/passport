package net36px.passport.app.data.service;

import net36px.passport.app.data.model.Credential;

public interface CredentialService {

    Credential insert(Credential item);

    Credential update(String name, Credential item);

    Credential get(String name);

    String[] listByDomain(String domain);

    boolean delete(String name);

}
