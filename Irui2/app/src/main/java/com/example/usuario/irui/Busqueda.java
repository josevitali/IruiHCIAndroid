package com.example.usuario.irui;

import android.content.ClipData;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.LayoutInflater;
import android.content.Context;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.text.TextUtils;
import android.widget.TextView;
import android.view.inputmethod.EditorInfo;
import android.view.KeyEvent;
import android.widget.Toast;

import com.example.usuario.irui.requestModels.User;
import com.google.gson.Gson;

import static android.widget.Toast.makeText;

public class Busqueda extends Base {


    public void realizarBusqueda(View view){
        Intent a = new Intent(this,ResultadosBusqueda.class);
        a.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);


        EditText mEdit   = (EditText)findViewById(R.id.search_text);
        String s = mEdit.getText().toString();

        a.putExtra("searchText", mEdit.getText().toString());
        startActivity(a);
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_busqueda, null, false);
        drawer.addView(contentView, 0);



        EditText edit_txt = (EditText) findViewById(R.id.search_text);

        edit_txt.setOnEditorActionListener(new EditText.OnEditorActionListener() {


            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (event != null && event.getAction() != KeyEvent.ACTION_DOWN) {
                    return false;
                } else if (actionId == EditorInfo.IME_ACTION_SEARCH
                        || event == null
                        || event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                    realizarBusqueda(v);
                    return true;
                }
                return false;
            }
        });



//        Menu menu = (Menu) findViewById(R.id.toolbar3);
//
//       // MenuItem item = (MenuItem)findViewById(R.id.buscar_item);
//
//        if(menu == null){
//            Toast.makeText(getApplicationContext(), "no encontro el menu",
//                    Toast.LENGTH_LONG).show();
//        }






//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);


    }

   @Override
   public void buscar() {
       return;
   }

}
