package com.jackson.weather.shareprefstorage;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by zhishengliu on 10/26/15.
 */
public class SharedPreferencesStorage {
    public static final String PREFS_ZIP = "zipCode";
    public static final String PREFS_DAYS = "days";
    public static final String PREFS_IS_CELSIUS = "isCelsius";
    public static final String PREFS_IS_NETWORK_LOCATION = "isNetworkLocation";
    public static final String ZIP_CODE_DEFAULT = "22202";
    public static final int DAYS_TO_DISPLAY_DEFAULT = 3;
    public static final boolean IS_CELSIUS_DEFAULT = false;
    public static final boolean IS_NETWORK_LOCATION = true;

    private Context mContext;

    public SharedPreferencesStorage(Context context) {
        mContext = context;
    }

    public String getZipCode(){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        return sharedPreferences.getString(PREFS_ZIP, ZIP_CODE_DEFAULT);
    }

    public int getNumOfDays2Display(){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        return sharedPreferences.getInt(PREFS_DAYS, DAYS_TO_DISPLAY_DEFAULT);
    }

    public boolean getIsCelsius(){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        return sharedPreferences.getBoolean(PREFS_IS_CELSIUS, IS_CELSIUS_DEFAULT);
    }

    public boolean getIsNetworkLocation(){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        return sharedPreferences.getBoolean(PREFS_IS_NETWORK_LOCATION, IS_NETWORK_LOCATION);
    }

    public void setZipCode(String zip){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PREFS_ZIP, zip);
        editor.apply();
    }

    public void setDays(int days){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(PREFS_DAYS, days);
        editor.apply();
    }

    public void setIsCelsius(boolean isCelsius){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(PREFS_IS_CELSIUS, isCelsius);
        editor.apply();
    }

    public void setIsNetworkLocation(boolean isNetworkLocation){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(PREFS_IS_NETWORK_LOCATION, isNetworkLocation);
        editor.apply();
    }
}
