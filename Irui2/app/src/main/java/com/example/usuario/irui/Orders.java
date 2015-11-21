package com.example.usuario.irui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.usuario.irui.adapters.OrderArrayAdapter;
import com.example.usuario.irui.adapters.ProductArrayAdapter;
import com.example.usuario.irui.requestModels.Order;
import com.example.usuario.irui.requestModels.Product;
import com.example.usuario.irui.requestModels.User;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Orders extends Base {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*setContentView(R.layout.activity_orders);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/

        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_orders, null, false);
        drawer.addView(contentView, 0);


        final SharedPreferences preferences = getSharedPreferences("MY_PREFS", MODE_PRIVATE);

        String token = preferences.getString("TOKEN", "no token defined");
        String account = preferences.getString("USER", "no account defined");

        Gson gson = new Gson();
        User user = gson.fromJson(account, User.class);


        String request ="http://eiffel.itba.edu.ar/hci/service3/Order.groovy?method=GetAllOrders&username="
                + user.getUsername() + "&authentication_token=" + token;

        new Connection(this, request).execute();
    }


    public void afterRequest(String s) {

        if(s != "error") {
            Toast.makeText(getApplicationContext(), s,
                    Toast.LENGTH_LONG).show();
            apiCall(s);


        }else{
            Toast.makeText(getApplicationContext(), "Hubo un error al intentar conectarse con el servidor",
                    Toast.LENGTH_LONG).show();
        }


    }

    public void apiCall(String url){

        JSONObject jsonRootObject = null;

        try {

            jsonRootObject = new JSONObject(url);
            String orders = jsonRootObject.getString("orders");
            Gson gson = new Gson();

            Order[] ords = gson.fromJson(orders, Order[].class);
           // for(Order o: ords){


                //if(j.getJSONObject("address") != null){
                  //  ordAddress = j.getJSONObject("address").getString("name");
                //}
                //Order o = new Order(j.getInt("id"), j.getJSONObject("address").getString("name"));
                //ords[i]=o;
            //}


            OrderArrayAdapter adapter = new OrderArrayAdapter(this, ords);

            ListView listView = (ListView)this.findViewById(R.id.orderList);
            listView.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(),"exception",
                    Toast.LENGTH_LONG).show();
        }
    }

}
