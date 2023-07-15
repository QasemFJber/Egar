package com.example.egar.Dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.egar.Models.Order;
import com.example.egar.R;
import com.example.egar.databinding.FragmentDeliveredDialogBinding;
import com.example.egar.interfaces.DialogListener;
import com.example.egar.interfaces.DialogRatingListener;

import java.io.Serializable;


public class DeliveredDialogFragment extends DialogFragment {

    private static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private Order order;

    private DialogRatingListener listener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof DialogRatingListener)
            listener= (DialogRatingListener) context;
        else {
            throw new RuntimeException(context.toString()
                    + " must implement DialogRatingListener");
        }
    }

    public DeliveredDialogFragment() {
        // Required empty public constructor
    }


    public static DeliveredDialogFragment newInstance(String param1, Order order) {
        DeliveredDialogFragment fragment = new DeliveredDialogFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putSerializable("order", (Serializable) order);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            order = (Order) getArguments().getSerializable("order");
        }
    }




    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());

        FragmentDeliveredDialogBinding binding = FragmentDeliveredDialogBinding.inflate(getLayoutInflater());

        //binding.etComment.getText().toString();
        //binding.ratingBar3.getRating();
        //String comment = binding.etComment.getText().toString();
        //float r = binding.ratingBar3.getRating();

        binding.buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onOkDialogListener(order,binding.etComment.getText().toString(), binding.ratingBar3.getRating());
                    dismiss();
                }
               // listener.onOkDialogListener(comment,r);
            }
        });



        //return binding.getRoot();

        builder.setView(binding.getRoot());
        return builder.create();

    }

 /*   @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

    }*/
}