package com.example.egar.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.egar.Fragments.pagerRentals.CompletedFragment;
import com.example.egar.Fragments.pagerRentals.InProgressFragment;
import com.example.egar.Fragments.pagerRentals.PendingFragment;

public class MyFragmentTapAdapter extends FragmentStateAdapter {


        public MyFragmentTapAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }


//    public MyFragmentTapAdapter(@NonNull Fragment fragment) {
//        super(fragment);
//    }



    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0){
            return new PendingFragment();
        }else if (position == 1){
            return new InProgressFragment();
        }
            return new CompletedFragment();


    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
