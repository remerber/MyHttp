package com.example.http;

/**
 * Created by HP on 2017/1/17.
 */

public interface OnGlobalExceptionListener {
    boolean handleException(AppException exception);
}
