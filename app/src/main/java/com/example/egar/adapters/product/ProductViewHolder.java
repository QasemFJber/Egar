package com.example.egar.adapters.product;

import androidx.recyclerview.widget.RecyclerView;


import com.example.egar.Models.Product;
import com.example.egar.databinding.ItemProductShowBinding;
import com.squareup.picasso.Picasso;

public class ProductViewHolder extends RecyclerView.ViewHolder /*implements View.OnClickListener*/ {

    ItemProductShowBinding binding;

    public ProductViewHolder(ItemProductShowBinding binding) {
        super(binding.getRoot());
        this.binding=binding;
        initializeView();
    }

    private void initializeView() {
        setOnClickListeners();
    }

    private void setOnClickListeners() {
       // binding.imgDelete.setOnClickListener(this::onClick);
        //binding.noteItemView.setOnClickListener(this::onClick);
    }

    public void savaData(Product product){
        binding.tvProductShow.setText(product.getName());
        binding.tvProductPrice.setText(String.valueOf(product.getPrice()));
        binding.tvProductDescription.setText(product.getDescription());
        //binding.imgProductShow.setImageURI(Uri.parse(product.getImageUrl()));
        Picasso.get().load(product.getImageUrl()).into(binding.imgProductShow);
/*
        binding.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callback != null) {
                    callback.onDelete(product);
                }
            }
        });
*/

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
