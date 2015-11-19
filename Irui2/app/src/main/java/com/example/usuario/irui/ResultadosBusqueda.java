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
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

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

        Intent myIntent = getIntent(); // gets the previously created intent


        //se realizo la busqueda por palabra desde la barra de navegacion
        if(myIntent.hasExtra("searchText")){
            String s = myIntent.getStringExtra("searchText");
            Toast.makeText(getApplicationContext(), s,
                    Toast.LENGTH_SHORT).show();
        }else if(myIntent.hasExtra("women")){
            Toast.makeText(getApplicationContext(), "muestro ropa de mujeres",
                    Toast.LENGTH_SHORT).show();
        }else if(myIntent.hasExtra("men")){
            Toast.makeText(getApplicationContext(), "muestro ropa de hombres",
                    Toast.LENGTH_SHORT).show();
        }else if(myIntent.hasExtra("children")){
            Toast.makeText(getApplicationContext(), "muestro ropa de ni;os",
                    Toast.LENGTH_SHORT).show();
        }







//        String s = "http://eiffel.itba.edu.ar/hci/service3/Common.groovy?method=GetAllStates";
//
//        new Connection(this).execute();

    }

        //resp = connection.connect(s);
/*
        try {
            resp.getJSONArray(r);
        } catch (JSONException e) {
            e.printStackTrace();
        }


*/

        public void afterRequest(String s){

        Toast.makeText(getApplicationContext(), s,
                Toast.LENGTH_SHORT).show();

    }




    public void filtros(View view){

        Intent intent = new Intent(this, Filtros.class);
        this.startActivity(intent);
    }

    public void ordenarPor(View view){
        Intent intent = new Intent(this, OrdenarPor.class);
        this.startActivity(intent);
    }

}
