package com.example.egar.Dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.egar.databinding.FragmentDialogReservationConfirmationBinding;
import com.example.egar.interfaces.DialogListener;


public class DialogReservationConfirmationFragment extends DialogFragment {

    private static final String ARG_TITLE = "title";
    private static final String ARG_DATE= "date";

   private String date;


    private DialogListener listener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof DialogListener)
            listener= (DialogListener) context;

    }

    public DialogReservationConfirmationFragment() {
        // Required empty public constructor
    }

    public static DialogReservationConfirmationFragment newInstance(String date) {
        DialogReservationConfirmationFragment fragment = new DialogReservationConfirmationFragment();
        Bundle args = new Bundle();
        args.putString(ARG_DATE, date);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            date = getArguments().getString(ARG_DATE);

        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());

        FragmentDialogReservationConfirmationBinding binding= FragmentDialogReservationConfirmationBinding.inflate(getLayoutInflater());

        binding.textDate.setText(date);


        binding.btnGoToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onOkDialogListener(date);
                //dismiss();
            }
        });
        builder.setView(binding.getRoot());
        return builder.create();
    }
}