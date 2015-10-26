package com.jackson.weather.core;

import android.util.Log;

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
    private static final String TAG = "WundergroundFetcher";

    private String location;
    private int numOfDays;

    public WundergroundFetcher(String location, int numOfDays) {
        this.location = location;
        this.numOfDays = numOfDays;
    }

    public ArrayList<WeatherData> getWeatherData() {
        URLBuilder urlBuilder = new URLBuilder();
        urlBuilder.setFeature("forecast10day/conditions/");
//        urlBuilder.setSetting("lang:CN/");
        urlBuilder.setQuery(location);
        urlBuilder.setFormat(".json");

        return convertString2WeatherData(fetchStringFromURL(urlBuilder.toURL()));
    }

    public String fetchStringFromURL(URL url) {

        try {
            Log.d(TAG, "before httpurlconnection");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuffer response = new StringBuffer();
            String inputLine;
            Log.d(TAG, "after httpurlconnection");
            while ((inputLine = bufferedReader.readLine()) != null) {
                response.append(inputLine);
            }

            bufferedReader.close();
            inputStream.close();
            urlConnection.disconnect();

            return response.toString();
        } catch (MalformedURLException e) {
            Log.d(TAG, "malformed url exception");
            e.printStackTrace();
//            Toast.makeText(mContext, mContext.getString(R.string.malformed_url), Toast.LENGTH_SHORT).show();
            return null;
        } catch (IOException e) {
            Log.d(TAG, "io exception");
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<WeatherData> convertString2WeatherData(String inputString) {

        try {
            Log.d(TAG, "before string 2 weather data list");
            ArrayList<WeatherData> weatherList = new ArrayList<WeatherData>();
            JSONObject jsonObject = new JSONObject(inputString);
            //TODO if the json return does not include the data we want

            for (int i = 0; i < numOfDays; i++) {
                WeatherData weatherData = convertJson2WeatherData(jsonObject, i);
                if(weatherData == null) {
                    return new ArrayList<WeatherData>();
                }
                weatherList.add(weatherData);
            }

            return weatherList;
        } catch (JSONException e) {
            Log.d(TAG, "json error in string 2 weather data list");
            e.printStackTrace();
//            Toast.makeText(mContext, mContext.getString(R.string.no_json_get), Toast.LENGTH_SHORT).show();
            return null;
        } catch (NullPointerException npe) {
            Log.d(TAG, "null pointer exception in string 2 weather data list");
            npe.printStackTrace();
            return null;
        }
    }

    public WeatherData convertJson2WeatherData(JSONObject json, int theNthDay) {

        try {
            Log.d(TAG, "before json 2 weather data");
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
            Log.d(TAG, "json error in json 2 weather data");
//            Toast.makeText(mContext, mContext.getString(R.string.no_json_get), Toast.LENGTH_SHORT).show();
            return null;
        } catch (NullPointerException npe) {
            Log.d(TAG, "null pointer exception in json 2 weather data");
            npe.printStackTrace();
            return null;
        }
    }

}
