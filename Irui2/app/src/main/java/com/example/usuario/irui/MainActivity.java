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


    private AlarmManager alarmManager;
    private PendingIntent alarmNotificationReceiverPendingIntent;
    private final static int INTERVAL = 10000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/


        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_main, null, false);
        drawer.addView(contentView, 0);


        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        Intent alarmNotificationReceiverIntent =
                new Intent(MainActivity.this, ChangeOrderReceiver.class);
        alarmNotificationReceiverPendingIntent =
                PendingIntent.getBroadcast(MainActivity.this, 0, alarmNotificationReceiverIntent, 0);


        final SharedPreferences preferences = getSharedPreferences("MY_PREFS", MODE_PRIVATE);

        String token = preferences.getString("TOKEN", "no token defined");

        alarmManager.set(AlarmManager.RTC_WAKEUP,
                System.currentTimeMillis() + INTERVAL,
                alarmNotificationReceiverPendingIntent);

        Toast.makeText(MainActivity.this, "Single alarm set", Toast.LENGTH_LONG)
                .show();




    }


    public void resultados(View view){

        alarmManager.cancel(alarmNotificationReceiverPendingIntent);

        Toast.makeText(MainActivity.this, "Repeating alarm cancelled", Toast.LENGTH_LONG)
                .show();


        Intent intent = new Intent(this, ResultadosBusqueda.class);
        this.startActivity(intent);
    }

}
