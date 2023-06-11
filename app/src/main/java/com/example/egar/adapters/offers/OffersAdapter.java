package com.example.egar.adapters.offers;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.egar.databinding.ItemOffersBinding;

public class OffersAdapter extends RecyclerView.Adapter<OffersViewHolder> {

    @NonNull
    @Override
    public OffersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemOffersBinding binding=ItemOffersBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);

        return new OffersViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull OffersViewHolder holder, int position) {
        holder.savaData();
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
