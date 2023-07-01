package com.example.egar.adapters.productHome;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;


import com.example.egar.Models.Product;
import com.example.egar.R;
import com.example.egar.controllers.ProductController;
import com.example.egar.databinding.ItemProductsBinding;
import com.example.egar.interfaces.ItemCallback;
import com.example.egar.interfaces.ItemCallbackProduct;
import com.example.egar.interfaces.ProcessCallback;
import com.squareup.picasso.Picasso;

public class ProductHomeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {

     ItemProductsBinding binding;
     ItemCallbackProduct callback;

    public ProductHomeViewHolder(ItemProductsBinding binding, ItemCallbackProduct callback) {
        super(binding.getRoot());
        this.binding=binding;
        this.callback=callback;
        initializeView();
    }

    private void initializeView() {
        setOnClickListeners();
    }

    private void setOnClickListeners() {
        //binding.imgDelete.setOnClickListener(this::onClick);
        binding.imgFavorite.setOnClickListener(this::onClick);
    }

    public void savaData(Product product){
        binding.tvProductName.setText(product.getName());
        binding.tvPrinc.setText(String.valueOf(product.getPrice()));
        binding.tvProviderName.setText(product.getProvider().getName());
        binding.tvProviderType.setText(product.getProvider().getProviderType());
        Picasso.get().load(product.getImageUrl()).into(binding.imgProduct);
        if(product.isFavorite()){
            binding.imgFavorite.setImageResource(R.drawable.baseline_favorite_24);
        }else{
            binding.imgFavorite.setImageResource(R.drawable.baseline_favorite);
        }

        binding.imgFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isClick;
                if (product.isFavorite()){
                    isClick = false;
                }else{
                    isClick =true;
                }
                setProductFavorite(product,product.getId(),isClick);
            }
        });



        binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.onItemClick(product);
            }
        });
    }

    private void setProductFavorite(Product product, String productId, boolean isFavorite){
        ProductController.getInstance().updateProductFavoriteStatus(productId, isFavorite, new ProcessCallback() {
            @Override
            public void onSuccess(String message) {
                synchronized(product){
                    product.notify();
                }
            }

            @Override
            public void onFailure(String message) {

            }
        });
    }

    @Override
    public void onClick(View v) {
/*        if (v.getId() == R.id.img_favorite) {
            setProductFavorite();
        }*/
    }
}
