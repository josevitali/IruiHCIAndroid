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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Date;

public class MainActivity extends Base{




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/


        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.content_main, null, false);
        drawer.addView(contentView, 0);


    }



    public void pagHombres(View view){
        Intent intent = new Intent(this, Hombres.class);
        this.startActivity(intent);
    }

    public void pagMujeres(View view){
        Intent intent = new Intent(this, Mujeres.class);
        this.startActivity(intent);
    }

    public void pagNinos(View view){
        Intent intent = new Intent(this, Ninos.class);
        this.startActivity(intent);
    }
    
    public void pagNinas(View view){
        Intent intent = new Intent(this, Girls.class);
        this.startActivity(intent);
    }

    public void pagBebes(View view){
        Intent intent = new Intent(this, Babies.class);
        this.startActivity(intent);
    }

}
