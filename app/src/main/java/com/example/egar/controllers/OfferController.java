package com.example.egar.controllers;

import com.example.egar.Models.Offer;
import com.example.egar.Models.Product;
import com.example.egar.interfaces.OnAllOffersFetch;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class OfferController {
    private static final String COLLECTION_PATH = "offers";
    private CollectionReference offersCollection;

    public OfferController() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        offersCollection = db.collection(COLLECTION_PATH);
    }
    public static void getProductsOnOffer(CollectionReference offersCollection, OnAllOffersFetch callback) {
        offersCollection.get()
                .addOnSuccessListener(querySnapshot -> {
                    List<Product> productsOnOffer = new ArrayList<>();

                    for (DocumentSnapshot document : querySnapshot) {
                        Offer offer = document.toObject(Offer.class);
                        Product product = offer.getProduct();
                        productsOnOffer.add(product);
                    }

                    callback.onSuccess(productsOnOffer);
                })
                .addOnFailureListener(e -> {
                    callback.onFailure(e.getMessage());
                });
    }

}
