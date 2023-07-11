package com.example.egar.adapters.order;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.egar.Models.Order;
import com.example.egar.databinding.ItemOrderBinding;
import com.example.egar.databinding.ItemOrderCompletedBinding;
import com.example.egar.interfaces.ItemCallbackOrder;

import java.util.List;

public class OrderCompletedAdapter extends RecyclerView.Adapter<OrderCompletedViewHolder> {

    List<Order> orders;
    ItemCallbackOrder callbackOrder;
    public OrderCompletedAdapter(List<Order> orders) {
        this.orders = orders;
    }

    public void setCallbackOrder(ItemCallbackOrder callbackOrder) {
        this.callbackOrder = callbackOrder;
    }

    @NonNull
    @Override
    public OrderCompletedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemOrderCompletedBinding binding=ItemOrderCompletedBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new OrderCompletedViewHolder(binding,callbackOrder);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderCompletedViewHolder holder, int position) {
        holder.savaData(orders.get(position));

    }

    @Override
    public int getItemCount() {
        return orders.size();
    }
}
