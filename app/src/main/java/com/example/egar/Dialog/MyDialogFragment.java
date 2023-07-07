package com.example.egar.Dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.egar.databinding.FragmentMyDialogBinding;
import com.example.egar.interfaces.DialogListener;

public class MyDialogFragment extends DialogFragment {

    private static final String ARG_TITLE = "title";

    private String title;

    private DialogListener listener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof  DialogListener)
            listener= (DialogListener) context;

    }

    public MyDialogFragment() {
        // Required empty public constructor
    }

    public static MyDialogFragment newInstance(String title) {
        MyDialogFragment fragment = new MyDialogFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TITLE, title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            title = getArguments().getString(ARG_TITLE);
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());

        FragmentMyDialogBinding binding= FragmentMyDialogBinding.inflate(getLayoutInflater());





        builder.setView(binding.getRoot());
        return builder.create();
    }
}