package net36px.passport.boot.data.objs;

import java.io.Closeable;

public interface ObjsSession extends Closeable {

	String path();

	ObjsSession getSubSession(String path);

	ObjsObject getObject(String path);

}
