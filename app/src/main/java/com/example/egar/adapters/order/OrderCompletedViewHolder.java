package com.example.egar.adapters.order;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.example.egar.Models.Order;
import com.example.egar.databinding.ItemOrderBinding;
import com.example.egar.databinding.ItemOrderCompletedBinding;
import com.example.egar.interfaces.ItemCallbackOrder;


public class OrderCompletedViewHolder extends RecyclerView.ViewHolder {
    ItemOrderCompletedBinding binding;
    ItemCallbackOrder callbackOrder;

    public OrderCompletedViewHolder(ItemOrderCompletedBinding binding,ItemCallbackOrder callbackOrder) {
        super(binding.getRoot());
        this.binding=binding;
        this.callbackOrder=callbackOrder;
    }
    public void savaData(Order order){
        binding.textNameProviderOrder.setText(order.getProduct().getProvider().getName());
        //binding.textPriceOrder.setText(String.valueOf(order.getTotalAmount()));
        binding.textEgarNmae.setText(order.getProduct().getName());
        //binding.textOderDate.setText(order.getOrderDate());

        binding.textAddEvaluation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callbackOrder.onItemClick(order);
            }
        });





    }
}
