package com.xoxoms.tetris.server.configuration;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public abstract class AbstractProperty {

    protected java.util.Properties properties;

    protected AbstractProperty() {

        super();

        properties = new java.util.Properties();
    }

    protected void load(InputStream in) throws IOException {

        properties.load(new InputStreamReader(in, StandardCharsets.UTF_8));
    }

    public Boolean getBooleanProperty(PropertyName propertyName) {

        String value = properties.getProperty(propertyName.key(), propertyName.defaultValue());

        if (value == null) {

            return null;
        }

        return Boolean.parseBoolean(value.trim());
    }

    public Integer getIntegerProperty(PropertyName propertyName) throws NumberFormatException {

        String value = properties.getProperty(propertyName.key(), propertyName.defaultValue());

        if (value == null) {

            return null;
        }

        return Integer.parseInt(value.trim());
    }

    public Long getLongProperty(PropertyName propertyName) throws NumberFormatException {

        String value = properties.getProperty(propertyName.key(), propertyName.defaultValue());

        if (value == null) {

            return null;
        }

        return Long.parseLong(value.trim());
    }

    public String getProperty(PropertyName propertyName) {

        String value = properties.getProperty(propertyName.key(), propertyName.defaultValue());

        return (value == null) ? null : value.trim();
    }

    public String[] getArrayProperty(PropertyName propertyName, String separator) {

        String property = this.getProperty(propertyName);

        if (property == null) {

            return null;
        }

        property = property.trim();

        return property.split(separator);
    }

    public List<String> getListProperty(PropertyName propertyName, String separator) {

        return Arrays.asList(this.getArrayProperty(propertyName, separator));
    }

    public Map<Object, Object> getPropertyMap() {

        return Collections.unmodifiableMap(properties);
    }
}
