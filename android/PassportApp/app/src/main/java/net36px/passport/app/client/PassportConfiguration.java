package net36px.passport.app.client;

import java.io.File;
import java.net.URI;

public class PassportConfiguration {

    private URI onlineConfigUrl;
    private File clientDataDir;
    private boolean useSSL;
    private boolean debug;

    public PassportConfiguration() {
    }

    public PassportConfiguration(PassportConfiguration init) {

        if (init == null) {
            return;
        }

        this.onlineConfigUrl = init.onlineConfigUrl;
        this.clientDataDir = init.clientDataDir;
    }

    public URI getOnlineConfigUrl() {
        return onlineConfigUrl;
    }

    public void setOnlineConfigUrl(URI onlineConfigUrl) {
        this.onlineConfigUrl = onlineConfigUrl;
    }

    public File getClientDataDir() {
        return clientDataDir;
    }

    public void setClientDataDir(File clientDataDir) {
        this.clientDataDir = clientDataDir;
    }

    public boolean isUseSSL() {
        return useSSL;
    }

    public void setUseSSL(boolean useSSL) {
        this.useSSL = useSSL;
    }

    public boolean isDebug() {
        return debug;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }
}
