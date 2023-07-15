package com.example.egar.Models;

public class ProductFavorite {
    private Product product;
    private String userId;

    public ProductFavorite(Product product, String userId) {
        this.product = product;
        this.userId = userId;
    }


    public ProductFavorite() {
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
