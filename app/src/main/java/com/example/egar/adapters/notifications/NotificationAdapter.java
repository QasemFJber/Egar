package com.example.egar.adapters.notifications;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.egar.Models.Notification;
import com.example.egar.Models.Offer;
import com.example.egar.databinding.NoteficationsItemBinding;

import java.util.ArrayList;
import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationViewHolder> {
    private List<Notification> notificationList = new ArrayList<>();

    public NotificationAdapter(List<Notification> notificationList){};

    @NonNull
    @Override
    public NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        NoteficationsItemBinding binding = NoteficationsItemBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new NotificationViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationViewHolder holder, int position) {
        holder.savaData(notificationList.get(position));

    }

    @Override
    public int getItemCount() {
        return notificationList.size();
    }
}
