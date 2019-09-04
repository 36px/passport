package net36px.passport.app;

import java.net.URI;

import net36px.passport.app.client.PassportConfiguration;

public final class PassportProfiles {

    public static AppProfile getDefaultProfile() {
        return AppProfile.DEBUG;
    }

    public static PassportConfiguration config(PassportConfiguration config, AppProfile profile) {

        switch (profile) {
            case DEBUG:
                config.setOnlineConfigUrl(URI.create("https://bitwormhole.com/etc/passport/passport-debug.conf"));
                config.setDebug(true);
                break;
            case TEST:
                config.setOnlineConfigUrl(URI.create("https://bitwormhole.com/etc/passport/passport-test.conf"));
                break;
            case DEVELOP:
                config.setOnlineConfigUrl(URI.create("https://bitwormhole.com/etc/passport/passport-develop.conf"));
                break;
            case PRODUCT:
            default:
                config.setOnlineConfigUrl(URI.create("https://bitwormhole.com/etc/passport/passport.conf"));
                config.setUseSSL(true);
                break;
        }

        return config;
    }

}
