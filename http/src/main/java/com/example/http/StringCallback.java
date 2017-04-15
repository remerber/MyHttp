package com.example.http;


import com.google.gson.Gson;

import org.json.JSONObject;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by HP on 2017/1/13.
 */

public abstract class StringCallback extends AbstractCallback {
    @Override
    protected String bindData(String result) throws AppException {

        return result;
    }
}
