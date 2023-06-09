package com.example.egar.controllers;

import com.example.egar.Models.Category;
import com.example.egar.interfaces.CategoryCallback;
import com.example.egar.interfaces.ProcessCallback;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class CategoryController {
    private static final String COLLECTION_NAME = "categories";

    private FirebaseFirestore db;
    private CollectionReference categoryRef;

    private static CategoryController instance;

    private CategoryController() {
        db = FirebaseFirestore.getInstance();
        categoryRef = db.collection(COLLECTION_NAME);
    }

    public static CategoryController getInstance() {
        if (instance == null) {
            instance = new CategoryController();
        }
        return instance;
    }


    public void addCategories(List<Category> categoryList, ProcessCallback callback) {
        int totalCategories = categoryList.size();
        AtomicInteger addedCategories = new AtomicInteger();

        for (Category category : categoryList) {
            categoryRef.add(category)
                    .addOnSuccessListener(documentReference -> {
                        addedCategories.getAndIncrement();
                        if (addedCategories.get() == totalCategories) {
                            callback.onSuccess("All categories added successfully");
                        }
                    })
                    .addOnFailureListener(e -> {
                        callback.onFailure(e.getMessage());
                    });
        }
    }


    public void getAllCategories(CategoryCallback callback) {
        categoryRef.get().addOnSuccessListener(queryDocumentSnapshots -> {
            List<Category> categories = new ArrayList<>();
            for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                Category category = documentSnapshot.toObject(Category.class);
                categories.add(category);
            }
            callback.onSuccess(categories);
        }).addOnFailureListener(e -> {
            callback.onFailure(e.getMessage());
        });
    }

    public void getCategoryByName(String name, CategoryCallback callback) {
        categoryRef.whereEqualTo("name", name).get().addOnSuccessListener(queryDocumentSnapshots -> {
            List<Category> categories = new ArrayList<>();
            for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                Category category = documentSnapshot.toObject(Category.class);
                categories.add(category);
            }
            callback.onSuccess(categories);
        }).addOnFailureListener(e -> {
            callback.onFailure(e.getMessage());
        });
    }
}

