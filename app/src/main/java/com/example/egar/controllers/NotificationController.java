package com.example.egar.controllers;

import com.example.egar.Models.Notification;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class NotificationController {
    private static NotificationController instance;
    private FirebaseFirestore firestore;

    private NotificationController() {
        firestore = FirebaseFirestore.getInstance();
    }

    public static synchronized NotificationController getInstance() {
        if (instance == null) {
            instance = new NotificationController();
        }
        return instance;
    }

    public void getAllNotifications(NotificationCallback callback) {
        firestore.collection("notifications")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        List<com.example.egar.Models.Notification> notifications = new ArrayList<>();
                        QuerySnapshot querySnapshot = task.getResult();
                        if (querySnapshot != null) {
                            for (QueryDocumentSnapshot document : querySnapshot) {
                                String notificationTitle = document.getString("title");
                                String notificationBody = document.getString("body");
                                String currentDate = document.getString("date");
                                String currentTime = document.getString("time");

                                com.example.egar.Models.Notification notification = new com.example.egar.Models.Notification(notificationTitle,notificationBody,currentDate,currentTime);
                                notifications.add(notification);
                            }
                        }
                        callback.onNotificationsLoaded(notifications);
                    } else {
                        callback.onNotificationsError(task.getException());
                    }
                });
    }

    public interface NotificationCallback {
        void onNotificationsLoaded(List<Notification> notifications);
        void onNotificationsError(Exception e);
    }
}

