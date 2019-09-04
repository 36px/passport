package net36px.passport.app.client;

public interface ClientAgent {

    PassportClient getClient();

    PassportSession getSession();

    PassportSession getSession(String account);

}
