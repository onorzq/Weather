package com.jackson.weather.core;

import com.jackson.weather.model.URLBuilder;
import com.jackson.weather.model.WeatherData;

import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.Assert.assertEquals;

public class WundergroundFetcherUnitTest {

    @Test
    public void weatherApiTest() {
        URLBuilder urlBuilder = new URLBuilder();
        urlBuilder.setFeature("conditions/");
//        urlBuilder.setSetting("lang:CN/");
        urlBuilder.setQuery("22202");
        urlBuilder.setFormat(".json");

        String response = "";

        try {
            response = WundergroundFetcher.fetchStringFromURL(new URL(urlBuilder.toString()));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

//        System.out.println(response);
        WeatherData weatherData = WundergroundFetcher.convertString2WeatherData(response);

        assertEquals(15.8, weatherData.getTempC(), 0.1);
    }
}