package com.example.egar.Fragments.BottomNavigationFragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.egar.Activities.Login;
import com.example.egar.FirebaseManger.FirebaseAuthController;
import com.example.egar.R;
import com.example.egar.databinding.FragmentHomeBinding;
import com.example.egar.databinding.FragmentProfileBinding;


public class Profile extends Fragment  implements View.OnClickListener {
    private FragmentProfileBinding binding;



    public Profile() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding  = FragmentProfileBinding.inflate(inflater,container,false);
        setOnClick();
        return binding.getRoot();
    }

    private void  setOnClick(){
        binding.btnUpdate.setOnClickListener(this::onClick);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_update:
                binding.tvInformation.setEnabled(true);
                binding.tvConversations.setEnabled(true);
                binding.tvLanguage.setEnabled(true);
                break;

            case R.id.logout:
              /*  FirebaseAuthController.getInstance().signOut();
                Intent intent = new Intent(getActivity(), Login.class);
                startActivity(intent);*/

                break;
        }

    }
}