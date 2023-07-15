package com.example.egar.Models;


import com.example.egar.enums.OrderStatus;

import java.io.Serializable;


public class Order implements Serializable {
    private String orderId;
    private User user;
    private  Product product;
    private int quantity;
    private double totalAmount;
    private String orderDate;
    private OrderStatus orderStatus;
    private String paymentMethod;
    private String shippingLocation;

    public Order() {
        // Required empty constructor for Firebase
    }

    public Order(String orderId, User userId, Product product, int quantity, double totalAmount, String orderDate, OrderStatus orderStatus, String paymentMethod, String shippingLocation) {
        this.orderId = orderId;
        this.user = userId;
        this.product = product;
        this.quantity = quantity;
        this.totalAmount = totalAmount;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.paymentMethod = paymentMethod;
        this.shippingLocation = shippingLocation;
    }

    public Order(User user, Product product, int i, double amount, String date, OrderStatus pending, String payPal, String address) {
        this.user = user;
        this.product = product;
        this.quantity = i;
        this.totalAmount = amount;
        this.orderDate = date;
        this.orderStatus = pending;
        this.paymentMethod = payPal;
        this.shippingLocation = address;
    }

    // Getters and Setters

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
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

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getShippingLocation() {
        return shippingLocation;
    }

    public void setShippingLocation(String shippingLocation) {
        this.shippingLocation = shippingLocation;
    }
}
