package com.example.usuario.irui.requestModels;

import java.util.Arrays;

/**
 * Created by natinavas on 11/21/15.
 */
public class ProductComplete {

    private int id;
    private String name;
    private double price;
    private String[] imageURL;
    private Category category;
    private Category subcategory;
    private Attribute[] attributes;


    public ProductComplete(int id, String name, double price, String[] imageUrl, Category category, Category subcategory, Attribute[] attributes) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imageURL = imageUrl;
        this.category = category;
        this.subcategory = subcategory;
        this.attributes = attributes;
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String[] getImageURL() {
        return imageURL;
    }

    public Category getCategory() {
        return category;
    }

    public Category getSubcategory() {
        return subcategory;
    }

    public Attribute[] getAttributes() {
        return attributes;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setImageURL(String[] imageURL) {
        this.imageURL = imageURL;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setSubcategory(Category subcategory) {
        this.subcategory = subcategory;
    }

    public void setAttributes(Attribute[] attributes) {
        this.attributes = attributes;
    }



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
}
