package com.example.hp.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.httplib.HttpUrlConnectionUtil;
import com.example.httplib.Request;

import java.io.IOException;
import java.net.HttpURLConnection;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void testPost() throws IOException {

        String url = "";
        String content = "";
        Request request = new Request(url, Request.RequestMethod.POST);
        String result = HttpUrlConnectionUtil.execute(request);

    }
}
