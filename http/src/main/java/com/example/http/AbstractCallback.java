package com.example.http;

import com.google.gson.Gson;

import org.apache.http.HttpStatus;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;

/**
 * Created by HP on 2017/1/13.
 */

public abstract class AbstractCallback<T> implements ICallBack<T> {


    private String cachePath;
    private String path;
    private volatile boolean isCancelled;

    @Override
    public T parse(HttpURLConnection connection) throws AppException {
        return parse(connection, null);
    }

    @Override
    public T parse(HttpURLConnection connection, onProgressUpdatedListener listener) throws AppException {
        try {
            checkIfCancelled();

            int status = connection.getResponseCode();
            if (status == HttpStatus.SC_OK) {
                if (path == null) {
                    ByteArrayOutputStream out = new ByteArrayOutputStream();
                    InputStream is = connection.getInputStream();
                    byte[] buffer = new byte[2048];
                    int len;
                    while ((len = is.read(buffer)) != -1) {
                        checkIfCancelled();
                        out.write(buffer, 0, len);
                    }
                    is.close();
                    out.flush();
                    out.close();

                    String result = new String(out.toByteArray());

                    T t = bindData(result);
                    return postRequest(t);

                } else {
                    FileOutputStream out = new FileOutputStream(path);
                    InputStream is = connection.getInputStream();
                    byte[] buffer = new byte[2048];
                    int len;
                    int totalLen = connection.getContentLength();
                    int curLen = 0;
                    while ((len = is.read(buffer)) != -1) {
                        checkIfCancelled();
                        out.write(buffer, 0, len);
                        curLen += len;
                        listener.onProgressUpdated(curLen, totalLen);

                    }
                    is.close();
                    out.flush();
                    out.close();

                    T t = bindData(path);
                    return postRequest(t);
                }

            } else {
                throw new AppException(status, connection.getResponseMessage());
            }
        } catch (IOException e) {
            throw new AppException(AppException.ErrorType.SERVER, e.getMessage());
        }


    }


    protected void checkIfCancelled() throws AppException {
        if (isCancelled) {
            throw new AppException(AppException.ErrorType.CANCEL, "the request has cancelled");
        }
    }

    @Override
    public void cancel() {
        isCancelled = true;
    }

    protected abstract T bindData(String result) throws AppException;

    @Override
    public void onProgressUpdated(int curLen, int totalLen) {

    }

    @Override
    public T preRequest() {
        return null;
    }

    @Override
    public T postRequest(T t) {
        return t;
    }

    public ICallBack setCachePath(String path) {
        this.path = path;
        return this;
    }
}
