package com.example.usuario.irui;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Usuario on 18/11/2015.
 */
public class Connection {
    private static Connection ourInstance = new Connection();

    public static Connection getInstance() {
        return ourInstance;
    }

    private Connection() {
    }


    public JSONObject connect(String urlString){


        JSONObject requestResult;

        URL url;
        try {
            url = new URL(urlString);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
        HttpURLConnection urlConnection;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();


            if(urlString.equals("http://eiffel.itba.edu.ar/hci/service3/Common.groovy?method=GetAllStates"))
                return null;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        try {
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            requestResult = new JSONObject(readStream(in));



            urlConnection.disconnect();
            return requestResult;
        } catch (IOException | JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String readStream(InputStream is) {
        try {
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            int i = is.read();
            while(i != -1) {
                bo.write(i);
                i = is.read();
            }
            return bo.toString();
        } catch (IOException e) {
            return "";
        }
    }


    public static String streamToString(InputStream is) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }



}

