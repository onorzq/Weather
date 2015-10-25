package com.jackson.weather.core;

import com.jackson.weather.model.URLBuilder;
import com.jackson.weather.model.WeatherData;

import org.junit.Before;
import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.Assert.assertEquals;

public class WundergroundFetcherUnitTest {
    URL mURL;

    @Before
    public void setup() {
        URLBuilder urlBuilder = new URLBuilder();
        urlBuilder.setFeature("conditions/");
//        urlBuilder.setSetting("lang:CN/");
        urlBuilder.setQuery("22202");
        urlBuilder.setFormat(".json");

        try {
            mURL = new URL(urlBuilder.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void weatherApiTest() {
        WeatherData weatherData = new WundergroundFetcher().getWeatherData();

        assertEquals(14.5, weatherData.getTempC(), 0.1);
    }

    @Test
    public void noJSONReturnedTest() {
        WeatherData weatherData = new WundergroundFetcher().convertString2WeatherData("nothing");
    }

    @Test
    public void notTargetDatainJSONTest() {

    }
}