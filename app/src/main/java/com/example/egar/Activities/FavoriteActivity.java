package com.example.egar.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.widget.Toast;

import com.example.egar.Models.Product;
import com.example.egar.Models.ProductFavorite;
import com.example.egar.R;
import com.example.egar.adapters.fP.ProductfAdapter;
import com.example.egar.adapters.product.ProductAdapter;
import com.example.egar.controllers.ProductFavoriteController;
import com.example.egar.databinding.ActivityFavoriteBinding;
import com.example.egar.interfaces.OnProductFavoritesFetchListener;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class FavoriteActivity extends AppCompatActivity {

    ActivityFavoriteBinding binding;

    List<ProductFavorite> products = new ArrayList<>();
    private ProductfAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFavoriteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //getFavorite();
        initializeView();
    }

    private void initializeView() {
        initializeRecyclerAdapter();
        getFavorite();
        setOnclick();
    }

    private void initializeRecyclerAdapter() {
        adapter = new ProductfAdapter(products);
        //adapter.setCallbackProduct(this::onItemClick);
        binding.recFavorite.setAdapter(adapter);
        binding.recFavorite.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }

    private void setOnclick() {
    }

        private void getFavorite(){

        ProductFavoriteController controller = new ProductFavoriteController();

        controller.getAllProductFavorites(FirebaseAuth.getInstance().getUid(), new OnProductFavoritesFetchListener() {
            @Override
            public void onProductFavoritesFetched(List<ProductFavorite> productFavorites) {
                Toast.makeText(FavoriteActivity.this, ""+productFavorites.size(), Toast.LENGTH_SHORT).show();
               products.clear();
               products.addAll(productFavorites);
               adapter.notifyDataSetChanged();
            }

            @Override
            public void onProductFavoritesFetchFailure(String message) {

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void setUpRecycler(){

    }
}