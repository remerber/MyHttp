package com.example.httplib;

/**
 * Created by HP on 2017/3/18.
 */

public abstract class StringCallback extends AbstractCallback<String> {
    @Override
    protected String bindData(String result) throws AppException {
        return result;
    }
}
