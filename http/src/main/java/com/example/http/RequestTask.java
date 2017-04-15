package com.example.http;

import android.os.AsyncTask;

import java.io.IOException;
import java.net.HttpURLConnection;

/**
 * Created by HP on 2017/1/13.
 */

public class RequestTask extends AsyncTask<Void, Integer, Object> {

    private Request request;

    public RequestTask(Request request) {
        this.request = request;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Object doInBackground(Void... params) {
        if (request.iCallBack != null) {
            Object o = request.iCallBack.preRequest();
            if (o != null) {
                return o;       
            }
        }

        return request(0);

    }

    public Object request(int retry) {
        try {
            isCancelled();
            HttpURLConnection connection = HttpUrlConnectionUtil.execute(request);
            if (request.enableProgressUpdated) {
                return request.iCallBack.parse(connection, new onProgressUpdatedListener() {
                    @Override
                    public void onProgressUpdated(int curLen, int totalLen) {
                        publishProgress(curLen, totalLen);
                    }
                });
            } else {
                return request.iCallBack.parse(connection);
            }

        } catch (AppException e) {
            if (e.type == AppException.ErrorType.TIMEOUT) {
                if (retry < request.maxRetryCount) {
                    retry++;
                    return request(retry);
                }
            }
            return e;

        }
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        if (o instanceof AppException) {
            if (request.onGlobalExceptionListener != null) {
                if (!request.onGlobalExceptionListener.handleException((AppException) o)) {
                    request.iCallBack.onFailure((AppException) o);
                }
            } else {
                request.iCallBack.onFailure((AppException) o);
            }

        } else {
            request.iCallBack.onSuccess(o);

        }
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        request.iCallBack.onProgressUpdated(values[0], values[1]);
    }
}
