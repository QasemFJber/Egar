package com.example.egar.adapters;

import android.content.Context;
import android.media.Image;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.egar.Models.Category;
import com.example.egar.R;
import com.example.egar.databinding.CustomCategoryItemBinding;
import com.example.egar.interfaces.OnItemClickListener;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    private List<Category> categoryList;
    private OnItemClickListener listener;


    public CategoryAdapter(List<Category> categoryList, OnItemClickListener listener) {
        this.categoryList = categoryList;
        this.listener = listener;
    }

    // ViewHolder class to hold each item in the RecyclerView

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CustomCategoryItemBinding binding=CustomCategoryItemBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
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

        CustomCategoryItemBinding binding;
        OnItemClickListener listener;

        public CategoryViewHolder(CustomCategoryItemBinding binding, OnItemClickListener listener) {
            super(binding.getRoot());
            this.binding=binding;
            this.listener=listener;

        }

        public void sava(Category category) {

            binding.categoryTitle.setText(category.getName());
            binding.categoryImage.setBackgroundResource(category.getImageUrl());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(category);
                }
            });
        }
    }

}
