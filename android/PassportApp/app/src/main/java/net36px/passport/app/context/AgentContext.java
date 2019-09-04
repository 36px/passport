package net36px.passport.app.context;


import net36px.passport.app.client.PassportClient;
import net36px.passport.app.client.PassportConfiguration;
import net36px.passport.app.client.PassportSession;

public class AgentContext {

    private PassportConfiguration configuration;
    private PassportClient client;
    private PassportSession session;

    public PassportConfiguration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(PassportConfiguration configuration) {
        this.configuration = configuration;
    }

    public PassportClient getClient() {
        return client;
    }

    public void setClient(PassportClient client) {
        this.client = client;
    }

    public PassportSession getSession() {
        return session;
    }

    public void setSession(PassportSession session) {
        this.session = session;
    }

}
