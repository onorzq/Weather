package com.jackson.weather.core;

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

    public static String fetchStringFromURL(URL url) {

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
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "No string is retrieved.";
    }

    public static WeatherData convertString2WeatherData(String inputString) {
        WeatherData weatherData = new WeatherData();

        try {
            JSONObject jsonObject = new JSONObject(inputString);
            weatherData.setTempC(jsonObject.getJSONObject("current_observation").getDouble("temp_c"));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return weatherData;
    }
}
