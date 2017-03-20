package com.example.httplib;

import java.util.Map;

/**
 * Created by HP on 2017/2/26.
 */

public class Request {

    public boolean enableProgressUpdated = false;


    public enum RequestMethod {GET, POST, PUT, DELETE}

    public String url;
    public String content;
    public Map<String, String> headers;

    public RequestMethod method;

    public ICallback iCallback;

    public void setCallback(ICallback iCallback) {
        this.iCallback = iCallback;
    }


    public Request(String url, RequestMethod method) {
        this.url = url;
        this.method = method;
    }

    public Request(String url) {
        this.url = url;
        this.method = RequestMethod.GET;
    }

    public void enableProgressUpdated(boolean enable) {
        this.enableProgressUpdated = enable;
    }
}
