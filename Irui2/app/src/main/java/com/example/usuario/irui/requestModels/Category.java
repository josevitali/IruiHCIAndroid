package com.example.usuario.irui.requestModels;

/**
 * Created by natinavas on 11/21/15.
 */
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