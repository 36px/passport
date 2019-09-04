package net36px.passport.boot.utils;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class HashTools {

	public static HashTools getInstance(String algorithm) {
		try {
			MessageDigest md = MessageDigest.getInstance(algorithm);
			return new HashTools(md);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}

	public static HashTools sha1() {
		return getInstance("SHA-1");
	}

	public static HashTools sha256() {
		return getInstance("SHA-256");
	}

	public static HashTools sha512() {
		return getInstance("SHA-512");
	}

	public byte[] digest(String data) {
		byte[] bytes = data.getBytes(ENCODING);
		md.reset();
		return md.digest(bytes);
	}

	public byte[] digest(byte[] data) {
		md.reset();
		return md.digest(data);
	}

	public String digestString(String data) {
		byte[] result = digest(data);
		return toString(result);
	}

	public String digestString(byte[] data) {
		byte[] result = digest(data);
		return toString(result);
	}

	// private

	private final static Charset ENCODING = Charset.forName("UTF-8");

	private final static char[] HEX_CHAR_SET = "0123456789abcdef".toCharArray();

	private final MessageDigest md;

	private HashTools(MessageDigest md) {
		this.md = md;
	}

	private String toString(byte[] array) {
		StringBuilder sb = new StringBuilder();
		for (byte b : array) {
			final int n1 = (b >> 4);
			final int n2 = b;
			sb.append(HEX_CHAR_SET[n1 & 0x0f]);
			sb.append(HEX_CHAR_SET[n2 & 0x0f]);
		}
		return sb.toString();
	}

}
