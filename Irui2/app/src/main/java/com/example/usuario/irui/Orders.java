package com.example.usuario.irui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.example.usuario.irui.requestModels.User;
import com.google.gson.Gson;

public class Orders extends Base {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*setContentView(R.layout.activity_orders);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/

        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_orders, null, false);
        drawer.addView(contentView, 0);


        final SharedPreferences preferences = getSharedPreferences("MY_PREFS", MODE_PRIVATE);

        String token = preferences.getString("TOKEN", "no token defined");
        String account = preferences.getString("USER", "no account defined");

        Gson gson = new Gson();
        User user = gson.fromJson(account, User.class);


        String request ="http://eiffel.itba.edu.ar/hci/service3/Order.groovy?method=GetAllOrders&username="
                + user.getUsername() + "&authentication_token=" + token;

        new Connection(this, request).execute();
    }


    public void afterRequest(String s) {

        if(s != "error") {
            Toast.makeText(getApplicationContext(), s,
                    Toast.LENGTH_LONG).show();



        }else{
            Toast.makeText(getApplicationContext(), "Hubo un error al intentar conectarse con el servidor",
                    Toast.LENGTH_LONG).show();
        }


    }

}
