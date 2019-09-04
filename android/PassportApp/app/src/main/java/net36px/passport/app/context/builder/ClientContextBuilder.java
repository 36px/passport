package net36px.passport.app.context.builder;

import net36px.passport.app.client.PassportConfiguration;
import net36px.passport.app.context.ClientContext;
import net36px.passport.app.context.DataRepositorySet;
import net36px.passport.app.context.DataServiceSet;
import net36px.passport.app.context.RestServiceSet;
import net36px.passport.app.data.security.AutoSecurityCompFactory;
import net36px.passport.app.data.security.SecurityKeysHolder;
import net36px.passport.app.data.security.SystemKeys;
import net36px.passport.app.data.service.impl.SignUpServiceImpl;
import net36px.passport.app.net.OkRestClient;
import net36px.passport.app.net.rest.impl.RestSignUpImpl;
import net36px.passport.app.support.DefaultPassportClient;

public class ClientContextBuilder {

    private final PassportConfiguration config;

    public ClientContextBuilder(PassportConfiguration conf) {
        this.config = conf;
    }

    public ClientContext create() {

        ClientContext client_context = new ClientContext();

        client_context.setConfiguration(new PassportConfiguration(config));
        client_context.setClient(new DefaultPassportClient(client_context));
        client_context.setRestClient(new OkRestClient(client_context));
        client_context.setSecurityComponents(null);


        // keys
        SecurityKeysHolder system_keys_holder = new SecurityKeysHolder();
        client_context.setSecurityComponents(new AutoSecurityCompFactory());
        client_context.setSystemKeysHolder(system_keys_holder);
        system_keys_holder.setKeys(new SystemKeys(client_context, system_keys_holder));

        // data-repository
        DataRepositorySet data_repo_set = client_context.getDataRepositorySet();
        ;

        // data-service
        DataServiceSet data_service_set = client_context.getDataServiceSet();
        data_service_set.setSignUpService(new SignUpServiceImpl(client_context));

        // rest
        client_context.setRestClient(new OkRestClient(client_context));
        RestServiceSet rest_service_set = client_context.getRestServiceSet();
        rest_service_set.setSignUpService(new RestSignUpImpl(client_context));

        this.startup(client_context);
        return client_context;
    }

    private void startup(ClientContext context) {
        context.getSystemKeysHolder().getKeys().getSecretKey();
    }

}
