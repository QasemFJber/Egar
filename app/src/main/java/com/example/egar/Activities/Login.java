package com.example.egar.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.egar.BroadcastReceivers.NetworkChangeListiners;
import com.example.egar.R;
import com.example.egar.databinding.ActivityLoginBinding;

public class Login extends AppCompatActivity implements View.OnClickListener {

    NetworkChangeListiners networkChangeListiners = new NetworkChangeListiners();
    ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        screenOperations();

    }

    private void screenOperations (){
        setOnClick();
       setDataInInputFieldFromRegister();

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
        String phone = intent.getStringExtra("phone");
        String password =intent.getStringExtra("password");
        if (password == null && phone == null){
            return false;
        }else {
            binding.etPhoneNumber.setText(intent.getStringExtra("phone"));
            binding.etPassword.setText(intent.getStringExtra("password"));
            return true;
        }
    }
    private boolean dataCheck (){
        String phone = binding.etPhoneNumber.getText().toString();
        String password = binding.etPassword.getText().toString();
         if (phone.isEmpty()) {
            binding.etPhoneNumber.setError("PhoneNumber field is Required");
            return false;
        } else if (password.isEmpty()) {
            binding.etPhoneNumber.setError("Password field is Required");
            return false;
        }
         return true;
    }
    private  void setOnClick(){
        binding.btnLogin.setOnClickListener(this::onClick);
        binding.createAccount.setOnClickListener(this::onClick);
        binding.btnBack.setOnClickListener(this::onClick);
        binding.forgotPassword.setOnClickListener(this::onClick);
        binding.btnVisitor.setOnClickListener(this::onClick);
    }
    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_login:
                if (dataCheck()){
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(this, "The Input Fields Required", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.create_account:
                Intent intent1 = new Intent(getApplicationContext(),Register.class);
                startActivity(intent1);
                break;
                case R.id.btn_back:
                Intent intent2 = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent2);
                break;
            case R.id.forgotPassword:
                Intent intent3 = new Intent(getApplicationContext(),ResetPassword.class);
                startActivity(intent3);
                break;
            case R.id.btn_visitor:
                Intent visitorIntent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(visitorIntent);
                break;

        }
    }
}