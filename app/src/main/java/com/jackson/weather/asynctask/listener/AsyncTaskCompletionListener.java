package com.jackson.weather.asynctask.listener;

/**
 * Created by zhishengliu on 10/25/15.
 */
public interface AsyncTaskCompletionListener<T> {
    public void onTaskComplete(T result);
}
