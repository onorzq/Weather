package com.jackson.weather.activity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jackson.weather.R;
import com.jackson.weather.model.WeatherData;

import java.util.List;

/**
 * Created by zhishengliu on 10/25/15.
 */
public class ListViewAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<WeatherData> mWeatherDataList;
//    private WeatherData mWeatherData;

    public ListViewAdapter(Context context, List<WeatherData> weatherDataList) {
        mInflater = LayoutInflater.from(context);
        mWeatherDataList = weatherDataList;
    }

    @Override
    public int getCount() {
        return mWeatherDataList.size();
    }

    @Override
    public Object getItem(int position) {
        return mWeatherDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (convertView == null) {
            view = mInflater.inflate(R.layout.list_item, null);
        }

        final TextView temperature = (TextView) view.findViewById(R.id.firstLine);
//        TextView description = (TextView) view.findViewById(R.id.secondLine);

//        description.setText("something");


//        new LoadWeatherDataAsyncTask(new AsyncTaskCompletionListener<WeatherData>() {
//            @Override
//            public void onTaskComplete(WeatherData result) {
////                mTextView.setText("temp c" + result.getTempC());
//                mWeatherDataList = new ArrayList<WeatherData>();
//                mWeatherDataList.add(result);
////                mListViewAdapter.notifyDataSetChanged();
//                temperature.setText("temp c " + mWeatherDataList.get(0).getTempC());
//                Log.i("getview", (String) temperature.getText());
//
//            }
//        }).execute();


        return view;
    }
}
