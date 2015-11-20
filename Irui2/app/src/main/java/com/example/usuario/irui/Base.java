package com.example.usuario.irui;

import android.app.AlarmManager;
import android.app.Fragment;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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

import com.example.usuario.irui.requestModels.User;
import com.google.gson.Gson;


public abstract class Base extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    protected DrawerLayout drawer;
    private boolean signOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_base);
        NavigationView nv = (NavigationView)findViewById(R.id.nav_view);
        Menu menu = nv.getMenu();

        if(nv==null){
            Toast.makeText(getApplicationContext(), "EL NV ES NULL",
                    Toast.LENGTH_LONG).show();
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        final SharedPreferences preferences = getSharedPreferences("MY_PREFS", MODE_PRIVATE);

        if(!preferences.contains("TOKEN")){
            menu.findItem(R.id.nav_share).setVisible(false);
            menu.findItem(R.id.nav_send).setVisible(false);
            menu.findItem(R.id.cerrar_sesion).setVisible(false);
            menu.findItem(R.id.nav_login).setVisible(true);

        }else{
            menu.findItem(R.id.nav_login).setVisible(false);
            menu.findItem(R.id.nav_share).setVisible(true);
            menu.findItem(R.id.nav_send).setVisible(true);
            menu.findItem(R.id.cerrar_sesion).setVisible(true);


        }


        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.base, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        else if(id == R.id.buscar_item){
            Intent intent = new Intent(this, Busqueda.class);
            this.startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_login){
            Intent intent = new Intent(this, Login.class);
            this.startActivity(intent);
        }
        else if(id == R.id.nav_send){
            Intent intent = new Intent(this, Orders.class);
            this.startActivity(intent);



        } else if(id == R.id.nav_share){
            Intent intent = new Intent(this, PersonalInformation.class);
            this.startActivity(intent);


        } else if (id == R.id.nav_women) {
            Intent intent = new Intent(this, Mujeres.class);
            intent.putExtra("women", true);
            this.startActivity(intent);

        } else if (id == R.id.nav_men) {
            Intent intent = new Intent(this, Hombres.class);
            intent.putExtra("men", true);
            this.startActivity(intent);

        } else if (id == R.id.nav_children) {
            Intent intent = new Intent(this, ResultadosBusqueda.class);
            intent.putExtra("children", true);
            this.startActivity(intent);

        } else if (id == R.id.nav_sale) {
            Intent intent = new Intent(this, ResultadosBusqueda.class);
            intent.putExtra("sale", true);
            this.startActivity(intent);

        }else if (id == R.id.nav_new) {
            Intent intent = new Intent(this, ResultadosBusqueda.class);
            intent.putExtra("new", true);
            this.startActivity(intent);
        }else if(id == R.id.cerrar_sesion){

            final SharedPreferences preferences = getSharedPreferences("MY_PREFS", MODE_PRIVATE);

            String token = preferences.getString("TOKEN", "no token defined");
            String account = preferences.getString("USER", "no account defined");

            Gson gson = new Gson();
            User user = gson.fromJson(account, User.class);


           String request = "http://eiffel.itba.edu.ar/hci/service3/Account.groovy?method=SignOut&username=" + user.getUsername()
                  +  "&authentication_token=" + token;


            new Connection(this, request).execute();
            signOut=true;





        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void backToHome(View view){
        Intent a = new Intent(this,MainActivity.class);
        a.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(a);
        return;
    }


    public void afterRequest(String s){
        if( s != "error" && signOut){


            final SharedPreferences preferences = getSharedPreferences("MY_PREFS", MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.remove("TOKEN");
            editor.remove("USER");
            editor.commit();

            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

            PendingIntent alarmNotificationReceiverPendingIntent;

            Intent alarmNotificationReceiverIntent =
                    new Intent(this, ChangeOrderReceiver.class);
            alarmNotificationReceiverPendingIntent =
                    PendingIntent.getBroadcast(this, 0, alarmNotificationReceiverIntent, 0);

            alarmManager.cancel(alarmNotificationReceiverPendingIntent);

            Toast.makeText(this, "Repeating alarm cancelled", Toast.LENGTH_LONG)
                    .show();


            Intent intent = new Intent(this, MainActivity.class);
            this.startActivity(intent);





        }
    }
}
