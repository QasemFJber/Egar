package com.example.egar.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.opengl.Visibility;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.egar.Models.Product;
import com.example.egar.R;
import com.example.egar.adapters.product.ProductAdapter;
import com.example.egar.controllers.ProductController;
import com.example.egar.databinding.ActivityShowAllItemsBinding;
import com.example.egar.interfaces.OnProductFetchListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ShowAll_Items extends AppCompatActivity implements View.OnClickListener {
    private ActivityShowAllItemsBinding binding ;
    private ProductAdapter adapter;
    private ArrayList<Product> products = new ArrayList<>();

    String category = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityShowAllItemsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setupRecyclerView();
    }
    private void operationsSccren() {
        setOnClick();
        getCategory();
        getWindow().setStatusBarColor(ContextCompat.getColor(ShowAll_Items.this, R.color.black));
    }

    private void  getCategory(){
        Intent intent = getIntent();
        category =intent.getStringExtra("category");
        binding.tvTitle.setText(category);
    }

    private void setupRecyclerView(){
//        ProductController.getInstance().getAllProductsByProviderType(category, new OnProductFetchListener() {
//            @Override
//            public void onFetchLListSuccess(ArrayList<Product> list) {
//                Toast.makeText(ShowAll_Items.this, "List size is "+list.size(), Toast.LENGTH_SHORT).show();
//                if (list.size() >0){
//                    binding.imageNoData.setVisibility(View.VISIBLE);
//                    Picasso.get().load("file:///C:/Users/jaber/Downloads/No%20data-cuate.svg").into(binding.imageNoData);
//                }else {
//                    products.clear();
//                    products.addAll(list);
//                    adapter.notifyDataSetChanged();
//                }
//
//            }
//
//            @Override
//            public void onFetchSuccess(Product product) {
//
//            }
//
//            @Override
//            public void onFetchFailure(String message) {
//
//            }
//        });

    }
    @Override
    protected void onStart() {
        super.onStart();
        operationsSccren();
        adapter = new ProductAdapter(new ArrayList<>());
        binding.rvItems.setAdapter(adapter);
        binding.rvItems.setLayoutManager(new LinearLayoutManager(this));
    }


    private void setOnClick(){
        binding.btnBack.setOnClickListener(this::onClick);
    }
    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_back:
                onBackPressed();
                break;
        }
    }
}