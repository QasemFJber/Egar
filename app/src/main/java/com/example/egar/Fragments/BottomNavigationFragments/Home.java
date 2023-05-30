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

import com.example.egar.Activities.ShowAll_Items;
import com.example.egar.Models.Category;
import com.example.egar.R;
import com.example.egar.adapters.CategoryAdapter;
import com.example.egar.adapters.StoreAdapter;
import com.example.egar.databinding.FragmentHomeBinding;
import com.example.egar.interfaces.OnItemClickListener;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class Home extends Fragment  implements OnItemClickListener {
    private RecyclerView categoryRecyclerView;
    private CategoryAdapter categoryAdapter;
    private List<Category> categoryList;

    private  FragmentHomeBinding binding;


    public Home() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding  = FragmentHomeBinding.inflate(inflater,container,false);
        addDataToRecyclerView();
        initializeView();
        binding.tvCategoryShowAll.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), ShowAll_Items.class);
            intent.putExtra("category","Categories");
            startActivity(intent);
        });
        return binding.getRoot();
    }
    private List<Category> addDataToRecyclerView(){

        categoryList = new ArrayList<>();
        categoryList.add(new Category("CARS", R.drawable.car));
        categoryList.add(new Category("CO WORKING", R.drawable.coworking));
        categoryList.add(new Category("HOUSE", R.drawable.house));
        categoryList.add(new Category("MAINTENANCE", R.drawable.maintenance));
        categoryList.add(new Category("MAN CLOTHES", R.drawable.man));
        categoryList.add(new Category("WOMAN CLOTHES", R.drawable.women));

        return  categoryList;

    }


    private void initializeView(){
        initializeRecyclerAdapter();
    }
    private void initializeRecyclerAdapter(){
        categoryAdapter = new CategoryAdapter(categoryList,this);
        binding.recyclerCategory.setAdapter(categoryAdapter);
        binding.recyclerCategory.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
    }

    @Override
    public void onItemClick(Category category) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}