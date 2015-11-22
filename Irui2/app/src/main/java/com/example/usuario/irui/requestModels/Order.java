package com.example.usuario.irui.requestModels;

import java.util.Arrays;

public class Order{

    private int id;
    private Address address;
    private CreditCard creditCard;
    private OrderStatus status;
    private String receivedDate;
    private String processedDate;
    private String shippedDate;
    private String deliveredDate;
    private int latitude;
    private int longitude;
    private Item[] items;

    public Order(int id, Address address, CreditCard creditCard, OrderStatus status, String receivedDate, String processedDate, String shippedDate, String deliveredDate, int latitude, int longitude, Item[] items) {
        this.id = id;
        this.address = address;
        this.creditCard = creditCard;
        this.status = status;
        this.receivedDate = receivedDate;
        this.processedDate = processedDate;
        this.shippedDate = shippedDate;
        this.deliveredDate = deliveredDate;
        this.latitude = latitude;
        this.longitude = longitude;
        this.items = items;
    }

    public Order() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        return id == order.id;

    }


    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", address=" + address +
                ", creditCard=" + creditCard +
                ", status=" + status +
                ", receivedDate='" + receivedDate + '\'' +
                ", processedDate='" + processedDate + '\'' +
                ", shippedDate='" + shippedDate + '\'' +
                ", deliveredDate='" + deliveredDate + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", items=" + Arrays.toString(items) +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public Address getAddress() {
        return address;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public Item[] getItems() {
        return items;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public String getReceivedDate() {
        return receivedDate;
    }

    public String getProcessedDate() {
        return processedDate;
    }

    public String getShippedDate() {
        return shippedDate;
    }

    public String getDeliveredDate() {
        return deliveredDate;
    }

    public int getLatitude() {
        return latitude;
    }

    public int getLongitude() {
        return longitude;
    }

    public int getSubtotal() {

        int subtotal = 0;

        for (int i = 0; i < items.length; i++) {
            Item item = items[i];

            subtotal += item.getPrice();

        }

        return subtotal;
    }
}


