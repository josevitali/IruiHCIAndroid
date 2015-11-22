package com.example.usuario.irui;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.usuario.irui.requestModels.Product;
import com.example.usuario.irui.adapters.ProductArrayAdapter;
import com.example.usuario.irui.requestModels.User;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ResultadosBusqueda extends Base{

    static final int GET_SUBCATEGORY = 1;

    private String search;
    private String num = "";
    private String gender = "";
    private String request;
    private String filters = "[";
    private String baseUrl = "http://eiffel.itba.edu.ar/hci/service3/Catalog.groovy?";
    private String method;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*setContentView(R.layout.resultados_busqueda);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/


        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.resultados_busqueda, null, false);
        drawer.addView(contentView, 0);

        Intent myIntent = getIntent(); // gets the previously created intent



        String baseUrl = "http://eiffel.itba.edu.ar/hci/service3/Catalog.groovy?";

        method = "GetProductsByCategoryId";





        if(myIntent.hasExtra("search")) {
            search = myIntent.getStringExtra("search");



            if(search.contains("Shoes")){
                num = "1";
            } else if(search.contains("Clothes")){
                num = "2";
            } else if(search.contains("Accesories")){
                num = "3";
            } else{
                method = "GetAllProducts";

                if(search.contains("OnSale")) {
                    filters += "{\"id\":5,\"value\":\"Oferta\"},";
                } else if(search.contains("NewArrivals")){
                    filters += "{\"id\":6,\"value\":\"Nuevo\"},";
                }
            }


            if(search.contains("women")){
                gender = "Femenino";
                filters += "{\"id\":1,\"value\":\"Femenino\"},";
            } else if(search.contains("men")){
                gender = "Masculino";
                filters += "{\"id\":1,\"value\":\"Masculino\"},";
            }


            filters = filters.substring(0, filters.length() -1);
            filters += "]";

            if(!num.equals("")) {
                Uri builtUri = Uri.parse(baseUrl)
                        .buildUpon()
                        .appendQueryParameter("method", method)
                        .appendQueryParameter("id", num)
                        .appendQueryParameter("filters", filters)
                .appendQueryParameter("page_size", "1000")
                        .build();
                request = builtUri.toString();
            } else{

                Uri builtUri = Uri.parse(baseUrl)
                        .buildUpon()
                        .appendQueryParameter("method", "GetAllProducts")
                        .appendQueryParameter("filters", filters)
                        .appendQueryParameter("page_size", "1000")
                        .build();
                request = builtUri.toString();
            }

        } else if(myIntent.hasExtra("searchText")) {
            String s = myIntent.getStringExtra("searchText");
            request = "http://eiffel.itba.edu.ar/hci/service3/Catalog.groovy?method=GetProductsByName&name=" + s;
        }

        new Connection(this, request).execute();


    }


    public void afterRequest(String resp){

        if(resp != "error")
            apiCall(resp);
        else{
            Toast.makeText(getApplicationContext(),resp
                    ,
                    Toast.LENGTH_LONG).show();
        }
    }



    public void filtros(View view){

        Intent intent = new Intent(this, Filtros.class);
        this.startActivity(intent);
    }

    public void ordenarPor(View view){


        Intent myIntent = getIntent(); // gets the previously created intent





        if(myIntent.hasExtra("search")) {
            Intent intent = new Intent(this, OrdenarPor.class);
            if(!num.equals("")){
                intent.putExtra("num", num);
                if(!gender.equals("")){
                    intent.putExtra("gender", gender);
                }

            }
            this.startActivityForResult(intent, GET_SUBCATEGORY);
        }


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == GET_SUBCATEGORY) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                String subcategory = data.getStringExtra("subcategory");
                int id = data.getIntExtra("id", -1);

                Toast.makeText(getApplicationContext(),"" + id + " : " + subcategory,
                        Toast.LENGTH_LONG).show();

                Uri builtUri = Uri.parse(baseUrl)
                        .buildUpon()
                        .appendQueryParameter("method", "GetProductsBySubcategoryId")
                        .appendQueryParameter("id", "" + id)
                        .appendQueryParameter("filters", filters)
                        .appendQueryParameter("page_size", "1000")
                        .build();
                request = builtUri.toString();

                new Connection(this, request).execute();
            }
        }
    }




    private void apiCall(String url){
        JSONObject jsonRootObject = null;

        try {

            jsonRootObject = new JSONObject(url);
            JSONArray products = jsonRootObject.getJSONArray("products");
            Gson gson = new Gson();


            ArrayList<Product> prodList = new ArrayList<>();
            Product[] prods = new Product[products.length()];
            for(int i = 0; i<products.length(); i++){

                JSONObject j = products.getJSONObject(i);
                JSONArray atts = j.getJSONArray("attributes");
                String brand = "-";
                for(int k = 0; k<atts.length(); k++){
                    if(atts.getJSONObject(k).getInt("id") == 9){
                        brand = atts.getJSONObject(k).getString("values");
                        brand = brand.substring(2,brand.length()-2).split(",")[0];
                    }
                }
                String imgUrl = j.getJSONArray("imageUrl").getString(0);
                Product p = new Product(j.getString("name"), j.getInt("price"), brand, imgUrl, j.getInt("id"));
                prods[i]=p;
            }


            ProductArrayAdapter adapter = new ProductArrayAdapter(this, prods);

            ListView listView = (ListView)this.findViewById(R.id.prodList);
            listView.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(),"No se encontraron productos que cumplan con la condición",
                    Toast.LENGTH_LONG).show();
        }


    }

    public void mostrarProducto(View view){
        Intent intent = new Intent(this, PaginaProducto.class);
        TextView idTextView = (TextView)view.findViewById(R.id.prodId);
        intent.putExtra("prodId", idTextView.getText().toString());
        this.startActivity(intent);
    }

}


