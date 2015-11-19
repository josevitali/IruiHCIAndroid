package com.example.usuario.irui;

import android.app.Activity;
import android.os.AsyncTask;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Usuario on 19/11/2015.
 */
public class Connection extends AsyncTask<Void, Void, String> {


    public interface AsyncResponse {
        void processFinish(String output);
    }

    private String myUrl = "";
    private Base activity = null;

    public Connection(Base  activity, String url) {
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
        activity.afterRequest(result);
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
