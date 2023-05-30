package com.example.egar.Fragments.BottomNavigationFragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.egar.Models.Category;
import com.example.egar.R;
import com.example.egar.adapters.CategoryAdapter;
import com.example.egar.adapters.StoreAdapter;
import com.example.egar.databinding.FragmentCategoriesBinding;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class Categories extends Fragment {
    private RecyclerView categoryRecyclerView;
    private CategoryAdapter categoryAdapter;
    private List<Category> categoryList;


    private FragmentCategoriesBinding binding;


    public Categories() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentCategoriesBinding.inflate(inflater,container,false);

        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Stores"));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("cars"));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Workspaces"));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Homes ,Apartments"));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("equipment"));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Wedding clothes"));

        binding.tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        //intiRecyclerViewStore();
        return binding.getRoot();
    }
/*
    private void intiRecyclerViewStore(){
        GridLayoutManager manager = new GridLayoutManager(getActivity(),3);
//        StoreAdapter adapter = new StoreAdapter(getActivity(),"Qasem Brand", R.drawable.coworking);
        setupCategoryRecyclerView(getActivity(), binding.rvCategory, addDataToRecyclerView(), new CategoryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Category category) {

            }
        });


    }
    private List<Category> addDataToRecyclerView(){

        categoryList = new ArrayList<>();
        categoryList.add(new Category("Category 1", R.drawable.car));
        categoryList.add(new Category("Category 2", R.drawable.coworking));
        categoryList.add(new Category("Category 3", R.drawable.house));
        categoryList.add(new Category("Category 4", R.drawable.maintenance));
        categoryList.add(new Category("Category 5", R.drawable.man));
        categoryList.add(new Category("Category 5", R.drawable.women));

        return  categoryList;

    }
    private CategoryAdapter setupCategoryRecyclerView(Context context, RecyclerView recyclerView, List<Category> categories, CategoryAdapter.OnItemClickListener listener) {
        // Set the layout manager for the RecyclerView
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);

        // Initialize the adapter
        CategoryAdapter adapter = new CategoryAdapter(categories, listener);

        // Set the adapter for the RecyclerView
        recyclerView.setAdapter(adapter);

        return adapter;
    }
*/


}