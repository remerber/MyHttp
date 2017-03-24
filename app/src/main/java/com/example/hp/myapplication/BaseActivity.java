package com.example.hp.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.httplib.AppException;
import com.example.httplib.OnGlobalExceptionListener;

/**
 * Created by HP on 2017/3/24.
 */

public class BaseActivity extends AppCompatActivity implements OnGlobalExceptionListener {


    @Override
    public boolean handleException(AppException exception) {

        if (exception.statusCode == 403) {
            if ("Forbidden".equals(exception.getMessage())) {
                //// TODO: reLogin
                Toast.makeText(getApplicationContext(), "重新登录", Toast.LENGTH_SHORT).show();
                return true;
            }
        }
        return false;
    }
}
