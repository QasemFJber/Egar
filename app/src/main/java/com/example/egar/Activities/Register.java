package com.example.egar.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.egar.R;
import com.example.egar.databinding.ActivityRegisterBinding;

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

    private boolean dataCheck (){
        String name = binding.etUserName.getText().toString();
        String phone = binding.etPhoneNumber.getText().toString();
        String password = binding.etPassword.getText().toString();

        if (name.isEmpty()) {
            binding.etUserName.setError("UserName field is Required");
            return false;
        } else if (phone.isEmpty()) {
            binding.etPhoneNumber.setError("PhoneNumber field is Required");
            return false;
        } else if (password.isEmpty()) {
            binding.etPhoneNumber.setError("Password field is Required");
            return false;
        }else if(!binding.checked.isChecked()) {
            Toast.makeText(this, "You must agree to the terms and conditions", Toast.LENGTH_SHORT).show();
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
                if (dataCheck()){
                    Intent intent1 = new Intent(getApplicationContext(),Login.class);
                    String phone =binding.etPhoneNumber.getText().toString().trim();
                    String pass = binding.etPassword.getText().toString().trim();
                    intent1.putExtra("phone",phone);
                    intent1.putExtra("password",pass);
                    startActivity(intent1);
                }else {
                    Toast.makeText(this, "The Input Fields Required", Toast.LENGTH_SHORT).show();
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

}