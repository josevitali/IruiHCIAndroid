package com.example.usuario.irui;

import android.annotation.TargetApi;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;

import com.example.usuario.irui.requestModels.User;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ChangeOrderReceiver extends BroadcastReceiver {
    ChangeInOrders changeInOrders;
    Context context;
    public ChangeOrderReceiver() {
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.

        final SharedPreferences preferences = context.getSharedPreferences("MY_PREFS", context.MODE_PRIVATE);

        String token = preferences.getString("TOKEN", "no token defined");


        this.context = context;

        String account = preferences.getString("USER", "no account defined");

        Gson gson = new Gson();
        User user = gson.fromJson(account, User.class);

        String request ="http://eiffel.itba.edu.ar/hci/service3/Order.groovy?method=GetAllOrders&username="
                + user.getUsername() + "&authentication_token=" + token;

        new Connection3(this, request).execute();




    }



    private boolean isNotificationVisible(Context context) {
        Intent notificationIntent = new Intent(context, MainActivity.class);
        PendingIntent test = PendingIntent.getActivity(context, 0, notificationIntent, PendingIntent.FLAG_NO_CREATE);
        return test != null;
    }

    public void afterOrders(String orders){


        final SharedPreferences preferences = context.getSharedPreferences("MY_PREFS", context.MODE_PRIVATE);

        JSONObject jsonRootObject = null;
        String neworders;
        try {
            jsonRootObject = new JSONObject(orders);

            neworders = jsonRootObject.getString("orders");



            String oldorders = preferences.getString("ORDERS", "no orders");


            String token = preferences.getString("TOKEN", "no token defined");

            if(!token.equals("no token defined") && !oldorders.equals(neworders)){
                ChangeInOrders changeInOrders = new ChangeInOrders();
                changeInOrders.notify(context, "Your orders have changed -- Tambien hay que  hacerlo en español", 1);


                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("ORDERS", neworders);
                editor.commit();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }



    }



    private class Connection3 extends AsyncTask<Void, Void, String> {




        private String myUrl = "";
        private ChangeOrderReceiver activity = null;

        public Connection3(ChangeOrderReceiver  activity, String url) {
            this.activity = activity;
            this.myUrl = url;
        }



        private static final String TAG = "HttpGetTask";


        @Override
        protected String doInBackground(Void... params) {

            HttpURLConnection urlConnection = null;

            try {
                URL url = new URL(myUrl);
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                return readStream(in);
            } catch (Exception exception) {
                exception.printStackTrace();
                return "error";
            }
            finally {
                if (urlConnection != null)
                    urlConnection.disconnect();
            }
        }


        @Override
        protected void onPostExecute(String result) {
            activity.afterOrders(result);
        }


        private String readStream(InputStream inputStream) {
            try {
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                int i = inputStream.read();
                while(i != -1) {
                    outputStream.write(i);
                    i = inputStream.read();
                }
                return outputStream.toString();
            } catch (IOException e) {
                return "";
            }
        }

    }




}
