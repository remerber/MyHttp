package com.example.httplib;

import org.apache.http.HttpStatus;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;

/**
 * Created by HP on 2017/3/9.
 */

public abstract class AbstractCallback<T> implements ICallback<T> {

    @Override
    public T parse(HttpURLConnection connection) throws Exception {
        int status = connection.getResponseCode();
        if (status == HttpStatus.SC_OK) {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            InputStream is = connection.getInputStream();
            byte[] buffer = new byte[2048];
            int len;
            while ((len = is.read(buffer)) != -1) {
                out.write(buffer, 0, len);
            }
            is.close();
            out.flush();
            out.close();
            String result = new String(out.toByteArray());
            return bindData(result);

        }


        return null;
    }

    protected abstract T bindData(String result) throws Exception;

}
