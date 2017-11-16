package com.example.pc.qg;

/**
 * Created by Pc on 11/7/2017.
 */

public class Model {
    private String name;
    private String category;
    private double price;
    private Integer image;
    private int quantity;

    public Model(String name, String category, double price, Integer image) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
    }
}
