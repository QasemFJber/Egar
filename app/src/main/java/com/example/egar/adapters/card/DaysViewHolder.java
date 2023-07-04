package com.example.egar.adapters.card;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.egar.Models.Days;
import com.example.egar.databinding.ItemCardDaysBinding;

public class DaysViewHolder extends RecyclerView.ViewHolder {
    ItemCardDaysBinding binding;
    public DaysViewHolder(ItemCardDaysBinding binding) {
        super(binding.getRoot());
        this.binding=binding;
    }

    public void savaData(Days days){
        binding.tvNameDay.setText(days.getNameDay());
    }


}
