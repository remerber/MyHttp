package com.example.hp.myhttp;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.http.AppException;
import com.example.http.FileCallback;
import com.example.http.JsonCallback;
import com.example.http.Module;
import com.example.http.OnGlobalExceptionListener;
import com.example.http.Request;
import com.example.http.RequestManager;
import com.example.http.RequestTask;
import com.example.http.User;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private Button mBtnget, mBtnpost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBtnget = (Button) findViewById(R.id.btn_get);
        mBtnget.setOnClickListener(this);
        mBtnpost = (Button) findViewById(R.id.btn_post);
        mBtnpost.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_get:
                break;
            case R.id.btn_post:
                testPost();
                break;


        }
    }


    public void testPost() {
        String url = "http://api.stay4it.com/v1/public/core/?service=user.login";
        //  String url = "http://api.stay4it.com/uploads/test.jpg";

        String content = "account=stay4it&password=123456";
        final Request request = new Request(url, Request.RequestMethod.POST);


        request.content = content;
        String path = Environment.getExternalStorageDirectory() + File.separator + "test2.jpg";
//        request.setCallback(new FileCallback() {
//            @Override
//            public void onProgressUpdated(int curLen, int totalLen) {
//                Log.e("ww", curLen + "/" + totalLen);
////                if (curLen * 100l / totalLen > 50) {
////                    request.cancel();
////                    // tasks.cancel(true);
////                    Log.e("ww", curLen + "//////" + totalLen);
////                }
//            }
//
//            @Override
//            public void onSuccess(String path) {
//
//            }
//
//            @Override
//            public void onFailure(AppException e) {
//                e.printStackTrace();
//            }
//
//
//        }.setCachePath(path));
        request.setCallback(new JsonCallback<User>() {

            @Override
            public User preRequest() {
                return null;
            }

            @Override
            public User postRequest(User user) {
                //TODO insert into db
                //TODO edit user
                user.email = "******";
                return user;
            }


            @Override
            public void onSuccess(User result) {
                Log.e("ww", result.toString());
            }

            @Override
            public void onFailure(AppException e) {

            }


        });

        request.enableProgressUpdated(true);
        request.setGlobalExceptionListener(this);
        request.setTag(toString());
        RequestManager.getInstance().performRequest(request);
//        tasks.cancel(true);
//        request.cancel();


    }

    public void testPostLoadMore() {
        String url = "http://api.stay4it.com/v1/public/core/?service=user.login";
        String content = "account=stay4it&password=123456";
        final Request request = new Request(url, Request.RequestMethod.POST);
        request.content = content;
        request.setCallback(new JsonCallback<ArrayList<Module>>() {


            @Override
            public ArrayList<Module> preRequest() {
                //TODO fetch data
                return null;
            }


            @Override
            public ArrayList<Module> postRequest(ArrayList<Module> modules) {
                //TODO insert into db
                return modules;
            }

            @Override
            public void onSuccess(ArrayList<Module> result) {

            }

            @Override
            public void onFailure(AppException e) {

            }


        });


        request.setGlobalExceptionListener(this);
        request.setTag(toString());
        RequestManager.getInstance().performRequest(request);


    }

    @Override
    protected void onStop() {
        super.onStop();
        RequestManager.getInstance().cancelRequest(toString());
    }
}
