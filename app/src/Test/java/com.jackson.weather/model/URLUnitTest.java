package com.jackson.weather.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by zhishengliu on 10/24/15.
 */
public class URLUnitTest {

    @Test
    public void testURL() {
        URLBuilder urlBuilder = new URLBuilder();
        urlBuilder.setFeature("forecast/");
        urlBuilder.setSetting("lang:CN/");
        urlBuilder.setQuery("22202");
        urlBuilder.setFormat(".json");

        assertEquals("http://api.wunderground.com/api/a44ecdabbb773734/forecast/lang:CN/q/22202.json", urlBuilder.toString());

        urlBuilder = new URLBuilder();
        assertEquals("http://api.wunderground.com/api/a44ecdabbb773734/q/", urlBuilder.toString());
    }
}
