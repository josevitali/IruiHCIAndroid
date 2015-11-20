package com.example.usuario.irui;

import android.app.Fragment;
import android.content.Intent;
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


public abstract class Base extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    protected DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_base);
        NavigationView nv = (NavigationView)findViewById(R.id.nav_view);
        Menu menu = nv.getMenu();
        RunningApplication app = (RunningApplication)this.getApplication();

        if(nv==null){
            Toast.makeText(getApplicationContext(), "EL NV ES NULL",
                    Toast.LENGTH_LONG).show();
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        if(app.getAuthenticationToken() == null){
            MenuItem item = menu.findItem(R.id.nav_login);
            if(item.isVisible() == true){
                Toast.makeText(getApplicationContext(),"deberia serlo y es visible",
                        Toast.LENGTH_SHORT).show();

            }



            menu.findItem(R.id.nav_share).setVisible(false);
            menu.findItem(R.id.nav_send).setVisible(false);
            menu.findItem(R.id.cerrar_sesion).setVisible(false);
            menu.findItem(R.id.nav_login).setVisible(true);

            if(item.isVisible() == true){
                Toast.makeText(getApplicationContext(),"deberia serlo pero es visible",
                        Toast.LENGTH_SHORT).show();

            }

            item = menu.findItem(R.id.nav_share);
            if(item.isVisible() == true){
                Toast.makeText(getApplicationContext(),"deberia serlo pero es visible",
                        Toast.LENGTH_SHORT).show();

            }


//            Item login = (MenuItem)findViewById(R.id.nav_view_logged);
//            login.setVisibility(View.GONE);
//            View notLogged = findViewById(R.id.nav_view);
//            notLogged.setVisibility(View.VISIBLE);

        }else{
            if(menu.findItem(R.id.nav_share) == null)
            Toast.makeText(getApplicationContext(), "hay alguien loggueado",
                    Toast.LENGTH_SHORT).show();

            menu.findItem(R.id.nav_share).setVisible(true);
            menu.findItem(R.id.nav_send).setVisible(true);
            menu.findItem(R.id.cerrar_sesion).setVisible(true);
            menu.findItem(R.id.nav_login).setVisible(false);


//            View notLogged = (View) findViewById(R.id.nav_view);
//            notLogged.setVisibility(View.GONE);
//            View login = (View) findViewById(R.id.nav_view_logged);
//            login.setVisibility(View.GONE);

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
            Intent intent = new Intent(this, ResultadosBusqueda.class);
            intent.putExtra("women", true);
            this.startActivity(intent);

        } else if (id == R.id.nav_men) {
            Intent intent = new Intent(this, ResultadosBusqueda.class);
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
            Toast.makeText(getApplicationContext(), "quiero cerrar sesion",
                    Toast.LENGTH_SHORT).show();
//
//
//            RunningApplication app = (RunningApplication)this.getApplication();
//            String user = app.getUser();
//            String token = app.getAuthenticationToken();
//
//            Toast.makeText(getApplicationContext(), "user " + user,
//                    Toast.LENGTH_LONG).show();
//
//            Toast.makeText(getApplicationContext(), "token " + token,
//                    Toast.LENGTH_LONG).show();

//            String request = "http://eiffel.itba.edu.ar/hci/service3/Account.groovy?method=SignOut&username=" + user +
//                    "&authentication_token=" + token;

           // new Connection(this, request).execute();


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
        Toast.makeText(getApplicationContext(), s,
                Toast.LENGTH_SHORT).show();
    }
}
