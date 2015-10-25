package com.jackson.weather.core;

import com.jackson.weather.model.URLBuilder;
import com.jackson.weather.model.WeatherData;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by zhishengliu on 10/24/15.
 */
public class WundergroundFetcher {

    public WeatherData getWeatherData() {
        URLBuilder urlBuilder = new URLBuilder();
        urlBuilder.setFeature("conditions/");
//        urlBuilder.setSetting("lang:CN/");
        urlBuilder.setQuery("22202");
        urlBuilder.setFormat(".json");

        return convertString2WeatherData(fetchStringFromURL(urlBuilder.toURL()));
    }

    public String fetchStringFromURL(URL url) {

        try {
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuffer response = new StringBuffer();
            String inputLine;

            while ((inputLine = bufferedReader.readLine()) != null) {
                response.append(inputLine);
            }

            bufferedReader.close();
            inputStream.close();
            urlConnection.disconnect();

            return response.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public WeatherData convertString2WeatherData(String inputString) {
        WeatherData weatherData = new WeatherData();

        try {
            JSONObject jsonObject = new JSONObject(inputString);
            //TODO if the json return does not include the data we want
            //TODO should return a list of weather info instead of just one day
            weatherData.setTempC(jsonObject.getJSONObject("current_observation").getDouble("temp_c"));

        } catch (JSONException e) {
            e.printStackTrace();
            //TODO do something if no the input string is not a json object
        }

        return weatherData;
    }
}
