package com.example.egar.adapters.card;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.egar.Models.Days;
import com.example.egar.databinding.ItemCardDaysBinding;

import java.util.List;

public class DaysAdapter extends RecyclerView.Adapter<DaysViewHolder> {

    List<Days> daysList;

    public DaysAdapter(List<Days> daysList) {
        this.daysList = daysList;
    }

    @NonNull
    @Override
    public DaysViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCardDaysBinding binding= ItemCardDaysBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new DaysViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull DaysViewHolder holder, int position) {
        holder.savaData(daysList.get(position));

    }

    @Override
    public int getItemCount() {
        return daysList.size();
    }
}
