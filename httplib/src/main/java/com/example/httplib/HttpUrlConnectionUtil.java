package com.example.httplib;

import android.renderscript.ScriptGroup;

import org.apache.http.HttpStatus;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

/**
 * Created by HP on 2017/2/26.
 */

public class HttpUrlConnectionUtil {


    public static HttpURLConnection execute(Request request) throws IOException {
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

    private static HttpURLConnection get(Request request) throws IOException {

        HttpURLConnection connection = (HttpURLConnection) new URL(request.url).openConnection();
        connection.setRequestMethod(request.method.name());
        connection.setConnectTimeout(15 * 3000);
        connection.setReadTimeout(15 * 3000);

        addHeaders(connection, request.headers);


        return connection;

    }

    private static HttpURLConnection post(Request request) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(request.url).openConnection();
        connection.setRequestMethod(request.method.name());
        connection.setConnectTimeout(15 * 3000);
        connection.setReadTimeout(15 * 3000);
        connection.setDoOutput(true);

        addHeaders(connection, request.headers);

        OutputStream os = connection.getOutputStream();
        os.write(request.content.getBytes());


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
