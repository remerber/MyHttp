package com.example.httplib;

/**
 * Created by HP on 2017/3/6.
 */

public interface ICallback {

    void onSuccess(String result);

    void onFailure(Exception e);

}
