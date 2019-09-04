package net36px.passport.boot.data.objs.oss;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import net36px.passport.boot.data.objs.ObjsObject;

public class OssObjsObjectImpl implements ObjsObject {

	private final OssObjsSessionContext mContext;
	private final String mPath;
	private final Charset mCharset;

	public OssObjsObjectImpl(OssObjsSessionContext context, String path) {
		this.mContext = context;
		this.mPath = path;
		this.mCharset = Charset.forName("utf-8");
	}

	@Override
	public String path() {
		return this.mPath;
	}

	@Override
	public void save(String text) {
		this.mContext.save(text.getBytes(this.mCharset), this.mPath);
	}

	@Override
	public void save(byte[] data) {
		this.mContext.save(data, this.mPath);
	}

	@Override
	public void save(Properties props) {
		String str = this.toString(props);
		this.mContext.save(str.getBytes(this.mCharset), this.mPath);
	}

	@Override
	public byte[] loadBytes(boolean required, byte[] value_default) {
		byte[] data = this.mContext.load(this.mPath, required);
		if (data == null) {
			data = value_default;
		}
		return data;
	}

	@Override
	public String loadString(boolean required, String value_default) {
		final byte[] data = this.mContext.load(this.mPath, required);
		final String value;
		if (data != null) {
			value = new String(data, this.mCharset);
		} else {
			value = value_default;
		}
		return value;
	}

	@Override
	public Properties loadProperties(boolean required, Properties value_default) {
		final byte[] data = this.mContext.load(this.mPath, required);
		if (data != null) {
			try {
				final Properties value = new Properties();
				value.load(new ByteArrayInputStream(data));
				return value;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return value_default;
	}

	private String toString(Properties props) {
		StringBuilder sb = new StringBuilder();
		Set<Entry<Object, Object>> items = props.entrySet();
		for (Entry<Object, Object> item : items) {
			String key = item.getKey().toString().trim();
			String value = item.getValue().toString().trim();
			sb.append(key).append('=').append(value).append('\n');
		}
		return sb.toString();
	}

}
