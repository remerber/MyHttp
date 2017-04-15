package com.example.http;

import java.util.Map;

/**
 * Created by HP on 2017/1/13.
 */

public class Request {

    public ICallBack iCallBack;
    public int maxRetryCount;

    public String url;
    public String content;
    public Map<String, String> headers;
    public boolean enableProgressUpdated = false;
    public OnGlobalExceptionListener onGlobalExceptionListener;
    public volatile boolean isCancelled;
    public String tag;

    public void setCallback(ICallBack iCallBack) {
        this.iCallBack = iCallBack;
    }

    public void enableProgressUpdated(boolean enalbe) {
        this.enableProgressUpdated = true;
    }

    public void setGlobalExceptionListener(OnGlobalExceptionListener onGlobalExceptionListener) {
        this.onGlobalExceptionListener = onGlobalExceptionListener;
    }

    public void checkIfCancelled() throws AppException {
        if (isCancelled) {
            throw new AppException(AppException.ErrorType.CANCEL, "the request has cancelled");
        }

    }

    public void cancel() {
        isCancelled = true;
        iCallBack.cancel();
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public enum RequestMethod {GET, POST, PUT, DELETE}

    public RequestMethod method;

    public Request(String url, RequestMethod method) {
        this.url = url;
        this.method = method;
    }

    public Request(String url) {
        this.url = url;
        this.method = RequestMethod.GET;
    }
}
