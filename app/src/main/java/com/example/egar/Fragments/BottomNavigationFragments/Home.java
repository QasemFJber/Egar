package com.example.egar.Fragments.BottomNavigationFragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.egar.Activities.Notifications;
import com.example.egar.Activities.ShowAll_Items;
import com.example.egar.Activities.ShowCategoriesActivity;
import com.example.egar.Models.Category;
import com.example.egar.Models.Product;
import com.example.egar.Models.Provider;
import com.example.egar.R;
import com.example.egar.adapters.CategoryAdapter;
import com.example.egar.adapters.ProviderAdapter.ProviderAdapter;
import com.example.egar.adapters.StoreAdapter;


import com.example.egar.adapters.productHome.ProductHomeAdapter;
import com.example.egar.controllers.CategoryController;
import com.example.egar.controllers.ProductController;

import com.example.egar.controllers.ServiceProviderController;
import com.example.egar.databinding.FragmentHomeBinding;
import com.example.egar.interfaces.OnItemClickListener;
import com.example.egar.interfaces.OnProductFetchListener;
import com.example.egar.interfaces.OnServiceProviderFetchListener;
import com.example.egar.interfaces.ProcessCallback;
import com.example.egar.interfaces.ProductCallback;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class Home extends Fragment  implements OnItemClickListener ,View.OnClickListener {
    private FragmentHomeBinding binding;
    private List<Category> categoryList ;
    private final ArrayList<Product> products = new ArrayList<>();
    private List<Provider> providers = new ArrayList<>();
    private CategoryAdapter categoryAdapter;
    private ProductHomeAdapter productHomeAdapter;
    private ProviderAdapter providerAdapter;



    public Home() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding  = FragmentHomeBinding.inflate(inflater,container,false);
        addDataToRecyclerView();
        initializeView();

        return binding.getRoot();
    }
    private List<Category> addDataToRecyclerView(){
        categoryList = new ArrayList<>();
        categoryList.add(new Category("cars",R.drawable.img_cars ,R.drawable.ic_car));
        categoryList.add(new Category("Workspaces",R.drawable.img_workspaces ,R.drawable.ic_maintenance));
        categoryList.add(new Category("House",R.drawable.img_home ,R.drawable.ic_house));
        categoryList.add(new Category("Equipment",R.drawable.img_equipment ,R.drawable.ic_maintenance));
        categoryList.add(new Category("Wedding clothes",R.drawable.img_wedding_clothes ,R.drawable.ic_man));
        categoryList.add(new Category("Woman Clothes",R.drawable.img_women ,R.drawable.women));

        return  categoryList;

    }


    private void initializeView(){
        setOnclick();
        getProduct();
        getProvider();
        initializeRecyclerAdapter();
    }
    private void initializeRecyclerAdapter(){
        categoryAdapter = new CategoryAdapter(categoryList,this);
        binding.recyclerCategory.setAdapter(categoryAdapter);
        binding.recyclerCategory.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));

        productHomeAdapter = new ProductHomeAdapter(products);
        binding.recyclerProduct.setAdapter(productHomeAdapter);
        binding.recyclerProduct.setLayoutManager(new GridLayoutManager(getActivity(),2));
       // binding.recyclerProduct.setLayoutManager(new LinearLayoutManager(getActivity()));

        providerAdapter = new ProviderAdapter(providers);
        binding.recyclerTopRatedStores.setAdapter(providerAdapter);
        binding.recyclerTopRatedStores.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));


    }

    @Override
    public void onItemClick(Category category) {
        Intent intent = new Intent(getActivity(), ShowCategoriesActivity.class);
        intent.putExtra("category_name",category.getName());
        startActivity(intent);

    }
    private void setOnclick(){
        binding.tvCategoryShowAll.setOnClickListener(this::onClick);
        binding.tvOffersShowAll2.setOnClickListener(this::onClick);
        binding.imgNotifications.setOnClickListener(this::onClick);
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.img_notifications){
            Intent intent=new Intent(getActivity(), Notifications.class);
            startActivity(intent);

        }
        if (v.getId() == R.id.tv_category_show_all){
            Intent intent = new Intent(getActivity(), ShowAll_Items.class);
            intent.putExtra("category","Categories");
            startActivity(intent);

        }if (v.getId() == R.id.tv_offers_show_all2){

        }
    }

    private void getProduct(){

        ProductController.getInstance().getAllProducts(new ProductCallback() {
            @Override
            public void onSuccess(List<Product> productList) {
              //  Toast.makeText(getActivity(), "list size is "+productList.size(), Toast.LENGTH_SHORT).show();
                products.clear();
                products.addAll(productList);
                productHomeAdapter.notifyDataSetChanged();
              //  adapter.notifyItemRangeInserted(0,productList.size());
            }

            @Override
            public void onFailure(String message) {

            }
        });

/*
        ProductController.getInstance().getAllProductsByProviderId(FirebaseAuth.getInstance().getCurrentUser().getUid(),new OnProductFetchListener() {
            @Override
            public void onFetchLListSuccess(ArrayList<Product> list) {
                productList.clear();
                productList.addAll(list);
                adapter.notifyItemRangeInserted(0,list.size());
            }

            @Override
            public void onFetchSuccess(Product product) {
                Snackbar.make(binding.getRoot(),"product Size is "+product.getCategory().toLowerCase(),Snackbar.LENGTH_LONG).show();


            }

            @Override
            public void onFetchFailure(String message) {
                Snackbar.make(binding.getRoot(),message,Snackbar.LENGTH_LONG).show();

            }
        });
*/
    }



    private void getProvider(){
        ServiceProviderController.getInstance().getAllServiceProviders(new OnServiceProviderFetchListener() {
            @Override
            public void onFetchSuccess(List<Provider> serviceProviders) {
                providers.clear();
                providers.addAll(serviceProviders);
                providerAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFetchFailure(String errorMessage) {

            }
        });
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}