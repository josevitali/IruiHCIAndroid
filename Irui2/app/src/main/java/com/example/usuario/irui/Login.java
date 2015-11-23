package com.example.usuario.irui;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.usuario.irui.requestModels.Order;
import com.example.usuario.irui.requestModels.User;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Login extends Base {

    private EditText mPasswordView;
    private EditText mUserView;

    private AlarmManager alarmManager;
    private PendingIntent alarmNotificationReceiverPendingIntent;
    private final static int INTERVAL = 10000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_login, null, false);
        drawer.addView(contentView, 0);

    }

    public void attemptLogin(View view){
        mPasswordView = (EditText) findViewById(R.id.password);
        mUserView = (EditText) findViewById(R.id.username);

        String user = mUserView.getText().toString();
        String pass = mPasswordView.getText().toString();



        String baseUrl = "http://eiffel.itba.edu.ar/hci/service3/Account.groovy?";

        Uri.Builder builder = new Uri.Builder();

        Uri builtUri = Uri.parse(baseUrl)
                .buildUpon()
                .appendQueryParameter("method", "SignIn")
                .appendQueryParameter("username", user)
                .appendQueryParameter("password", pass)
                .build();

        String request = builtUri.toString();

        new Connection(this, request).execute();


    }

    public void afterRequest(String resp){
        if(resp == "error"){
            Toast.makeText(getApplicationContext(), getString(R.string.connectionError),
                    Toast.LENGTH_LONG).show();
        }

        JSONObject  jsonRootObject = null;
        try {
            jsonRootObject = new JSONObject(resp);

            String token = jsonRootObject.getString("authenticationToken");

            String account = jsonRootObject.getString("account");


            Gson gson = new Gson();
            User user = gson.fromJson(account, User.class);

            RunningApplication app = (RunningApplication)this.getApplication();

            app.setAuthenticationToken(token);

            final SharedPreferences preferences = getSharedPreferences("MY_PREFS", MODE_PRIVATE);

            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("TOKEN", token);
            editor.putString("USER", account);
            editor.commit();


            alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

            Intent alarmNotificationReceiverIntent =
                    new Intent(this, ChangeOrderReceiver.class);
            alarmNotificationReceiverPendingIntent =
                    PendingIntent.getBroadcast(this, 0, alarmNotificationReceiverIntent, 0);




            alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME,
                    SystemClock.elapsedRealtime() + INTERVAL,
                    INTERVAL,
                    alarmNotificationReceiverPendingIntent);









            String request ="http://eiffel.itba.edu.ar/hci/service3/Order.groovy?method=GetAllOrders&username="
                    + user.getUsername() + "&authentication_token=" + token;


            new Connection2(this, request).execute();

            //Intent intent = new Intent(this, MainActivity.class);
            //this.startActivity(intent);
            backToHome(this.findViewById(R.id.homepage));


        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    public void afterOrders(String resp){

        if(resp == "error"){
            return;
        }

        JSONObject  jsonRootObject = null;
        try {
            jsonRootObject = new JSONObject(resp);

            String orders = jsonRootObject.getString("orders");

            final SharedPreferences preferences = getSharedPreferences("MY_PREFS", MODE_PRIVATE);

            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("ORDERS", orders);
            editor.commit();




        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    private class Connection2 extends AsyncTask<Void, Void, String> {




        private String myUrl = "";
        private Login activity = null;

        public Connection2(Login  activity, String url) {
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
