package com.example.egar.controllers;

import com.example.egar.Models.Offer;
import com.example.egar.Models.Product;
import com.example.egar.interfaces.OnAllOffersFetch;

import com.example.egar.interfaces.OnOfferFetchListener;
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
    public void getAllOffers(OnOfferFetchListener callback) {
        offersCollection.get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    List<Offer> offersList = queryDocumentSnapshots.toObjects(Offer.class);
                    callback.onListFetchSuccess(offersList);
                })
                .addOnFailureListener(e -> {
                    callback.onListFetchFailure(e.getMessage());
                });
    }

}
