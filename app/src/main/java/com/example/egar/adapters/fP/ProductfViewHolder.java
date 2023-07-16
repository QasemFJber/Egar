package com.example.egar.adapters.fP;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.example.egar.Models.Product;
import com.example.egar.Models.ProductFavorite;
import com.example.egar.R;
import com.example.egar.databinding.ItemProductShowBinding;
import com.example.egar.interfaces.ItemCallbackProduct;
import com.squareup.picasso.Picasso;

public class ProductfViewHolder extends RecyclerView.ViewHolder /*implements View.OnClickListener*/ {

    ItemProductShowBinding binding;

    ItemCallbackProduct callbackProduct;

    public ProductfViewHolder(ItemProductShowBinding binding, ItemCallbackProduct callbackProduct) {
        super(binding.getRoot());
        this.binding=binding;
        this.callbackProduct=callbackProduct;
    }

    public void savaData(ProductFavorite product){
        binding.tvProductShow.setText(product.getProduct().getName());
        binding.tvProductPrice.setText(String.valueOf(product.getProduct().getPrice()));
        binding.tvProductDescription.setText(product.getProduct().getDescription());
        Picasso.get().load(product.getProduct().getImageUrl()).into(binding.imgProductShow);

        if(product.getProduct().isFavorite()){
            binding.imgFavorite.setImageResource(R.drawable.baseline_favorite_24);
        }else{
            binding.imgFavorite.setImageResource(R.drawable.baseline_favorite);
        }

        binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callbackProduct !=null) {
                    callbackProduct.onItemClick(product.getProduct());
                }
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
