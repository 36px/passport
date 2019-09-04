package net36px.passport.app.context;

import java.net.URI;

import net36px.passport.app.client.PassportClient;
import net36px.passport.app.client.PassportConfiguration;
import net36px.passport.app.data.security.SecurityComponentFactory;
import net36px.passport.app.data.security.SecurityKeysHolder;
import net36px.passport.app.data.security.SecuritySpaceManager;
import net36px.passport.app.net.RestClient;

public class ClientContext {

    // the root context

    private PassportClient client;
    private PassportConfiguration configuration;
    private RestClient restClient;
    private URI restServiceURI;

    private final RestServiceSet restServiceSet;
    private final DataServiceSet dataServiceSet;
    private final DataRepositorySet dataRepositorySet;

    // security
    private SecuritySpaceManager securitySpaceManager;
    private SecurityComponentFactory securityComponents;
    private SecurityKeysHolder systemKeysHolder;

    public ClientContext() {
        this.restServiceSet = new RestServiceSet();
        this.dataServiceSet = new DataServiceSet();
        this.dataRepositorySet = new DataRepositorySet();
    }

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

    public SecurityComponentFactory getSecurityComponents() {
        return securityComponents;
    }

    public void setSecurityComponents(SecurityComponentFactory securityComponents) {
        this.securityComponents = securityComponents;
    }

    public RestServiceSet getRestServiceSet() {
        return restServiceSet;
    }

    public DataServiceSet getDataServiceSet() {
        return dataServiceSet;
    }

    public DataRepositorySet getDataRepositorySet() {
        return dataRepositorySet;
    }

    public RestClient getRestClient() {
        return restClient;
    }

    public void setRestClient(RestClient restClient) {
        this.restClient = restClient;
    }

    public URI getRestServiceURI() {
        return restServiceURI;
    }

    public void setRestServiceURI(URI restServiceURI) {
        this.restServiceURI = restServiceURI;
    }

    public SecuritySpaceManager getSecuritySpaceManager() {
        return securitySpaceManager;
    }

    public void setSecuritySpaceManager(SecuritySpaceManager securitySpaceManager) {
        this.securitySpaceManager = securitySpaceManager;
    }

    public SecurityKeysHolder getSystemKeysHolder() {
        return systemKeysHolder;
    }

    public void setSystemKeysHolder(SecurityKeysHolder systemKeysHolder) {
        this.systemKeysHolder = systemKeysHolder;
    }
}
