package net36px.passport.app.utils.collection;

import java.util.List;
import java.util.Map;

import net36px.passport.app.utils.KeyValueMap;
import net36px.passport.app.utils.KeyValuePair;

public class KeyValueMapWrapper implements KeyValueMap {

    private final KeyValueMap source;

    public KeyValueMapWrapper(KeyValueMap src) {
        source = src;
    }

    @Override
    public String get(String key, String the_default_value) {
        String value = source.get(key);
        if (value == null) {
            value = the_default_value;
        }
        return value;
    }

    @Override
    public String get(String key, boolean required) {
        String value = source.get(key);
        if ((value == null) && required) {
            throw new RuntimeException("the value is required, key=" + key);
        }
        return value;
    }

    @Override
    public String get(String key) {
        return this.get(key, true);
    }

    @Override
    public boolean contains(String key) {
        return source.contains(key);
    }

    @Override
    public void put(String key, String value) {
        source.put(key, value);
    }

    @Override
    public KeyValuePair[] items() {
        return source.items();
    }
}
