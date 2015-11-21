package com.example.usuario.irui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.usuario.irui.adapters.ProductArrayAdapter;
import com.example.usuario.irui.requestModels.Product;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class OrdenarPor extends Base {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
 /*       setContentView(R.layout.activity_ordenar_por);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/

        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_ordenar_por, null, false);
        drawer.addView(contentView, 0);


        String request = "";


        Intent myIntent = getIntent(); // gets the previously created intent


        Intent intent = new Intent(this, OrdenarPor.class);
        String search = myIntent.getStringExtra("search");
        switch(search){
            case "womenClothes":
                request = "http://eiffel.itba.edu.ar/hci/service3/Catalog.groovy?method=GetAllSubcategories&id=2&filters=[{%20%22id%22:%201,%20%22value%22:%20%22Femenino%22%20}]";
                break;

        }



        new Connection(this, request).execute();

    }


    public void afterRequest(String resp){

        if(resp.equals("error")){
            Toast.makeText(getApplicationContext(), "error",
                    Toast.LENGTH_SHORT).show();
            return;
        }


        JSONObject jsonRootObject = null;

        try {

            jsonRootObject = new JSONObject(resp);
            JSONArray subcategories = jsonRootObject.getJSONArray("subcategories");
            Gson gson = new Gson();


  /*          LinearLayout ll = new LinearLayout(this);
            ll.setOrientation(LinearLayout.HORIZONTAL);
*/

            RadioGroup rgp= (RadioGroup) findViewById(R.id.radioGroup);
            RadioGroup.LayoutParams rprms;

            for(int i = 0; i<subcategories.length(); i++){

                JSONObject j = subcategories.getJSONObject(i);
                String name = j.getString("name");

/*
                    RadioButton rdbtn = new RadioButton(this);
                    rdbtn.setId(i);
                    rdbtn.setText(name);
                    ll.addView(rdbtn);*/

                RadioButton radioButton = new RadioButton(this);
                radioButton.setText(name);
                radioButton.setId(i);
                radioButton.setTextSize(25);
                rprms= new RadioGroup.LayoutParams(RadioGroup.LayoutParams.WRAP_CONTENT, RadioGroup.LayoutParams.WRAP_CONTENT);
                rgp.addView(radioButton, rprms);
/*
                Toast.makeText(getApplicationContext(), name,
                        Toast.LENGTH_SHORT).show();*/

            }


  //          ((ViewGroup) findViewById(R.id.radioGroup)).addView(ll);





        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(),"No se encontraron subcategorias",
                    Toast.LENGTH_LONG).show();
        }



    }

    public void ordenar(View view){


        RadioGroup rgp= (RadioGroup) findViewById(R.id.radioGroup);

        int id = rgp.getCheckedRadioButtonId();

        RadioButton rb = (RadioButton) findViewById(id);


        Intent returnIntent = new Intent();
        returnIntent.putExtra("subcategory",rb.getText());
        setResult(Activity.RESULT_OK,returnIntent);
        finish();
    }

}
