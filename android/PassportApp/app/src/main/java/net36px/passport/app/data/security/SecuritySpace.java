package net36px.passport.app.data.security;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;

public interface SecuritySpace {

    File getSpaceDirectory();

    File getFile(String path);

    InputStream openInputStream(String path);

    InputStream openInputStream(File path);

    OutputStream openOutputStream(String path, boolean mkdirs);

    OutputStream openOutputStream(File path, boolean mkdirs);

}
