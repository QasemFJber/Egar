package com.example.egar.adapters.order;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.egar.Models.Order;
import com.example.egar.databinding.ItemOrderBinding;
import com.example.egar.interfaces.ItemCallbackOrder;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderViewHolder> {

    List<Order> orders;
    ItemCallbackOrder callbackOrder;


    public OrderAdapter(List<Order> orders) {
        this.orders = orders;
    }

    public void setCallbackOrder(ItemCallbackOrder callbackOrder) {
        this.callbackOrder = callbackOrder;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemOrderBinding binding=ItemOrderBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new OrderViewHolder(binding,callbackOrder);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        holder.savaData(orders.get(position));

    }

    @Override
    public int getItemCount() {
        return orders.size();
    }
}
