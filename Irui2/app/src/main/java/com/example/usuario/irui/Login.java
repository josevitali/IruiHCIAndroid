package com.example.usuario.irui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.usuario.irui.requestModels.User;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Login extends Base {

    private EditText mPasswordView;
    private EditText mUserView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_login, null, false);
        drawer.addView(contentView, 0);

    }

    public void attemptLogin(View view){
        mPasswordView = (EditText) findViewById(R.id.password);
        mUserView = (EditText) findViewById(R.id.username);

        String user = mUserView.getText().toString();
        String pass = mPasswordView.getText().toString();



        String request = "http://eiffel.itba.edu.ar/hci/service3/Account.groovy?method=SignIn&username=" + user +
                "&password=" + pass;

        new Connection(this, request).execute();


    }

    public void afterRequest(String resp){

        JSONObject  jsonRootObject = null;
        try {
            jsonRootObject = new JSONObject(resp);

            String token = jsonRootObject.getString("authenticationToken");

            String account = jsonRootObject.getString("account");


            Gson gson = new Gson();
            User user = gson.fromJson(account, User.class);

            RunningApplication app = (RunningApplication)this.getApplication();

            app.setAuthenticationToken(token);

            Intent intent = new Intent(this, MainActivity.class);
            this.startActivity(intent);


        } catch (JSONException e) {
            e.printStackTrace();
        }


    }


}
