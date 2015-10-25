package com.jackson.weather.asynctask;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertNotNull;

/**
 * Created by zhishengliu on 10/24/15.
 */

@Config(manifest = "src/main/AndroidManifest.xml", sdk = 21)
@RunWith(RobolectricTestRunner.class)
public class LoadWeatherDataAsyncTaskTest {
    LoadWeatherDataAsyncTask mLoadWeatherDataAsyncTask = new LoadWeatherDataAsyncTask();

    @Before
    public void setup() {
        mLoadWeatherDataAsyncTask.execute();
    }

    @Test
    public void shouldNotNull() {
        Robolectric.flushBackgroundThreadScheduler();

        //can run asserts on result now
        try {
            assertNotNull(mLoadWeatherDataAsyncTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
