package com.example.egar.adapters.category;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.egar.Models.Category;
import com.example.egar.databinding.CustomCategoryItemBinding;
import com.example.egar.databinding.ItemCategoryBinding;
import com.example.egar.interfaces.OnItemClickListener;

import java.util.List;

public class CategoryNavAdapter extends RecyclerView.Adapter<CategoryNavAdapter.CategoryViewHolder> {

    private List<Category> categoryList;
    private OnItemClickListener listener;


    public CategoryNavAdapter(List<Category> categoryList, OnItemClickListener listener) {
        this.categoryList = categoryList;
        this.listener = listener;
    }

    // ViewHolder class to hold each item in the RecyclerView

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCategoryBinding binding=ItemCategoryBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new CategoryViewHolder(binding,listener);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        Category category = categoryList.get(position);
        holder.sava(category);
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public static class CategoryViewHolder extends RecyclerView.ViewHolder {

        ItemCategoryBinding binding;
        OnItemClickListener listener;

        public CategoryViewHolder(ItemCategoryBinding binding, OnItemClickListener listener) {
            super(binding.getRoot());
            this.binding=binding;
            this.listener=listener;

        }

        public void sava(Category category) {

            binding.categoryTitle.setText(category.getName());
            binding.imageUrl.setBackgroundResource(category.getImageUrl());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(category);
                }
            });
        }
    }

}
