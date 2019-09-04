package net36px.passport.app.support;

import net36px.passport.app.client.PassportClient;
import net36px.passport.app.client.PassportSession;
import net36px.passport.app.context.ClientContext;
import net36px.passport.app.context.SessionContext;
import net36px.passport.app.context.builder.SessionContextBuilder;
import net36px.passport.app.data.security.SecuritySpaceManager;
import net36px.passport.app.data.service.CurrentService;
import net36px.passport.app.data.service.SignInService;
import net36px.passport.app.data.service.SignUpService;

public class DefaultPassportClient implements PassportClient {

    private final ClientContext context;

    public DefaultPassportClient(ClientContext context) {
        this.context = context;
    }

    @Override
    public PassportSession openSession(String email) {
        SessionContextBuilder builder = new SessionContextBuilder(context, email);
        SessionContext session_context = builder.create();
        return session_context.getSession();
    }

    @Override
    public SignUpService getSignUpService() {
        return context.getDataServiceSet().getSignUpService();
    }

    @Override
    public CurrentService getCurrentService() {
        return context.getDataServiceSet().getCurrentService();
    }

    @Override
    public SecuritySpaceManager getSecuritySpaceManager() {
        return context.getSecuritySpaceManager();
    }
}
