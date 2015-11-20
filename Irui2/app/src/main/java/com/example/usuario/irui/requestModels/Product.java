package com.example.usuario.irui.requestModels;

/**
 * Created by Francisco on 19/11/2015.
 */
public class Product {

    private int id;
    private String name;
    private Integer price;
    private String imageUrl;
    private String brand;


    public Product(String name, int price, String brand, String imageUrl){
        this.name = name;
        this.price = price;
        this.brand = brand;
        this.imageUrl = imageUrl;
    }

    public int getId() {
        return id;
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

}
