package com.example.usuario.irui.requestModels;

/**
 * Created by natinavas on 11/20/15.
 */
public class Item {
    private int id;
    private Product product;
    private int quantity;
    private double price;

    public Item(int id, Product product, int quantity, double price) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
    }

    public int getId() {

        return id;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", product=" + product.toString() +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }
}
