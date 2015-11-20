package com.example.usuario.irui;

import android.app.Application;

import com.example.usuario.irui.requestModels.User;

/**
 * Created by natinavas on 11/19/15.
 */
public class RunningApplication extends Application {

    private String authenticationToken;
    private User user;





    public RunningApplication() {
        super();
    }

    public void setAuthenticationToken(String token){
        this.authenticationToken = token;
    }

    public void setUser(User user){
        this.user=user;
    }

    public String getAuthenticationToken(){
        return authenticationToken;
    }
}
