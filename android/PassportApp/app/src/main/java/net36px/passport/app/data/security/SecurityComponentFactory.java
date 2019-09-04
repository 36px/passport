package net36px.passport.app.data.security;

import java.io.File;
import java.security.KeyPairGenerator;
import java.security.KeyStore;

import javax.crypto.KeyGenerator;

public interface SecurityComponentFactory {

    KeyPairGenerator createKeyPairGenerator();

    KeyGenerator createKeyGenerator();

    KeyStore createKeyStore();

    KeyStore loadKeyStore(File file);

    void save(KeyStore keyStore, File file);

}
