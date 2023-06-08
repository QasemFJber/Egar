package com.example.egar.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.egar.R;
import com.example.egar.FirebaseManger.FirebaseAuthController;
import com.example.egar.databinding.ActivityResetPasswordBinding;
import com.example.egar.interfaces.ProcessCallback;
import com.google.android.material.snackbar.Snackbar;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ResetPassword extends AppCompatActivity implements View.OnClickListener {
    ActivityResetPasswordBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityResetPasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }
    private void operationsScreen() {
        setOnClick();
        getWindow().setStatusBarColor(ContextCompat.getColor(ResetPassword.this,R.color.black));
    }

    @Override
    protected void onStart() {
        super.onStart();
        operationsScreen();
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
    @Override
    public void onBackPressed() {
//        // Create an exit dialog
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle("Exit");
//        builder.setMessage("Are you sure you want to exit?");
//        builder.setIcon(R.drawable.baseline_exit_to_app_24);
//        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                // Close the application
//                finish();
//            }
//        });
//        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                // Dismiss the dialog and continue with the application
//                dialog.dismiss();
//            }
//        });
//        // Create the dialog and show it
//        AlertDialog dialog = builder.create();
//        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
//            @Override
//            public void onDismiss(DialogInterface dialog) {
//                // Do nothing
//            }
//        });
//        dialog.show();
    }

    private void setOnClick(){
        binding.btnBack.setOnClickListener(this);
        binding.btnSend.setOnClickListener(this);
    }
    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.btn_back:
                onBackPressed();
                break;
            case R.id.btn_send:
                if (isValidEmail()){
                    forgotPassword();
                }
                break;
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

    private void forgotPassword() {
        FirebaseAuthController.getInstance().forgetPassword(binding.etEmail.getText().toString(),
                new ProcessCallback() {
                    @Override
                    public void onSuccess(String message) {
                        Toast.makeText(ResetPassword.this, message, Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onFailure(String message) {
                        Toast.makeText(ResetPassword.this, message, Toast.LENGTH_SHORT).show();
                    }
                });
    }

}