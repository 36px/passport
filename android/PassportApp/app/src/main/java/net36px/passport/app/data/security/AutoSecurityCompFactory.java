package net36px.passport.app.data.security;

import java.io.File;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.KeyStoreException;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class AutoSecurityCompFactory implements SecurityComponentFactory {

    private SecurityComponentFactory mImpl;

    public AutoSecurityCompFactory() {
    }


    public SecurityComponentFactory getImpl() {

        SecurityComponentFactory impl = this.mImpl;

        if (impl == null) {
            try {
                impl = new AndroidSecurityCompFactory();
                this.test(impl);
                this.mImpl = impl;
            } catch (Exception e) {
                impl = null;
            }
        }

        if (impl == null) {
            impl = new DefaultSecurityCompFactory();
            this.mImpl = impl;
        }

        return impl;
    }

    private void test(SecurityComponentFactory impl) throws KeyStoreException {
        KeyStore ks = impl.createKeyStore();
        ks.aliases();
    }

    @Override
    public KeyPairGenerator createKeyPairGenerator() {
        return this.getImpl().createKeyPairGenerator();
    }

    @Override
    public KeyGenerator createKeyGenerator() {
        return this.getImpl().createKeyGenerator();
    }

    @Override
    public KeyStore createKeyStore() {
        return this.getImpl().createKeyStore();
    }

    @Override
    public KeyStore loadKeyStore(File file) {
        return this.getImpl().loadKeyStore(file);
    }

    @Override
    public void save(KeyStore keyStore, File file) {
        this.getImpl().save(keyStore, file);
    }
}
