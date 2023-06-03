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
import com.example.egar.Activities.ShowCategoriesActivity;
import com.example.egar.Models.Category;
import com.example.egar.R;
import com.example.egar.adapters.CategoryAdapter;
import com.example.egar.adapters.StoreAdapter;

import com.example.egar.databinding.FragmentHomeBinding;
import com.example.egar.interfaces.OnItemClickListener;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class Home extends Fragment  implements OnItemClickListener ,View.OnClickListener {
    private CategoryAdapter categoryAdapter;
    private List<Category> categoryList;

    private FragmentHomeBinding binding;


    public Home() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding  = FragmentHomeBinding.inflate(inflater,container,false);
        addDataToRecyclerView();
        initializeView();

        return binding.getRoot();
    }
    private List<Category> addDataToRecyclerView(){

        categoryList = new ArrayList<>();
        categoryList.add(new Category("Cars",R.drawable.img_cars ,R.drawable.car));
        categoryList.add(new Category("Workspaces",R.drawable.img_workspaces ,R.drawable.coworking));
        categoryList.add(new Category("House",R.drawable.img_home ,R.drawable.house));
        categoryList.add(new Category("Equipment",R.drawable.img_equipment ,R.drawable.maintenance));
        categoryList.add(new Category("Wedding clothes",R.drawable.img_wedding_clothes ,R.drawable.man));
        //categoryList.add(new Category("WOMAN CLOTHES", ,R.drawable.women));

        return  categoryList;

    }


    private void initializeView(){
        setOnclick();
        initializeRecyclerAdapter();
    }
    private void initializeRecyclerAdapter(){
        categoryAdapter = new CategoryAdapter(categoryList,this);
        binding.recyclerCategory.setAdapter(categoryAdapter);
        binding.recyclerCategory.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
    }

    @Override
    public void onItemClick(Category category) {
        Intent intent = new Intent(getActivity(), ShowCategoriesActivity.class);
        intent.putExtra("category_name",category.getName());
        startActivity(intent);

    }
    private void setOnclick(){
        binding.tvCategoryShowAll.setOnClickListener(this::onClick);
        binding.tvOffersShowAll2.setOnClickListener(this::onClick);
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tv_category_show_all){
            Intent intent = new Intent(getActivity(), ShowAll_Items.class);
            intent.putExtra("category","Categories");
            startActivity(intent);

        }if (v.getId() == R.id.tv_offers_show_all2){

        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}