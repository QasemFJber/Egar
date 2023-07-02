package com.example.egar.FirebaseManger;


import com.example.egar.interfaces.UserDataCallBack;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class FirebaseFetchingDataController {

    private static FirebaseFetchingDataController instance;

    private FirebaseFetchingDataController() {
    }

    public static FirebaseFetchingDataController getInstance() {
        if (instance == null) {
            synchronized (FirebaseFetchingDataController.class) {
                if (instance == null) {
                    instance = new FirebaseFetchingDataController();
                }
            }
        }
        return instance;
    }


    public void getCurrentUserData(UserDataCallBack callback) {
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            DocumentReference userRef = db.collection("users").document(currentUser.getUid());

            userRef.get().addOnSuccessListener(documentSnapshot -> {
                if (documentSnapshot.exists()) {
                    String name = documentSnapshot.getString("name");
                    String id = documentSnapshot.getString("id");
                    String phoneNumber = documentSnapshot.getString("phoneNumber");
                    String profileImageUrl = documentSnapshot.getString("profileImageUrl");
                    String email = currentUser.getEmail();

                    callback.onSuccess(name, id, phoneNumber, profileImageUrl, email);
                } else {
                    callback.onFailure("User data does not exist");
                }
            }).addOnFailureListener(e -> {
                callback.onFailure(e.getMessage());
            });
        } else {
            callback.onFailure("No current user");
        }
    }


}



