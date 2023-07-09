package com.example.egar.adapters.order;

import androidx.recyclerview.widget.RecyclerView;

import com.example.egar.Models.Order;
import com.example.egar.databinding.ItemOrderBinding;


public class OrderViewHolder extends RecyclerView.ViewHolder {
    ItemOrderBinding binding;

    public OrderViewHolder(ItemOrderBinding binding) {
        super(binding.getRoot());
        this.binding=binding;
    }
    public void savaData(Order order){
        binding.textNameProviderOrder.setText(order.getProduct().getProvider().getName());
        binding.textPriceOrder.setText(String.valueOf(order.getTotalAmount()));
        binding.textEgarNmae.setText(order.getProduct().getName());
        binding.textOderDate.setText(order.getOrderDate());




    }
}
