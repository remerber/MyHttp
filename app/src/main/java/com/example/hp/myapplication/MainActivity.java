package com.example.hp.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.httplib.HttpUrlConnectionUtil;
import com.example.httplib.ICallback;
import com.example.httplib.JsonCallback;
import com.example.httplib.Request;
import com.example.httplib.RequestTask;

import java.io.IOException;
import java.net.HttpURLConnection;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.button);
        textView = (TextView) findViewById(R.id.textView);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testHttpPostOnSubThread();
            }
        });

    }


    public void testHttpPostOnSubThread() {
        String url = "http://api.stay4it.com/v1/public/core/?service=user.login";
        String content = "account=stay4it&password=123456";
        final Request request = new Request(url, Request.RequestMethod.POST);
        request.setCallback(new JsonCallback<User>() {

            @Override
            public void onSuccess(User result) {
                textView.setText(result.toString());
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
        request.content = content;
        RequestTask task = new RequestTask(request);
        task.execute();

    }
}
