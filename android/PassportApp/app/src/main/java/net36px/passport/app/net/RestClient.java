package net36px.passport.app.net;

import java.util.Map;

public interface RestClient {

    <T> T get(String type, String id, Map<String, String> query, Class<T> doc_type);

    <T> T post(String type, String id, T doc);

    <T> T put(String type, String id, T doc);

    <T> T delete(String type, String id, Class<T> doc_type);

}
