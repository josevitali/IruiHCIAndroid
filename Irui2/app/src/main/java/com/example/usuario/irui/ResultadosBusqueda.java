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

public class ResultadosBusqueda extends Base {

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
