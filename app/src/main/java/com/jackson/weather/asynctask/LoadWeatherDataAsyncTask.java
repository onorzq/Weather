package com.jackson.weather.asynctask;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.util.Log;

import com.jackson.weather.activity.adapter.ListViewAdapter;
import com.jackson.weather.asynctask.listener.AsyncTaskCompletionListener;
import com.jackson.weather.core.WundergroundFetcher;
import com.jackson.weather.model.WeatherData;

import java.util.ArrayList;

/**
 * Created by zhishengliu on 10/24/15.
 */
public class LoadWeatherDataAsyncTask extends AsyncTask<String, Integer, ArrayList<WeatherData>> {
    private static final String TAG = "LoadWeatherDataAsyncTask";

//    private Context mContext;
    private AsyncTaskCompletionListener<WeatherData> mListener;
    private ListViewAdapter mListViewAdapter;


    public LoadWeatherDataAsyncTask(ListViewAdapter adapter) {
//        mContext = context;
        mListViewAdapter = adapter;
    }

    @SuppressLint("LongLogTag")
    @Override
    protected ArrayList<WeatherData> doInBackground(String... params) {

        Log.i(TAG, "doInBackground get data from wunderground");
        return new WundergroundFetcher().getWeatherData();
    }

    @Override
    protected void onProgressUpdate(Integer... params){
        //TODO progress bar
    }

    @SuppressLint("LongLogTag")
    @Override
    protected void onPostExecute(ArrayList<WeatherData> result) {
        Log.i(TAG, "onPostExecute post data from wunderground");
//        super.onPostExecute(result);
        mListViewAdapter.upDateEntries(result);
    }
}
