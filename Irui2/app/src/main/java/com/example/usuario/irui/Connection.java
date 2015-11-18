package com.example.usuario.irui;

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
}
