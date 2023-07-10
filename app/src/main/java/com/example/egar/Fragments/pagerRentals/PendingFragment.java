package com.example.egar.Fragments.pagerRentals;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.egar.Models.Order;
import com.example.egar.R;
import com.example.egar.adapters.offers.OffersAdapter;
import com.example.egar.adapters.order.OrderAdapter;
import com.example.egar.controllers.OrderController;
import com.example.egar.databinding.FragmentPendingBinding;
import com.example.egar.enams.OrderStatus;
import com.example.egar.interfaces.OnOrderFetchListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;


public class PendingFragment extends Fragment {

    FragmentPendingBinding binding;

    List<Order> orderList = new ArrayList<>();

    OrderAdapter adapter;


    public PendingFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentPendingBinding.inflate(inflater);
        initializeView();
        return binding.getRoot();
    }


    private void initializeView() {
        initializeRecyclerAdapter();
        getOrdersByStates();
    }

    private void initializeRecyclerAdapter() {
        adapter = new OrderAdapter(orderList);
        binding.recOrderPending.setAdapter(adapter);
        binding.recOrderPending.setLayoutManager(new LinearLayoutManager(getActivity()));
    }


    private void getOrdersByStates(){

        Toast.makeText(getActivity(), ""+FirebaseAuth.getInstance().getUid(), Toast.LENGTH_SHORT).show();
        OrderController.getInstance().getOrdersByServiceProviderIdAndOrderStatus(FirebaseAuth.getInstance().getUid(), String.valueOf(OrderStatus.PENDING), new OnOrderFetchListener() {
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
                Toast.makeText(getActivity(), orders.size()+"size", Toast.LENGTH_SHORT).show();
                orderList.clear();
                orderList.addAll(orders);
                adapter.notifyDataSetChanged();
                Toast.makeText(getActivity(), orders.size()+"size", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onGetOrdersByServiceProviderIdFailure(String message) {
                Snackbar.make(binding.getRoot(),message,Snackbar.LENGTH_LONG).show();

            }
        });
    }
}