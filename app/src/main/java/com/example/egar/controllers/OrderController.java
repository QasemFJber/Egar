package com.example.egar.controllers;
import static android.content.ContentValues.TAG;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.egar.Models.Order;
import com.example.egar.interfaces.OnOrderFetchListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

    public void addOrder(Order order, OnOrderFetchListener listener) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference ordersCollection = db.collection("orders");

        HashMap<String, Object> orderData = new HashMap<>();

        orderData.put("orderId", ordersCollection.document().getId());
        orderData.put("user", order.getUser());
        orderData.put("product", order.getProduct());
        orderData.put("quantity", order.getQuantity());
        orderData.put("totalAmount", order.getTotalAmount());
        orderData.put("orderDate", order.getOrderDate());
        orderData.put("orderStatus", order.getOrderStatus().toString());
        orderData.put("paymentMethod", order.getPaymentMethod());
        orderData.put("shippingLocation", order.getShippingLocation());

        ordersCollection.add(orderData)
                .addOnSuccessListener(documentReference -> {
                    orderData.put("orderId", documentReference.getId());

                    Log.d(TAG, "Order added with ID: " + documentReference.getId());
                    listener.onAddOrderSuccess(documentReference.getId());
                })
                .addOnFailureListener(e -> {
                    // On failure, log an error message to the console
                    Log.e(TAG, "Error adding order", e);
                    listener.onAddOrderFailure(e.getMessage());
                });
    }

    public void updateOrder(Order order,OnOrderFetchListener listener) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference orderRef = db.collection("orders").document(order.getOrderId());

        orderRef.update("user", order.getUser(),
                        "product", order.getProduct(),
                        "quantity", order.getQuantity(),
                        "totalAmount", order.getTotalAmount(),
                        "orderDate", order.getOrderDate(),
                        "orderStatus", order.getOrderStatus().toString(),
                        "paymentMethod", order.getPaymentMethod(),
                        "shippingLocation", order.getShippingLocation())
                .addOnSuccessListener(aVoid -> {

                    listener.onDeleteOrderSuccess();
                    Log.d(TAG, "Order updated successfully");
                })
                .addOnFailureListener(e -> {
                    Log.w(TAG, "Error updating order", e);
                    listener.onDeleteOrderFailure(e.getMessage());
                });
    }


    public void deleteOrder(Order order,OnOrderFetchListener listener) {
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
    public void getOrdersByServiceProviderId(String serviceProviderId,OnOrderFetchListener listener) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference ordersCollection = db.collection("orders");

        Query query = ordersCollection.whereEqualTo("serviceProviderId", serviceProviderId);

        query.get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        List<Order> orders = new ArrayList<>();
                        for (DocumentSnapshot document : task.getResult()) {
                            Order order = document.toObject(Order.class);
                            orders.add(order);
                            listener.onGetOrdersByServiceProviderIdSuccess(orders);
                        }
                    } else {
                        Log.w(TAG, "Error getting orders by service provider ID", task.getException());
                        listener.onGetOrdersByServiceProviderIdFailure(task.getException().getMessage());
                    }
                });
    }
    public void getOrdersByServiceProviderIdAndOrderStatus(String userId, String orderStatus, OnOrderFetchListener listener) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference ordersCollection = db.collection("orders");

        Query query = ordersCollection.whereEqualTo("userId", userId)
                .whereEqualTo("orderStatus", orderStatus);

        query.get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        List<Order> orders = new ArrayList<>();
                        for (DocumentSnapshot document : task.getResult()) {
                            Order order = document.toObject(Order.class);
                            orders.add(order);
                        }
                        listener.onGetOrdersByServiceProviderIdSuccess(orders);
                    } else {
                        Log.w(TAG, "Error getting orders by service provider ID", task.getException());
                        listener.onGetOrdersByServiceProviderIdFailure(task.getException().getMessage());
                    }
                });
    }


}

