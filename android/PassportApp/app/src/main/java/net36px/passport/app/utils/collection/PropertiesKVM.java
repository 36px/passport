package net36px.passport.app.utils.collection;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import net36px.passport.app.utils.KeyValueMap;
import net36px.passport.app.utils.KeyValuePair;

public final class PropertiesKVM implements KeyValueMap {

    private final Properties properties;

    private PropertiesKVM(Properties props) {
        this.properties = props;
    }

    public static KeyValueMap create(Properties props) {
        KeyValueMap kvm = new PropertiesKVM(props);
        return new KeyValueMapWrapper(kvm);
    }

    @Override
    public String get(String key, String the_default_value) {
        return properties.getProperty(key);
    }

    @Override
    public String get(String key, boolean required) {
        return properties.getProperty(key);
    }

    @Override
    public String get(String key) {
        return properties.getProperty(key);
    }

    @Override
    public boolean contains(String key) {
        return properties.containsKey(key);
    }

    @Override
    public void put(String key, String value) {
        properties.setProperty(key, value);
    }

    @Override
    public KeyValuePair[] items() {
        Set<Map.Entry<Object, Object>> from = properties.entrySet();
        List<KeyValuePair> list = new ArrayList<>(from.size());
        for (Map.Entry<Object, Object> entry : from) {
            KeyValuePair pair = new KeyValuePair();
            pair.setKey(entry.getKey().toString());
            pair.setValue(entry.getValue().toString());
            list.add(pair);
        }
        return list.toArray(new KeyValuePair[list.size()]);
    }
}
