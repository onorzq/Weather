package com.jackson.weather.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import com.jackson.weather.R;
import com.jackson.weather.activity.adapter.ImageAdapter;
import com.jackson.weather.activity.adapter.ListViewAdapter;
import com.jackson.weather.asynctask.LoadWeatherDataAsyncTask;
import com.jackson.weather.model.WeatherData;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView mListView;
    private TextView mTextView;
    private ListViewAdapter mListViewAdapter;
    private LoadWeatherDataAsyncTask loadWeatherDataAsyncTask;
    private List<WeatherData> mWeatherDataList = new ArrayList<WeatherData>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = (ListView) findViewById(R.id.weather_list);
//        mTextView = (TextView) findViewById(R.id.hello);



//        mListViewAdapter = new ListViewAdapter(MainActivity.this, mWeatherDataList);
//        mListView.setAdapter(mListViewAdapter);

        ImageAdapter adapter = new ImageAdapter(this);


        LoadWeatherDataAsyncTask task = new LoadWeatherDataAsyncTask(adapter);
        task.execute();
        mListView.setAdapter(adapter);
    }

//    public void weatherDataFetched(List<WeatherData> weatherData) {
//        ArrayAdapter<WeatherData> adapter = new ArrayAdapter<WeatherData>(this,R.layout.list_item, weatherData);
//        mListView.setAdapter(adapter);
//    }

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
