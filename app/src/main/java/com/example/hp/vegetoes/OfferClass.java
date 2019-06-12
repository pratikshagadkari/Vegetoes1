package com.example.hp.vegetoes;

public class OfferClass {
    String product,price,pid;

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

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public OfferClass(String product, String price) {
        this.product = product;
        this.price = price;

    }

    public OfferClass() {
    }
}
