package com.example.egar.enums;

public enum OrderStatus {
    PENDING("Pending"),
    IN_PROGRESS("InProgress"),
    COMPLETED("Completed"),
    CANCELLED("Cancelled");

    private String status;

    OrderStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}

