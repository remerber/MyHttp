package com.example.http;

import java.net.HttpURLConnection;

/**
 * Created by HP on 2017/1/13.
 */

public interface ICallBack<T> {
    void onSuccess(T result);

    void onFailure(AppException e);

    /**
     * invoked on sub thread
     *
     * @param t serialized by subCallbacks
     * @return final result by onSuccess(t)
     */

    T postRequest(T t);

    /**
     * invoked on sub thread
     *
     * @return if not null,will skip the http request,call {@link #onSuccess(Object)} directly
     */

    T preRequest();

    T parse(HttpURLConnection connection, onProgressUpdatedListener listener) throws AppException;

    T parse(HttpURLConnection connection) throws AppException;

    void onProgressUpdated(int curLen, int totalLen);

    void cancel();
}
