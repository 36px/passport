package net36px.passport.app.data.security;

import java.security.KeyPair;

import javax.crypto.SecretKey;

public class SecurityKeysHolder {

    private SecretKey secretKey;
    private KeyPair keyPair;
    private SecurityKeys keys;

    public SecretKey getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(SecretKey secretKey) {
        this.secretKey = secretKey;
    }

    public KeyPair getKeyPair() {
        return keyPair;
    }

    public void setKeyPair(KeyPair keyPair) {
        this.keyPair = keyPair;
    }

    public SecurityKeys getKeys() {
        return keys;
    }

    public void setKeys(SecurityKeys keys) {
        this.keys = keys;
    }
}
