package com.example.httplib;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.net.HttpURLConnection;

/**
 * Created by HP on 2017/3/6.
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
        return request(0);

    }

    public Object request(int retry) {
        try {
            HttpURLConnection connection = HttpUrlConnectionUtil.execute(request);
            if (request.enableProgressUpdated) {
                return request.iCallback.parse(connection, new OnProgressUpdatedListener() {
                    @Override
                    public void onProgressUpdated(int curLen, int totalLen) {
                        publishProgress(curLen, totalLen);
                    }
                });
            } else {
                return request.iCallback.parse(connection);
            }
        } catch (AppException e) {
            if (e.type == AppException.ErrorType.TIMEOUT) {
                if (retry < request.maxRetryCount) {
                    retry++;
                    Log.e("tag", "retry=" + retry);
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
                    request.iCallback.onFailure((AppException) o);
                }
            }

        } else {
            request.iCallback.onSuccess(o);
        }
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        request.iCallback.onProgressUpdated(values[0], values[1]);
    }
}
