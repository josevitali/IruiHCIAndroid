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

public class Ninos extends Base {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_ninos, null, false);
        drawer.addView(contentView, 0);

    }

    public void showAllBoys(View view){
        Intent intent = new Intent(this, ResultadosBusqueda.class);
        intent.putExtra("search", "menAllKid");
        this.startActivity(intent);
    }

    public void showOnSale(View view){
        Intent intent = new Intent(this, ResultadosBusqueda.class);
        intent.putExtra("search", "menOnSaleKid");
        this.startActivity(intent);
    }

    public void showNewArrivals(View view){
        Intent intent = new Intent(this, ResultadosBusqueda.class);
        intent.putExtra("search", "menNewArrivalsKid");
        this.startActivity(intent);
    }

    public void showClothes(View view){
        Intent intent = new Intent(this, ResultadosBusqueda.class);
        intent.putExtra("search", "menClothesKid");
        this.startActivity(intent);
    }

    public void showShoes(View view){
        Intent intent = new Intent(this, ResultadosBusqueda.class);
        intent.putExtra("search", "menShoesKid");
        this.startActivity(intent);
    }

    public void showAccesories(View view){
        Intent intent = new Intent(this, ResultadosBusqueda.class);
        intent.putExtra("search", "menAccesoriesKid");
        this.startActivity(intent);
    }


}
