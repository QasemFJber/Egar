package com.example.egar.adapters.ProviderAdapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.egar.Models.Provider;
import com.example.egar.databinding.ItemProviderBinding;

import java.util.List;

public class ProviderAdapter extends RecyclerView.Adapter<ProviderViewHolder> {

    List<Provider> providerList;

    public ProviderAdapter(List<Provider> providerList) {
        this.providerList = providerList;
    }

    @NonNull
    @Override
    public ProviderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemProviderBinding binding = ItemProviderBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ProviderViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProviderViewHolder holder, int position) {
        holder.savaData(providerList.get(position));

    }

    @Override
    public int getItemCount() {
        return providerList.size();
    }
}
