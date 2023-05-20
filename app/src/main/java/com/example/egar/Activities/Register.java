package com.example.egar.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.egar.R;
import com.example.egar.FirebaseManger.FirebaseAuthController;
import com.example.egar.databinding.ActivityRegisterBinding;
import com.example.egar.interfaces.ProcessCallback;
import com.google.android.material.snackbar.Snackbar;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Register extends AppCompatActivity implements View.OnClickListener {

    ActivityRegisterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        screenOperations();
    }

    private void screenOperations (){
        setOnClick();

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
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    private void setOnClick(){
        binding.singin.setOnClickListener(this::onClick);
        binding.btnRegister.setOnClickListener(this::onClick);
        binding.btnBack.setOnClickListener(this::onClick);
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
        FirebaseAuthController.getInstance().createAccount(binding.etUserName.getText().toString(),
                binding.etEmail.getText().toString().trim(),
                binding.etPassword.getText().toString().trim(),
                binding.etPhoneNumber.getText().toString().trim(),
                new ProcessCallback() {
                    @Override
                    public void onSuccess(String message) {
                        Snackbar.make(binding.getRoot(),message,Snackbar.LENGTH_LONG).show();
                        onBackPressed();
                    }

                    @Override
                    public void onFailure(String message) {
                        Snackbar.make(binding.getRoot(),message,Snackbar.LENGTH_LONG).show();
                    }
                });
    }

}