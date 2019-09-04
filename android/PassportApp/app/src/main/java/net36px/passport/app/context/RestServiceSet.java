package net36px.passport.app.context;

import net36px.passport.app.net.rest.RestAccount;
import net36px.passport.app.net.rest.RestBackup;
import net36px.passport.app.net.rest.RestRestore;
import net36px.passport.app.net.rest.RestSession;
import net36px.passport.app.net.rest.RestSignIn;
import net36px.passport.app.net.rest.RestSignUp;
import net36px.passport.app.net.rest.RestUnlock;

public class RestServiceSet {

    private RestAccount accountService;
    private RestSession sessionService;
    private RestSignIn signInService;
    private RestSignUp signUpService;
    private RestBackup backupService;
    private RestRestore restoreService;
    private RestUnlock unlockService;

    public RestServiceSet() {
    }

    public RestServiceSet(RestServiceSet init) {

        if (init == null) {
            return;
        }

        this.accountService = init.accountService;
        this.sessionService = init.sessionService;
        this.signInService = init.signInService;
        this.signUpService = init.signUpService;
        this.backupService = init.backupService;
        this.restoreService = init.restoreService;
        this.unlockService = init.unlockService;
    }

    public RestAccount getAccountService() {
        return accountService;
    }

    public void setAccountService(RestAccount accountService) {
        this.accountService = accountService;
    }

    public RestSession getSessionService() {
        return sessionService;
    }

    public void setSessionService(RestSession sessionService) {
        this.sessionService = sessionService;
    }

    public RestSignIn getSignInService() {
        return signInService;
    }

    public void setSignInService(RestSignIn signInService) {
        this.signInService = signInService;
    }

    public RestSignUp getSignUpService() {
        return signUpService;
    }

    public void setSignUpService(RestSignUp signUpService) {
        this.signUpService = signUpService;
    }

    public RestBackup getBackupService() {
        return backupService;
    }

    public void setBackupService(RestBackup backupService) {
        this.backupService = backupService;
    }

    public RestRestore getRestoreService() {
        return restoreService;
    }

    public void setRestoreService(RestRestore restoreService) {
        this.restoreService = restoreService;
    }

    public RestUnlock getUnlockService() {
        return unlockService;
    }

    public void setUnlockService(RestUnlock unlockService) {
        this.unlockService = unlockService;
    }
}
