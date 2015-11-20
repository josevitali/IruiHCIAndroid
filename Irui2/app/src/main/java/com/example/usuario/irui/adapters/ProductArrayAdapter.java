package com.example.usuario.irui.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.usuario.irui.R;
import com.example.usuario.irui.requestModels.Product;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Francisco on 19/11/2015.
 */
public class ProductArrayAdapter extends ArrayAdapter<Product> {

    Activity context;
    Product[] products;

    public ProductArrayAdapter(Activity context, Product[] products) {
        super(context, R.layout.view_product, products);
        this.context = context;
        this.products = products;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Product product = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.view_product, parent, false);
        }

        ImageView imageView = (ImageView)convertView.findViewById(R.id.prodImg);
        //imageView.setImageResource(R.drawable.ic_launcher);
        Picasso.with(context)
                .load(product.getImageUrl())
                .into(imageView);

        TextView nameTextView = (TextView)convertView.findViewById(R.id.prodName);
        nameTextView.setText(product.getName().length()>22 ? product.getName().substring(0,22)+"...": product.getName());

        TextView brandTextView = (TextView)convertView.findViewById(R.id.prodBrand);
        brandTextView.setText(product.getBrand());

        TextView priceTextView = (TextView)convertView.findViewById(R.id.prodPrice);
        priceTextView.setText("$" + product.getPrice().toString());

        return convertView;
    }
}
