package com.example.usuario.irui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class ChangeOrderReceiver extends BroadcastReceiver {
    public ChangeOrderReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.

        final SharedPreferences preferences = context.getSharedPreferences("MY_PREFS", context.MODE_PRIVATE);

        String token = preferences.getString("TOKEN", "no token defined");

        if(!token.equals("no token defined")){
            ChangeInOrders changeInOrders = new ChangeInOrders();
            changeInOrders.notify(context, "Your orders have changed", 1);
        }
    }
}
