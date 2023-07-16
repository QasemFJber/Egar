package com.example.egar.adapters.notifications;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.egar.Models.Notification;
import com.example.egar.Models.Product;
import com.example.egar.R;
import com.example.egar.databinding.NoteficationsItemBinding;
import com.squareup.picasso.Picasso;

public class NotificationViewHolder extends RecyclerView.ViewHolder {
    NoteficationsItemBinding binding;
    public NotificationViewHolder(NoteficationsItemBinding binding) {
        super(binding.getRoot());
        this.binding=binding;
    }
    public void savaData(Notification notification){
        binding.tvTitle.setText(notification.getTitle());
        binding.tvMessage.setText(notification.getBody());
        binding.tvDate.setText(notification.getDate());
        binding.tvTime.setText(notification.getTime());
    }

}
