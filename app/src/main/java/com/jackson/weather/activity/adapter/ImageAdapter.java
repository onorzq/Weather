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
public class ImageAdapter extends BaseAdapter {
    private Context mContext;

    private LayoutInflater mLayoutInflater;

    private ArrayList<WeatherData> mEntries = new ArrayList<WeatherData>();


    public ImageAdapter(Context context) {
        mContext = context;
        mLayoutInflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mEntries.size();
    }

    @Override
    public Object getItem(int position) {
        return mEntries.get(position);
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
        TextView textView = (TextView) view.findViewById(R.id.firstLine);
        textView.setText("temp c " + mEntries.get(position).getTempC());

        return view;
    }

    public void upDateEntries(ArrayList<WeatherData> entries) {
        mEntries = entries;
        notifyDataSetChanged();
    }
}
