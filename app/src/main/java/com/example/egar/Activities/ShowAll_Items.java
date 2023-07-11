package com.example.egar.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.opengl.Visibility;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.egar.Models.Offer;
import com.example.egar.Models.Product;
import com.example.egar.Models.Provider;
import com.example.egar.R;
import com.example.egar.adapters.ProviderAdapter.ProviderAdapter;
import com.example.egar.adapters.offers.OffersAdapter;
import com.example.egar.adapters.product.ProductAdapter;
import com.example.egar.controllers.OfferController;
import com.example.egar.controllers.ProductController;
import com.example.egar.controllers.ServiceProviderController;
import com.example.egar.databinding.ActivityShowAllItemsBinding;
import com.example.egar.interfaces.ItemCallbackOffer;
import com.example.egar.interfaces.ItemCallbackProvider;
import com.example.egar.interfaces.OnOfferFetchListener;
import com.example.egar.interfaces.OnProductFetchListener;
import com.example.egar.interfaces.OnServiceProviderFetchListener;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class ShowAll_Items extends AppCompatActivity implements View.OnClickListener, ItemCallbackOffer , ItemCallbackProvider {
    private ActivityShowAllItemsBinding binding ;
    private ProductAdapter adapter;

   // private ArrayList<Product> products = new ArrayList<>();

    private List<Provider> providers = new ArrayList<>();

    private List<Offer> offerList = new ArrayList<>();

    private ProviderAdapter providerAdapter;

    private OffersAdapter offersAdapter;

    OfferController offerController =new OfferController();


    String category = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityShowAllItemsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //setupRecyclerView();
       // initializeView();
    }

    private void operationsSccren() {
       // setOnClick();
        //getCategory();
        getWindow().setStatusBarColor(ContextCompat.getColor(ShowAll_Items.this, R.color.black));
    }

    private void initializeView(){
        setOnclick();

        //check
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new TimerTask() {
                    @Override
                    public void run() {
                        check();
                    }
                });
            }
        },100);
    }

    private void check(){
        Intent intent = getIntent();
        //category =intent.getStringExtra("category");
        //binding.tvTitle.setText(category);
        if (Objects.equals(intent.getStringExtra("SHOW_ALL"), "Categories")){
            binding.tvTitle.setText("Categories");

        }else if (Objects.equals(intent.getStringExtra("SHOW_ALL"), "Offers")){
            binding.tvTitle.setText("Offers");
            getOffer();
            Toast.makeText(this, ""+offerList.size(), Toast.LENGTH_SHORT).show();
            offersAdapter =new OffersAdapter(offerList);
            offersAdapter.setCallbackOffer(this::onItemClick);
            binding.rvItems.setAdapter(offersAdapter);
            binding.rvItems.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        }else if (Objects.equals(intent.getStringExtra("SHOW_ALL"), "Services Provider")){
            binding.tvTitle.setText("Services Provider");
            getProvider();
            providerAdapter = new ProviderAdapter(providers);
            providerAdapter.setCallbackProvider(this::onItemClick);
            binding.rvItems.setAdapter(providerAdapter);
            binding.rvItems.setLayoutManager(new LinearLayoutManager(getApplicationContext()));


        }

    }


    private void setOnclick(){
        binding.btnBack.setOnClickListener(this::onClick);

    }

/*    private void  getCategory(){
        Intent intent = getIntent();
        category =intent.getStringExtra("category");
        binding.tvTitle.setText(category);
    }*/

    private void setupRecyclerView(){
        providerAdapter = new ProviderAdapter(providers);
        providerAdapter.setCallbackProvider(this::onItemClick);
        binding.rvItems.setAdapter(providerAdapter);
        binding.rvItems.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        offersAdapter =new OffersAdapter(offerList);
        offersAdapter.setCallbackOffer(this::onItemClick);
        binding.rvItems.setAdapter(offersAdapter);
        binding.rvItems.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

    }

    private void getProvider(){
        ServiceProviderController.getInstance().getAllServiceProviders(new OnServiceProviderFetchListener() {
            @Override
            public void onFetchSuccess(List<Provider> serviceProviders) {
                Toast.makeText(ShowAll_Items.this, ""+serviceProviders.size(), Toast.LENGTH_SHORT).show();
                providers.clear();
                providers.addAll(serviceProviders);
                if (providerAdapter != null){
                    providerAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onFetchFailure(String errorMessage) {

            }
        });
    }


    private void getOffer(){
        offerController.getAllOffers(new OnOfferFetchListener() {
            @Override
            public void onListFetchSuccess(List<Offer> offersList) {
                Toast.makeText(ShowAll_Items.this, ""+offersList.size(), Toast.LENGTH_SHORT).show();
                offerList.clear();
                offerList.addAll(offersList);
                if (offersAdapter != null) {
                    offersAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onListFetchFailure(String message) {
                Snackbar.make(binding.getRoot(),message,Snackbar.LENGTH_LONG).show();
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        operationsSccren();
        initializeView();

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

    @Override
    public void onItemClick(Offer offer) {
        Intent intent = new Intent(getApplicationContext(), ShowService_Offer_Details.class);
        intent.putExtra("offer", (Serializable) offer);
        startActivity(intent);
    }

    @Override
    public void onItemClick(Provider provider) {
        Intent intent = new Intent(getApplicationContext(), Service_Provider_Store_Details.class);
        intent.putExtra("provider", (Serializable) provider);
        startActivity(intent);

    }
}