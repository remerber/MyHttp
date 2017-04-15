package com.example.http;

/**
 * Created by HP on 2017/1/13.
 */

public class User {
    public String id;
    public String account;
    public String email;
    public String username;
    public String token;

    @Override
    public String toString() {
        return username + "   : " + email;
    }
}
