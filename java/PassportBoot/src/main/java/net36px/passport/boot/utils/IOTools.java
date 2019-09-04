package net36px.passport.boot.utils;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class IOTools {

	public static void close(Closeable s) {
		if (s != null) {
			try {
				s.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static long pump(InputStream in, OutputStream out, byte[] buffer) throws IOException {
		if (buffer == null) {
			buffer = new byte[1024 * 4];
		}
		long count = 0;
		for (;;) {
			int cb = in.read(buffer);
			if (cb < 0) {
				break;
			} else {
				out.write(buffer, 0, cb);
				count += cb;
			}
		}
		return count;
	}

}
