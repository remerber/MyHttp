package com.example.http;



/**
 * Created by HP on 2017/1/13.
 */

public abstract class XMLCallback<T> extends AbstractCallback<T> {
    @Override
    protected T bindData(String result) throws AppException {
        return null;
    }
}
