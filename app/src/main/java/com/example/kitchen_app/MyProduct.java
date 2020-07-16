package com.example.kitchen_app;


public class MyProduct {
    private String productName;
    private long productDate;
    private int id;

    public MyProduct(String productName, long productDate,int id) {
        this.productName = productName;
        this.productDate = productDate;
        this.id = id;
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

    public long getProductDate() {
        return productDate;
    }
}
