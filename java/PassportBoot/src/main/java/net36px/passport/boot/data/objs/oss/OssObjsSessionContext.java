package net36px.passport.boot.data.objs.oss;

public interface OssObjsSessionContext {

	byte[] load(String path, boolean required);

	void save(byte[] data, String path);

}
