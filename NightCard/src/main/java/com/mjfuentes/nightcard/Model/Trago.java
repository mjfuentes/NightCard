package com.mjfuentes.nightcard.Model;

/**
 * Created by matias on 02/05/14.
 */
public class Trago {

    private int price;
    private String name;
    private int stock;
    private String description;
    private int selected;

    public Trago(int price,String name, int stock){
        this.setPrice(price);
        this.setName(name);
        this.setStock(stock);
        this.setSelected(0);
    }

    public Trago(){}


    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSelected() {
        return selected;
    }

    public void setSelected(int selected) {
        this.selected = selected;
    }
}
