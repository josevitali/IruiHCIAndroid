package com.example.usuario.irui.requestModels;

/**
 * Created by Francisco on 19/11/2015.
 */
public class Product {


    private String name;
    private Integer price;
    private String imageUrl;
    private String brand;
    private int id;


    public Product(String name, int price, String brand, String imageUrl, int id){
        this.name = name;
        this.price = price;
        this.brand = brand;
        this.imageUrl = imageUrl;
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getBrand(){
        return brand;
    }

    public Integer getId(){
        return id;
    }





}
