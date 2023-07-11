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
       // Toast.makeText(this, ""+getSelectedChipText(), Toast.LENGTH_SHORT).show();

        //Toast.makeText(this, ""+ getIntent().getStringExtra("category"), Toast.LENGTH_SHORT).show();

        //addDataToRecyclerView();
        initializeView();
    }

    private  void setOnClick() {
        binding.buttonNext.setOnClickListener(this::onClick);
        binding.etStartDate.setOnClickListener(this::onClick);
       // binding.etEndDate.setOnClickListener(this::onClick);
    }



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

    public String getSelectedChipText2() {

        int checkedChipId = binding.chipGroupDelivery.getCheckedChipId();

        if (checkedChipId != View.NO_ID) {
            Chip checkedChip = binding.chipGroupDelivery.findViewById(checkedChipId);
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
                    binding.layoutDate.setVisibility(View.VISIBLE);


                }else if (Objects.equals(getSelectedChipText(), "In month")){
                    binding.dateDay.setVisibility(View.GONE);
                    binding.layoutDate.setVisibility(View.VISIBLE);
                    //binding.etEndDate.setText(null);
                   // binding.etStartDate.setText(null);

                }else if (Objects.equals(getSelectedChipText(), "in the year")){
                    binding.dateDay.setVisibility(View.GONE);
                    //binding.etEndDate.setText(null);
                    //binding.etStartDate.setText(null);
                    //binding.layoutDate.setVisibility(View.VISIBLE);

                }

            }
        });

        String category= getIntent().getStringExtra("category");
        if (category.equals("cars") || category.equals("Woman Clothes") || category.equals("Equipment") || category.equals("Wedding clothes") ){
            binding.cardDelivery.setVisibility(View.VISIBLE);
        }

        binding.chipGroupDelivery.setOnCheckedStateChangeListener(new ChipGroup.OnCheckedStateChangeListener() {
            @Override
            public void onCheckedChanged(@NonNull ChipGroup group, @NonNull List<Integer> checkedIds) {
                if (Objects.equals(getSelectedChipText2(), "Yes")) {
                    //binding.layoutDate.setVisibility(View.VISIBLE);
                    Intent intent = new Intent(getApplicationContext(), AddATitleActivity.class);
                    startActivity(intent);

                }

            }
        });


    }


    private void date(){
        String id_product = getIntent().getStringExtra("id_product");
        Intent intent =new Intent(getApplicationContext(), ConfirmationTheEgarActivity.class);
        intent.putExtra("id_product",id_product);
        if (!binding.etStartDate.getText().toString().isEmpty()) {
            intent.putExtra("date_start", binding.etStartDate.getText().toString());
            //intent.putExtra("date_end", );

        }/* if (!binding.etEndDate.getText().toString().isEmpty()){
            intent.putExtra("date_end", binding.etEndDate.getText().toString());
        }*/
        startActivity(intent);
    }

    private boolean dataCheck (){
        String dateS = binding.etStartDate.getText().toString();
        //String dateE = binding.etEndDate.getText().toString();
        if (dateS.isEmpty()) {
            binding.etStartDate.setError("StartDate field is Required");
            return false;
        } /*else if (dateE.isEmpty()) {
            binding.etEndDate.setError("EndDate field is Required");
            return false;
        }*/
        return true;
    }
    private void form(){
        if (dataCheck()){
            date();
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button_next){
          form();

        }
        if (v.getId() == R.id.et_start_date){
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

        }
        /*if (v.getId() == R.id.et_end_date){
            DatePickerDialog dpd = DatePickerDialog.newInstance(new DatePickerDialog.OnDateSetListener() {
                      @Override
                      public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                          //binding.etEndDate.setText(dayOfMonth+"/"+monthOfYear+"/"+year);
                      }
                  },
                    now.get(Calendar.YEAR), // Initial year selection
                    now.get(Calendar.MONTH), // Initial month selection
                    now.get(Calendar.DAY_OF_MONTH) // Inital day selection

            );
            dpd.show(getSupportFragmentManager(),"Datepickerdialog");

        }*/
    }
}