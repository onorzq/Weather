package com.jackson.weather.asynctask;

import android.app.Activity;

import com.jackson.weather.activity.adapter.ListViewAdapter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by zhishengliu on 10/24/15.
 */

@Config(manifest = "src/main/AndroidManifest.xml", sdk = 21)
@RunWith(RobolectricTestRunner.class)
public class LoadWeatherDataAsyncTaskTest {
    LoadWeatherDataAsyncTask mLoadWeatherDataAsyncTask;

    @Before
    public void setup() {
        Activity activity = new Activity();
        mLoadWeatherDataAsyncTask = new LoadWeatherDataAsyncTask(activity, new ListViewAdapter(activity));
        mLoadWeatherDataAsyncTask.execute();
    }

    @Test
    public void shouldNotNull() {
        Robolectric.flushBackgroundThreadScheduler();

        try {
            assertNotNull(mLoadWeatherDataAsyncTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getTempCTest() {
        Robolectric.flushBackgroundThreadScheduler();
        assertEquals("14", mLoadWeatherDataAsyncTask.doInBackground().get(0).getTempCHi());
    }
}
