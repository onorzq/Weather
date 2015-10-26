//package com.jackson.weather.asynctask;
//
//import com.jackson.weather.asynctask.listener.AsyncTaskCompletionListener;
//import com.jackson.weather.model.WeatherData;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.robolectric.Robolectric;
//import org.robolectric.RobolectricTestRunner;
//import org.robolectric.annotation.Config;
//
//import java.util.concurrent.ExecutionException;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotNull;
//
///**
// * Created by zhishengliu on 10/24/15.
// */
//
//@Config(manifest = "src/main/AndroidManifest.xml", sdk = 21)
//@RunWith(RobolectricTestRunner.class)
//public class LoadWeatherDataAsyncTaskTest {
//    LoadWeatherDataAsyncTask mLoadWeatherDataAsyncTask;
//
//    @Before
//    public void setup() {
//        mLoadWeatherDataAsyncTask = new LoadWeatherDataAsyncTask(new AsyncTaskCompletionListener<WeatherData>() {
//            @Override
//            public void onTaskComplete(WeatherData result) {
//
//            }
//        });
//        mLoadWeatherDataAsyncTask.execute();
//    }
//
//    @Test
//    public void shouldNotNull() {
//        Robolectric.flushBackgroundThreadScheduler();
//
//        try {
//            assertNotNull(mLoadWeatherDataAsyncTask.get());
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public void getTempCTest() {
//        Robolectric.flushBackgroundThreadScheduler();
//        assertEquals(14.5, mLoadWeatherDataAsyncTask.doInBackground().getTempC(), 0.1);
//    }
//}
