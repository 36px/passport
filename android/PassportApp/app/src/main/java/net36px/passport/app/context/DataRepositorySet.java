package net36px.passport.app.context;

import net36px.passport.app.data.repository.CredentialRepository;
import net36px.passport.app.data.repository.KeyPairRepository;
import net36px.passport.app.data.repository.SessionRepository;
import net36px.passport.app.data.service.BackupService;

public class DataRepositorySet {

    private CredentialRepository credentialRepository;
    private KeyPairRepository keyPairRepository;
    private SessionRepository sessionRepository;
    private BackupService securityKeyRepository;

    public DataRepositorySet() {
    }

    public DataRepositorySet(DataRepositorySet init) {

        if (init == null) {
            return;
        }

        this.credentialRepository = init.credentialRepository;
        this.securityKeyRepository = init.securityKeyRepository;
        this.keyPairRepository = init.keyPairRepository;
        this.sessionRepository = init.sessionRepository;
    }

    public CredentialRepository getCredentialRepository() {
        return credentialRepository;
    }

    public void setCredentialRepository(CredentialRepository credentialRepository) {
        this.credentialRepository = credentialRepository;
    }

    public BackupService getSecurityKeyRepository() {
        return securityKeyRepository;
    }

    public void setSecurityKeyRepository(BackupService securityKeyRepository) {
        this.securityKeyRepository = securityKeyRepository;
    }

    public KeyPairRepository getKeyPairRepository() {
        return keyPairRepository;
    }

    public void setKeyPairRepository(KeyPairRepository keyPairRepository) {
        this.keyPairRepository = keyPairRepository;
    }

    public SessionRepository getSessionRepository() {
        return sessionRepository;
    }

    public void setSessionRepository(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }
}
