package net36px.passport.app.support;

import java.io.IOException;

import net36px.passport.app.client.PassportClient;
import net36px.passport.app.client.PassportSession;
import net36px.passport.app.client.SessionState;
import net36px.passport.app.context.SessionContext;
import net36px.passport.app.data.security.SecuritySpace;
import net36px.passport.app.data.service.CredentialService;
import net36px.passport.app.data.service.SessionService;
import net36px.passport.app.data.service.SignInService;

public class DefaultPassportSession implements PassportSession {

    private final SessionContext context;

    public DefaultPassportSession(SessionContext context) {
        this.context = context;
    }

    @Override
    public void login(char[] password) {
    }


    @Override
    public PassportClient getClient() {
        return context.getParent().getClient();
    }

    @Override
    public void logout() {
        this.getSessionService().logout();
    }

    @Override
    public void unlock(char[] pass_code) {
        this.getSessionService().unlock(pass_code);
    }

    @Override
    public void lock() {
        this.getSessionService().lock();
    }

    @Override
    public String getAccount() {
        return context.getAccount();
    }

    @Override
    public SessionState getState() {
        return SessionState.INIT;
    }

    @Override
    public SecuritySpace getSecuritySpace() {
        return context.getSecuritySpace();
    }

    @Override
    public SignInService getSignInService() {
        return context.getDataServiceSet().getSignInService();
    }

    @Override
    public CredentialService getCredentialService() {
        return context.getDataServiceSet().getCredentialService();
    }

    @Override
    public SessionService getSessionService() {
        return context.getDataServiceSet().getSessionService();
    }

    @Override
    public void close() throws IOException {
        this.lock();
    }
}
