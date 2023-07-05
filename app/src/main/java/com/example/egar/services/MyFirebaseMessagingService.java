package com.example.egar.services;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = "MyFirebaseMsgService";

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        Log.d(TAG, "From: " + remoteMessage.getFrom());

        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());
            // قم بمعالجة البيانات المستلمة هنا
            // يمكنك تنفيذ إجراءات مخصصة استنادًا إلى البيانات التي تم استلامها
            // مثلاً، يمكنك إنشاء إشعار أو تنفيذ إجراءات أخرى
        }

        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
            // قم بمعالجة جسم الإشعار هنا
            // يمكنك عرض إشعار للمستخدم أو تنفيذ إجراءات أخرى استنادًا إلى جسم الإشعار المستلم
        }
    }

    @Override
    public void onNewToken(@NonNull String token) {
        // يتم استدعاء هذه الدالة عند تغيير رمز الجهاز الخاص بالتطبيق (Firebase Token)
        // قم بتحديث رمز الجهاز في قاعدة البيانات أو المكان المناسب لاستخدامه لاحقًا
        Log.d(TAG, "Refreshed token: " + token);
        // قم بتنفيذ الإجراءات المطلوبة لتحديث رمز الجهاز هنا
    }

    private void handleNow() {
        // قم بتنفيذ الإجراءات اللازمة للتعامل مع الإشعار في الوقت الحالي (أقل من 10 ثوانٍ)
    }

    private void scheduleJob() {
        // قم بجدولة العمل الطويل الأمد (10 ثوانٍ أو أكثر) باستخدام WorkManager
    }
}
