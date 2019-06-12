package com.example.hp.vegetoes;

public class OrganicFruitsClass {
    String product,price;

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public OrganicFruitsClass(String product, String price) {
        this.product = product;
        this.price = price;
    }

    public OrganicFruitsClass() {
    }
}
