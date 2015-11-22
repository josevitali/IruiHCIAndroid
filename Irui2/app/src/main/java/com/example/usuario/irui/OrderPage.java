package com.example.usuario.irui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
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
import com.example.usuario.irui.requestModels.OrderStatus;
import com.example.usuario.irui.requestModels.User;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;

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

        String baseUrl = "http://eiffel.itba.edu.ar/hci/service3/Order.groovy?";

        Uri.Builder builder = new Uri.Builder();

        Uri builtUri = Uri.parse(baseUrl)
                .buildUpon()
                .appendQueryParameter("method", "GetOrderById")
                .appendQueryParameter("username", user.getUsername())
                .appendQueryParameter("authentication_token", token)
                .appendQueryParameter("id", myIntent.getStringExtra("id"))
                .build();
        String request = builtUri.toString();

        new Connection(this, request).execute();

    }

    public void afterRequest(String s){

            JSONObject jsonRootObject = null;
            if (s != "error") {

                try {
                    jsonRootObject = new JSONObject(s);
                    String orderAux = jsonRootObject.getString("order");

                    Gson gson = new Gson();
                    Order order = gson.fromJson(orderAux, Order.class);


                    TextView addressName = (TextView) findViewById(R.id.addrName);
                    addressName.setText((order.getAddress() == null || order.getAddress().getName() == null) ? "-" : order.getAddress().getName());


                    TextView createDate = (TextView) findViewById(R.id.orderDate);
                    createDate.setText(order.getProcessedDate() == null ? "-" : order.getProcessedDate());

                    TextView shipDate = (TextView) findViewById(R.id.deliverDate);
                    shipDate.setText(order.getShippedDate() == null ? "-" : order.getShippedDate());

                    TextView orderNumber = (TextView) findViewById(R.id.orderNumber);
                    orderNumber.setText("#" + order.getId().toString());

                    TextView paymentInfoView = (TextView) findViewById(R.id.paymentInfo);
                    paymentInfoView.setText(order.getCreditCard() == null ? "-" : order.getCreditCard().getNumber().toString());

                    TextView priceView = (TextView) findViewById(R.id.totalPrice);
                    priceView.setText("$" + order.getSubtotal());




                    TextView createdView = (TextView) findViewById(R.id.orderStateCreated);
                    TextView shippedView = (TextView) findViewById(R.id.orderStateShipped);
                    TextView deliveredView = (TextView) findViewById(R.id.orderStateDelivered);
                    TextView confirmedView = (TextView) findViewById(R.id.orderStateConfirmed);

                    OrderStatus status = order.getStatus();

                    if (status == OrderStatus.DELIVERED) {
                        createdView.setVisibility(View.GONE);
                        shippedView.setVisibility(View.GONE);
                        deliveredView.setVisibility(View.VISIBLE);
                        confirmedView.setVisibility(View.GONE);


                    } else if (status == OrderStatus.CREATED) {
                        createdView.setVisibility(View.VISIBLE);
                        shippedView.setVisibility(View.GONE);
                        deliveredView.setVisibility(View.GONE);
                        confirmedView.setVisibility(View.GONE);

                    } else if (status == OrderStatus.SHIPPED) {
                        createdView.setVisibility(View.GONE);
                        shippedView.setVisibility(View.VISIBLE);
                        deliveredView.setVisibility(View.GONE);
                        confirmedView.setVisibility(View.GONE);

                    } else {
                        createdView.setVisibility(View.GONE);
                        shippedView.setVisibility(View.GONE);
                        deliveredView.setVisibility(View.GONE);
                        confirmedView.setVisibility(View.VISIBLE);

                    }


                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(), "exception",
                            Toast.LENGTH_SHORT).show();
                } catch (JsonParseException e2) {
                    Toast.makeText(getApplicationContext(), e2.toString(),
                            Toast.LENGTH_SHORT).show();
                }


            } else {
                Toast.makeText(getApplicationContext(), "Hubo un error conectando al servidor",
                        Toast.LENGTH_LONG).show();
            }
    }

}
