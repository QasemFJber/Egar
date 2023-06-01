package com.example.egar.adapters.offers;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.egar.databinding.ItemOffersBinding;

public class OffersViewHolder extends RecyclerView.ViewHolder {
    ItemOffersBinding binding;
    public OffersViewHolder(ItemOffersBinding binding) {
        super(binding.getRoot());
        this.binding=binding;
    }

    public void savaData(){

    }
}
