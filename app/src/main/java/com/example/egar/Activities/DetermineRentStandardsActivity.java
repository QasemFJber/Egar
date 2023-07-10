package com.example.egar.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.egar.Models.Category;
import com.example.egar.Models.Days;
import com.example.egar.R;
import com.example.egar.adapters.CategoryAdapter;
import com.example.egar.adapters.ProviderAdapter.ProviderAdapter;
import com.example.egar.adapters.card.DaysAdapter;
import com.example.egar.adapters.offers.OffersAdapter;
import com.example.egar.adapters.productHome.ProductHomeAdapter;
import com.example.egar.databinding.ActivityDetermineRentStandardsBinding;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.snackbar.Snackbar;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class DetermineRentStandardsActivity extends AppCompatActivity implements View.OnClickListener {
    ActivityDetermineRentStandardsBinding binding;
    private List<Days> daysList = new ArrayList<>();
    DaysAdapter adapter;
    String month;
    int month_ =0;
    Calendar now;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetermineRentStandardsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

         now = Calendar.getInstance();



        SimpleDateFormat monthFormat = new SimpleDateFormat("MMMM", Locale.getDefault());
        month = monthFormat.format(now.getTime());

        // الحصول على الشهر
        month_ = now.get(Calendar.MONTH) + 1;

        binding.textMonth.setText(month);
        Toast.makeText(this, ""+getSelectedChipText(), Toast.LENGTH_SHORT).show();


        //addDataToRecyclerView();
        initializeView();
    }

    private  void setOnClick() {
        binding.buttonNext.setOnClickListener(this::onClick);
        binding.etStartDate.setOnClickListener(this::onClick);
        binding.etEndDate.setOnClickListener(this::onClick);
    }

/*
    private List<Days> addDataToRecyclerView(){
        daysList = new ArrayList<>();
        daysList.add(new Days(String.valueOf(month_+1)));
        daysList.add(new Days(String.valueOf(month_+2)));
        daysList.add(new Days(String.valueOf(month_+3)));
        daysList.add(new Days(String.valueOf(month_+4)));
        daysList.add(new Days(String.valueOf(month_+5)));
        daysList.add(new Days(String.valueOf(month_+6)));
        daysList.add(new Days(String.valueOf(month_+7)));
*/
/*        daysList.add(new Days("Sunday"));
        daysList.add(new Days("Monday"));
        daysList.add(new Days("Tuesday"));
        daysList.add(new Days("Wednesday"));
        daysList.add(new Days("Thursday"));
        daysList.add(new Days("Friday"));*//*


        return  daysList;

    }
*/



    private void initializeView(){
        //initializeRecyclerAdapter();
        setOnClick();
        visibilityDate();
    }
/*    private void initializeRecyclerAdapter(){
        adapter = new DaysAdapter(daysList);
        binding.recMonth.setAdapter(adapter);
        binding.recMonth.setLayoutManager(new LinearLayoutManager(getApplicationContext(),RecyclerView.HORIZONTAL,false));

    }*/

    public String getSelectedChipText() {

        int checkedChipId = binding.chipGroup.getCheckedChipId();

        if (checkedChipId != View.NO_ID) {
            Chip checkedChip = binding.chipGroup.findViewById(checkedChipId);
            return checkedChip.getText().toString();
        } else {
            Snackbar.make(binding.getRoot(),"Select The Store Type ",Snackbar.LENGTH_LONG).show();
            return null;
        }
    }

    public void visibilityDate(){

        binding.chipGroup.setOnCheckedStateChangeListener(new ChipGroup.OnCheckedStateChangeListener() {
            @Override
            public void onCheckedChanged(@NonNull ChipGroup group, @NonNull List<Integer> checkedIds) {


                if (Objects.equals(getSelectedChipText(), "Per day")) {

                    DatePickerDialog dpd = DatePickerDialog.newInstance(new DatePickerDialog.OnDateSetListener() {
                                                                            @Override
                                                                            public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                                                                                binding.dateDay.setVisibility(View.VISIBLE);
                                                                                binding.dateDay.setText(String.valueOf(dayOfMonth));
                                                                            }
                                                                        },
                            now.get(Calendar.YEAR), // Initial year selection
                            now.get(Calendar.MONTH), // Initial month selection
                            now.get(Calendar.DAY_OF_MONTH) // Inital day selection

                    );
                    dpd.show(getSupportFragmentManager(),"Datepickerdialog");


                }else if (Objects.equals(getSelectedChipText(), "In month")){
                    binding.dateDay.setVisibility(View.GONE);
                    binding.layoutDate.setVisibility(View.VISIBLE);
                    binding.etEndDate.setText(null);
                    binding.etStartDate.setText(null);

                }else if (Objects.equals(getSelectedChipText(), "in the year")){
                    binding.dateDay.setVisibility(View.GONE);
                    binding.etEndDate.setText(null);
                    binding.etStartDate.setText(null);
                    binding.layoutDate.setVisibility(View.VISIBLE);

                }




/*                if (Objects.equals(getSelectedChipText(), "Per day")){
                    //binding.scrollDataDay.setVisibility(View.VISIBLE);
                    //binding.recMonth.setVisibility(View.GONE);

                }else if (Objects.equals(getSelectedChipText(), "In month")){
                    //binding.scrollDataDay.setVisibility(View.GONE);
                    //binding.recMonth.setVisibility(View.VISIBLE);

                }else if (Objects.equals(getSelectedChipText(), "in the year")){
                    //binding.scrollDataDay.setVisibility(View.GONE);
                    //binding.recMonth.setVisibility(View.GONE);
                }*/
            }
        });



    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button_next){
            String id_product = getIntent().getStringExtra("id_product");
            Intent intent =new Intent(getApplicationContext(), ConfirmationTheEgarActivity.class);
            intent.putExtra("id_product",id_product);
            if (!binding.etStartDate.getText().toString().isEmpty()) {
                intent.putExtra("date_start", binding.etStartDate.getText().toString());
            } if (!binding.etEndDate.getText().toString().isEmpty()){
                intent.putExtra("date_end", binding.etEndDate.getText().toString());
            }if (!binding.dateDay.getText().toString().isEmpty()) {
                intent.putExtra("date_day", binding.dateDay.getText().toString());

            }
            startActivity(intent);
        }if (v.getId() == R.id.et_start_date){
            DatePickerDialog dpd = DatePickerDialog.newInstance(new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                    binding.etStartDate.setText(dayOfMonth+"/"+monthOfYear+"/"+year);
                }
                },
                    now.get(Calendar.YEAR), // Initial year selection
                    now.get(Calendar.MONTH), // Initial month selection
                    now.get(Calendar.DAY_OF_MONTH) // Inital day selection

            );
            dpd.show(getSupportFragmentManager(),"Datepickerdialog");

        }if (v.getId() == R.id.et_end_date){
            DatePickerDialog dpd = DatePickerDialog.newInstance(new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                    binding.etEndDate.setText(dayOfMonth+"/"+monthOfYear+"/"+year);
                }
                },
                    now.get(Calendar.YEAR), // Initial year selection
                    now.get(Calendar.MONTH), // Initial month selection
                    now.get(Calendar.DAY_OF_MONTH) // Inital day selection

            );
            dpd.show(getSupportFragmentManager(),"Datepickerdialog");

        }
    }
}