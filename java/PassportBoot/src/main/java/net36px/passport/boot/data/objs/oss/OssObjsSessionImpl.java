package net36px.passport.boot.data.objs.oss;

import java.io.IOException;

import net36px.passport.boot.data.objs.ObjsObject;
import net36px.passport.boot.data.objs.ObjsSession;

public class OssObjsSessionImpl implements ObjsSession {

	private final String mPath;
	private final OssObjsSessionContext mContext;

	public OssObjsSessionImpl(OssObjsSessionContext context, String path) {
		this.mContext = context;
		this.mPath = path;
	}

	private static String normalizePath(String path) {
		final char ch1 = '\r';
		final char ch2 = '\n';
		final StringBuilder sb = new StringBuilder();
		final String[] parts = path.replace(ch1, ch2).split(String.valueOf(ch2));
		for (String part : parts) {
			part = part.trim();
			if (part.length() == 0) {
			} else if (part.equals(".")) {
			} else if (part.equals("..")) {
			} else {
				String prefix = (sb.length() == 0) ? "" : "/";
				sb.append(prefix).append(part);
			}
		}
		return sb.toString();
	}

	@Override
	public void close() throws IOException {
		// NOP
	}

	@Override
	public String path() {
		return this.mPath;
	}

	@Override
	public ObjsSession getSubSession(String path) {
		return new OssObjsSessionImpl(this.mContext, this.mPath + "/" + path);
	}

	@Override
	public ObjsObject getObject(String path) {
		String fullpath = normalizePath(this.mPath + "/" + path);
		return new OssObjsObjectImpl(this.mContext, fullpath);
	}

}
