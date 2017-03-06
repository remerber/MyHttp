package com.example.httplib;

import android.os.AsyncTask;

import java.io.IOException;

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
        try {
            return HttpUrlConnectionUtil.execute(request);
        } catch (IOException e) {
            return e;
        }

    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        if (o instanceof Exception) {
            request.iCallback.onFailure((Exception) o);
        } else {
            request.iCallback.onSuccess((String) o);
        }
    }
}
