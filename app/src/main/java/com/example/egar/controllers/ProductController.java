package com.example.egar.controllers;

import static android.content.ContentValues.TAG;

import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;


import com.example.egar.Models.Product;
import com.example.egar.Models.Provider;
import com.example.egar.interfaces.OnProductFetchListener;

import com.example.egar.interfaces.ProcessCallback;
import com.example.egar.interfaces.ProductCallback;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.storage.FirebaseStorage;


import java.util.ArrayList;

import java.util.List;
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


    public void updateProductFavoriteStatus(String productId, boolean isFavorite, ProcessCallback callback) {
        CollectionReference productsCollection = FirebaseFirestore.getInstance().collection("products");

        productsCollection.document(productId)
                .update("isFavorite", isFavorite)
                .addOnSuccessListener(aVoid -> {

                    callback.onSuccess("Product favorite status updated successfully.");
                })
                .addOnFailureListener(e -> {
                    callback.onFailure(e.getMessage());
                });
    }

    public void getAllProductsByProviderId(String providerId, OnProductFetchListener listener) {
        db.collection("products")
                .whereEqualTo("provider.id", providerId)
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
                                    product.setImageUrl(uri.toString());

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

    public void getProductById(String productId, ProductCallback callback) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference productRef = db.collection("products").document(productId);

        productRef.get().addOnSuccessListener(documentSnapshot -> {
            if (documentSnapshot.exists()) {
                String id = documentSnapshot.getId();
                String name = documentSnapshot.getString("name");
                String description = documentSnapshot.getString("description");
                double price = documentSnapshot.getDouble("price");
                boolean isFavorite = documentSnapshot.getBoolean("isFavorite");
                int quantityInCart = documentSnapshot.getLong("quantityInCart").intValue();
                String category = documentSnapshot.getString("category");
                String providerId = documentSnapshot.getString("provider.id");
                String imageUrl = documentSnapshot.getString("imageUrl");

                DocumentReference providerRef = db.collection("serviceproviders").document(providerId);
                providerRef.get().addOnSuccessListener(providerDocumentSnapshot -> {
                    if (providerDocumentSnapshot.exists()) {
                        String providerName = providerDocumentSnapshot.getString("name");
                        String providerEmail = providerDocumentSnapshot.getString("email");
                        String providerType = providerDocumentSnapshot.getString("providerType");
                        String providerPhoneNumber = providerDocumentSnapshot.getString("phoneNumber");
                        String providerAddress = providerDocumentSnapshot.getString("address");
                        String providerCity = providerDocumentSnapshot.getString("city");
                        String providerBio = providerDocumentSnapshot.getString("bio");
                        String providerImage = providerDocumentSnapshot.getString("image");

                        Provider provider = new Provider(providerId, providerName, providerEmail, providerType, providerPhoneNumber, providerAddress, providerCity, providerBio, providerImage);

                        Product product = new Product(id, name, description, price, isFavorite, quantityInCart, category, provider, imageUrl);
                        callback.onProductFetchSuccess(product);
                    } else {
                        callback.onFailure("Provider document does not exist");
                    }
                }).addOnFailureListener(e -> {
                    callback.onFailure(e.getMessage());
                });
            } else {
                callback.onFailure("Product document does not exist");
            }
        }).addOnFailureListener(e -> {
            callback.onFailure(e.getMessage());
        });
    }

    public void getAllProducts(ProductCallback callback) {
        CollectionReference productsCollection = FirebaseFirestore.getInstance().collection("products");
        CollectionReference providersCollection = FirebaseFirestore.getInstance().collection("serviceproviders");

        productsCollection.get().addOnSuccessListener(queryDocumentSnapshots -> {
            List<Product> productList = new ArrayList<>();

            for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                String id = documentSnapshot.getId();
                String name = documentSnapshot.getString("name");
                String description = documentSnapshot.getString("description");
                double price = documentSnapshot.getDouble("price");
                boolean isFavorite = documentSnapshot.getBoolean("isFavorite");
                int quantityInCart = documentSnapshot.getLong("quantityInCart").intValue();
                String category = documentSnapshot.getString("category");
                String providerId = documentSnapshot.getString("provider.id");

                String imageUrl = documentSnapshot.getString("imageUrl");

                providersCollection.document(providerId).get().addOnSuccessListener(providerDocumentSnapshot -> {
                    if (providerDocumentSnapshot.exists()) {
                        String providerName = providerDocumentSnapshot.getString("name");
                        String providerEmail = providerDocumentSnapshot.getString("email");
                        String providerType = providerDocumentSnapshot.getString("providerType");
                        String providerPhoneNumber = providerDocumentSnapshot.getString("phoneNumber");
                        String providerAddress = providerDocumentSnapshot.getString("address");
                        String providerCity = providerDocumentSnapshot.getString("city");
                        String providerBio = providerDocumentSnapshot.getString("bio");
                        String providerImage = providerDocumentSnapshot.getString("image");

                        Provider provider = new Provider(providerId, providerName, providerEmail, providerType, providerPhoneNumber, providerAddress, providerCity, providerBio, providerImage);

                        Product product = new Product(id, name, description, price, isFavorite, quantityInCart, category, provider, imageUrl);
                        productList.add(product);

                        if (productList.size() == queryDocumentSnapshots.size()) {
                            callback.onSuccess(productList);
                        }
                    } else {
                        callback.onFailure("Provider document does not exist");
                    }
                }).addOnFailureListener(e -> {
                    callback.onFailure(e.getMessage());
                });
            }
        }).addOnFailureListener(e -> {
            callback.onFailure(e.getMessage());
        });
    }

    public void getProductsByCategory(String category, ProductCallback callback) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference productsCollection = db.collection("products");

        productsCollection.whereEqualTo("category", category)
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    List<Product> products = new ArrayList<>();
                    for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                        String id = documentSnapshot.getId();
                        String name = documentSnapshot.getString("name");
                        String description = documentSnapshot.getString("description");
                        double price = documentSnapshot.getDouble("price");
                        boolean isFavorite = documentSnapshot.getBoolean("isFavorite");
                        int quantityInCart = documentSnapshot.getLong("quantityInCart").intValue();
                        String productCategory = documentSnapshot.getString("category");
                        String providerId = documentSnapshot.getString("provider.id");
                        String imageUrl = documentSnapshot.getString("imageUrl");

                        DocumentReference providerRef = db.collection("serviceproviders").document(providerId);
                        providerRef.get().addOnSuccessListener(providerDocumentSnapshot -> {
                            if (providerDocumentSnapshot.exists()) {
                                String providerName = providerDocumentSnapshot.getString("name");
                                String providerEmail = providerDocumentSnapshot.getString("email");
                                String providerType = providerDocumentSnapshot.getString("providerType");
                                String providerPhoneNumber = providerDocumentSnapshot.getString("phoneNumber");
                                String providerAddress = providerDocumentSnapshot.getString("address");
                                String providerCity = providerDocumentSnapshot.getString("city");
                                String providerBio = providerDocumentSnapshot.getString("bio");
                                String providerImage = providerDocumentSnapshot.getString("image");

                                Provider provider = new Provider(providerId, providerName, providerEmail, providerType, providerPhoneNumber, providerAddress, providerCity, providerBio, providerImage);

                                Product product = new Product(id, name, description, price, isFavorite, quantityInCart, productCategory, provider, imageUrl);
                                products.add(product);

                                if (products.size() == queryDocumentSnapshots.size()) {
                                    callback.onSuccess(products);
                                }
                            } else {
                                callback.onFailure("Provider document does not exist");
                            }
                        }).addOnFailureListener(e -> {
                            callback.onFailure(e.getMessage());
                        });
                    }
                })
                .addOnFailureListener(e -> {
                    callback.onFailure(e.getMessage());
                });
    }

}



