package com.example.httplib;

import android.renderscript.ScriptGroup;
import android.webkit.URLUtil;

import org.apache.http.HttpStatus;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

/**
 * Created by HP on 2017/2/26.
 */

public class HttpUrlConnectionUtil {


    public static HttpURLConnection execute(Request request) throws AppException {
        if (!URLUtil.isNetworkUrl(request.url)) {
            throw new AppException(AppException.ErrorType.MANUAL, "the url" + request.url + "is  not valid");
        }


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

        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(request.url).openConnection();
            connection.setRequestMethod(request.method.name());
            connection.setConnectTimeout(15 * 3000);
            connection.setReadTimeout(15 * 3000);

            addHeaders(connection, request.headers);


            return connection;
        } catch (InterruptedIOException e) {
            throw new AppException(AppException.ErrorType.TIMEOUT, e.getMessage());
        } catch (IOException e) {
            throw new AppException(AppException.ErrorType.SERVER, e.getMessage());
        }

    }

    private static HttpURLConnection post(Request request) throws AppException {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(request.url).openConnection();
            connection.setRequestMethod(request.method.name());
            connection.setConnectTimeout(15 * 3000);
            connection.setReadTimeout(15 * 3000);
            connection.setDoOutput(true);

            addHeaders(connection, request.headers);

            OutputStream os = connection.getOutputStream();
            os.write(request.content.getBytes());
            return connection;
        } catch (InterruptedIOException e) {
            throw new AppException(AppException.ErrorType.TIMEOUT, e.getMessage());
        } catch (IOException e) {
            throw new AppException(AppException.ErrorType.SERVER, e.getMessage());
        }


    }

    private static void addHeaders(HttpURLConnection connection, Map<String, String> headers) {
        if (headers == null || headers.size() == 0)
            return;
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            connection.addRequestProperty(entry.getKey(), entry.getValue());
        }
    }

}
