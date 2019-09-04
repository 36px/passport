package net36px.passport.app.data.security;

import java.security.KeyPair;

import javax.crypto.SecretKey;

public interface SecurityKeys {

    KeyPair getKeyPair();

    SecretKey getSecretKey();

}
