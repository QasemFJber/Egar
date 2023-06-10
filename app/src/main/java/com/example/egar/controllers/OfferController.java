package com.example.egar.controllers;

import com.example.egar.Models.Offer;
import com.example.egar.interfaces.ProcessCallback;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class OfferController {
    private static final String COLLECTION_PATH = "offers";
    private CollectionReference offersCollection;

    public OfferController() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        offersCollection = db.collection(COLLECTION_PATH);
    }

    public void addOffer(Offer offer, ProcessCallback callback) {
        offersCollection.add(offer)
                .addOnSuccessListener(documentReference -> {
                    callback.onSuccess("Offer added successfully");
                })
                .addOnFailureListener(e -> {
                    callback.onFailure(e.getMessage());
                });
    }

    public void deleteOffer(String offerId, ProcessCallback callback) {
        DocumentReference offerRef = offersCollection.document(offerId);
        offerRef.delete()
                .addOnSuccessListener(aVoid -> {
                    callback.onSuccess("Offer deleted successfully");
                })
                .addOnFailureListener(e -> {
                    callback.onFailure(e.getMessage());
                });
    }

    public void updateOffer(Offer offer, ProcessCallback callback) {
        DocumentReference offerRef = offersCollection.document(offer.getId());
        offerRef.set(offer)
                .addOnSuccessListener(aVoid -> {
                    callback.onSuccess("Offer updated successfully");
                })
                .addOnFailureListener(e -> {
                    callback.onFailure(e.getMessage());
                });
    }

    public void getAllOffers(ProcessCallback callback) {
        offersCollection.get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    callback.onSuccess(queryDocumentSnapshots.toObjects(Offer.class).toString());
                })
                .addOnFailureListener(e -> {
                    callback.onFailure(e.getMessage());
                });
    }
}
