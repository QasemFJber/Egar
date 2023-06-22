package com.example.egar.controllers;
import static android.content.ContentValues.TAG;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.egar.Models.Review;

import com.example.egar.interfaces.ListCallBack;
import com.example.egar.interfaces.OnReviewFetchListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ReviewController {

    private static ReviewController instance;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    private ReviewController() {
        // Private constructor to prevent direct instantiation
    }

    public static ReviewController getInstance() {
        if (instance == null) {
            synchronized (ReviewController.class) {
                if (instance == null) {
                    instance = new ReviewController();
                }
            }
        }
        return instance;
    }

    public void addReview(Review review) {
        CollectionReference reviewsCollection = FirebaseFirestore.getInstance().collection("reviews");

        HashMap<String, Object> reviewData = new HashMap<>();

        reviewData.put("userId", review.getUserId());
        reviewData.put("serviceId", review.getServiceId());
        reviewData.put("reviewText", review.getReviewText());
        reviewData.put("rating", review.getRating());
        reviewData.put("date", review.getDate());

        reviewsCollection.add(reviewData)
                .addOnSuccessListener(documentReference -> {
                    reviewData.put("reviewId", documentReference.getId());
                    Log.d(TAG, "Review added with ID: " + documentReference.getId());
                })
                .addOnFailureListener(e -> {
                    Log.e(TAG, "Error adding review", e);
                });
    }

    public void updateReview(Review review) {
        DocumentReference reviewRef = db.collection("reviews").document(review.getReviewId());

        reviewRef.update("userId", review.getUserId(),
                        "serviceId", review.getServiceId(),
                        "reviewText", review.getReviewText(),
                        "rating", review.getRating(),
                        "date", review.getDate())
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "Review updated successfully");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error updating review", e);
                    }
                });
    }

    public void deleteReview(Review review) {
        db.collection("reviews").document(review.getReviewId())
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "Review deleted successfully");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error deleting review", e);
                    }
                });
    }
    public void getAllReviews(ListCallBack listCallBack) {
        db.collection("reviews")
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    List<Review> reviewList = new ArrayList<>();
                    for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                        Review review = document.toObject(Review.class);
                        reviewList.add(review);
                    }
                    listCallBack.onSuccess(reviewList);
                })
                .addOnFailureListener(e -> {
                    listCallBack.onFul("Failed to fetch reviews");
                });
    }

    public void getReviewById(String reviewId, OnReviewFetchListener listener) {
        DocumentReference docRef = db.collection("reviews").document(reviewId);
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    Review review = documentSnapshot.toObject(Review.class);
                    listener.onFetchSuccess(review);
                } else {
                    listener.onFetchFailure("Review not found");
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                listener.onFetchFailure("Failed to fetch review");
            }
        });
    }

}
