package com.jackson.weather.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.jackson.weather.R;
import com.jackson.weather.activity.adapter.ListViewAdapter;
import com.jackson.weather.asynctask.LoadWeatherDataAsyncTask;

public class MainActivity extends AppCompatActivity {
    private ListView mListView;
    private ListViewAdapter mListViewAdapter;
    private LoadWeatherDataAsyncTask mloadWeatherDataAsyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = (ListView) findViewById(R.id.weather_list);
        mListViewAdapter = new ListViewAdapter(this);
        mListView.setAdapter(mListViewAdapter);

        mloadWeatherDataAsyncTask = new LoadWeatherDataAsyncTask(mListViewAdapter);
        mloadWeatherDataAsyncTask.execute();

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
}
