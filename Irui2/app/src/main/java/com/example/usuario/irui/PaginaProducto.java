package com.example.usuario.irui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.usuario.irui.requestModels.ProductComplete;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

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
                
                TextView nombreProd = (TextView)findViewById(R.id.productName);
                nombreProd.setText(product2.getName());

                TextView prodPrice = (TextView)findViewById(R.id.prodPrice);
                prodPrice.setText("$" + product2.getPrice());

                //busco imagen
                JSONObject jsProd = jsonRootObject.getJSONObject("product");
                JSONArray jp = jsProd.getJSONArray("imageUrl");
                TextView imageUrl = (TextView)findViewById(R.id.imageUrl);
                imageUrl.setText(jp.getString(0));
                ImageView imageView = (ImageView)findViewById(R.id.prodImg);
                Picasso.with(this)
                        .load(jp.getString(0))
                        .into(imageView);


                if(jp.getString(1) != null) {
                    ImageView imageView2 = (ImageView) findViewById(R.id.firstImage);
                    imageView2.setVisibility(View.VISIBLE);
                    TextView firstImageUrl = (TextView)findViewById(R.id.firstImageUrl);
                    firstImageUrl.setText(jp.getString(1));
                    Picasso.with(this)
                            .load(jp.getString(1))
                            .into(imageView2);
                }


                if(jp.getString(2) != null) {
                    ImageView imageView3 = (ImageView) findViewById(R.id.secondImage);
                    imageView3.setVisibility(View.VISIBLE);
                    TextView secondImageUrl = (TextView)findViewById(R.id.secondImageUrl);
                    secondImageUrl.setText(jp.getString(2));
                    Picasso.with(this)
                            .load(jp.getString(2))
                            .into(imageView3);
                }

                //busco marca
                jp = jsProd.getJSONArray("attributes");
                for(int i = 0; i < jp.length(); i++){
                    if(jp.getJSONObject(i).getInt("id") == 9){
                        TextView prodBrand = (TextView)findViewById(R.id.infoBrand);
                        prodBrand.setText(prodBrand.getText().toString() + " " + jp.getJSONObject(i).getJSONArray("values").getString(0));
                    }
                }

                //busco material
                jp = jsProd.getJSONArray("attributes");
                for(int i = 0; i < jp.length(); i++){

                    if(jp.getJSONObject(i).getString("name").split("-")[0].equals("Material")){
                        TextView prodMaterial = (TextView)findViewById(R.id.infoMaterial);
                        prodMaterial.setText(prodMaterial.getText().toString() + " " + jp.getJSONObject(i).getJSONArray("values").getString(0));
                    }
                }

                //busco ocasion
                jp = jsProd.getJSONArray("attributes");
                for(int i = 0; i < jp.length(); i++){

                    if(jp.getJSONObject(i).getInt("id") == 3){
                        TextView prodOcassion = (TextView)findViewById(R.id.infoOcassion);
                        prodOcassion.setText(prodOcassion.getText().toString() + " " + jp.getJSONObject(i).getJSONArray("values").getString(0));
                    }
                }

                //talles
                Spinner spinner = (Spinner) findViewById(R.id.prodSizes);
                ArrayList<String> talles = new ArrayList<>();
                jp = jsProd.getJSONArray("attributes");
                for(int i = 0; i < jp.length(); i++){
                    if(jp.getJSONObject(i).getString("name").split("-")[0].equals("Talle")){
                        for(int j = 0; j < jp.getJSONObject(i).getJSONArray("values").length(); j++)
                        talles.add(jp.getJSONObject(i).getJSONArray("values").getString(j));
                    }
                }
                TextView t = (TextView)findViewById(R.id.prodSizesText);
                talles.add(0, t.getText().toString());
                ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this,   android.R.layout.simple_spinner_item, talles);
                spinner.setAdapter(spinnerArrayAdapter);

                //colores
                Spinner spinner2 = (Spinner) findViewById(R.id.prodColors);
                ArrayList<String> colores = new ArrayList<>();
                jp = jsProd.getJSONArray("attributes");
                for(int i = 0; i < jp.length(); i++){
                    if(jp.getJSONObject(i).getInt("id") == 4){
                        for(int j = 0; j < jp.getJSONObject(i).getJSONArray("values").length(); j++)
                            colores.add(jp.getJSONObject(i).getJSONArray("values").getString(j));
                    }
                }
                TextView t2 = (TextView)findViewById(R.id.prodColorsText);
                colores.add(0, t2.getText().toString());
                ArrayAdapter<String> spinnerArrayAdapter2 = new ArrayAdapter<String>(this,   android.R.layout.simple_spinner_item, colores);
                spinner2.setAdapter(spinnerArrayAdapter2);


            } catch (JSONException e) {
                e.printStackTrace();
            }





        }


    }


    public void changeFirstPic(View view){
        TextView firstImageUrl = (TextView)findViewById(R.id.firstImageUrl);
        String url = firstImageUrl.getText().toString();

        TextView imageUrl = (TextView)findViewById(R.id.imageUrl);
        String url2 = imageUrl.getText().toString();

        firstImageUrl.setText(url2);
        imageUrl.setText(url);

        ImageView imageView = (ImageView)findViewById(R.id.prodImg);
        Picasso.with(this)
                .load(url)
                .into(imageView);


        ImageView imageView1 = (ImageView)findViewById(R.id.firstImage);
        Picasso.with(this)
                .load(url2)
                .into(imageView1);


    }

    public void changeSecondPic(View view){

        TextView secondImageUrl = (TextView)findViewById(R.id.secondImageUrl);
        String url1 = secondImageUrl.getText().toString();

        TextView imageUrl = (TextView)findViewById(R.id.imageUrl);
        String url2 = imageUrl.getText().toString();

        imageUrl.setText(url1);
        secondImageUrl.setText(url2);


        ImageView imageView = (ImageView)findViewById(R.id.prodImg);
        Picasso.with(this)
                .load(url1)
                .into(imageView);

        ImageView imageView1 = (ImageView)findViewById(R.id.secondImage);
        Picasso.with(this)
                .load(url2)
                .into(imageView1);


    }


}
