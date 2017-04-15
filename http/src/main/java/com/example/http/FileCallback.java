package com.example.http;



/**
 * Created by HP on 2017/1/13.
 */

public abstract class FileCallback extends AbstractCallback<String> {
    @Override
    protected String bindData(String path) throws AppException {
        return path;
    }
}
