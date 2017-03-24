package com.example.httplib;

/**
 * Created by HP on 2017/3/20.
 */

public class AppException extends Exception {
    public int statusCode;
    public String responseMessage;

    public enum ErrorType {TIMEOUT, SERVER, JSON, IO, FILE_NO_FOUND, MANUAL}

    public ErrorType type;


    public AppException(int status, String responseMessage) {
        super(responseMessage);
        type = ErrorType.SERVER;
        this.statusCode = status;
        this.responseMessage = responseMessage;
    }

    public AppException(ErrorType type, String detailMessage) {
        super(detailMessage);
        this.type = type;
    }
}
