package com.example.httplib;

import java.net.HttpURLConnection;

/**
 * Created by HP on 2017/3/6.
 */

public interface ICallback<T> {

    void onSuccess(T result);

    void onFailure(AppException e);


    T parse(HttpURLConnection connection, OnProgressUpdatedListener listener) throws AppException;

    T parse(HttpURLConnection connection) throws AppException;

    void onProgressUpdated(int curLen, int totalLen);
}
