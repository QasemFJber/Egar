package com.example.egar.Dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.example.egar.R;

public class ConfirmDialog {
        private AlertDialog dialog;
        private Activity activity;

        public ConfirmDialog(Activity activity1){
            activity =activity1;
        }

        public void startDialog(){
            AlertDialog.Builder builder = new AlertDialog.Builder(activity);

            LayoutInflater layoutInflater = activity.getLayoutInflater();
            builder.setView(layoutInflater.inflate(R.layout.purchase_was_completed_successfully,null));
            builder.setCancelable(true);

            dialog = builder.create();

            dialog.show();

        }


        public void dismissDialog(){
            dialog.dismiss();
        }
}
