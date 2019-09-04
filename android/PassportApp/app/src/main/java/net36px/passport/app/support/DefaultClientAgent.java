package net36px.passport.app.support;

import net36px.passport.app.client.ClientAgent;
import net36px.passport.app.client.PassportClient;
import net36px.passport.app.client.PassportClientFactory;
import net36px.passport.app.client.PassportConfiguration;
import net36px.passport.app.client.PassportSession;
import net36px.passport.app.context.AgentContext;
import net36px.passport.app.utils.IOTools;

public class DefaultClientAgent implements ClientAgent {

    private final AgentContext context;

    public DefaultClientAgent(AgentContext context) {
        this.context = context;
    }

    @Override
    public PassportClient getClient() {
        PassportClient client = context.getClient();
        if (client == null) {
            PassportConfiguration config = context.getConfiguration();
            PassportClientFactory client_factory = new DefaultPassportClientFactory();
            client = client_factory.create(config);
            context.setClient(client);
        }
        return client;
    }

    @Override
    public PassportSession getSession() {
        return this.getSession(null);
    }

    @Override
    public PassportSession getSession(String account) {
        PassportSession session = context.getSession();
        if (this.isSessionForAccount(account, session)) {
            return session;
        } else {
            IOTools.close(context.getSession());
            context.setSession(null);
            session = null;
        }
        if (session == null) {
            PassportClient client = this.getClient();
            session = client.openSession(account);
            context.setSession(session);
        }
        return session;
    }

    private boolean isSessionForAccount(String account, PassportSession session) {
        if (account == null) {
            return true;
        } else if (session == null) {
            return false;
        } else {
            return account.equals(session.getAccount());
        }
    }

}
