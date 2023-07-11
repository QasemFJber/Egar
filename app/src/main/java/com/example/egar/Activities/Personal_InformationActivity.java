package com.example.egar.Activities;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.example.egar.FirebaseManger.FirebaseAuthController;
import com.example.egar.FirebaseManger.FirebaseFetchingDataController;
import com.example.egar.Models.User;
import com.example.egar.R;
import com.example.egar.databinding.ActivityPersonalInformationBinding;
import com.example.egar.interfaces.ProcessCallback;
import com.example.egar.interfaces.UserDataCallBack;
import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;

public class Personal_InformationActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityPersonalInformationBinding binding;

    private ActivityResultLauncher<String> permissionRL;
    private ActivityResultLauncher<Void> cameraRL;



    private Uri pickedImageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPersonalInformationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    @Override
    protected void onStart() {
        super.onStart();
        initializeView();
    }

    private void initializeView(){
        setOnClick();
        setProfile();
    }

    private void  setOnClick(){
        binding.btnBack.setOnClickListener(this::onClick);
        binding.btnModify.setOnClickListener(this::onClick);
        binding.imagevEdit.setOnClickListener(this::onClick);

    }

    private void setProfile(){
        FirebaseFetchingDataController.getInstance().getCurrentUserData(new UserDataCallBack() {
            @Override
            public void onSuccess(String name, String address, String number, String providerImage, String email) {
                binding.etUserName.setText(name);
                binding.etEmail.setText(email);
                binding.etPhoneNumber.setText(number);
                Picasso.get().load(providerImage).into(binding.imageProvider);
            }

            @Override
            public void onFailure(String message) {

            }
        });


    }

    private void update(){
//    public User(String name, String email, String phoneNumber, String profileImageUri) {
        String name = binding.etUserName.getText().toString();
        String email = binding.etEmail.getText().toString();
        String phoneNumber = binding.etPhoneNumber.getText().toString();

        User user = new User(name,email,phoneNumber,String.valueOf(pickedImageUri));


            FirebaseAuthController.getInstance().updateUserData(FirebaseAuth.getInstance().getUid(), user, new ProcessCallback() {
                @Override
                public void onSuccess(String message) {
                    onBackPressed();
                }

                @Override
                public void onFailure(String message) {

                }
            });

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_back){
            onBackPressed();

        }
        if (v.getId() == R.id.btn_Modify){
            update();
        }if (v.getId() == R.id.imagev_edit){
            selectImage();
        }

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
            binding.imageProvider.setImageURI(pickedImageUri);
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
}