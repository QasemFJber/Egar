package com.example.egar.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.example.egar.Dialog.LoadingDialog;
import com.example.egar.Fragments.BottomNavigationFragments.RentalsFragment;
import com.example.egar.Fragments.BottomNavigationFragments.Categories;
import com.example.egar.Fragments.BottomNavigationFragments.Favorite;
import com.example.egar.Fragments.BottomNavigationFragments.Home;
import com.example.egar.Fragments.BottomNavigationFragments.Profile;
import com.example.egar.Models.Order;
import com.example.egar.Models.Rating;
import com.example.egar.R;

import com.example.egar.controllers.RatingController;
import com.example.egar.databinding.ActivityMainBinding;
import com.example.egar.interfaces.DialogRatingListener;
import com.example.egar.interfaces.OnRatingOperationListener;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener, DialogRatingListener {
    //Categories categories = new Categories();
    //Favorite favorite = new Favorite();
    FirebaseAuth auth = FirebaseAuth.getInstance();
    //Home home = new Home();
   // Profile profile = new Profile();

    ActivityMainBinding binding;
    int selectedTab = 1;
    Rating rating;
    RatingController ratingController;

    private LoadingDialog dialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initializeView();

    }

    private void initializeView(){
        dialog = new LoadingDialog(this);
        onSelectedTab();
        setOnClick();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();

            }
        },3500);

    }

    private void setUpDialog(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();

            }
        },1000);
    }
    private  void setOnClick(){
        binding.homeLayout.setOnClickListener(this::onClick);
        binding.categoryLayout.setOnClickListener(this::onClick);
        binding.cartLayout.setOnClickListener(this::onClick);
        binding.personLayout.setOnClickListener(this::onClick);
    }

    private void onSelectedTab(){
        if (selectedTab ==1){
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.fragmentView, Home.class,null)
                    .commit();

            binding.cartImage.setBackgroundResource(R.drawable.baseline_calendar);
            binding.categoryImage.setBackgroundResource(R.drawable.baseline_category_selected);
            binding.personImage.setBackgroundResource(R.drawable.baseline_person_selected);


            binding.tvHome.setVisibility(View.VISIBLE);
            binding.homeImage.setBackgroundResource(R.drawable.baseline_home_selected);
            binding.homeLayout.setBackgroundResource(R.drawable.home_round);
        }

        if (selectedTab ==2){
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.fragmentView,Categories.class,null)
                    .commit();
            binding.tvCategory.setVisibility(View.VISIBLE);
            binding.categoryImage.setBackgroundResource(R.drawable.baseline_category_selected);
            binding.categoryLayout.setBackgroundResource(R.drawable.home_round);
        }

        if (selectedTab ==3){
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.fragmentView, RentalsFragment.class,null)
                    .commit();
            binding.tvCart.setVisibility(View.VISIBLE);
            binding.cartImage.setBackgroundResource(R.drawable.baseline_calendar);
            binding.cartLayout.setBackgroundResource(R.drawable.home_round);
        }

        if (selectedTab ==4){
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.fragmentView,Profile.class,null)
                    .commit();
            binding.tvPerson.setVisibility(View.VISIBLE);
            binding.personImage.setBackgroundResource(R.drawable.baseline_person_selected);
            binding.personLayout.setBackgroundResource(R.drawable.home_round);
        }

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.homeLayout){
            if (selectedTab !=1){
                getSupportFragmentManager().beginTransaction()
                        .setReorderingAllowed(true)
                        .replace(R.id.fragmentView,Home.class,null)
                        .commit();

                binding.tvCart.setVisibility(View.GONE);
                binding.tvPerson.setVisibility(View.GONE);
                binding.tvCategory.setVisibility(View.GONE);


/*
                binding.cartImage.setBackgroundResource(R.drawable.baseline_shopping_cart);
                binding.categoryImage.setBackgroundResource(R.drawable.baseline_category);
                binding.personImage.setBackgroundResource(R.drawable.baseline_person);
*/



                binding.cartLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                binding.personLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                binding.categoryLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));

                binding.tvHome.setVisibility(View.VISIBLE);
                binding.homeImage.setBackgroundResource(R.drawable.baseline_home_selected);
                binding.homeLayout.setBackgroundResource(R.drawable.home_round);

                ScaleAnimation scaleAnimation = new ScaleAnimation(0.0f,1.0f,1f,1f, Animation.RELATIVE_TO_SELF,1f);
                scaleAnimation.setDuration(400);
                scaleAnimation.setFillAfter(true);
                binding.homeLayout.startAnimation(scaleAnimation);

                selectedTab = 1;
            }

        }
        if (v.getId() == R.id.categoryLayout){
            if (selectedTab !=2){
                getSupportFragmentManager().beginTransaction()
                        .setReorderingAllowed(true)
                        .replace(R.id.fragmentView,Categories.class,null)
                        .commit();
                binding.tvCart.setVisibility(View.GONE);
                binding.tvPerson.setVisibility(View.GONE);
                binding.tvHome.setVisibility(View.GONE);

/*
                binding.cartImage.setBackgroundResource(R.drawable.baseline_shopping_cart);
                binding.homeImage.setBackgroundResource(R.drawable.baseline_home);
                binding.personImage.setBackgroundResource(R.drawable.baseline_person);
*/

                binding.cartLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                binding.personLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                binding.homeLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));

                binding.tvCategory.setVisibility(View.VISIBLE);
                binding.categoryImage.setBackgroundResource(R.drawable.baseline_category_selected);
                binding.categoryLayout.setBackgroundResource(R.drawable.home_round);

                ScaleAnimation scaleAnimation = new ScaleAnimation(0.0f,1.0f,1f,1f, Animation.RELATIVE_TO_SELF,1f);
                scaleAnimation.setDuration(400);
                scaleAnimation.setFillAfter(true);
                binding.categoryLayout.startAnimation(scaleAnimation);

                selectedTab = 2;
            }
        }
        if (v.getId() == R.id.cartLayout){
            if (selectedTab !=3){
                getSupportFragmentManager().beginTransaction()
                        .setReorderingAllowed(true)
                        .replace(R.id.fragmentView, RentalsFragment.class,null)
                        .commit();
                binding.tvHome.setVisibility(View.GONE);
                binding.tvPerson.setVisibility(View.GONE);
                binding.tvCategory.setVisibility(View.GONE);

        /*        binding.homeImage.setBackgroundResource(R.drawable.baseline_home);
                binding.categoryImage.setBackgroundResource(R.drawable.baseline_category);
                binding.personImage.setBackgroundResource(R.drawable.baseline_person);
*/
                binding.homeLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                binding.personLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                binding.categoryLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));

                binding.tvCart.setVisibility(View.VISIBLE);
                binding.cartImage.setBackgroundResource(R.drawable.baseline_calendar);
                binding.cartLayout.setBackgroundResource(R.drawable.home_round);

                ScaleAnimation scaleAnimation = new ScaleAnimation(0.0f,1.0f,1f,1f, Animation.RELATIVE_TO_SELF,1f);
                scaleAnimation.setDuration(400);
                scaleAnimation.setFillAfter(true);
                binding.cartLayout.startAnimation(scaleAnimation);

                selectedTab = 3;
            }
        }
        if (v.getId() == R.id.personLayout){
            if (selectedTab !=4){
                getSupportFragmentManager().beginTransaction()
                        .setReorderingAllowed(true)
                        .replace(R.id.fragmentView,Profile.class,null)
                        .commit();
                binding.tvCart.setVisibility(View.GONE);
                binding.tvHome.setVisibility(View.GONE);
                binding.tvCategory.setVisibility(View.GONE);

   /*             binding.cartImage.setBackgroundResource(R.drawable.baseline_shopping_cart);
                binding.categoryImage.setBackgroundResource(R.drawable.baseline_category);
                binding.homeImage.setBackgroundResource(R.drawable.baseline_home);
*/
                binding.cartLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                binding.homeLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                binding.categoryLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));

                binding.tvPerson.setVisibility(View.VISIBLE);
                binding.personImage.setBackgroundResource(R.drawable.baseline_person_selected);
                binding.personLayout.setBackgroundResource(R.drawable.home_round);

                ScaleAnimation scaleAnimation = new ScaleAnimation(0.0f,1.0f,1f,1f, Animation.RELATIVE_TO_SELF,1f);
                scaleAnimation.setDuration(400);
                scaleAnimation.setFillAfter(true);
                binding.personLayout.startAnimation(scaleAnimation);

                selectedTab = 4;
            }
        }
    }

    @Override
    public void onOkDialogListener(Order order,String comment, float ratingValue) {
        ratingController = new RatingController();
        Calendar calendar = java.util.Calendar.getInstance();
        int year = calendar.get(java.util.Calendar.YEAR);
        int month = calendar.get(java.util.Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        rating = new Rating(order.getOrderId(),order.getProduct().getId(),order.getUser().getId(),ratingValue,comment,day+"/"+month+"/"+year,order.getProduct().getProvider().getId());
        ratingController.addRating(rating, new OnRatingOperationListener() {
            @Override
            public void onRatingOperationSuccess(String ratingId) {
                Toast.makeText(getApplicationContext(), "add rating", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onRatingOperationFailure(String message) {

            }
        });
    }






/*
    private void operationsSccren() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentcontainer,home);
    }

    @Override
    protected void onStart() {
        super.onStart();
        operationsSccren();
        setOnClick();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    private void setOnClick(){
*//*        binding.imageNotification.setOnClickListener(this::onClick);
        binding.menuDrw.setOnClickListener(this::onClick);*//*
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()){
*//*
         case R.id.image_notification:
                Intent intent = new Intent(getApplicationContext(),Notifications.class);
                startActivity(intent);
                break;
            case R.id.menu_drw:
                FirebaseAuthController.getInstance().signOut();
                Intent intentLogin = new Intent(getApplicationContext(),HomeActivity.class);
                startActivity(intentLogin);
                break;*//*

        }
    }*/

    @Override
    protected void onStart() {
        super.onStart();
        dialog.show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        setUpDialog();

    }
}