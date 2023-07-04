package com.example.egar.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.egar.Models.Category;
import com.example.egar.Models.Days;
import com.example.egar.R;
import com.example.egar.adapters.CategoryAdapter;
import com.example.egar.adapters.ProviderAdapter.ProviderAdapter;
import com.example.egar.adapters.card.DaysAdapter;
import com.example.egar.adapters.offers.OffersAdapter;
import com.example.egar.adapters.productHome.ProductHomeAdapter;
import com.example.egar.databinding.ActivityDetermineRentStandardsBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class DetermineRentStandardsActivity extends AppCompatActivity implements View.OnClickListener {
    ActivityDetermineRentStandardsBinding binding;
    private List<Days> daysList = new ArrayList<>();
    DaysAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetermineRentStandardsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Calendar now = Calendar.getInstance();



        SimpleDateFormat monthFormat = new SimpleDateFormat("MMMM", Locale.getDefault());
        String month = monthFormat.format(now.getTime());

        binding.textMonth.setText(month);


        addDataToRecyclerView();
        initializeView();
    }

    private  void setOnClick() {
        binding.buttonNext.setOnClickListener(this::onClick);
    }

    private List<Days> addDataToRecyclerView(){
        daysList = new ArrayList<>();
        daysList.add(new Days("Saturday"));
        daysList.add(new Days("Sunday"));
        daysList.add(new Days("Monday"));
        daysList.add(new Days("Tuesday"));
        daysList.add(new Days("Wednesday"));
        daysList.add(new Days("Thursday"));
        daysList.add(new Days("Friday"));

        return  daysList;

    }


    private void initializeView(){
        initializeRecyclerAdapter();
        setOnClick();
    }
    private void initializeRecyclerAdapter(){
        adapter = new DaysAdapter(daysList);
        binding.recDays.setAdapter(adapter);
        binding.recDays.setLayoutManager(new LinearLayoutManager(getApplicationContext(),RecyclerView.HORIZONTAL,false));

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button_next){
            Intent intent =new Intent(getApplicationContext(), ConfirmationTheEgarActivity.class);
            startActivity(intent);
        }
    }
}