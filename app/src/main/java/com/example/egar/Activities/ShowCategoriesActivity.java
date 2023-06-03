package com.example.egar.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.egar.Models.Product;
import com.example.egar.R;
import com.example.egar.adapters.product.ProductAdapter;
import com.example.egar.controllers.ProductController;
import com.example.egar.databinding.ActivityShowCategoriesBinding;
import com.example.egar.interfaces.OnProductFetchListener;

import java.util.ArrayList;

public class ShowCategoriesActivity extends AppCompatActivity implements View.OnClickListener {
    ActivityShowCategoriesBinding binding;

    private ArrayList<Product> products = new ArrayList<>();
    private ProductAdapter adapter;
    String category_name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityShowCategoriesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        category_name= intent.getStringExtra("category_name");


        binding.tvTitleCategory.setText(category_name);
        initializeView();
    }

    private void initializeView() {
        initializeRecyclerAdapter();
        getAllProducts();
        setOnclick();
    }

    private void initializeRecyclerAdapter() {
        adapter = new ProductAdapter(products);
        //adapter.setCallback(this);
        binding.recItemsCategory.setAdapter(adapter);
        binding.recItemsCategory.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }

    private void setOnclick(){
        binding.btnBackCategory.setOnClickListener(this::onClick);
    }

    private void getAllProducts(){
        ProductController.getInstance().getAllProducts(category_name, new OnProductFetchListener() {
            @Override
            public void onFetchLListSuccess(ArrayList<Product> list) {
                products.clear();
                products.addAll(list);
                Log.d("EGAR", "onFetchLListSuccess: ");
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFetchSuccess(Product product) {

            }

            @Override
            public void onFetchFailure(String message) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_back_category){
           onBackPressed();
           finish();
        }
    }
}