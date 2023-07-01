package com.example.egar.adapters.product;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;


import com.example.egar.Models.Product;
import com.example.egar.R;
import com.example.egar.databinding.ItemProductShowBinding;
import com.example.egar.interfaces.ItemCallbackProduct;
import com.squareup.picasso.Picasso;

public class ProductViewHolder extends RecyclerView.ViewHolder /*implements View.OnClickListener*/ {

    ItemProductShowBinding binding;

    ItemCallbackProduct callbackProduct;

    public ProductViewHolder(ItemProductShowBinding binding, ItemCallbackProduct callbackProduct) {
        super(binding.getRoot());
        this.binding=binding;
        this.callbackProduct=callbackProduct;
    }

    public void savaData(Product product){
        binding.tvProductShow.setText(product.getName());
        binding.tvProductPrice.setText(String.valueOf(product.getPrice()));
        binding.tvProductDescription.setText(product.getDescription());
        Picasso.get().load(product.getImageUrl()).into(binding.imgProductShow);

        if(product.isFavorite()){
            binding.imgFavorite.setImageResource(R.drawable.baseline_favorite_24);
        }else{
            binding.imgFavorite.setImageResource(R.drawable.baseline_favorite);
        }

        binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callbackProduct.onItemClick(product);
            }
        });


    }

/*    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.img_delete) {
            if (callback != null) {
                callback.onDelete(getAdapterPosition());
            }
        }

    }*/
}
