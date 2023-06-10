package com.example.egar.FirebaseManger;

import android.net.Uri;

import androidx.annotation.NonNull;

import com.example.egar.interfaces.ProcessCallback;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class FirebaseAuthController {

    private FirebaseAuth auth = FirebaseAuth.getInstance();
    private static FirebaseAuthController instance;

    private FirebaseAuthController() {
    }

    public static synchronized FirebaseAuthController getInstance() {
        if(instance == null) {
            instance = new FirebaseAuthController();
        }
        return instance;
    }

    public void createAccount(String id ,String name, String email, String password, String phoneNumber, Uri profileImageUri, ProcessCallback callback) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                auth.getCurrentUser().sendEmailVerification();

                FirebaseStorage storage = FirebaseStorage.getInstance();
                StorageReference imagesRef = storage.getReference().child("profile_images_users").child(auth.getCurrentUser().getUid());

                UploadTask uploadTask = imagesRef.putFile(profileImageUri);
                uploadTask.continueWithTask(task2 -> {
                    if (!task2.isSuccessful()) {
                        throw task2.getException();
                    }
                    return imagesRef.getDownloadUrl();
                }).addOnCompleteListener(task2 -> {
                    if (task2.isSuccessful()) {
                        Uri downloadUri = task2.getResult();

                        FirebaseFirestore db = FirebaseFirestore.getInstance();
                        CollectionReference usersRef = db.collection("users");

                        Map<String, Object> userData = new HashMap<>();
                        userData.put("name", name);
                        userData.put("email", email);
                        userData.put("phoneNumber", phoneNumber);
                        userData.put("profileImageUrl", downloadUri.toString());

                        usersRef.document(auth.getCurrentUser().getUid())
                                .set(userData)
                                .addOnSuccessListener(aVoid -> {
                                    callback.onSuccess("Account created successfully");
                                })
                                .addOnFailureListener(e -> {
                                    callback.onFailure(e.getMessage());
                                });
                    } else {
                        callback.onFailure("Error uploading profile image");
                    }
                }).addOnFailureListener(e -> {
                    callback.onFailure(e.getMessage());
                });

            } else {
                callback.onFailure(task.getException().getMessage());
            }
        }).addOnFailureListener(e -> {
            callback.onFailure(e.getMessage());
        });
    }

    public void signIn(String email, String password, ProcessCallback callback) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    if (Objects.requireNonNull(auth.getCurrentUser()).isEmailVerified()) {
                        //TODO: Login success, Navigate to home screen (FROM UI)
                        callback.onSuccess("Logged in successfully");
                    } else {
                        auth.signOut();
                        callback.onFailure("Verify email to login");
                    }
                } else {
                    callback.onFailure(Objects.requireNonNull(task.getException()).getMessage());
                }
            }
        });
    }



    public void forgetPassword(String email, ProcessCallback callback) {
        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    callback.onSuccess("Reset email sent successfully");
                } else {
                    callback.onFailure(task.getException().getMessage());
                }
            }
        });
    }

    public void signOut() {
        auth.signOut();

    }

    public boolean isSignedIn() {
        return auth.getCurrentUser() != null;
    }

}
