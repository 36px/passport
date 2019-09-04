package net36px.passport.app.context.builder;

import net36px.passport.app.client.PassportSession;
import net36px.passport.app.context.ClientContext;
import net36px.passport.app.context.SessionContext;
import net36px.passport.app.support.DefaultPassportSession;

public class SessionContextBuilder {

    private final String account;
    private final ClientContext clientContext;

    public SessionContextBuilder(ClientContext context, String email) {
        this.clientContext = context;
        this.account = email;
    }

    public SessionContext create() {
        SessionContext session_context = new SessionContext(clientContext);
        PassportSession session = new DefaultPassportSession(session_context);
        session_context.setSession(session);
        session_context.setAccount(account);
        return session_context;
    }

}
