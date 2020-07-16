package com.example.kitchen_app;

public class MyProductBasket {
    private String productName;
    private int quantity;
    private int id;
    public MyProductBasket( int id, String productName,int quantity) {
        this.productName = productName;
        this.id = id;
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }
}
