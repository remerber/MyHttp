package com.example.hp.myhttp;

import android.support.v7.app.AppCompatActivity;

import com.example.http.AppException;
import com.example.http.OnGlobalExceptionListener;
import com.example.http.RequestManager;

/**
 * Created by HP on 2017/1/17.
 */

public class BaseActivity extends AppCompatActivity implements OnGlobalExceptionListener {

    @Override
    public boolean handleException(AppException e) {
        if (e.status == 403) {
            if ("token invalid".equals(e.getMessage())) {
                //TODO reLogin
                return true;
            }
        }
        return false;

    }

    @Override
    protected void onStop() {
        super.onStop();
        RequestManager.getInstance().cancelRequest(toString());
    }
}
