package com.example.egar.services;

import android.util.Log;

import androidx.annotation.NonNull;
import com.example.egar.Models.Notification;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.CollectionReference;
import java.util.HashMap;
import java.util.Map;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = "MyFirebaseMsgService";

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        Log.d(TAG, "From: " + remoteMessage.getFrom());

        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());
            String notificationTitle = remoteMessage.getData().get("title");
            String notificationBody = remoteMessage.getData().get("body");
            Notification notification = new Notification(notificationTitle, notificationBody);
            addNotificationToDatabase(notification);
        }

        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
            String notificationTitle = remoteMessage.getNotification().getTitle();
            String notificationBody = remoteMessage.getNotification().getBody();
            Notification notification = new Notification(notificationTitle, notificationBody);
            addNotificationToDatabase(notification);
        }
    }

    private void addNotificationToDatabase(Notification notification) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference notificationsCollection = db.collection("notifications");

        Map<String, Object> notificationData = new HashMap<>();
        notificationData.put("title", notification.getTitle());
        notificationData.put("body", notification.getBody());

        notificationsCollection.add(notificationData)
                .addOnSuccessListener(documentReference -> {
                    Log.d(TAG, "Notification added with ID: " + documentReference.getId());
                })
                .addOnFailureListener(e -> {
                    Log.e(TAG, "Error adding notification", e);
                });
    }

    @Override
    public void onNewToken(@NonNull String token) {
        // يمكنك تنفيذ الإجراءات اللازمة عند تحديث رمز التوكن الخاص بالجهاز هنا
    }
}
