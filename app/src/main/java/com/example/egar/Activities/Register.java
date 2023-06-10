package com.example.egar.Activities;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.example.egar.Models.User;
import com.example.egar.R;
import com.example.egar.FirebaseManger.FirebaseAuthController;
import com.example.egar.databinding.ActivityRegisterBinding;
import com.example.egar.interfaces.ProcessCallback;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Register extends AppCompatActivity implements View.OnClickListener {

    ActivityRegisterBinding binding;

    private ActivityResultLauncher<Void> cameraRL;

    private ActivityResultLauncher<String> permissionRL;

    private Uri pickedImageUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        screenOperations();
    }

    private void screenOperations (){
        setOnClick();
        setupActivityResults();

    }
    private boolean isValidPalestinianPhoneNumber() {
        String phoneNumber = binding.etPhoneNumber.getText().toString().trim();
        if (phoneNumber.matches("^(059|056)\\d{7}$")) {
            return true;
        } else {
            Snackbar.make(binding.getRoot(),"Enter The Valid Palestinian Phone Number ",Snackbar.LENGTH_LONG).show();
            return false;
        }
    }

    public boolean isValidEmail() {
        String email = binding.etEmail.getText().toString().trim();
        boolean isValid = false;
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        if (email == null) {
            isValid = false;
        } else {
            Matcher matcher = pattern.matcher(email);
            isValid = matcher.matches();
        }
        if (!isValid) {
            Snackbar.make(binding.getRoot(), "Invalid email address", Snackbar.LENGTH_LONG).setTextColor(ContextCompat.getColor(this,R.color.bronze)).show();

        }
        return isValid;
    }

    private boolean dataCheck (){
        String name = binding.etUserName.getText().toString().trim();
        String email = binding.etEmail.getText().toString().trim();
        String password = binding.etPassword.getText().toString().trim();

        if (name.isEmpty()) {
            binding.etUserName.setError("UserName field is Required");
            return false;
        } else if (email.isEmpty()  ) {
            binding.etEmail.setError("Email field is Required");
            return false;
        } else if (password.isEmpty()) {
            binding.etPhoneNumber.setError("Password field is Required");
            return false;
        }else if(!binding.checked.isChecked()) {
            Snackbar.make(binding.getRoot(),"You must agree to the terms and conditions",Snackbar.LENGTH_LONG).setTextColor(ContextCompat.getColor(this,R.color.bronze)).show();
            return false;
        }

        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();

    }


    private void setOnClick(){
        binding.singin.setOnClickListener(this::onClick);
        binding.btnRegister.setOnClickListener(this::onClick);
        binding.btnBack.setOnClickListener(this::onClick);
        binding.image.setOnClickListener(this::onClick);
        binding.animationView.setOnClickListener(this::onClick);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.singin:
                Intent intent = new Intent(getApplicationContext(),Login.class);
                startActivity(intent);
                break;
            case R.id.btn_register:
                if (dataCheck() && isValidPalestinianPhoneNumber() && isValidEmail()){
                    register();
                    Intent intent1 = new Intent(getApplicationContext(),Login.class);
                    String email =binding.etEmail.getText().toString().trim();
                    String pass = binding.etPassword.getText().toString().trim();
                    intent1.putExtra("email",email);
                    intent1.putExtra("password",pass);
                    startActivity(intent1);
                }else {

                }
                break;
            case R.id.btn_back:
                Intent intent2 = new Intent(getApplicationContext(),Login.class);
                startActivity(intent2);
                overridePendingTransition(R.anim.slid_in, R.anim.slid_out);
                break;

            case R.id.animationView:
                selectImage();
                break;
        }
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

    private void register() {
        String id = FirebaseAuth.getInstance().getUid();
        String name =binding.etUserName.getText().toString();
        String email =binding.etEmail.getText().toString().trim();
        String pass =binding.etPassword.getText().toString().trim();
        String phone =binding.etPhoneNumber.getText().toString().trim();
        User user = new User(id,name,email,pass,phone);
        FirebaseAuthController.getInstance().createAccount(user, pickedImageUri, new ProcessCallback() {
            @Override
            public void onSuccess(String message) {
                Snackbar.make(binding.getRoot(),message,Snackbar.LENGTH_LONG).show();

            }

            @Override
            public void onFailure(String message) {
                Snackbar.make(binding.getRoot(),message,Snackbar.LENGTH_LONG).show();

            }
        });
    }



    private void selectImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 100);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100 && data != null && data.getData() != null) {
            pickedImageUri = data.getData();
            binding.image.setImageURI(pickedImageUri);
            binding.animationView.setVisibility(View.GONE);
            binding.image.setVisibility(View.VISIBLE);
        }
    }

    private void setupActivityResults() {
        permissionRL = registerForActivityResult(new ActivityResultContracts.RequestPermission(), new ActivityResultCallback<Boolean>() {
            @Override
            public void onActivityResult(Boolean result) {
                if (result) {
                    cameraRL.launch(null);

                }
            }
        });
    }

/*    private void pickImage() {
        permissionRL.launch(Manifest.permission.CAMERA);
    }*/

}