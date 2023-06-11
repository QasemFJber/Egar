package com.example.egar.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;

import com.example.egar.Models.Product;
import com.example.egar.Models.Provider;
import com.example.egar.R;
import com.example.egar.adapters.product.ProductAdapter;
import com.example.egar.adapters.productShowProvider.ProductShowProviderAdapter;
import com.example.egar.controllers.ProductController;
import com.example.egar.databinding.ActivityServiceProviderStoreDetailsBinding;
import com.example.egar.interfaces.OnProductFetchListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Service_Provider_Store_Details extends AppCompatActivity {
    ActivityServiceProviderStoreDetailsBinding binding;

    List<Product> products = new ArrayList<>();
    ProductShowProviderAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityServiceProviderStoreDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initializeView();
    }
/*    @Override
    public void onBackPressed() {
       // onBackPressed();
        // Create an exit dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Exit");
        builder.setMessage("Are you sure you want to exit?");
        builder.setIcon(R.drawable.baseline_exit_to_app_24);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Close the application
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Dismiss the dialog and continue with the application
                dialog.dismiss();
            }
        });
        // Create the dialog and show it
        AlertDialog dialog = builder.create();
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                // Do nothing
            }
        });
        dialog.show();
    }*/

    @Override
    protected void onStart() {
        super.onStart();

    }
    private void initializeView() {
        initializeRecyclerAdapter();
        getDetails();
        getProduct();
    }
    private void initializeRecyclerAdapter() {
        adapter = new ProductShowProviderAdapter(products);
        //adapter.setCallback(this);
        binding.recyclerViewProduct.setAdapter(adapter);
        binding.recyclerViewProduct.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }
    private Provider getProvider(){
        Provider provider = (Provider) getIntent().getSerializableExtra("provider");

        return provider;
    }

    private void getDetails(){
       // Provider provider = (Provider) getIntent().getSerializableExtra("provider");
       // getProvider();
        binding.tvNameAdmin.setText(getProvider().getName());
        binding.tvLoction.setText(getProvider().getAddress());
        Picasso.get().load(getProvider().getImage()).into(binding.imgProvider);
    }

    private void getProduct(){
        ProductController.getInstance().getAllProductsByProviderId(getProvider().getId(), new OnProductFetchListener() {
            @Override
            public void onFetchLListSuccess(ArrayList<Product> list) {
                products.clear();
                products.addAll(list);
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
}