package com.example.egar.adapters.productHome;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.egar.Models.Product;
import com.example.egar.databinding.ItemProductsBinding;
import com.example.egar.interfaces.ItemCallbackProduct;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private List<Product> productList;
    ItemCallbackProduct callbackProduct;


    public ProductAdapter(List<Product> productList,ItemCallbackProduct callbackProduct) {
        this.productList = productList;
        this.callbackProduct=callbackProduct;
    }

    public void setCallbackProduct(ItemCallbackProduct callbackProduct) {
        this.callbackProduct = callbackProduct;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemProductsBinding binding = ItemProductsBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ProductViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = productList.get(position);

        holder.binding.tvProductName.setText(product.getName());
        holder.binding.tvProviderType.setText(product.getCategory());
        holder.binding.tvPrinc.setText(String.valueOf(product.getPrice()));
        holder.binding.tvProductName.setText(product.getDescription());
        Picasso.get().load(product.getImageUrl()).into(holder.binding.imgProduct);

        holder.binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callbackProduct.onItemClick(product);
            }
        });

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {
        ItemProductsBinding binding;

        public ProductViewHolder(@NonNull ItemProductsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
