package com.example.egar.Fragments.BottomNavigationFragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.egar.Activities.ChangePasswordActivity;
import com.example.egar.Activities.ChatActivity;
import com.example.egar.Activities.FavoriteActivity;
import com.example.egar.Activities.Login;
import com.example.egar.FirebaseManger.FirebaseAuthController;
import com.example.egar.FirebaseManger.FirebaseFetchingDataController;
import com.example.egar.R;
import com.example.egar.databinding.FragmentHomeBinding;
import com.example.egar.databinding.FragmentProfileBinding;
import com.example.egar.interfaces.UserDataCallBack;
import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;


public class Profile extends Fragment  implements View.OnClickListener {
    private FragmentProfileBinding binding;



    public Profile() {
        // Required empty public constructor
    }


    @Override
    public void onStart() {
        super.onStart();
        initializeView();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding  = FragmentProfileBinding.inflate(inflater,container,false);

        return binding.getRoot();
    }

    private void initializeView(){
        setOnClick();
        setProfile();
    }

    private void  setOnClick(){
        binding.logout.setOnClickListener(this);
        binding.changepassword.setOnClickListener(this);
        binding.favoriteP.setOnClickListener(this);
        binding.conversations.setOnClickListener(this);


    }

    private void setProfile(){
        //String name = FirebaseAuth.getInstance().getCurrentUser().getDisplayName();
        //String email = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        //String photo= String.valueOf(FirebaseAuth.getInstance().getCurrentUser().getPhotoUrl());

        FirebaseFetchingDataController.getInstance().getCurrentUserData(new UserDataCallBack() {
            @Override
            public void onSuccess(String name, String address, String number, String providerImage, String email) {
                binding.textName.setText(name);
                binding.textEmail.setText(email);
                Picasso.get().load(providerImage).into(binding.imageUser);
            }

            @Override
            public void onFailure(String message) {

            }
        });


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.logout:
                FirebaseAuthController.getInstance().signOut();
                getActivity().finish();
                Intent intent = new Intent(getActivity(), Login.class);
                startActivity(intent);
                break;
            case R.id.favorite_p:
                Intent intent1 = new Intent(getActivity(), FavoriteActivity.class);
                startActivity(intent1);
                break;
            case R.id.changepassword:
                Intent intent3 = new Intent(getActivity(), ChangePasswordActivity.class);
                startActivity(intent3);
                break;
            case R.id.conversations:
                Intent intent4 = new Intent(getActivity(), ChatActivity.class);
                startActivity(intent4);
                break;
        }

    }
}