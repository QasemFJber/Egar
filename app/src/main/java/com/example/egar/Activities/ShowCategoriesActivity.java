package com.example.egar.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.egar.Models.Product;
import com.example.egar.R;
import com.example.egar.adapters.product.ProductAdapter;
import com.example.egar.controllers.ProductController;
import com.example.egar.databinding.ActivityShowCategoriesBinding;
import com.example.egar.interfaces.OnProductFetchListener;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class ShowCategoriesActivity extends AppCompatActivity implements View.OnClickListener {
    ActivityShowCategoriesBinding binding;

    private final ArrayList<Product> products = new ArrayList<>();
    private ProductAdapter adapter;
    String category_name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityShowCategoriesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initializeView();
        Toast.makeText(this, getCategory(), Toast.LENGTH_SHORT).show();
    }

    private void initializeView() {
        initializeRecyclerAdapter();
        getAllProducts();
        setOnclick();
    }

    private String getCategory(){
        Intent intent = getIntent();
        category_name= intent.getStringExtra("category_name");
        binding.tvTitleCategory.setText(category_name);
        return category_name;
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
        ProductController.getInstance().getAllProductsByCategory(getCategory(), new OnProductFetchListener() {
            @Override
            public void onFetchLListSuccess(ArrayList<Product> list) {
                Toast.makeText(ShowCategoriesActivity.this, "list size is "+list.size(), Toast.LENGTH_SHORT).show();
                products.clear();
                products.addAll(list);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFetchSuccess(Product product) {
                Toast.makeText(ShowCategoriesActivity.this, product.getCategory(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFetchFailure(String message) {
                Snackbar.make(binding.getRoot(),message,Snackbar.LENGTH_LONG).show();

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