package net36px.passport.app.client;

import net36px.passport.app.data.security.SecuritySpaceManager;
import net36px.passport.app.data.service.CurrentService;
import net36px.passport.app.data.service.SignUpService;

public interface PassportClient {

    PassportSession openSession(String email);

    SignUpService getSignUpService();

    CurrentService getCurrentService();

    SecuritySpaceManager getSecuritySpaceManager();

}
