package net36px.passport.app;

import android.app.Activity;
import android.app.Application;

import java.net.URI;

import net36px.passport.app.client.ClientAgent;
import net36px.passport.app.client.PassportConfiguration;
import net36px.passport.app.context.AgentContext;
import net36px.passport.app.support.DefaultClientAgent;

public class PassportApplication extends Application {

    private ClientAgent clientAgent;


    public static PassportApplication getInstance(Activity activity) {
        return (PassportApplication) activity.getApplication();
    }

    public ClientAgent getClientAgent() {
        if (clientAgent == null) {
            clientAgent = this.createClientAgent();
        }
        return clientAgent;
    }

    private ClientAgent createClientAgent() {
        PassportConfiguration config = this.getConfiguration();
        AgentContext ac = new AgentContext();
        ac.setConfiguration(config);
        return new DefaultClientAgent(ac);
    }

    private PassportConfiguration getConfiguration() {
        AppProfile profile = PassportProfiles.getDefaultProfile();
        PassportConfiguration config = new PassportConfiguration();
        PassportProfiles.config(config, profile);
        config.setClientDataDir(this.getFilesDir());
        return config;
    }

}
