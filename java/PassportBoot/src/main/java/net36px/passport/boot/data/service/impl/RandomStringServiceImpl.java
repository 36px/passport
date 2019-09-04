package net36px.passport.boot.data.service.impl;

import java.util.Base64;
import java.util.List;
import java.util.Random;

import net36px.passport.boot.data.service.RandomStringService;
import net36px.passport.boot.utils.HashTools;

public class RandomStringServiceImpl implements RandomStringService {

	private final Random rand;
	private long index;

	public RandomStringServiceImpl() {
		long seed = System.currentTimeMillis();
		this.rand = new Random(seed);
	}

	@Override
	public synchronized String nextString(int length, List<String> salt) {
		Builder builder = new Builder();
		for (; builder.length() < length;) {
			builder.buildup(salt);
		}
		return builder.create(length);
	}

	private class Builder {

		final StringBuilder buffer;
		final long now;
		final HashTools hash;

		public Builder() {
			this.buffer = new StringBuilder();
			this.now = System.currentTimeMillis();
			this.hash = HashTools.sha256();
		}

		public String create(int length) {
			String str = this.buffer.toString();
			str = str.replace('/', '_');
			str = str.replace('+', '_');
			str = str.replace('=', '_');
			if (str.length() > length) {
				return str.substring(0, length);
			} else {
				return str;
			}
		}

		public void buildup(List<String> salt) {

			final RandomStringServiceImpl parent = RandomStringServiceImpl.this;
			final char nl = '\n';
			final long index = parent.index++;
			final long nonce = parent.rand.nextLong();
			final StringBuilder sb = new StringBuilder();

			if (salt != null) {
				for (String item : salt) {
					sb.append(item).append(nl);
				}
			}

			sb.append(this.now).append(nl);
			sb.append(index).append(nl);
			sb.append(nonce).append(nl);

			byte[] data = this.hash.digest(sb.toString());
			this.buffer.append(Base64.getEncoder().encodeToString(data));
		}

		public int length() {
			return this.buffer.length();
		}
	}

}
