package net36px.passport.app.utils;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class HashTools {

    public static final String SHA_512 = "SHA-512";
    public static final String SHA_256 = "SHA-256";
    public static final String SHA1 = "SHA-1";
    public static final String MD5 = "MD5";

    public static HashTools getInstance(String algorithm) {
        try {
            MessageDigest md = MessageDigest.getInstance(algorithm);
            return new HashTools(md);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public byte[] hash(byte[] data) {
        md.reset();
        return md.digest(data);
    }

    public byte[] hash(String data) {
        Charset cs = Charset.forName("utf-8");
        return hash(data.getBytes(cs));
    }

    public String hashString(byte[] data) {
        byte[] digest = hash(data);
        return toString(digest);
    }

    public String hashString(String data) {
        byte[] digest = hash(data);
        return toString(digest);
    }

    //  private

    private final static char[] hex_char_set = "0123456789abcdef".toCharArray();
    private final MessageDigest md;

    private HashTools(MessageDigest md) {
        this.md = md;
    }

    private String toString(byte[] data) {
        StringBuilder sb = new StringBuilder();
        for (byte b : data) {
            sb.append(hex_char_set[(b & 0xf0) >> 4]);
            sb.append(hex_char_set[b & 0x0f]);
        }
        return sb.toString();
    }

}
