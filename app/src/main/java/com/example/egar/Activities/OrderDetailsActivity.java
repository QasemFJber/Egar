package com.example.egar.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.egar.Models.Offer;
import com.example.egar.Models.Order;
import com.example.egar.R;
import com.example.egar.databinding.ActivityOrderDetailsBinding;
import com.squareup.picasso.Picasso;

public class OrderDetailsActivity extends AppCompatActivity {

    ActivityOrderDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOrderDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Toast.makeText(this, "id"+order().getProduct().getName(), Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onStart() {
        super.onStart();
        initializeView();
    }

    private void initializeView(){
        getOrder();
    }


    private Order order(){
        Order order = (Order) getIntent().getSerializableExtra("order");
        return order;
    }

    private void getOrder(){
        binding.textOrderNumber.setText(order().getOrderId());
        binding.textOrderNumber2.setText(order().getOrderId());
        binding.textNameProvider.setText(order().getProduct().getProvider().getName());
        binding.textNameProductOrder.setText(order().getProduct().getName());
        binding.textOrderdate.setText(order().getOrderDate());
        binding.textDelivery.setText("");
        binding.textSubtotal.setText(String.valueOf(order().getTotalAmount()));
        Picasso.get().load(order().getProduct().getImageUrl()).into(binding.imageView6);


        binding.textGrandTotal.setText(String.valueOf(order().getTotalAmount()));
        binding.textPriceOrderO.setText(String.valueOf(order().getProduct().getPrice()));
        binding.textPaymentMethod.setText(order().getPaymentMethod());
    }
}