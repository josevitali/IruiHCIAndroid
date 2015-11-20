package com.example.usuario.irui.requestModels;

/**
 * Created by natinavas on 11/20/15.
 */
public class OrderItem {
    private int id;
    private Product product;
    private int quantity;
    private int price;

    public OrderItem(int id, Product product, int quantity, int price) {
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
        return "OrderItem{" +
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

    public int getPrice() {
        return price;
    }
}
