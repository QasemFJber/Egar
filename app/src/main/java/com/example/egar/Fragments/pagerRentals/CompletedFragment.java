package com.example.egar.Fragments.pagerRentals;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.egar.Models.Order;
import com.example.egar.adapters.order.OrderAdapter;
import com.example.egar.controllers.OrderController;
import com.example.egar.databinding.FragmentCompletedBinding;
import com.example.egar.enums.OrderStatus;
import com.example.egar.interfaces.OnOrderFetchListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;


public class CompletedFragment extends Fragment {

    FragmentCompletedBinding binding;
    List<Order> orderList = new ArrayList<>();

    OrderAdapter adapter;



    public CompletedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentCompletedBinding.inflate(inflater);
        getOrdersByStates();
        initializeRecyclerAdapter();
        return binding.getRoot();
    }
    private void initializeRecyclerAdapter() {
        adapter = new OrderAdapter(orderList);
        binding.rvOrdersCompleted.setAdapter(adapter);
        binding.rvOrdersCompleted.setLayoutManager(new LinearLayoutManager(getActivity()));
    }
    private void getOrdersByStates(){
        OrderController.getInstance().getOrdersByServiceProviderIdAndOrderStatus(FirebaseAuth.getInstance().getUid(), String.valueOf(OrderStatus.COMPLETED), new OnOrderFetchListener() {
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
}