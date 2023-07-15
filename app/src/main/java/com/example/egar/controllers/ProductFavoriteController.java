package com.example.egar.controllers;

import com.example.egar.Models.Product;
import com.example.egar.interfaces.OnProductFavoritesFetchListener;
import com.example.egar.interfaces.ProcessCallback;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


import com.example.egar.Models.ProductFavorite;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class ProductFavoriteController {
    private static final String COLLECTION_NAME = "productFavorites";

    private FirebaseFirestore db;
    private CollectionReference productFavoritesCollection;

    public ProductFavoriteController() {
        db = FirebaseFirestore.getInstance();
        productFavoritesCollection = db.collection(COLLECTION_NAME);
    }


        public void addProductToFavorite(Product product, String userId, ProcessCallback callback) {
            ProductFavorite productFavorite = new ProductFavorite(product, userId);

            productFavoritesCollection.add(productFavorite)
                    .addOnSuccessListener(documentReference -> {
                        String productFavoriteId = documentReference.getId();
                        callback.onSuccess("Product added to favorites successfully");
                    })
                    .addOnFailureListener(e -> {
                        callback.onFailure(e.getMessage());
                    });
        }

        public void updateProductFavorite(String productFavoriteId, ProductFavorite updatedProductFavorite, ProcessCallback callback) {
            productFavoritesCollection.document(productFavoriteId)
                    .set(updatedProductFavorite)
                    .addOnSuccessListener(aVoid -> {
                        callback.onSuccess("Product favorite updated successfully");
                    })
                    .addOnFailureListener(e -> {
                        callback.onFailure(e.getMessage());
                    });
        }

        public void removeProductFromFavorite(String productFavoriteId, ProcessCallback callback) {
            productFavoritesCollection.document(productFavoriteId)
                    .delete()
                    .addOnSuccessListener(aVoid -> {
                        callback.onSuccess("Product removed from favorites successfully");
                    })
                    .addOnFailureListener(e -> {
                        callback.onFailure(e.getMessage());
                    });
        }

        public void getAllProductFavorites(String userId, OnProductFavoritesFetchListener listener) {
            productFavoritesCollection.whereEqualTo("userId", userId)
                    .get()
                    .addOnSuccessListener(queryDocumentSnapshots -> {
                        List<ProductFavorite> productFavorites = new ArrayList<>();
                        for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots.getDocuments()) {
                            ProductFavorite productFavorite = documentSnapshot.toObject(ProductFavorite.class);
                            productFavorite.setProduct(documentSnapshot.toObject(Product.class));
                            productFavorites.add(productFavorite);
                        }
                        listener.onProductFavoritesFetched(productFavorites);
                    })
                    .addOnFailureListener(e -> {
                        listener.onProductFavoritesFetchFailure(e.getMessage());
                    });
        }


}

