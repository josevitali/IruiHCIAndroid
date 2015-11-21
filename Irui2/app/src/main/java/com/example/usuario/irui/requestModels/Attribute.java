package com.example.usuario.irui.requestModels;

/**
 * Created by natinavas on 11/21/15.
 */
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
