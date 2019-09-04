package net36px.passport.app.data.security;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;

import javax.crypto.SecretKey;

class SecuritySpaceImpl implements SecuritySpace {

    public SecuritySpaceImpl(File dir, SecretKey key) {
    }

    @Override
    public File getSpaceDirectory() {
        return null;
    }

    @Override
    public File getFile(String path) {
        return null;
    }

    @Override
    public InputStream openInputStream(String path) {
        return null;
    }

    @Override
    public InputStream openInputStream(File path) {
        return null;
    }

    @Override
    public OutputStream openOutputStream(String path, boolean mkdirs) {
        return null;
    }

    @Override
    public OutputStream openOutputStream(File path, boolean mkdirs) {
        return null;
    }
}
