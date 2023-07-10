package com.example.egar.FirebaseManger;

import android.net.Uri;

import androidx.annotation.NonNull;

import com.example.egar.Models.User;
import com.example.egar.interfaces.ProcessCallback;
import com.example.egar.interfaces.SignInStatusListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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

    public void createAccount(User user, Uri profileImageUri, ProcessCallback callback) {
        auth.createUserWithEmailAndPassword(user.getEmail(), user.getPassword())
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        auth.getCurrentUser().sendEmailVerification();

                        FirebaseStorage storage = FirebaseStorage.getInstance();
                        StorageReference imagesRef = storage.getReference()
                                .child("profile_images_users")
                                .child(auth.getCurrentUser().getUid());

                        UploadTask uploadTask = imagesRef.putFile(profileImageUri);
                        uploadTask.continueWithTask(task2 -> {
                            if (!task2.isSuccessful()) {
                                throw task2.getException();
                            }
                            return imagesRef.getDownloadUrl();
                        }).addOnCompleteListener(task2 -> {
                            if (task2.isSuccessful()) {
                                Uri downloadUri = task2.getResult();

                                user.setProfileImageUri(downloadUri.toString());

                                FirebaseFirestore db = FirebaseFirestore.getInstance();
                                DocumentReference userRef = db.collection("users")
                                        .document(auth.getCurrentUser().getUid());

                                userRef.set(user)
                                        .addOnSuccessListener(aVoid -> {
                                            // Store the document ID in the user object
                                            user.setId(userRef.getId());

                                            // Update the user data with the updated user object
                                            userRef.set(user)
                                                    .addOnSuccessListener(aVoid1 -> {
                                                        callback.onSuccess("Account created successfully");
                                                    })
                                                    .addOnFailureListener(e -> {
                                                        callback.onFailure(e.getMessage());
                                                    });
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

    public void changePassword(String previousPassword, String newPassword, String confirmPassword, ProcessCallback callback) {
        if (!newPassword.equals(confirmPassword)) {
            callback.onFailure("تأكيد كلمة المرور الجديدة غير صحيح");
            return;
        }

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            AuthCredential credential = EmailAuthProvider.getCredential(user.getEmail(), previousPassword);
            user.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        user.updatePassword(newPassword).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    callback.onSuccess("تم تغيير كلمة المرور بنجاح");
                                } else {
                                    callback.onFailure(task.getException().getMessage());
                                }
                            }
                        });
                    } else {
                        callback.onFailure("كلمة المرور السابقة غير صحيحة");
                    }
                }
            });
        } else {
            callback.onFailure("فشل في الحصول على معلومات المستخدم الحالي");
        }
    }

    public void updateUserData(String userId, User updatedUser, ProcessCallback callback) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference userRef = db.collection("users").document(userId);

        userRef.set(updatedUser)
                .addOnSuccessListener(aVoid -> {
                    callback.onSuccess("User data updated successfully");
                })
                .addOnFailureListener(e -> {
                    callback.onFailure(e.getMessage());
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

    public boolean isSignedIn(){
        return auth != null;
    }
    public void isSignedIn(final SignInStatusListener listener) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            String userId = user.getUid();

            FirebaseFirestore db = FirebaseFirestore.getInstance();

            DocumentReference userRef = db.collection("users").document(userId);
            userRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot userSnapshot) {
                    if (userSnapshot.exists()) {
                        listener.onUserSignedInAsRegularUser(userId);
                    } else {
                        DocumentReference serviceProviderRef = db.collection("serviceproviders").document(userId);
                        serviceProviderRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                            @Override
                            public void onSuccess(DocumentSnapshot serviceProviderSnapshot) {
                                if (serviceProviderSnapshot.exists()) {
                                    listener.onUserSignedInAsAdminUser(userId);
                                } else {
                                    listener.onUserNotSignedIn(userId);
                                }
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // Handle the error
                            }
                        });
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    // Handle the error
                }
            });
        } else {
            listener.onUserNotSignedIn(null);
        }
    }



}
