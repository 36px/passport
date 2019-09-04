package net36px.passport.app.utils;

public interface KeyValueMap {

    String get(String key, String the_default_value);

    String get(String key, boolean required);

    String get(String key);

    boolean contains(String key);

    void put(String key, String value);

    KeyValuePair[] items();

}
