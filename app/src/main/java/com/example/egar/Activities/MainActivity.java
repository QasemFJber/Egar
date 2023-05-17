package com.example.egar.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;

import com.example.egar.Activities.ui.home.HomeFragment;
import com.example.egar.Fragments.BottomNavigationFragments.CartFragment;
import com.example.egar.Fragments.BottomNavigationFragments.Categories;
import com.example.egar.Fragments.BottomNavigationFragments.Favorite;
import com.example.egar.Fragments.BottomNavigationFragments.Home;
import com.example.egar.Fragments.BottomNavigationFragments.Profile;
import com.example.egar.R;

import com.example.egar.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener {
    //Categories categories = new Categories();
    //Favorite favorite = new Favorite();
    FirebaseAuth auth = FirebaseAuth.getInstance();
    //Home home = new Home();
   // Profile profile = new Profile();

    ActivityMainBinding binding;
    int selectedTab = 1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initializeView();



/*
        binding.bottmNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                item.setChecked(true);
                switch (item.getItemId()) {
                    case R.id.home:
                        item.setIcon(R.drawable.homeyi);
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentcontainer,home).commit();
                        break;
                    case R.id.profile:
                        item.setIcon(R.drawable.useryi);
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentcontainer,profile).commit();
                        break;
                    case R.id.favorite:
                        item.setIcon(R.drawable.baseline_favoriteyi_24);
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentcontainer,favorite).commit();
                        break;
                    case R.id.category:
                        item.setIcon(R.drawable.baseline_categoryyl_24);
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentcontainer,categories).commit();
                        break;
                }

                return false;
            }
        });
*/


    }

    private void initializeView(){
        onSelectedTab();
        setOnClick();
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


            binding.cartImage.setBackgroundResource(R.drawable.baseline_shopping_cart);
            binding.categoryImage.setBackgroundResource(R.drawable.baseline_category);
            binding.personImage.setBackgroundResource(R.drawable.baseline_person);


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
            binding.categoryLayout.setBackgroundResource(R.drawable.category_round);
        }

        if (selectedTab ==3){
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.fragmentView,Favorite.class,null)
                    .commit();
            binding.tvCart.setVisibility(View.VISIBLE);
            binding.cartImage.setBackgroundResource(R.drawable.baseline_shopping_cart_selected);
            binding.cartLayout.setBackgroundResource(R.drawable.cart_round);
        }

        if (selectedTab ==4){
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.fragmentView,Profile.class,null)
                    .commit();
            binding.tvPerson.setVisibility(View.VISIBLE);
            binding.personImage.setBackgroundResource(R.drawable.baseline_person_selected);
            binding.personLayout.setBackgroundResource(R.drawable.person_round);
        }

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.homeLayout){
            if (selectedTab !=1){
                getSupportFragmentManager().beginTransaction()
                        .setReorderingAllowed(true)
                        .replace(R.id.fragmentView,HomeFragment.class,null)
                        .commit();

                binding.tvCart.setVisibility(View.GONE);
                binding.tvPerson.setVisibility(View.GONE);
                binding.tvCategory.setVisibility(View.GONE);


                binding.cartImage.setBackgroundResource(R.drawable.baseline_shopping_cart);
                binding.categoryImage.setBackgroundResource(R.drawable.baseline_category);
                binding.personImage.setBackgroundResource(R.drawable.baseline_person);



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

                binding.cartImage.setBackgroundResource(R.drawable.baseline_shopping_cart);
                binding.homeImage.setBackgroundResource(R.drawable.baseline_home);
                binding.personImage.setBackgroundResource(R.drawable.baseline_person);

                binding.cartLayout.setBackgroundResource(R.color.white);
                binding.personLayout.setBackgroundResource(R.color.white);
                binding.homeLayout.setBackgroundResource(R.color.white);

                binding.tvCategory.setVisibility(View.VISIBLE);
                binding.categoryImage.setBackgroundResource(R.drawable.baseline_category_selected);
                binding.categoryLayout.setBackgroundResource(R.drawable.category_round);

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
                        .replace(R.id.fragmentView, CartFragment.class,null)
                        .commit();
                binding.tvHome.setVisibility(View.GONE);
                binding.tvPerson.setVisibility(View.GONE);
                binding.tvCategory.setVisibility(View.GONE);

                binding.homeImage.setBackgroundResource(R.drawable.baseline_home);
                binding.categoryImage.setBackgroundResource(R.drawable.baseline_category);
                binding.personImage.setBackgroundResource(R.drawable.baseline_person);

                binding.homeLayout.setBackgroundResource(R.color.white);
                binding.personLayout.setBackgroundResource(R.color.white);
                binding.categoryLayout.setBackgroundResource(R.color.white);

                binding.tvCart.setVisibility(View.VISIBLE);
                binding.cartImage.setBackgroundResource(R.drawable.baseline_shopping_cart_selected);
                binding.cartLayout.setBackgroundResource(R.drawable.cart_round);

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

                binding.cartImage.setBackgroundResource(R.drawable.baseline_shopping_cart);
                binding.categoryImage.setBackgroundResource(R.drawable.baseline_category);
                binding.homeImage.setBackgroundResource(R.drawable.baseline_home);

                binding.cartLayout.setBackgroundResource(R.color.white);
                binding.homeLayout.setBackgroundResource(R.color.white);
                binding.categoryLayout.setBackgroundResource(R.color.white);

                binding.tvPerson.setVisibility(View.VISIBLE);
                binding.personImage.setBackgroundResource(R.drawable.baseline_person_selected);
                binding.personLayout.setBackgroundResource(R.drawable.person_round);

                ScaleAnimation scaleAnimation = new ScaleAnimation(0.0f,1.0f,1f,1f, Animation.RELATIVE_TO_SELF,1f);
                scaleAnimation.setDuration(400);
                scaleAnimation.setFillAfter(true);
                binding.personLayout.startAnimation(scaleAnimation);

                selectedTab = 4;
            }
        }
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
}