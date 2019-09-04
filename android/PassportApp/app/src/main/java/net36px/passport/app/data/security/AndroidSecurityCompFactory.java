package net36px.passport.app.data.security;

import android.security.keystore.KeyProperties;

import java.io.File;
import java.security.KeyPairGenerator;
import java.security.KeyStore;

import javax.crypto.KeyGenerator;

public class AndroidSecurityCompFactory implements SecurityComponentFactory {

    private final static String ANDROID_PROVIDER = "AndroidKeyStore";

    public AndroidSecurityCompFactory() {
    }

    @Override
    public KeyPairGenerator createKeyPairGenerator() {
        try {
            return KeyPairGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, ANDROID_PROVIDER);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public KeyGenerator createKeyGenerator() {
        try {
            return KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_RSA, ANDROID_PROVIDER);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public KeyStore createKeyStore() {
        try {
            return KeyStore.getInstance(ANDROID_PROVIDER);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public KeyStore loadKeyStore(File file) {
        try {
            KeyStore ks = this.createKeyStore();
            ks.load(null);
            return ks;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(KeyStore keyStore, File file) {
        try {
            keyStore.store(null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
