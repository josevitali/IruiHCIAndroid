package com.example.usuario.irui.adapters;

import android.app.Activity;
import android.content.Context;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.usuario.irui.R;
import com.example.usuario.irui.requestModels.Order;
import com.example.usuario.irui.requestModels.OrderStatus;
import com.example.usuario.irui.requestModels.Product;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

/**
 * Created by Francisco on 20/11/2015.
 */
public class OrderArrayAdapter extends ArrayAdapter<Order>{

    Activity context;
    Order[] orders;

    public OrderArrayAdapter(Activity context, Order[] orders) {
        super(context, R.layout.view_product, orders);
        this.context = context;
        this.orders = orders;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Order order = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.view_order, parent, false);
        }

        TextView nameTextView = (TextView) convertView.findViewById(R.id.addressName);
        nameTextView.setText(order.getAddress() == null ? "-" : order.getAddress().getName());

        TextView brandTextView = (TextView) convertView.findViewById(R.id.orderId);
        brandTextView.setText("Id: #" + order.getId().toString());


        ImageView imageView = (ImageView) convertView.findViewById(R.id.statusIcon);


        OrderStatus status = order.getStatus();
        if (status == OrderStatus.CREATED) {
            imageView.setImageResource(R.drawable.created);


        } else if (status == OrderStatus.DELIVERED){

            imageView.setImageResource(R.drawable.shipped);

        }else if(status == OrderStatus.SHIPPED){
            imageView.setImageResource(R.drawable.process);
        }else{
            imageView.setImageResource(R.drawable.confirmed);
        }



        return convertView;
    }
}
