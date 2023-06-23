package com.example.egar.adapters.offers;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.egar.databinding.ItemOffersBinding;

import java.util.List;

public class OffersAdapter extends RecyclerView.Adapter<OffersViewHolder> {

/*    List<Offer> offerList;

    public OffersAdapter(List<Offer> offerList) {
        this.offerList = offerList;
    }*/

    @NonNull
    @Override
    public OffersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemOffersBinding binding=ItemOffersBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);

        return new OffersViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull OffersViewHolder holder, int position) {
      //  holder.savaData(offerList.get(position));
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
