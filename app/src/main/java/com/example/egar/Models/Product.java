package com.example.egar.Models;

import android.net.Uri;

public class Product {
    private String id;
    private String serviceProviderId; // New field for merchant ID
    private String name;
    private String description;
    private double price;
    private Uri imageUrl;
    private boolean isFavorite;
    private int quantityInCart;
    private String category; // New field for product category

    public Product() {
    }

    public Product(String serviceProviderId ,String name, String description, double price, Uri imageUrl, int quantityInCart, String category) {
        this.serviceProviderId = serviceProviderId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
        this.quantityInCart = quantityInCart;
        this.category = category;
    }

    public Product(String id, String serviceProviderId, String name, String description, double price, Uri imageUrl, String category) {
        this.id = id;
        this.serviceProviderId = serviceProviderId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
        this.isFavorite = false;
        this.quantityInCart  = getQuantityInCart();
        this.category = category;
    }


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getServiceProviderId() {
        return serviceProviderId;
    }

    public void setServiceProviderId(String serviceProviderId) {
        this.serviceProviderId = serviceProviderId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Uri getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(Uri imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public int getQuantityInCart() {
        return quantityInCart;
    }

    public void setQuantityInCart(int quantityInCart) {
        this.quantityInCart = quantityInCart;
    }
}
