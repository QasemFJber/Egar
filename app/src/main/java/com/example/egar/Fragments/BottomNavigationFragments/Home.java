package com.example.egar.Fragments.BottomNavigationFragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.egar.Activities.Notifications;
import com.example.egar.Activities.ShowAll_Items;
import com.example.egar.Activities.ShowCategoriesActivity;
import com.example.egar.Models.Category;
import com.example.egar.Models.Product;
import com.example.egar.R;
import com.example.egar.adapters.CategoryAdapter;
import com.example.egar.adapters.StoreAdapter;


import com.example.egar.adapters.productHome.ProductHomeAdapter;
import com.example.egar.controllers.ProductController;
import com.example.egar.databinding.FragmentHomeBinding;
import com.example.egar.interfaces.OnItemClickListener;
import com.example.egar.interfaces.OnProductFetchListener;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class Home extends Fragment  implements OnItemClickListener ,View.OnClickListener {
    private CategoryAdapter categoryAdapter;
    private List<Category> categoryList;
    List<Product> productList;
    private ProductHomeAdapter adapter;

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
        categoryList.add(new Category("cars",R.drawable.img_cars ,R.drawable.ic_car));
        categoryList.add(new Category("Workspaces",R.drawable.img_workspaces ,R.drawable.ic_maintenance));
        categoryList.add(new Category("House",R.drawable.img_home ,R.drawable.ic_house));
        categoryList.add(new Category("Equipment",R.drawable.img_equipment ,R.drawable.ic_maintenance));
        categoryList.add(new Category("Wedding clothes",R.drawable.img_wedding_clothes ,R.drawable.ic_man));
        categoryList.add(new Category("Woman Clothes",R.drawable.img_women ,R.drawable.women));

        return  categoryList;

    }


    private void initializeView(){
        setOnclick();
        getProduct();
        initializeRecyclerAdapter();
    }
    private void initializeRecyclerAdapter(){
        categoryAdapter = new CategoryAdapter(categoryList,this);
        binding.recyclerCategory.setAdapter(categoryAdapter);
        binding.recyclerCategory.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));

        adapter = new ProductHomeAdapter(productList);
        binding.recyclerProduct.setAdapter(adapter);
        binding.recyclerProduct.setLayoutManager(new GridLayoutManager(getActivity(),2));
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
        binding.imgNotifications.setOnClickListener(this::onClick);
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.img_notifications){
            Intent intent=new Intent(getActivity(), Notifications.class);
            startActivity(intent);

        }
        if (v.getId() == R.id.tv_category_show_all){
            Intent intent = new Intent(getActivity(), ShowAll_Items.class);
            intent.putExtra("category","Categories");
            startActivity(intent);

        }if (v.getId() == R.id.tv_offers_show_all2){

        }
    }

    private void getProduct(){
        ProductController.getInstance().getAllProducts(new OnProductFetchListener() {
            @Override
            public void onFetchLListSuccess(ArrayList<Product> list) {
               // Toast.makeText(getActivity(), ""+list, Toast.LENGTH_SHORT).show();
               // Log.d("GET", "onFetchLListSuccess: "+list);
                productList.addAll(list);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFetchSuccess(Product product) {
               // productList.add(product);
               // Log.d("GET", "onFetchSuccess: "+ product);

            }

            @Override
            public void onFetchFailure(String message) {
                Log.d("GET", "onFetchFailure: "+message);

            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}