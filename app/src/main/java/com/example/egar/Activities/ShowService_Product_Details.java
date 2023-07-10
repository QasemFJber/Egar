package com.example.egar.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.egar.Models.Offer;
import com.example.egar.Models.Product;
import com.example.egar.R;
import com.example.egar.adapters.product.ProductAdapter;
import com.example.egar.adapters.productHome.ProductHomeAdapter;
import com.example.egar.controllers.ProductController;
import com.example.egar.databinding.ActivityShowServiceProductDetailsBinding;
import com.example.egar.interfaces.ItemCallbackProduct;
import com.example.egar.interfaces.ProductCallback;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ShowService_Product_Details extends AppCompatActivity implements View.OnClickListener, ItemCallbackProduct {

    ActivityShowServiceProductDetailsBinding binding;

    List<Product> products = new ArrayList<>();
    private ProductHomeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityShowServiceProductDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initializeView();
    }
    private void operationsSccren() {
        getWindow().setStatusBarColor(ContextCompat.getColor(ShowService_Product_Details.this, R.color.black));
    }

    @Override
    protected void onStart() {
        super.onStart();
       // operationsSccren();
    }

    private void initializeView() {
        initializeRecyclerAdapter();
        getDetails();
        getAllProducts();
        setOnClick();
    }

    private Product product(){
        Product product = (Product) getIntent().getSerializableExtra("product");
        return product;
    }

    private  void setOnClick() {
        binding.buttonToSet.setOnClickListener(this::onClick);
    }


    private void initializeRecyclerAdapter() {
        adapter = new ProductHomeAdapter(products);
        adapter.setCallback(this::onItemClick);
        binding.recyclerMostRelevant.setAdapter(adapter);
        binding.recyclerMostRelevant.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.HORIZONTAL,false));
    }

    private void getAllProducts(){
        ProductController.getInstance().getProductsByCategory(product().getCategory(), new ProductCallback() {
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

            @Override
            public void onProductFetchSuccess(Product product) {

            }
        });
    }



    private void getDetails(){
        binding.tvTextProductName.setText(product().getName());
        binding.tvTextDescription.setText(product().getDescription());
        binding.tvPrice.setText(String.valueOf(product().getPrice()));
        binding.tvTextproviderName.setText(product().getProvider().getName());
        Picasso.get().load(product().getImageUrl()).into(binding.productImg);
        Picasso.get().load(product().getProvider().getImage()).into(binding.imageProviderImg);
        if (product().isFavorite()){
            binding.imgFavorite.setImageResource(R.drawable.baseline_favorite_24);

        }else if(!product().isFavorite()){
            binding.imgFavorite.setImageResource(R.drawable.baseline_favorite);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button_toSet){
            Intent intent =new Intent(getApplicationContext(), DetermineRentStandardsActivity.class);
            intent.putExtra("id_product",product().getId());
            startActivity(intent);
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