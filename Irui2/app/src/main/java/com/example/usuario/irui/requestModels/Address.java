package com.example.usuario.irui.requestModels;


public class Address {

    private int id;
    private String name;

    public Address(int id, String name) {

        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "AddressItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
