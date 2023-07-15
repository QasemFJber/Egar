package com.example.egar.Fragments.pagerRentals;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.egar.Activities.OrderDetailsActivity;
import com.example.egar.Activities.ShowService_Product_Details;
import com.example.egar.Models.Order;
import com.example.egar.R;
import com.example.egar.adapters.order.OrderAdapter;
import com.example.egar.controllers.OrderController;
import com.example.egar.databinding.FragmentInProgressBinding;
import com.example.egar.enums.OrderStatus;
import com.example.egar.interfaces.ItemCallbackOrder;
import com.example.egar.interfaces.OnOrderFetchListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class InProgressFragment extends Fragment implements ItemCallbackOrder {


    FragmentInProgressBinding binding;
    List<Order> orderList = new ArrayList<>();

    OrderAdapter adapter;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentInProgressBinding.inflate(inflater);
        getOrdersByStates();
        initializeRecyclerAdapter();
        return binding.getRoot();
    }
    private void initializeRecyclerAdapter() {
        adapter = new OrderAdapter(orderList);
        adapter.setCallbackOrder(this);
        binding.rvOrdersInProgress.setAdapter(adapter);
        binding.rvOrdersInProgress.setLayoutManager(new LinearLayoutManager(getActivity()));
    }
    private void getOrdersByStates(){
        OrderController.getInstance().getOrdersByServiceProviderIdAndOrderStatus(FirebaseAuth.getInstance().getUid(), String.valueOf(OrderStatus.IN_PROGRESS), new OnOrderFetchListener() {
            @Override
            public void onAddOrderSuccess(String orderId) {

            }

            @Override
            public void onAddOrderFailure(String message) {

            }

            @Override
            public void onUpdateOrderSuccess() {

            }

            @Override
            public void onUpdateOrderFailure(String message) {

            }

            @Override
            public void onDeleteOrderSuccess() {

            }

            @Override
            public void onDeleteOrderFailure(String message) {

            }

            @Override
            public void onGetOrdersByServiceProviderIdSuccess(List<Order> orders) {
                orderList.clear();
                orderList.addAll(orders);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onGetOrdersByServiceProviderIdFailure(String message) {
                Snackbar.make(binding.getRoot(),message,Snackbar.LENGTH_LONG).show();

            }
        });
    }

    @Override
    public void onItemClick(Order order) {
        Intent intent = new Intent(getActivity(), OrderDetailsActivity.class);
        intent.putExtra("order", (Serializable) order);
        startActivity(intent);
    }
}