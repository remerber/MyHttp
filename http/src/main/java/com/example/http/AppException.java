package com.example.http;

/**
 * Created by HP on 2017/1/17.
 */

public class AppException extends Exception {
    public int status;
    public String responseMessage;

    public enum ErrorType {CANCEL, TIMEOUT, SERVER, JSON, IO, FILE_NOT_FOUND, MANUAL}

    public ErrorType type;

    public AppException(int status, String responseMessage) {
        super(responseMessage);
        this.type = ErrorType.SERVER;
        this.status = status;
        this.responseMessage = responseMessage;
    }

    public AppException(ErrorType type, String detailMessage) {
        super(detailMessage);
        this.type = type;

    }
}
