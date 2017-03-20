package com.example.httplib;

/**
 * Created by HP on 2017/3/18.
 */

public abstract class FileCallback extends AbstractCallback<String> {
    @Override
    protected String bindData(String path) throws Exception {
        return path;
    }
}
