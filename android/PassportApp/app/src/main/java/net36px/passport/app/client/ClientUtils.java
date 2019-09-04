package net36px.passport.app.client;

import android.app.Activity;

import net36px.passport.app.PassportApplication;

public final class ClientUtils {

    public static ClientAgent getClientAgent(Activity activity) {
        PassportApplication app = PassportApplication.getInstance(activity);
        return app.getClientAgent();
    }

}
