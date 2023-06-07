package com.example.egar.controllers;

import static android.content.ContentValues.TAG;

import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;


import com.example.egar.Models.Product;
import com.example.egar.interfaces.OnProductFetchListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.storage.FirebaseStorage;


import java.util.ArrayList;

import java.util.concurrent.atomic.AtomicInteger;

public class ProductController {
    private static ProductController instance;
    private  FirebaseFirestore db = FirebaseFirestore.getInstance();

    private ProductController() {
    }

    public static ProductController getInstance() {
        if (instance == null) {
            synchronized(ProductController.class) {
                if (instance == null) {
                    instance = new ProductController();
                }
            }
        }
        return instance;
    }


    public void getAllProducts(String serviceProviderId, OnProductFetchListener listener) {
        // Get all products from Firestore where merchantId matches
        db.collection("products")
                .whereEqualTo("serviceProviderId", serviceProviderId)
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    // Get a list of products
                    ArrayList<Product> productList = new ArrayList<>();
                    int productCount = queryDocumentSnapshots.size(); // Total product count
                    AtomicInteger processedCount = new AtomicInteger(0); // Number of products processed so far

                    for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                        // Retrieve product data
                        Product product = document.toObject(Product.class);

                        // Retrieve image URL from the product data
                        String imageUrl = String.valueOf(product.getImageUrl());

                        // Download the image from Firebase Storage
                        FirebaseStorage.getInstance().getReferenceFromUrl(imageUrl).getDownloadUrl()
                                .addOnSuccessListener(uri -> {
                                    // Set the downloaded image URL to the product
                                    product.setImageUrl(Uri.parse(uri.toString()));

                                    // Add the product to the list
                                    productList.add(product);

                                    // Increase the processed count
                                    int count = processedCount.incrementAndGet();

                                    // Check if all products have been processed
                                    if (count == productCount) {
                                        // Return the list of products to the listener
                                        listener.onFetchLListSuccess(productList);
                                    }
                                })
                                .addOnFailureListener(e -> {
                                    // Handle image download failure
                                    listener.onFetchFailure("Failed to download product image");
                                });
                    }
                })
                .addOnFailureListener(e -> {
                    // Return the error message to the listener
                    listener.onFetchFailure("Failed to fetch products");
                });
    }

    public void getAllProducts(OnProductFetchListener listener) {
        // Get all products from Firestore
        db.collection("products")
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    // Get a list of products
                    ArrayList<Product> productList = new ArrayList<>();
                    int productCount = queryDocumentSnapshots.size();
                    AtomicInteger processedCount = new AtomicInteger(0);

                    for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                        // Retrieve product data
                        Product product = document.toObject(Product.class);

                        // Retrieve image URL from the product data
                        String imageUrl = String.valueOf(product.getImageUrl());

                        // Download the image from Firebase Storage
                        FirebaseStorage.getInstance().getReferenceFromUrl(imageUrl).getDownloadUrl()
                                .addOnSuccessListener(uri -> {
                                    // Set the downloaded image URL to the product
                                    product.setImageUrl(Uri.parse(uri.toString()));

                                    // Add the product to the list
                                    productList.add(product);

                                    // Increase the processed count
                                    int count = processedCount.incrementAndGet();

                                    // Check if all products have been processed
                                    if (count == productCount) {
                                        // Return the list of products to the listener
                                        listener.onFetchLListSuccess(productList);
                                    }
                                })
                                .addOnFailureListener(e -> {
                                    // Handle image download failure
                                    listener.onFetchFailure("Failed to download product image");
                                });
                    }
                })
                .addOnFailureListener(e -> {
                    // Return the error message to the listener
                    listener.onFetchFailure("Failed to fetch products");
                });
    }

    public void getProductById(String productId, OnProductFetchListener listener) {
        // Get product by ID from Firestore
        DocumentReference docRef = db.collection("products").document(productId);
        docRef.get().addOnSuccessListener(documentSnapshot -> {
            if (documentSnapshot.exists()) {
                // Retrieve product data
                Product product = documentSnapshot.toObject(Product.class);

                // Retrieve image URL from the product data
                String imageUrl = String.valueOf(product.getImageUrl());

                // Download the image from Firebase Storage
                FirebaseStorage.getInstance().getReferenceFromUrl(imageUrl).getDownloadUrl()
                        .addOnSuccessListener(uri -> {
                            // Set the downloaded image URL to the product
                            product.setImageUrl(Uri.parse(uri.toString()));

                            // Return the product to the listener
                            listener.onFetchSuccess(product);
                        })
                        .addOnFailureListener(e -> {
                            // Handle image download failure
                            listener.onFetchFailure("Failed to download product image");
                        });
            } else {
                listener.onFetchFailure("Product not found");
            }
        }).addOnFailureListener(e -> {
            listener.onFetchFailure("Failed to fetch product");
        });
    }

    public void getAllProductsByCategory(String category, OnProductFetchListener listener) {
        db.collection("products")
                .whereEqualTo("category", category)
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    ArrayList<Product> productList = new ArrayList<>();
                    int productCount = queryDocumentSnapshots.size();
                    AtomicInteger processedCount = new AtomicInteger(0);

                    for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                        Product product = document.toObject(Product.class);
                        String imageUrl = String.valueOf(product.getImageUrl());

                        FirebaseStorage.getInstance().getReferenceFromUrl(imageUrl).getDownloadUrl()
                                .addOnSuccessListener(uri -> {
                                    product.setImageUrl(Uri.parse(uri.toString()));
                                    productList.add(product);

                                    int count = processedCount.incrementAndGet();
                                    if (count == productCount) {
                                        listener.onFetchLListSuccess(productList);
                                    }
                                })
                                .addOnFailureListener(e -> {
                                    listener.onFetchFailure("Failed to download product image");
                                });
                    }
                })
                .addOnFailureListener(e -> {
                    // Return the error message to the listener
                    listener.onFetchFailure("Failed to fetch products");
                });
    }

}



