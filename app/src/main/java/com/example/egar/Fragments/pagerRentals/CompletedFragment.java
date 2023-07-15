package com.example.egar.Fragments.pagerRentals;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.egar.Dialog.DeliveredDialogFragment;
import com.example.egar.Models.Order;
import com.example.egar.Models.Rating;
import com.example.egar.adapters.order.OrderCompletedAdapter;
import com.example.egar.controllers.OrderController;

import com.example.egar.controllers.RatingController;
import com.example.egar.databinding.FragmentCompletedBinding;
import com.example.egar.enums.OrderStatus;
import com.example.egar.interfaces.ItemCallbackOrder;
import com.example.egar.interfaces.OnOrderFetchListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

import java.util.List;


public class CompletedFragment extends Fragment implements ItemCallbackOrder /*,DialogRatingListener */{

    FragmentCompletedBinding binding;
    List<Order> orderList = new ArrayList<>();

    OrderCompletedAdapter adapter;
    DeliveredDialogFragment dialogFragment;
    Rating rating;
    Order orderC;
    RatingController ratingController;



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
        adapter = new OrderCompletedAdapter(orderList);
        binding.rvOrdersCompleted.setAdapter(adapter);
        adapter.setCallbackOrder(this::onItemClick);
        binding.rvOrdersCompleted.setLayoutManager(new LinearLayoutManager(getActivity()));
    }
    private void getOrdersByStates(){
        OrderController.getInstance().getOrdersByServiceProviderIdAndOrderStatus(FirebaseAuth.getInstance().getUid(), String.valueOf(OrderStatus.COMPLETED), new OnOrderFetchListener() {
            @Override
            public void onAddOrderSuccess(String orderId, String id) {

            }

            @Override
            public void onAddOrderFailure(String message) {

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
        //Toast.makeText(getActivity(), "تقيم", Toast.LENGTH_SHORT).show();

        dialogFragment = DeliveredDialogFragment.newInstance("",order);
        dialogFragment.show(getChildFragmentManager(),null);
        orderC = order;

    }


/*    @Override
    public void onOkDialogListener(String comment, float ratingValue) {

        ratingController = new RatingController();
        Calendar calendar = java.util.Calendar.getInstance();
        int year = calendar.get(java.util.Calendar.YEAR);
        int month = calendar.get(java.util.Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        Toast.makeText(getActivity(), "okay", Toast.LENGTH_SHORT).show();


   *//*     rating = new Rating(orderC.getOrderId(),orderC.getProduct().getId(),orderC.getUser().getId(),ratingValue,comment,day+"/"+month+"/"+year);
        ratingController.addRating(rating, new OnRatingOperationListener() {
            @Override
            public void onRatingOperationSuccess(String ratingId) {
                Toast.makeText(getActivity(), "add rating", Toast.LENGTH_SHORT).show();

                dialogFragment.dismiss();


            }

            @Override
            public void onRatingOperationFailure(String message) {

            }
        });*//*

    }*/
}