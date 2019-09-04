package net36px.passport.app.client;

import java.io.Closeable;

import net36px.passport.app.data.security.SecuritySpace;
import net36px.passport.app.data.service.CredentialService;
import net36px.passport.app.data.service.SessionService;
import net36px.passport.app.data.service.SignInService;

public interface PassportSession extends Closeable {

    PassportClient getClient();

    String getAccount();

    SessionState getState();

    SecuritySpace getSecuritySpace();

    // services

    SignInService getSignInService();

    CredentialService getCredentialService();

    SessionService getSessionService();

    // action

    void login(char[] verification);

    void logout();

    void unlock(char[] pass_code);

    void lock();

}
