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
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


import android.widget.ListView;


import com.google.gson.Gson;


import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ResultadosBusqueda extends Base{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*setContentView(R.layout.resultados_busqueda);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/


        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.resultados_busqueda, null, false);
        drawer.addView(contentView, 0);


        String s = "http://eiffel.itba.edu.ar/hci/service3/Catalog.groovy?method=GetAllProducts";

        new Connection(this, s).execute();

    }

        //resp = connection.connect(s);
/*
        try {
            resp.getJSONArray(r);
        } catch (JSONException e) {
            e.printStackTrace();
        }


*/


        public void afterRequest(String resp){

        Toast.makeText(getApplicationContext(), resp,
                Toast.LENGTH_SHORT).show();

            Gson gson = new Gson();


           // Type type = new TypeToken<Respuesta>() {}.getType();

            //gson.fromJson(resp, type);
    }

/*
    public class Meta{
        String uuid;
        String time;
    }

    public class ProductList{
        Meta meta;
        int page;
        pageSize;
        total;
        List<Product> products;
    }

*/


    public void filtros(View view){

        Intent intent = new Intent(this, Filtros.class);
        this.startActivity(intent);
    }

    public void ordenarPor(View view){
        Intent intent = new Intent(this, OrdenarPor.class);
        this.startActivity(intent);
    }

}
