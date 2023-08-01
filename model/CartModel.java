package com.example.maandpadailygroceries.model;

import java.util.ArrayList;
import java.util.List;

public class CartModel {
    String name;
    String price;

    int imageUrl ;

    public CartModel(String name, String price, int imageUrl) {
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName() {
        this.name = name;
    }



    public String getPrice() {
        return price;
    }

    public void setPrice() {
        this.price = price;
    }



    public int getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(int imageUrl) {
        this.imageUrl = imageUrl;
    }
}
