package com.example.egar.adapters.productHome;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;


import com.example.egar.Models.Product;
import com.example.egar.R;
import com.example.egar.databinding.ItemProductsBinding;
import com.example.egar.interfaces.ItemCallback;
import com.squareup.picasso.Picasso;

public class ProductHomeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

     ItemProductsBinding binding;
     ItemCallback callback;

    public ProductHomeViewHolder(ItemProductsBinding binding, ItemCallback callback) {
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
        binding.layoutItem.setOnClickListener(this::onClick);
    }

    public void savaData(Product product){
        binding.tvProductName.setText(product.getName());
        binding.tvPrinc.setText(String.valueOf(product.getPrice()));
       // binding.tvProviderName.setText(product.getProvider().getName());
        binding.tvProviderName.setText("ProviderName");
        //binding.tvProviderType.setText(product.getProvider().getProviderType());
        binding.tvProviderType.setText("ProviderType");

        Picasso.get().load(product.getImageUrl()).into(binding.imgProduct);
        binding.layoutItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.onItem(product.getId());
            }
        });

    }

    @Override
    public void onClick(View v) {

/*        if (v.getId() == R.id.layoutItem) {
            if (callback != null) {
                callback.onItem(getAdapterPosition());
            }
        }*/

    }
}
