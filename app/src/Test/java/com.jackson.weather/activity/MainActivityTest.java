package com.jackson.weather.activity;

import android.widget.TextView;

import com.jackson.weather.R;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertEquals;
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

    @Test
    public void shouldTextViewDisplayTempC() {
        TextView textView = (TextView) mMainActivity.findViewById(R.id.hello);
        assertEquals("temp c20.3", textView.getText().toString());
    }

    @Test
    public void shouldListViewDisplayTempC() {
        TextView textView = (TextView) mMainActivity.findViewById(R.id.firstLine);
        assertEquals("temp c20.3", textView.getText().toString());
    }
}
