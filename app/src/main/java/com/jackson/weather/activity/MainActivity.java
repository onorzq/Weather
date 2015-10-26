package com.jackson.weather.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.jackson.weather.R;
import com.jackson.weather.activity.adapter.ListViewAdapter;
import com.jackson.weather.asynctask.LoadWeatherDataAsyncTask;
import com.jackson.weather.sensor.LocationFinder;
import com.jackson.weather.shareprefstorage.SharedPreferencesStorage;

public class MainActivity extends AppCompatActivity implements LocationFinder.LocationDetector,
        LoadWeatherDataAsyncTask.TaskCompletionListener {
    private static final String TAG = "MainActivity";

    private ListView mListView;
    private ListViewAdapter mListViewAdapter;
    private LoadWeatherDataAsyncTask mloadWeatherDataAsyncTask;
    private String latitudeAndlongitude;
    private LocationFinder mLocationFinder;
    private boolean isAsyncTaskExecuted;
    private ProgressDialog mGetLocationProgressDialog;
    private ProgressDialog mGetWeatherProgressDialog;
    private SharedPreferencesStorage mSharedPreferencesStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSharedPreferencesStorage = new SharedPreferencesStorage(this);

        mGetLocationProgressDialog = new ProgressDialog(this);
        mGetLocationProgressDialog.setCancelable(true);
        mGetLocationProgressDialog.setCanceledOnTouchOutside(false);
        mGetLocationProgressDialog.setMessage(this.getString(R.string.find_location));
        mGetLocationProgressDialog.show();


        mListView = (ListView) findViewById(R.id.weather_list);
        mListViewAdapter = new ListViewAdapter(this);
        mListView.setAdapter(mListViewAdapter);

        if (mSharedPreferencesStorage.getIsNetworkLocation()) {
            mLocationFinder = new LocationFinder(this, this);
            mLocationFinder.detectLocation();

            mloadWeatherDataAsyncTask = new LoadWeatherDataAsyncTask(this, mListViewAdapter);
            mloadWeatherDataAsyncTask.setCompletionListener(this);

            isAsyncTaskExecuted = false;
        } else {
            if (mGetLocationProgressDialog.isShowing()) {
                mGetLocationProgressDialog.dismiss();
            }

            mloadWeatherDataAsyncTask = new LoadWeatherDataAsyncTask(this, mListViewAdapter);
            mloadWeatherDataAsyncTask.setCompletionListener(this);
            mloadWeatherDataAsyncTask.execute();
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, SettingActivity.class);
            startActivity(intent);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void locationFound(Location location) {
        Log.d(TAG, "location found, isAsyncTaskExecuted " + isAsyncTaskExecuted);

        latitudeAndlongitude = location.getLatitude() + "," + location.getLongitude();


        if (mGetLocationProgressDialog.isShowing()) {
            mGetLocationProgressDialog.dismiss();
        }

        if (!isAsyncTaskExecuted) {
            Log.d(TAG, "location found, latitudeAndlongitude " + latitudeAndlongitude);
            mloadWeatherDataAsyncTask.execute(latitudeAndlongitude);
            isAsyncTaskExecuted = true;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mGetLocationProgressDialog.dismiss();
        if(mGetWeatherProgressDialog != null){
            mGetWeatherProgressDialog.dismiss();
        }

    }


    @Override
    public void locationNotFound(LocationFinder.FailureReason failureReason) {
        Toast.makeText(this, failureReason.toString(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void taskStart() {
        mGetWeatherProgressDialog = new ProgressDialog(this);
        mGetWeatherProgressDialog.setCancelable(true);
        mGetWeatherProgressDialog.setCanceledOnTouchOutside(false);
        mGetWeatherProgressDialog.setMessage(this.getString(R.string.find_location));
        mGetWeatherProgressDialog.show();
    }

    @Override
    public void taskComplete() {
        if (mGetWeatherProgressDialog != null) {
            mGetWeatherProgressDialog.dismiss();
        }
    }
}
