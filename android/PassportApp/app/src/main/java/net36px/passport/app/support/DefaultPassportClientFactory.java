package net36px.passport.app.support;

import net36px.passport.app.client.PassportClient;
import net36px.passport.app.client.PassportClientFactory;
import net36px.passport.app.client.PassportConfiguration;
import net36px.passport.app.context.ClientContext;
import net36px.passport.app.context.builder.ClientContextBuilder;
import net36px.passport.app.net.OkRestClient;

public class DefaultPassportClientFactory implements PassportClientFactory {

    @Override
    public PassportClient create(PassportConfiguration config) {
        ClientContextBuilder builder = new ClientContextBuilder(config);
        ClientContext context = builder.create();
        return context.getClient();
    }
}
