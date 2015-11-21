package com.example.usuario.irui;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.example.usuario.irui.requestModels.Product;
import com.example.usuario.irui.requestModels.User;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

public class PaginaProducto extends Base {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_pagina_producto, null, false);
        drawer.addView(contentView, 0);

        Intent myIntent = getIntent();

        if(myIntent.hasExtra("prodId") && myIntent.getStringExtra("prodId") != null) {
            String request = "http://eiffel.itba.edu.ar/hci/service3/Catalog.groovy?method=GetProductById&id=" + myIntent.getStringExtra("prodId");
            Toast.makeText(getApplicationContext(), request,
                    Toast.LENGTH_LONG).show();

            new Connection(this, request).execute();
        }
        else
            Toast.makeText(getApplicationContext(), "NOOOOO",
                    Toast.LENGTH_LONG).show();

    }




    public void afterRequest(String s){
        if(s != "error"){

            JSONObject jsonRootObject = null;

            try {
                jsonRootObject = new JSONObject(s);

                String prod = jsonRootObject.getString("product");

                Gson gson = new Gson();
                Product product = gson.fromJson(prod, Product.class);


                Intent intent = new Intent(this, MainActivity.class);
                this.startActivity(intent);


            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "Datos Invalidos",
                        Toast.LENGTH_LONG).show();
            }





        }


    }


}
