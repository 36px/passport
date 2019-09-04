package net36px.passport.app.data.security;

import java.io.File;
import java.security.KeyPair;
import java.security.KeyStore;

import javax.crypto.SecretKey;

import net36px.passport.app.context.ClientContext;

public class SystemKeys implements SecurityKeys {

    private final SecurityKeysHolder keys_holder;
    private final ClientContext client_context;

    public SystemKeys(ClientContext context, SecurityKeysHolder holder) {
        this.client_context = context;
        this.keys_holder = holder;
    }

    @Override
    public KeyPair getKeyPair() {
        KeyPair key_pair = keys_holder.getKeyPair();
        if (key_pair == null) {
            key_pair = this.loadKeyPair();
            keys_holder.setKeyPair(key_pair);
        }
        return key_pair;
    }

    @Override
    public SecretKey getSecretKey() {
        SecretKey key = keys_holder.getSecretKey();
        if (key == null) {
            key = this.loadSecretKey();
            keys_holder.setSecretKey(key);
        }
        return key;
    }

    private SecretKey loadSecretKey() {

        SecurityComponentFactory keys_factory = client_context.getSecurityComponents();
        File file = this.getKeyStoreFile();
        KeyStore ks = null;

        if (file.exists()) {
            ks = keys_factory.loadKeyStore(file);
        } else {
            ks = keys_factory.createKeyStore();
        }


        return null;
    }

    private File getKeyStoreFile() {
    }

    private KeyPair loadKeyPair() {
        return null;
    }

}
