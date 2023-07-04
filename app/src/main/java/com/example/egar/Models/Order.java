package com.example.egar.Models;

import android.location.Location;

import com.example.egar.enams.OrderStatus;

import java.util.Date;

public class Order {
    private String orderId;
    private String userId;
    private String serviceProviderId;
    private int quantity;
    private double totalAmount;
    private String orderDate;
    private OrderStatus orderStatus;
    private String paymentMethod;
    private String shippingLocation;

    public Order() {
        // Required empty constructor for Firebase
    }

    public Order(String orderId, String userId, String serviceProviderId, int quantity, double totalAmount, String orderDate, OrderStatus orderStatus, String paymentMethod, String shippingLocation) {
        this.orderId = orderId;
        this.userId = userId;
        this.serviceProviderId = serviceProviderId;
        this.quantity = quantity;
        this.totalAmount = totalAmount;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.paymentMethod = paymentMethod;
        this.shippingLocation = shippingLocation;
    }

    // Getters and Setters

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
