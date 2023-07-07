package com.example.egar.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.egar.Dialog.MyDialogFragment;
import com.example.egar.Models.Product;
import com.example.egar.R;
import com.example.egar.adapters.productHome.ProductAdapter;
import com.example.egar.databinding.ActivitySearchBinding;
import com.example.egar.interfaces.OnProductFetchListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {
    ActivitySearchBinding binding;

    MyDialogFragment fragment;
    private ArrayList<Product> products = new ArrayList<>();
    private ProductAdapter productAdapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySearchBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        onSearchTextSub();
        setupRecyclerView();

        binding.imgSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment=MyDialogFragment.newInstance("");
                fragment.show(getSupportFragmentManager(),null);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    private void setupRecyclerView(){
        productAdapter = new ProductAdapter(products);
        binding.rvSearch.setAdapter(productAdapter);
        binding.rvSearch.setLayoutManager(new GridLayoutManager(this,2));
    }
    private void searchProductInDatabase(String searchText, OnProductFetchListener listener) {
        // Assuming you are using Firebase Firestore
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // Perform the search query
        db.collection("products")
                .whereGreaterThanOrEqualTo("name", searchText)
                .get()
                .addOnSuccessListener(querySnapshot -> {
                    List<Product> productList = new ArrayList<>();

                    // Handle the search results
                    // You can update the UI or do further processing here
                    for (DocumentSnapshot document : querySnapshot.getDocuments()) {
                        // Retrieve the product data and create a Product object
                        String productName = document.getString("name");
                        String productCategory = document.getString("category");
                        String productDescription = document.getString("description");
                        double productPrice = document.getDouble("price");
                        String productImage = document.getString("imageUrl");

                        Product product = new Product(productName, productCategory, productDescription, productPrice, productImage);

                        productList.add(product);
                    }

                    // Notify the listener with the resulting product list
                    listener.onFetchLListSuccess((ArrayList<Product>) productList);
                })
                .addOnFailureListener(e -> {
                    // Notify the listener with the failure
                    listener.onFetchFailure(e.getMessage());
                });
    }
    private void onSearchTextSub() {
        binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(SearchActivity.this, query, Toast.LENGTH_SHORT).show();
                // Perform the search operation in the database
                searchProductInDatabase(query, new OnProductFetchListener() {
                    @Override
                    public void onFetchLListSuccess(ArrayList<Product> productList) {
                        productAdapter = new ProductAdapter(productList);
                        binding.rvSearch.setAdapter(productAdapter);
                        productAdapter.notifyDataSetChanged();

                    }

                    @Override
                    public void onFetchSuccess(Product product) {
                        Snackbar.make(binding.getRoot(),product.getCategory(),Snackbar.LENGTH_LONG).show();

                        // Handle individual product retrieval if needed
                    }

                    @Override
                    public void onFetchFailure(String message) {
                        // Handle search failure if needed
                        Snackbar.make(binding.getRoot(),message,Snackbar.LENGTH_LONG).show();
                    }
                });

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchProductInDatabase(newText, new OnProductFetchListener() {
                    @Override
                    public void onFetchLListSuccess(ArrayList<Product> productList) {
                        productAdapter = new ProductAdapter(productList);
                        binding.rvSearch.setAdapter(productAdapter);
                        productAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFetchSuccess(Product product) {
                        // Handle individual product retrieval if needed
                    }

                    @Override
                    public void onFetchFailure(String message) {
                        Snackbar.make(binding.getRoot(),message,Snackbar.LENGTH_LONG).show();

                        // Handle search failure if needed
                    }
                });

                return true;
            }
        });
    }

}