package com.example.usuario.irui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.usuario.irui.requestModels.Order;
import com.example.usuario.irui.requestModels.User;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

public class OrderPage extends Base {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_order_page, null, false);
        drawer.addView(contentView, 0);

        Intent myIntent = getIntent();

        final SharedPreferences preferences = getSharedPreferences("MY_PREFS", MODE_PRIVATE);

        String token = preferences.getString("TOKEN", "no token defined");
        String account = preferences.getString("USER", "no account defined");

        Gson gson = new Gson();
        User user = gson.fromJson(account, User.class);

        String request = "http://eiffel.itba.edu.ar/hci/service3/Order.groovy?method=GetOrderById&username="+
                user.getUsername() +"&authentication_token=" + token + "&id=" + myIntent.getStringExtra("id");




        new Connection(this, request).execute();




    }

    public void afterRequest(String s){


        Toast.makeText(getApplicationContext(), s,
                Toast.LENGTH_SHORT).show();

        JSONObject jsonRootObject = null;
        if(s!= "error"){

            try {
                jsonRootObject = new JSONObject(s);
                String orderAux = jsonRootObject.getString("order");

                Gson gson = new Gson();
                Order order = gson.fromJson(orderAux, Order.class);


                if(order !=null)
                Toast.makeText(getApplicationContext(), order.getId().toString(),
                        Toast.LENGTH_SHORT).show();

                if(order == null)
                    Toast.makeText(getApplicationContext(), "la orden es null por alguna razon",
                            Toast.LENGTH_SHORT).show();

//                TextView addressName = (TextView)findViewById(R.id.addrName);
//                addressName.setText((order.getAddress() == null || order.getAddress().getName() == null)? "-" : order.getAddress().getName());


//                TextView createDate = (TextView)findViewById(R.id.orderDate);
//                createDate.setText(order.getProcessedDate() == null ? "-" : order.getProcessedDate());
//
//                TextView shipDate = (TextView)findViewById(R.id.deliverDate);
//                shipDate.setText(order.getShippedDate() == null ? "-" : order.getShippedDate());

//                TextView orderNumber = (TextView)findViewById(R.id.orderNumber);
//                orderNumber.setText(order.getAddress() == null ? "-" : "#" + order.getId().toString());



            }catch(JSONException e){
                Toast.makeText(getApplicationContext(), "aca flasheo",
                        Toast.LENGTH_SHORT).show();
            }


        }else{
            Toast.makeText(getApplicationContext(), "Hubo un error conectando al servidor",
                    Toast.LENGTH_SHORT).show();
        }
    }

}
