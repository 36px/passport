package net36px.passport.app.data.security;

import android.security.keystore.KeyProperties;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.KeyPairGenerator;
import java.security.KeyStore;

import javax.crypto.KeyGenerator;

import net36px.passport.app.utils.IOTools;

public class DefaultSecurityCompFactory implements SecurityComponentFactory {

    private final static String KS_PASSWORD = "net.36px.passport";


    public DefaultSecurityCompFactory() {
    }

    @Override
    public KeyPairGenerator createKeyPairGenerator() {
        try {
            return KeyPairGenerator.getInstance(KeyProperties.KEY_ALGORITHM_RSA);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public KeyGenerator createKeyGenerator() {
        try {
            return KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public KeyStore createKeyStore() {
        try {
            return KeyStore.getInstance(KeyStore.getDefaultType());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public KeyStore loadKeyStore(File file) {
        InputStream in = null;
        try {
            in = new FileInputStream(file);
            KeyStore ks = this.createKeyStore();
            ks.load(in, KS_PASSWORD.toCharArray());
            return ks;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            IOTools.close(in);
        }
    }

    @Override
    public void save(KeyStore keyStore, File file) {
        OutputStream out = null;
        try {
            out = new FileOutputStream(file);
            keyStore.store(out, KS_PASSWORD.toCharArray());
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            IOTools.close(out);
        }
    }
}
