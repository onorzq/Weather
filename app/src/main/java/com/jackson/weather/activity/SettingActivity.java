package com.jackson.weather.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.NumberPicker.OnValueChangeListener;

import com.jackson.weather.R;
import com.jackson.weather.shareprefstorage.SharedPreferencesStorage;

public class SettingActivity extends AppCompatActivity {
    public static final int DEFAULT_LENGTH_OF_ZIP_CODE = 5;

    private SharedPreferencesStorage mSharedPreferencesStorage;
    private NumberPicker mNumDayPicker;
    private TextView numOfDaysText;
    private EditText zipCodeEditText;
    private Switch celsiusFahrenheitSwitch;
    private Switch zipNetworkLocationSwitch;
    private TextWatcher mTextWatcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        mSharedPreferencesStorage = new SharedPreferencesStorage(this);

        zipCodeEditText = (EditText) findViewById(R.id.zip_code_edit_text);
        numOfDaysText = (TextView) findViewById(R.id.number_of_days_text);
        mNumDayPicker = (NumberPicker) findViewById(R.id.number_of_days_picker);
        celsiusFahrenheitSwitch = (Switch) findViewById(R.id.celsius_fahrenheit_switch);
        zipNetworkLocationSwitch = (Switch) findViewById(R.id.zip_network_location_switch);

        celsiusFahrenheitSwitch.setChecked(mSharedPreferencesStorage.getIsCelsius());
        celsiusFahrenheitSwitch.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mSharedPreferencesStorage.setIsCelsius(true);
                } else {
                    mSharedPreferencesStorage.setIsCelsius(false);
                }
            }
        });



        zipNetworkLocationSwitch.setChecked(mSharedPreferencesStorage.getIsNetworkLocation());
        zipNetworkLocationSwitch.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mSharedPreferencesStorage.setIsNetworkLocation(true);
                    zipCodeEditText.setEnabled(false);
                    zipCodeEditText.setTextColor(Color.GRAY);
                } else {
                    mSharedPreferencesStorage.setIsNetworkLocation(false);
                    zipCodeEditText.setEnabled(true);
                    zipCodeEditText.setTextColor(Color.BLACK);
                }
            }
        });

        numOfDaysText.setText("" + mSharedPreferencesStorage.getNumOfDays2Display());

        mNumDayPicker.setMinValue(1);
        mNumDayPicker.setMaxValue(10);
        mNumDayPicker.setWrapSelectorWheel(false);
        mNumDayPicker.setValue(mSharedPreferencesStorage.getNumOfDays2Display());

        mNumDayPicker.setOnValueChangedListener(new OnValueChangeListener() {

            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                numOfDaysText.setText(String.valueOf(newVal));
                mSharedPreferencesStorage.setDays(newVal);
            }
        });

        zipCodeEditText.setText(mSharedPreferencesStorage.getZipCode());
        if(mSharedPreferencesStorage.getIsNetworkLocation()) {
            zipCodeEditText.setEnabled(false);
            zipCodeEditText.setTextColor(Color.GRAY);
        }


        mTextWatcher = new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.d("zip", zipCodeEditText.getText().length()+"");
                mSharedPreferencesStorage.setZipCode(zipCodeEditText.getText().toString());
//                if (zipCodeEditText.getText().length() != DEFAULT_LENGTH_OF_ZIP_CODE) {
//                    mSharedPreferencesStorage.setZipCode(mSharedPreferencesStorage.ZIP_CODE_DEFAULT);
//                    Log.d("zip", zipCodeEditText.getText().length()+"");
//                } else {
//                    try {
//                        Integer.parseInt(zipCodeEditText.getText().toString());
//                        mSharedPreferencesStorage.setZipCode(zipCodeEditText.getText().toString());
//                        Log.d("zip",zipCodeEditText.getText().toString());
//                    } catch (Exception e) {
//                        e.getStackTrace();
//                    }
//                }
            }
        };

        zipCodeEditText.addTextChangedListener(mTextWatcher);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_setting, menu);
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
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
