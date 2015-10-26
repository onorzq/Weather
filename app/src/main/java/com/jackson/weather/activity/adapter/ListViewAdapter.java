package com.jackson.weather.activity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jackson.weather.R;
import com.jackson.weather.model.WeatherData;

import java.util.ArrayList;

/**
 * Created by zhishengliu on 10/25/15.
 */
public class ListViewAdapter extends BaseAdapter {
    private Context mContext;

    private LayoutInflater mLayoutInflater;

    private ArrayList<WeatherData> mWeatherDataArrayList = new ArrayList<WeatherData>();


    public ListViewAdapter(Context context) {
        mContext = context;
        mLayoutInflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mWeatherDataArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return mWeatherDataArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (convertView == null) {
            view = mLayoutInflater.inflate(R.layout.list_item, parent, false);
        }
//        ImageView imageView = (ImageView) view.findViewById(R.id.icon);
        TextView hiTempTextView = (TextView) view.findViewById(R.id.hi_temp_text);
        TextView loTemptextView = (TextView) view.findViewById(R.id.lo_temp_text);
        TextView locationTextView = (TextView) view.findViewById(R.id.location_text);
        TextView descriptionTextView = (TextView) view.findViewById(R.id.description_text);
        TextView dateTextView = (TextView) view.findViewById(R.id.date_text);

        boolean isCelsius = true;
        if(isCelsius) {
            hiTempTextView.setText(mWeatherDataArrayList.get(position).getTempCHi());
            loTemptextView.setText(mWeatherDataArrayList.get(position).getTempCLo());
        }
        else {
            hiTempTextView.setText(mWeatherDataArrayList.get(position).getTempFHi());
            loTemptextView.setText(mWeatherDataArrayList.get(position).getTempFLo());
        }
        locationTextView.setText(mWeatherDataArrayList.get(position).getLocation());
        descriptionTextView.setText(mWeatherDataArrayList.get(position).getDescription());
        dateTextView.setText(mWeatherDataArrayList.get(position).getDate());

        return view;
    }

    public void upDateEntries(ArrayList<WeatherData> weatherDataArrayList) {
        mWeatherDataArrayList = weatherDataArrayList;
        notifyDataSetChanged();
    }
}
