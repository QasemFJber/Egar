package com.example.egar.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.egar.BroadcastReceivers.NetworkChangeListiners;
import com.example.egar.Models.Category;
import com.example.egar.R;
import com.example.egar.FirebaseManger.FirebaseAuthController;
import com.example.egar.SharedPreferences.AppSharedPreferences;
import com.example.egar.controllers.CategoryController;
import com.example.egar.databinding.ActivityLoginBinding;
import com.example.egar.interfaces.ProcessCallback;
import com.example.egar.interfaces.ProviderTypeCallback;
import com.example.egar.interfaces.SignInStatusListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class Login extends AppCompatActivity implements View.OnClickListener {

    NetworkChangeListiners networkChangeListiners = new NetworkChangeListiners();
    ActivityLoginBinding binding;
    private ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        screenOperations();

    }

    @Override
    public void onBackPressed() {
        // Create an exit dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Exit");
        builder.setMessage("Are you sure you want to exit?");
        builder.setIcon(R.drawable.baseline_exit_to_app_24);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Close the application
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Dismiss the dialog and continue with the application
                dialog.dismiss();
            }
        });
        // Create the dialog and show it
        AlertDialog dialog = builder.create();
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                // Do nothing
            }
        });
        dialog.show();
    }


    private void screenOperations (){
        setOnClick();
       setDataInInputFieldFromRegister();
        AppSharedPreferences appSharedPreferences = AppSharedPreferences.getInstance();
        boolean isFirstRun = appSharedPreferences.getSharedPreferences().getBoolean("isFirstRun", false);

        if (isFirstRun) {
            addCategoriesToDatabase();
        }else {
            Toast.makeText(Login.this, "Category Not Added", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkChangeListiners,intentFilter);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(networkChangeListiners);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    private  boolean setDataInInputFieldFromRegister (){
        Intent intent = getIntent();
        String email = intent.getStringExtra("email");
        String password =intent.getStringExtra("password");
        if (password == null && email == null){
            return false;
        }else {
            binding.etEmail.setText(intent.getStringExtra("email"));
            binding.etPassword.setText(intent.getStringExtra("password"));
            return true;
        }
    }
    private boolean dataCheck (){
        String email = binding.etEmail.getText().toString();
        String password = binding.etPassword.getText().toString();
         if (email.isEmpty()) {
            binding.etEmail.setError("Email field is Required");
            return false;
        } else if (password.isEmpty()) {
            binding.etPassword.setError("Password field is Required");
            return false;
        }
         return true;
    }

    private  void setOnClick(){
        binding.btnLogin.setOnClickListener(this::onClick);
        binding.createAccount.setOnClickListener(this::onClick);
        binding.forgotPassword.setOnClickListener(this::onClick);
        binding.btnVisitor.setOnClickListener(this::onClick);
    }
    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_login:
                if (dataCheck()){
                    loginAndCheckProviderType();
                }else {
                    Snackbar.make(binding.getRoot(), "Please enter Data , The Input Filed is Required", Snackbar.LENGTH_LONG).setTextColor(ContextCompat.getColor(this, R.color.bronze)).show();
                }
                break;
            case R.id.create_account:
                Intent intent1 = new Intent(getApplicationContext(),Register.class);
                startActivity(intent1);
                overridePendingTransition(R.anim.slid_in, R.anim.slid_out);
                break;
            case R.id.forgotPassword:
                Intent intent3 = new Intent(getApplicationContext(),ResetPassword.class);
                startActivity(intent3);
                break;
            case R.id.btn_visitor:
                Intent visitorIntent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(visitorIntent);
                overridePendingTransition(R.anim.slid_in, R.anim.slid_out);
                break;

        }
    }
    private void addCategoriesToDatabase() {
        List<Category> categoryList = addDataToRecyclerView();

        CategoryController categoryController = CategoryController.getInstance();
        categoryController.addCategories(categoryList, new ProcessCallback() {
            @Override
            public void onSuccess(String message) {
//                AppSharedPreferences.getInstance().getEditor().putString("category","categoryAdded").apply();
                Snackbar.make(binding.getRoot(),message,Snackbar.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(String error) {
                Snackbar.make(binding.getRoot(),error,Snackbar.LENGTH_LONG).show();

            }
        });
    }

    private List<Category> addDataToRecyclerView() {
        List<Category> categoryList = new ArrayList<>();
        categoryList.add(new Category("cars", R.drawable.img_cars, R.drawable.ic_car));
        categoryList.add(new Category("Workspaces", R.drawable.img_workspaces, R.drawable.ic_maintenance));
        categoryList.add(new Category("House", R.drawable.img_home, R.drawable.ic_house));
        categoryList.add(new Category("Equipment", R.drawable.img_equipment, R.drawable.ic_maintenance));
        categoryList.add(new Category("Wedding clothes", R.drawable.img_wedding_clothes, R.drawable.ic_man));
        categoryList.add(new Category("Woman Clothes", R.drawable.img_women, R.drawable.women));
        return categoryList;
    }




    private void loginAndCheckProviderType() {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseAuthController.getInstance().signIn(
                binding.etEmail.getText().toString(),
                binding.etPassword.getText().toString(),
                new ProcessCallback() {
                    @Override
                    public void onSuccess(String message) {
                        checkProviderTypeAndRedirect(new ProviderTypeCallback() {
                            @Override
                            public void onRegularUserSignedIn() {
                                if (auth.getCurrentUser() != null){
                                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }else {

                                }

                            }

                            @Override
                            public void onProviderSignedIn() {
                                Snackbar.make(binding.getRoot(),"Please Register User Account",Snackbar.LENGTH_LONG).show();

                            }

                            @Override
                            public void onUserNotSignedIn() {
                                    Snackbar.make(binding.getRoot(),"Please Register Your Account",Snackbar.LENGTH_LONG).show();

                            }
                        });
                    }

                    @Override
                    public void onFailure(String message) {
                        Snackbar.make(binding.getRoot(), message, Snackbar.LENGTH_LONG).show();
                    }
                });
    }

    private void checkProviderTypeAndRedirect(ProviderTypeCallback callback) {
        String userId = getCurrentUserId();
        if (userId != null) {
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            CollectionReference usersCollection = db.collection("users");
            CollectionReference serviceProvidersCollection = db.collection("serviceproviders");

            usersCollection.document(userId).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document != null && document.exists()) {

                            callback.onRegularUserSignedIn();
                        } else {
                            serviceProvidersCollection.document(userId).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                    if (task.isSuccessful()) {
                                        DocumentSnapshot serviceProviderDocument = task.getResult();
                                        if (serviceProviderDocument != null && serviceProviderDocument.exists()) {

                                            callback.onProviderSignedIn();
                                        } else {

                                            callback.onUserNotSignedIn();
                                        }
                                    }
                                }
                            });
                        }
                    }
                }
            });
        }
    }

    private String getCurrentUserId() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            return user.getUid();
        }
        return null;
    }


}