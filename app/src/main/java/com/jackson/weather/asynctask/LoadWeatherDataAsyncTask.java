package com.jackson.weather.asynctask;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.util.Log;

import com.jackson.weather.asynctask.listener.AsyncTaskCompletionListener;
import com.jackson.weather.core.WundergroundFetcher;
import com.jackson.weather.model.WeatherData;

/**
 * Created by zhishengliu on 10/24/15.
 */
public class LoadWeatherDataAsyncTask extends AsyncTask<String, Integer, WeatherData> {
    private static final String TAG = "LoadWeatherDataAsyncTask";

//    private Context mContext;
    private AsyncTaskCompletionListener<WeatherData> mListener;

    public LoadWeatherDataAsyncTask(AsyncTaskCompletionListener<WeatherData> listener) {
//        mContext = context;
        mListener = listener;
    }

    @SuppressLint("LongLogTag")
    @Override
    protected WeatherData doInBackground(String... params) {

        Log.i(TAG, "doInBackground get data from wunderground");
        return new WundergroundFetcher().getWeatherData();
    }

    @Override
    protected void onProgressUpdate(Integer... params){
        //TODO progress bar
    }

    @SuppressLint("LongLogTag")
    @Override
    protected void onPostExecute(WeatherData result) {
        Log.i(TAG, "onPostExecute post data from wunderground");
        super.onPostExecute(result);
        mListener.onTaskComplete(result);
    }
}
