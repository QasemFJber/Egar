package com.example.egar.Dialog;

import android.app.Activity;
import android.view.LayoutInflater;

import androidx.appcompat.app.AlertDialog;

import com.example.egar.R;

public class DeliveredDialog {
    private AlertDialog dialog;
    private Activity activity;

    public DeliveredDialog(Activity activity1){
        activity =activity1;
    }

    public void startDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        LayoutInflater layoutInflater = activity.getLayoutInflater();
        builder.setView(layoutInflater.inflate(R.layout.delivereddialog,null));
        builder.setCancelable(true);

        dialog = builder.create();

        dialog.show();

    }


    public void dismissDialog(){
        dialog.dismiss();
    }
}
