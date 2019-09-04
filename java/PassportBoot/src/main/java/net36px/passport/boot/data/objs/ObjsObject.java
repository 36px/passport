package net36px.passport.boot.data.objs;

import java.util.Properties;

public interface ObjsObject {

	String path();

	void save(String text);

	void save(byte[] data);

	void save(Properties props);

	byte[] loadBytes(boolean required, byte[] value_default);

	String loadString(boolean required, String value_default);

	Properties loadProperties(boolean required, Properties value_default);

}
