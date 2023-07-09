package com.example.egar.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import com.example.egar.Models.Product;
import com.example.egar.Models.Provider;
import com.example.egar.R;
import com.example.egar.adapters.product.ProductAdapter;
import com.example.egar.adapters.productShowProvider.ProductShowProviderAdapter;
import com.example.egar.controllers.ProductController;
import com.example.egar.databinding.ActivityServiceProviderStoreDetailsBinding;
import com.example.egar.interfaces.ItemCallbackProduct;
import com.example.egar.interfaces.OnProductFetchListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Service_Provider_Store_Details extends AppCompatActivity implements ItemCallbackProduct , OnMapReadyCallback {
    ActivityServiceProviderStoreDetailsBinding binding;

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private GoogleMap myGoogleMap;
    private UiSettings uiSettings;


    List<Product> products = new ArrayList<>();
    ProductShowProviderAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityServiceProviderStoreDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapView);
        supportMapFragment.getMapAsync(this::onMapReady);


        initializeView();
    }

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
        adapter.setCallbackProduct(this::onItemClick);
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

    @Override
    public void onItemClick(Product product) {
        Intent intent = new Intent(getApplicationContext(), ShowService_Product_Details .class);
        intent.putExtra("product", (Serializable) product);
        startActivity(intent);

    }



    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
//        31.515573507377145, 34.44048516931093


        this.myGoogleMap = googleMap;
        uiSettings = googleMap.getUiSettings();
        // Customize your map options and add markers or other functionalities here

        // Add zoom controls
        uiSettings.setZoomControlsEnabled(true);

        LatLng gaza = new LatLng(31.515573507377145,34.44048516931093);
        myGoogleMap.addMarker(new MarkerOptions().position(gaza).title("Gaza"));
        myGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(gaza));

        myGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(gaza, 11));

        // Request location permission if not granted
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    LOCATION_PERMISSION_REQUEST_CODE);
        } else {
            // Start location updates
//            startLocationUpdates();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, start location updates
                Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show();
//                startLocationUpdates();
            } else {
                // Permission denied, handle accordingly
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

}