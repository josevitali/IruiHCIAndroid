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

public class Girls extends Base {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_girls, null, false);
        drawer.addView(contentView, 0);

    }

    public void showAllGirls(View view){
        Intent intent = new Intent(this, ResultadosBusqueda.class);
        intent.putExtra("search", "womenAllKid");
        this.startActivity(intent);
    }

    public void showOnSale(View view){
        Intent intent = new Intent(this, ResultadosBusqueda.class);
        intent.putExtra("search", "womenOnSaleKid");
        this.startActivity(intent);
    }

    public void showNewArrivals(View view){
        Intent intent = new Intent(this, ResultadosBusqueda.class);
        intent.putExtra("search", "womenNewArrivalsKid");
        this.startActivity(intent);
    }

    public void showClothes(View view){
        Intent intent = new Intent(this, ResultadosBusqueda.class);
        intent.putExtra("search", "womenClothesKid");
        this.startActivity(intent);
    }

    public void showShoes(View view){
        Intent intent = new Intent(this, ResultadosBusqueda.class);
        intent.putExtra("search", "womenShoesKid");
        this.startActivity(intent);
    }

    public void showAccesories(View view){
        Intent intent = new Intent(this, ResultadosBusqueda.class);
        intent.putExtra("search", "womenAccesoriesKid");
        this.startActivity(intent);
    }


}
