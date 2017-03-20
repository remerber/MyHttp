package com.example.hp.myapplication;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.httplib.FileCallback;
import com.example.httplib.HttpUrlConnectionUtil;
import com.example.httplib.ICallback;
import com.example.httplib.JsonCallback;
import com.example.httplib.Request;
import com.example.httplib.RequestTask;

import java.io.File;
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
                // testHttpPostOnSubThread();
                downloadFile();
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

    public void downloadFile() {
        String url = "http://api.stay4it.com/v1/public/core/?service=user.login";
        String url2 = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1489858035017&di=69938e9522375fd9f72968830ea85369&imgtype=0&src=http%3A%2F%2Fwww.yuyihz.com%2Fuserfile%2Fimage%2F201512%2F20151219115741_4222.jpg";
        String content = "account=stay4it&password=123456";
        String path = Environment.getExternalStorageDirectory() + File.separator + "pic222.jpg";
        Request request = new Request(url2, Request.RequestMethod.GET);
        request.setCallback(new FileCallback() {
            @Override
            public void onProgressUpdated(int curLen, int totalLen) {
                Log.e("tag", "curLen/totalLen=  " + curLen + "   /   " + totalLen);
            }

            @Override
            public void onSuccess(String result) {
                Log.e("tag", result);
            }

            @Override
            public void onFailure(Exception e) {

            }
        }.setCachePath(path));
        request.content = content;
        request.enableProgressUpdated(true);
        RequestTask task = new RequestTask(request);
        task.execute();
    }
}
