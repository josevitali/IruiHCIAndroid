package com.example.usuario.irui;

import android.app.Application;

/**
 * Created by natinavas on 11/19/15.
 */
public class RunningApplication extends Application {

    private String authenticationToken;
    private String name;


    private static RunningApplication ourInstance = new RunningApplication();


    public static RunningApplication getInstance() {
        return ourInstance;
    }

    private RunningApplication() {
    }

    public void setAuthenticationToken(String token){
        this.authenticationToken = token;
    }

    public void setName(String name){
        this.name=name;
    }
}
