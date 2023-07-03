package com.example.egar.Fragments.BottomNavigationFragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.egar.Activities.ShowCategoriesActivity;
import com.example.egar.Models.Category;
import com.example.egar.Models.Product;
import com.example.egar.R;
import com.example.egar.adapters.CategoryAdapter;
import com.example.egar.adapters.StoreAdapter;
import com.example.egar.adapters.productHome.ProductHomeAdapter;
import com.example.egar.databinding.FragmentCategoriesBinding;
import com.example.egar.interfaces.OnItemClickListener;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class Categories extends Fragment implements OnItemClickListener {
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

/*        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Stores"));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("cars"));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Workspaces"));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Homes ,Apartments"));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("equipment"));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Wedding clothes"));

        binding.tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);*/

        //intiRecyclerViewStore();
        addDataToRecyclerView();
        initializeView();
        return binding.getRoot();
    }

    private void initializeView() {
        initializeRecyclerAdapter();

    }

    private List<Category> addDataToRecyclerView(){
        categoryList = new ArrayList<>();
        categoryList.add(new Category("cars",R.drawable.img_cars ,R.drawable.ic_car));
        categoryList.add(new Category("Workspaces",R.drawable.img_workspaces ,R.drawable.ic_maintenance));
        categoryList.add(new Category("House",R.drawable.img_home ,R.drawable.ic_house));
        categoryList.add(new Category("Equipment",R.drawable.img_equipment ,R.drawable.ic_maintenance));
        categoryList.add(new Category("Wedding clothes",R.drawable.img_wedding_clothes ,R.drawable.ic_man));
        categoryList.add(new Category("Woman Clothes",R.drawable.img_women ,R.drawable.women));

        return  categoryList;

    }


    private void initializeRecyclerAdapter(){

        categoryAdapter = new CategoryAdapter(categoryList,this);
        binding.recCategory.setAdapter(categoryAdapter);
        //binding.recCategory.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        binding.recCategory.setLayoutManager(new GridLayoutManager(getActivity(),2));


    }


    @Override
    public void onItemClick(Category category) {
        Intent intent = new Intent(getActivity(), ShowCategoriesActivity.class);
        intent.putExtra("category_name",category.getName());
        startActivity(intent);
    }
}