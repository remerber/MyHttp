package com.example.http;

import org.apache.http.HttpStatus;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.BrokenBarrierException;

/**
 * Created by HP on 2017/1/13.
 */

public class HttpUrlConnectionUtil {

    public static HttpURLConnection execute(Request request) throws AppException {
        switch (request.method) {

            case GET:
            case DELETE:
                return get(request);
            case POST:
            case PUT:
                return post(request);

        }
        return null;

    }


    private static HttpURLConnection get(Request request) throws AppException {
        HttpURLConnection connection = null;
        try {

            request.checkIfCancelled();

            connection = (HttpURLConnection) new URL(request.url).openConnection();
            connection.setRequestMethod(request.method.name());
            connection.setConnectTimeout(15 * 3000);
            connection.setReadTimeout(15 * 3000);

            addHeaders(connection, request.headers);

            request.checkIfCancelled();

        } catch (InterruptedIOException e) {
            throw new AppException(AppException.ErrorType.TIMEOUT, e.getMessage());
        } catch (IOException e) {
            throw new AppException(AppException.ErrorType.SERVER, e.getMessage());
        }

        return connection;

    }

    private static HttpURLConnection post(Request request) throws AppException {

        HttpURLConnection connection = null;
        try {

            request.checkIfCancelled();

            connection = (HttpURLConnection) new URL(request.url).openConnection();
            connection.setRequestMethod(request.method.name());
            connection.setConnectTimeout(15 * 3000);
            connection.setReadTimeout(15 * 3000);
            connection.setDoOutput(true);

            addHeaders(connection, request.headers);

            OutputStream os = connection.getOutputStream();
            os.write(request.content.getBytes());

            request.checkIfCancelled();

        } catch (InterruptedIOException e) {
            throw new AppException(AppException.ErrorType.TIMEOUT, e.getMessage());
        } catch (IOException e) {
            throw new AppException(AppException.ErrorType.SERVER, e.getMessage());
        }

        return connection;


    }

    private static void addHeaders(HttpURLConnection connection, Map<String, String> headers) {
        if (headers == null || headers.size() == 0)
            return;
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            connection.addRequestProperty(entry.getKey(), entry.getValue());
        }

    }
}
