package com.example.httplib;

/**
 * Created by HP on 2017/3/24.
 */

public interface OnGlobalExceptionListener {

    boolean handleException(AppException exception);
}
