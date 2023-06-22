package com.example.egar.Models;

import java.util.Date;

public class Order {
    private String orderId;
    private String customerId;
    private String serviceProviderId;
    private int quantity;
    private double totalAmount;
    private Date orderDate ;

    public Order() {
        // Required empty constructor for Firebase
    }

    public Order(String orderId, String customerId, String serviceProviderId, int quantity, double totalAmount, Date orderDate) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.serviceProviderId = serviceProviderId;
        this.quantity = quantity;
        this.totalAmount = totalAmount;
        this.orderDate=orderDate;
    }

    // Getters and Setters
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getServiceProviderId() {
        return serviceProviderId;
    }

    public void setServiceProviderId(String serviceProviderId) {
        this.serviceProviderId = serviceProviderId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
}

