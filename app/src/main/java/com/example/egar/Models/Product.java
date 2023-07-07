package com.example.egar.Models;


import java.io.Serializable;

public class Product implements Serializable {
    private String id;
    private String serviceProviderId;
    private String name;
    private String description;
    private double price;
    private String imageUrl;
    private boolean isFavorite;
    private int quantityInCart;
    private String category;

    private Provider provider;
    public Product(String productName, String productCategory, String productDescription, double productPrice, String productImage) {
        this.name = productName;
        this.description = productDescription;
        this.category = productCategory;
        this.price = productPrice;
        this.imageUrl = productImage;
    }

    public Product() {
        // Default constructor required for Firestore deserialization
    }

    public Product(String serviceProviderId , String name, String description, double price, String imageUrl, int quantityInCart, String category, com.example.egar.Models.Provider provider) {
        this.serviceProviderId = serviceProviderId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
        this.quantityInCart = quantityInCart;
        this.category = category;
        this.provider = provider;
    }

    public Product(String id, String serviceProviderId, String name, String description, double price, String imageUrl, String category) {
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

    public Product(String name, String description, double price, String imageUrl, int quantityInCart, String category, Provider provider) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
        this.quantityInCart = quantityInCart;
        this.category = category;
        this.provider = provider;
    }

    public Product(String id, String name, String description, double price, boolean isFavorite, int quantityInCart, String category, Provider provider, String imageUrl) {
        this.id=id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
        this.quantityInCart = quantityInCart;
        this.category = category;
        this.provider = provider;
    }

    public Product(String productName, String productCategory, double productPrice, String productDescription) {
        this.name = productName;
        this.description = productDescription;
        this.category = productCategory;
        this.price = productPrice;


    }


    public com.example.egar.Models.Provider getProvider() {
        return provider;
    }

    public void setProvider(com.example.egar.Models.Provider provider) {
        this.provider = provider;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
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
