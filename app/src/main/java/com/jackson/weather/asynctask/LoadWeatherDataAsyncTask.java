package com.jackson.weather.asynctask;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.jackson.weather.Storage.SharedPreferencesStorage;
import com.jackson.weather.activity.adapter.ListViewAdapter;
import com.jackson.weather.core.WundergroundFetcher;
import com.jackson.weather.model.WeatherData;

import java.util.ArrayList;

/**
 * Created by zhishengliu on 10/24/15.
 */
public class LoadWeatherDataAsyncTask extends AsyncTask<String, Integer, ArrayList<WeatherData>> {
    private static final String TAG = "LoadWeatherDataAsyncTask";

    private Context mContext;
    private ListViewAdapter mListViewAdapter;
    private ProgressDialog mProgressDialog;
    private SharedPreferencesStorage mSharedPreferencesStorage;


    public LoadWeatherDataAsyncTask(Context context, ListViewAdapter adapter) {
        mContext = context;
        mListViewAdapter = adapter;
        mProgressDialog = new ProgressDialog(context);
        mSharedPreferencesStorage = new SharedPreferencesStorage(context);
    }

    @Override
    protected void onPreExecute() {
        mProgressDialog.setCancelable(false);
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.setMessage("Downloading Weather Data");
        mProgressDialog.show();
    }

    @SuppressLint("LongLogTag")
    @Override
    protected ArrayList<WeatherData> doInBackground(String... params) {

        Log.i(TAG, "doInBackground get data from wunderground");
        String location;
        if (mSharedPreferencesStorage.getIsNetworkLocation()) {
            location = params[0];
        } else {
            location = mSharedPreferencesStorage.getZipCode();
        }
        return new WundergroundFetcher(location,
                mSharedPreferencesStorage.getNumOfDays2Display()).getWeatherData();
    }

    @Override
    protected void onProgressUpdate(Integer... params) {
    }

    @SuppressLint("LongLogTag")
    @Override
    protected void onPostExecute(ArrayList<WeatherData> result) {
        Log.i(TAG, "onPostExecute post data from wunderground");
//        super.onPostExecute(result);
        mListViewAdapter.upDateEntries(result);

        if (mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }
}
