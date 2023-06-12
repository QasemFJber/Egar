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
import com.example.egar.interfaces.ItemCallbackProduct;
import com.example.egar.interfaces.OnProductFetchListener;
import com.example.egar.interfaces.ProductCallback;
import com.google.android.material.snackbar.Snackbar;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ShowCategoriesActivity extends AppCompatActivity implements View.OnClickListener , ItemCallbackProduct {
    ActivityShowCategoriesBinding binding;

    List<Product> products = new ArrayList<>();
    private ProductAdapter adapter;
    String category_name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityShowCategoriesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initializeView();
       // Toast.makeText(this, getCategory(), Toast.LENGTH_SHORT).show();
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
        adapter.setCallbackProduct(this::onItemClick);
        binding.recItemsCategory.setAdapter(adapter);
        binding.recItemsCategory.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }

    private void setOnclick(){
        binding.btnBackCategory.setOnClickListener(this::onClick);
    }

    private void getAllProducts(){
        ProductController.getInstance().getProductsByCategory(getCategory(), new ProductCallback() {
            @Override
            public void onSuccess(List<Product> productList) {
               // Toast.makeText(ShowCategoriesActivity.this, "list size is "+productList.size(), Toast.LENGTH_SHORT).show();
                products.clear();
                products.addAll(productList);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(String message) {

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

    @Override
    public void onItemClick(Product product) {
        Intent intent = new Intent(getApplicationContext(), ShowService_Product_Details.class);
        intent.putExtra("product", (Serializable) product);
        startActivity(intent);

    }
}