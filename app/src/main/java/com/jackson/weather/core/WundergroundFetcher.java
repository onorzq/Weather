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
import java.util.ArrayList;

/**
 * Created by zhishengliu on 10/24/15.
 */
public class WundergroundFetcher {

    public ArrayList<WeatherData> getWeatherData(String location) {
//        location = "22202";
//        Log.d("weather fetc", location);
        URLBuilder urlBuilder = new URLBuilder();
        urlBuilder.setFeature("forecast10day/conditions/");
//        urlBuilder.setSetting("lang:CN/");
        urlBuilder.setQuery(location);
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

    public ArrayList<WeatherData> convertString2WeatherData(String inputString) {

        try {
            ArrayList<WeatherData> weatherList = new ArrayList<WeatherData>();
            JSONObject jsonObject = new JSONObject(inputString);
            //TODO if the json return does not include the data we want
//TODO setting of number of days
            int numOfDays = 10;
            for (int i = 0; i < numOfDays; i++) {
                weatherList.add(convertJson2WeatherData(jsonObject, i));
            }

            return weatherList;
        } catch (JSONException e) {
            e.printStackTrace();
            //TODO do something if no the input string is not a json object
            return null;
        }
    }

    public WeatherData convertJson2WeatherData(JSONObject json, int theNthDay) {

        try {
            JSONObject singleDayJson = json.getJSONObject("forecast")
                    .getJSONObject("simpleforecast")
                    .getJSONArray("forecastday")
                    .getJSONObject(theNthDay);

            WeatherData weatherData = new WeatherData();
            weatherData.setDate(singleDayJson.getJSONObject("date").getInt("year") + "/" +
                    singleDayJson.getJSONObject("date").getInt("month") + "/" +
                    singleDayJson.getJSONObject("date").getInt("day") + " " +
                    singleDayJson.getJSONObject("date").getString("weekday_short"));
            weatherData.setDescription(singleDayJson.getString("conditions"));
            weatherData.setLocation(json.getJSONObject("current_observation")
                    .getJSONObject("display_location").getString("full"));
            weatherData.setTempCHi(singleDayJson.getJSONObject("high").getString("celsius"));
            weatherData.setTempFHi(singleDayJson.getJSONObject("high").getString("fahrenheit"));
            weatherData.setTempCLo(singleDayJson.getJSONObject("low").getString("celsius"));
            weatherData.setTempFLo(singleDayJson.getJSONObject("low").getString("fahrenheit"));
            weatherData.setIconUrl(singleDayJson.getString("icon_url"));

            return weatherData;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

}
