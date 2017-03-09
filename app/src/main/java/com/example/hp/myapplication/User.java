package com.example.hp.myapplication;

/**
 * Created by HP on 2017/3/9.
 */

public class User {
    public String id;
    public String account;
    public String email;
    public String username;
    public String token;

    @Override
    public String toString() {
        return username + "   :   " + email;
    }
}
