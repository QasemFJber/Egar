package com.example.egar.Fragments.pagerRentals;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.egar.Models.Order;
import com.example.egar.adapters.order.OrderAdapter;
import com.example.egar.controllers.OrderController;
import com.example.egar.databinding.FragmentCancelledBinding;
import com.example.egar.enums.OrderStatus;
import com.example.egar.interfaces.OnOrderFetchListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CancelledFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CancelledFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    FragmentCancelledBinding binding;
    List<Order> orderList = new ArrayList<>();

    OrderAdapter adapter;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CancelledFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CancelledFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CancelledFragment newInstance(String param1, String param2) {
        CancelledFragment fragment = new CancelledFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentCancelledBinding.inflate(inflater);
        getOrdersByStates();
        initializeRecyclerAdapter();
        return binding.getRoot();
    }
    private void initializeRecyclerAdapter() {
        adapter = new OrderAdapter(orderList);
        binding.rvOrdersCancelled.setAdapter(adapter);
        binding.rvOrdersCancelled.setLayoutManager(new LinearLayoutManager(getActivity()));
    }
    private void getOrdersByStates(){
        OrderController.getInstance().getOrdersByServiceProviderIdAndOrderStatus(FirebaseAuth.getInstance().getUid(), String.valueOf(OrderStatus.CANCELLED), new OnOrderFetchListener() {
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
}