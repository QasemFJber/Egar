package com.example.egar.controllers;
import static android.content.ContentValues.TAG;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.egar.Models.Order;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class OrderController {

    private static OrderController instance;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    private OrderController() {
        // Private constructor to prevent direct instantiation
    }

    public static OrderController getInstance() {
        if (instance == null) {
            synchronized (OrderController.class) {
                if (instance == null) {
                    instance = new OrderController();
                }
            }
        }
        return instance;
    }

    public void addOrder(Order order) {
        // Add order to Firestore
        // Get a reference to the orders collection in Firestore
        CollectionReference ordersCollection = FirebaseFirestore.getInstance().collection("orders");

        // Convert order object to a HashMap
        HashMap<String, Object> orderData = new HashMap<>();
        orderData.put("orderId", order.getOrderId());
        orderData.put("customerId", order.getCustomerId());
        orderData.put("serviceProviderId", order.getServiceProviderId());
        orderData.put("quantity", order.getQuantity());
        orderData.put("totalPrice", order.getTotalAmount());
        orderData.put("orderDate", order.getOrderDate());

        // Add the order to Firestore
        ordersCollection.add(orderData)
                .addOnSuccessListener(documentReference -> {
                    // On success, log a message to the console
                    Log.d(TAG, "Order added with ID: " + documentReference.getId());
                })
                .addOnFailureListener(e -> {
                    // On failure, log an error message to the console
                    Log.e(TAG, "Error adding order", e);
                });
    }

    public void updateOrder(Order order) {
        // Update order in Firestore
        DocumentReference orderRef = db.collection("orders").document(order.getOrderId());

        orderRef.update("customerId", order.getCustomerId(),
                        "serviceProviderId", order.getServiceProviderId(),
                        "quantity", order.getQuantity(),
                        "totalPrice", order.getTotalAmount(),
                        "orderDate", order.getOrderDate())
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "Order updated successfully");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error updating order", e);
                    }
                });
    }

    public void deleteOrder(Order order) {
        // Delete order from Firestore
        db.collection("orders").document(order.getOrderId())
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "Order deleted successfully");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error deleting order", e);
                    }
                });
    }
}

