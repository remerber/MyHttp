package com.example.http;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by HP on 2017/1/18.
 */

public class RequestManager {

    public static RequestManager mInstance;
    private HashMap<String, ArrayList<Request>> mCacheRequest;

    public static RequestManager getInstance() {
        if (mInstance == null) {
            mInstance = new RequestManager();
        }
        return mInstance;

    }

    public RequestManager() {
        mCacheRequest = new HashMap<>();
    }

    public void performRequest(Request request) {
        RequestTask task = new RequestTask(request);
        task.execute();
        if (!mCacheRequest.containsKey(request.tag)) {
            ArrayList<Request> requests = new ArrayList<>();
            mCacheRequest.put(request.tag, requests);
        }
        mCacheRequest.get(request.tag).add(request);

    }

    public void cancelRequest(String tag) {
        if (tag == null || "".equals(tag.trim())) {
            return;
        }
        if (mCacheRequest.containsKey(tag)) {
            ArrayList<Request> requests = mCacheRequest.remove(tag);
            for (Request request : requests) {
                if (!request.isCancelled && tag.equals(request.tag)) {
                    request.cancel();
                }
            }


        }
    }

    public void cancelAll() {

        for (Map.Entry<String, ArrayList<Request>> entry : mCacheRequest.entrySet()) {

            ArrayList<Request> requests = entry.getValue();
            for (Request request : requests) {
                request.cancel();
            }


        }
    }
}
