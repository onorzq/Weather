package com.jackson.weather.asynctask;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.jackson.weather.R;
import com.jackson.weather.shareprefstorage.SharedPreferencesStorage;
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
    private TaskCompletionListener mTaskCompletionListener;

    public interface TaskCompletionListener{
        public void taskStart();
        public void taskComplete();
    }


    public LoadWeatherDataAsyncTask(Context context, ListViewAdapter adapter) {
        mContext = context;
        mListViewAdapter = adapter;

        mSharedPreferencesStorage = new SharedPreferencesStorage(context);
    }

    public void setCompletionListener(TaskCompletionListener completionListener){
        mTaskCompletionListener = completionListener;
    }

    @Override
    protected void onPreExecute() {
        mTaskCompletionListener.taskStart();
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


        if (result == null) {
            Toast.makeText(mContext, mContext.getString(R.string.no_json_get), Toast.LENGTH_SHORT).show();
        }
        else if(result.size() == 0) {
            Toast.makeText(mContext, mContext.getString(R.string.wrong_json_get), Toast.LENGTH_SHORT).show();
        }
        else {
            mListViewAdapter.upDateEntries(result);
        }

        mTaskCompletionListener.taskComplete();
    }

}
