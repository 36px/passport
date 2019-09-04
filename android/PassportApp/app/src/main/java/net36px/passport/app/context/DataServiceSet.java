package net36px.passport.app.context;

import net36px.passport.app.data.service.AccountService;
import net36px.passport.app.data.service.BackupService;
import net36px.passport.app.data.service.CredentialService;
import net36px.passport.app.data.service.CurrentService;
import net36px.passport.app.data.service.SessionService;
import net36px.passport.app.data.service.SignInService;
import net36px.passport.app.data.service.SignUpService;

public class DataServiceSet {

    private AccountService accountService;
    private CredentialService credentialService;
    private SessionService sessionService;
    private SignInService signInService;
    private BackupService backupService;
    private SignUpService signUpService;
    private CurrentService currentService;

    public DataServiceSet() {
    }

    public DataServiceSet(DataServiceSet init) {

        if (init == null) {
            return;
        }

        this.accountService = init.accountService;
        this.credentialService = init.credentialService;
        this.sessionService = init.sessionService;
        this.signInService = init.signInService;
        this.signUpService = init.signUpService;
        this.backupService = init.backupService;
        this.currentService = init.currentService;

    }

    public AccountService getAccountService() {
        return accountService;
    }

    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    public CredentialService getCredentialService() {
        return credentialService;
    }

    public void setCredentialService(CredentialService credentialService) {
        this.credentialService = credentialService;
    }

    public SessionService getSessionService() {
        return sessionService;
    }

    public void setSessionService(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    public SignInService getSignInService() {
        return signInService;
    }

    public void setSignInService(SignInService signInService) {
        this.signInService = signInService;
    }

    public BackupService getBackupService() {
        return backupService;
    }

    public void setBackupService(BackupService backupService) {
        this.backupService = backupService;
    }

    public SignUpService getSignUpService() {
        return signUpService;
    }

    public void setSignUpService(SignUpService signUpService) {
        this.signUpService = signUpService;
    }

    public CurrentService getCurrentService() {
        return currentService;
    }

    public void setCurrentService(CurrentService currentService) {
        this.currentService = currentService;
    }
}
