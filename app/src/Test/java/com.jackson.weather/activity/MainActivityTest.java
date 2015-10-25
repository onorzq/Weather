package com.jackson.weather.activity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertNotNull;

/**
 * Created by zhishengliu on 10/24/15.
 */

@Config(manifest = "src/main/AndroidManifest.xml", sdk = 21)
@RunWith(RobolectricTestRunner.class)
public class MainActivityTest {
    private MainActivity mMainActivity;

    @Before
    public void setup() {
        mMainActivity = Robolectric.buildActivity(MainActivity.class).create().get();
    }

    @Test
    public void shouldNotNull() {
        assertNotNull(mMainActivity);
    }
}
