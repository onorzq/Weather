package com.jackson.weather.activity;

import android.app.ProgressDialog;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.jackson.weather.R;
import com.jackson.weather.activity.adapter.ListViewAdapter;
import com.jackson.weather.asynctask.LoadWeatherDataAsyncTask;
import com.jackson.weather.sensor.LocationFinder;

public class MainActivity extends AppCompatActivity implements LocationFinder.LocationDetector {
    private ListView mListView;
    private ListViewAdapter mListViewAdapter;
    private LoadWeatherDataAsyncTask mloadWeatherDataAsyncTask;
    private String latitudeAndlongitude;
    private LocationFinder mLocationFinder;
    private boolean isAsyncTaskExecuted;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.setMessage("Finding Location");
        mProgressDialog.show();

        mLocationFinder = new LocationFinder(this, this);
        mLocationFinder.detectLocation();

        mListView = (ListView) findViewById(R.id.weather_list);
        mListViewAdapter = new ListViewAdapter(this);
        mListView.setAdapter(mListViewAdapter);

        mloadWeatherDataAsyncTask = new LoadWeatherDataAsyncTask(this, mListViewAdapter);

        isAsyncTaskExecuted = false;

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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void locationFound(Location location) {
        Log.d("main ", "location found, isAsyncTaskExecuted" + isAsyncTaskExecuted);

        latitudeAndlongitude = location.getLatitude() + "," + location.getLongitude();

        if (mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }

        if (!isAsyncTaskExecuted) {
            mloadWeatherDataAsyncTask.execute(latitudeAndlongitude);
            isAsyncTaskExecuted = true;
        }
    }

    @Override
    public void locationNotFound(LocationFinder.FailureReason failureReason) {

    }
}
