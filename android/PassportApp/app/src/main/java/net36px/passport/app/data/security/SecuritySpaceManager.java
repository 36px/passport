package net36px.passport.app.data.security;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;

import javax.crypto.SecretKey;

import net36px.passport.app.context.ClientContext;
import net36px.passport.app.context.SessionContext;

public interface SecuritySpaceManager {

    SecuritySpace getSystemSpace(ClientContext context);

    SecuritySpace getUserSpace(SessionContext context);

    File getSystemDir(ClientContext context);

    File getUserDir(SessionContext context);

}
