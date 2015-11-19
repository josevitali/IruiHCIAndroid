package com.example.usuario.irui;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class PersonalInformation extends Base {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*setContentView(R.layout.activity_personal_information);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/

        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_personal_information, null, false);
        drawer.addView(contentView, 0);


        String s = "http://eiffel.itba.edu.ar/hci/service3/Catalog.groovy?method=GetAllProducts";
        new Connection(this, s).execute();




    }

    public void afterRequest(String resp){



        Type type = new TypeToken<Products>(){}.getType();
        Gson gson = new Gson();



        Products prod = gson.fromJson(resp, type);




        Toast.makeText(getApplicationContext(), prod.products[0].name,
                Toast.LENGTH_SHORT).show();


        // Type type = new TypeToken<Respuesta>() {}.getType();

        //gson.fromJson(resp, type);
    }


    public class Meta{
        String uuid;
        String time;

        public Meta(String uuid, String time){
            this.uuid = uuid;
            this.time = time;
        }
    }

    public class Price{
        int min;
        int max;

        public Price(int min, int max){
            this.min = min;
            this.max = max;
        }
    }


    public class Product {

        private int id;
        private String name;
        private int price;
        private String[] imageUrl;
        private Category category;
        private SubCategory subcategory;
        private Attribute[] attributes;

        public Product(int id, String name, int price, String[] imageUrl, Category category, SubCategory subcategory, Attribute[] attributes) {
            this.id = id;
            this.name = name;
            this.price = price;
            this.imageUrl = imageUrl;
            this.category = category;
            this.subcategory = subcategory;
            this.attributes = attributes;
        }


    }

    public class SubCategory {
        private int id;
        private String name;

        public SubCategory(int id, String name){
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }



    public class Category {
        private int id;
        private String name;

        public Category(int id, String name){
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }

    public class Attribute {
        private int id;
        private String name;
        private String[] values;

        public Attribute(int id, String name, String[] values) {
            this.id = id;
            this.name = name;
            this.values = values;
        }
    }



    public class Products{
        Meta meta;
        int page;
        int pageSize;
        int total;
        Product products[];
        Price price;

        public Products(Meta meta, int page, int pageSize, int total, Product products[], Price price){
            this.meta = meta;
            this.page = page;
            this.pageSize = pageSize;
            this.total = total;
            this.products = products;
            this.price = price;
        }
    }


}