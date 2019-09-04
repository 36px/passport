package net36px.passport.app.data.security;

import java.io.File;

import javax.crypto.SecretKey;

import net36px.passport.app.context.ClientContext;
import net36px.passport.app.context.SessionContext;
import net36px.passport.app.utils.HashTools;

public class DefaultSecuritySpaceManager implements SecuritySpaceManager {

    public DefaultSecuritySpaceManager() {
    }

    @Override
    public SecuritySpace getSystemSpace(ClientContext context) {
        SecretKey key = context.getSystemKeysHolder().getKeys().getSecretKey();
        File dir = this.getSystemDir(context);
        return new SecuritySpaceImpl(dir, key);
    }

    @Override
    public SecuritySpace getUserSpace(SessionContext context) {
        SecretKey key = context.getUserKeysHolder().getKeys().getSecretKey();
        File dir = this.getUserDir(context);
        return new SecuritySpaceImpl(dir, key);
    }

    @Override
    public File getSystemDir(ClientContext context) {
        File base = context.getConfiguration().getClientDataDir();
        return new File(base, "s");
    }

    @Override
    public File getUserDir(SessionContext context) {
        String user = context.getAccount();
        String user2 = HashTools.getInstance(HashTools.SHA1).hashString(user).substring(0, 16);
        File base = context.getParent().getConfiguration().getClientDataDir();
        return new File(base, "u/" + user2);
    }
}
