package com.jackson.weather.model;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by zhishengliu on 10/24/15.
 */
public class URLBuilder {
    private static final String BASE_URL = "http://api.wunderground.com/api/";
    private static final String API_KEY = "a44ecdabbb773734/";
    private String feature;
    private String setting;
    private static final String QUERY_PATH = "q/";
    private String query;
    private String format;

    public URLBuilder() {
        feature = "";
        setting = "";
        query = "";
        format = "";
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public String getSetting() {
        return setting;
    }

    public void setSetting(String setting) {
        this.setting = setting;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public URL toURL() {
        try {
            return new URL(toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String toString() {
        return BASE_URL + API_KEY +
                feature +
                setting +
                QUERY_PATH +
                query +
                format;
    }
}
