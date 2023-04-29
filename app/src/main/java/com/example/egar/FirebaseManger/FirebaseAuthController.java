package com.example.egar.FirebaseManger;

import androidx.annotation.NonNull;

import com.example.egar.interfaces.ProcessCallback;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserProfileChangeRequest;

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

    public void createAccount(String name, String email, String password, ProcessCallback callback) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    auth.getCurrentUser().sendEmailVerification();

                    UserProfileChangeRequest request = new UserProfileChangeRequest.Builder()
                            .setDisplayName(name)
                            .build();
                    auth.getCurrentUser().updateProfile(request);

                    auth.signOut();
                    callback.onSuccess("Account created successfully");
                } else {
                    callback.onFailure(task.getException().getMessage());
                }
            }
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
