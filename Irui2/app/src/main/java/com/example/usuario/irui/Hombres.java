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

public class Hombres extends Base {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_hombres, null, false);
        drawer.addView(contentView, 0);

    }

    public void showAllMen(View view){
        Intent intent = new Intent(this, ResultadosBusqueda.class);
        intent.putExtra("search", "menAllAdult");
        this.startActivity(intent);
    }

    public void showOnSale(View view){
        Intent intent = new Intent(this, ResultadosBusqueda.class);
        intent.putExtra("search", "menOnSaleAdult");
        this.startActivity(intent);
    }

    public void showNewArrivals(View view){
        Intent intent = new Intent(this, ResultadosBusqueda.class);
        intent.putExtra("search", "menNewArrivalsAdult");
        this.startActivity(intent);
    }

    public void showClothes(View view){
        Intent intent = new Intent(this, ResultadosBusqueda.class);
        intent.putExtra("search", "menClothesAdult");
        this.startActivity(intent);
    }

    public void showShoes(View view){
        Intent intent = new Intent(this, ResultadosBusqueda.class);
        intent.putExtra("search", "menShoesAdult");
        this.startActivity(intent);
    }

    public void showAccesories(View view){
        Intent intent = new Intent(this, ResultadosBusqueda.class);
        intent.putExtra("search", "menAccesoriesAdult");
        this.startActivity(intent);
    }

}
