package com.example.egar.Models;

import java.util.Date;

public class Offer {
    private String id;
    private String productId;
    private String providerId;
    private double price;
    private int quantity;
    private Date startDate;
    private Date endDate;

    public Offer() {
        // البناء الافتراضي المطلوب للتعامل مع Firebase
    }

    public Offer(String id, String productId, String providerId, double price, int quantity, Date startDate, Date endDate) {
        this.id = id;
        this.productId = productId;
        this.providerId = providerId;
        this.price = price;
        this.quantity = quantity;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    // Getter و Setter للحصول على القيم وتعيينها

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
