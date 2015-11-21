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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.usuario.irui.requestModels.Attribute;
import com.example.usuario.irui.requestModels.Product;
import com.example.usuario.irui.requestModels.ProductComplete;
import com.example.usuario.irui.requestModels.User;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
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
            Toast.makeText(getApplicationContext(), "NOOOOO",
                    Toast.LENGTH_LONG).show();
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
                String product = jsonRootObject.getString("product");

                Gson gson = new Gson();
                ProductComplete product2 = gson.fromJson(product, ProductComplete.class);


                Toast.makeText(getApplicationContext(), product2.getName(),
                        Toast.LENGTH_LONG).show();

                TextView nombreProd = (TextView)findViewById(R.id.productName);
                nombreProd.setText(product2.getName());

                TextView prodPrice = (TextView)findViewById(R.id.prodPrice);
                prodPrice.setText("$"+ product2.getPrice());

                ImageView imageView = (ImageView)findViewById(R.id.prodImg);
                Picasso.with(this)
                        .load(product2.getImageURL()[0])
                        .into(imageView);

//                Attribute attr =

//                JSONObject j = products.getJSONObject(i);
//                JSONArray atts = j.getJSONArray("attributes");
//                String brand = "-";
//                for(int k = 0; k<atts.length(); k++){
//                    if(atts.getJSONObject(k).getInt("id") == 9){
//                        brand = atts.getJSONObject(k).getString("values");
//                        brand = brand.substring(2,brand.length()-2).split(",")[0];
//                    }
//                }
//                String imgUrl = j.getJSONArray("imageUrl").getString(0);
//                Product p = new Product(j.getString("name"), j.getInt("price"), brand, imgUrl, j.getInt("id"));
//                prods[i]=p;



            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "Datos Invalidos",
                        Toast.LENGTH_LONG).show();
            }





        }


    }


}
