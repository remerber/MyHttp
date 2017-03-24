package com.example.httplib;

/**
 * Created by HP on 2017/3/9.
 */

public  abstract  class XMLCallback<T> extends  AbstractCallback<T> {
    @Override
    protected T bindData(String result) throws AppException {
        return null;
    }
}
