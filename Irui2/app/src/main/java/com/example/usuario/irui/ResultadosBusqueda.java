package com.example.usuario.irui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
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

        String request = "http://eiffel.itba.edu.ar/hci/service3/Catalog.groovy?method=GetAllProducts&page_size=1000";

        if(myIntent.hasExtra("searchText")){
            String s = myIntent.getStringExtra("searchText");
            request = "http://eiffel.itba.edu.ar/hci/service3/Catalog.groovy?method=GetProductsByName&name="+ s;

        }else if(myIntent.hasExtra("womenAll")){
            request = "http://eiffel.itba.edu.ar/hci/service3/Catalog.groovy?method=GetAllProducts&filters=[%20{%20%22id%22:%201,%20%22value%22:%20%22Femenino%22%20}%20]&page_size=1000";

        }else if(myIntent.hasExtra("menAll")){
            request = "http://eiffel.itba.edu.ar/hci/service3/Catalog.groovy?method=GetAllProducts&filters=[%20{%20%22id%22:%201,%20%22value%22:%20%22Masculino%22%20}%20]&page_size=1000";

        }else if(myIntent.hasExtra("childrenAll")){
            Toast.makeText(getApplicationContext(), "muestro ropa de ni;os",
                    Toast.LENGTH_SHORT).show();
           // request = "http://eiffel.itba.edu.ar/hci/service3/Catalog.groovy?method=GetAllProducts&filters=[%20{%20%22id%22:%201,%20%22value%22:%20%22Infantil%22%20}%20]&page_size=1000";

        }else if(myIntent.hasExtra("new")){
            Toast.makeText(getApplicationContext(), "muestro ropa nueva",
                    Toast.LENGTH_SHORT).show();

        }else if(myIntent.hasExtra("sale")){
            Toast.makeText(getApplicationContext(), "muestro ropa en descuento",
                    Toast.LENGTH_SHORT).show();
        }


        new Connection(this, request).execute();


    }


        public void afterRequest(String resp){


            apiCall(resp);
    }



    public void filtros(View view){

        Intent intent = new Intent(this, Filtros.class);
        this.startActivity(intent);
    }

    public void ordenarPor(View view){
        Intent intent = new Intent(this, OrdenarPor.class);
        this.startActivity(intent);
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
                Product p = new Product(j.getString("name"), j.getInt("price"), brand, imgUrl);
                prods[i]=p;
            }


            ProductArrayAdapter adapter = new ProductArrayAdapter(this, prods);

            ListView listView = (ListView)this.findViewById(R.id.prodList);
            listView.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(),"exception",
                    Toast.LENGTH_LONG).show();
        }


    }
}


