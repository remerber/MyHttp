package com.example.httplib;

import java.util.Map;

/**
 * Created by HP on 2017/2/26.
 */

public class Request {
    public enum  RequestMethod {GET,POST,PUT,DELETE}

    public String url;
    public String content;
    public Map<String,String> headers;

     public RequestMethod method;

    public Request(String url, RequestMethod method) {
        this.url = url;
        this.method = method;
    }

    public Request(String url) {
        this.url = url;
        this.method=RequestMethod.GET;
    }
}
