package com.example.egar.Fragments.BottomNavigationFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.egar.R;
import com.example.egar.adapters.MyFragmentTapAdapter;
import com.example.egar.databinding.FragmentRentalsBinding;
import com.google.android.material.tabs.TabLayout;


public class RentalsFragment extends Fragment {

    FragmentRentalsBinding binding;

    MyFragmentTapAdapter adapter;

    public RentalsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentRentalsBinding.inflate(inflater);
        // Inflate the layout for this fragment

        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Pending"));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Progress"));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Completed"));


         FragmentManager fragmentManager = getParentFragmentManager();
        adapter = new MyFragmentTapAdapter(fragmentManager , getLifecycle());

        //adapter = new MyFragmentTapAdapter(getParentFragment());
        binding.pagerRentals.setAdapter(adapter);

        binding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                binding.pagerRentals.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        binding.pagerRentals.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                binding.tabLayout.selectTab(binding.tabLayout.getTabAt(position));
            }
        });




        return binding.getRoot();
    }
}