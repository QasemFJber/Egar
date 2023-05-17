package com.example.egar.Fragments.BottomNavigationFragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.egar.Activities.ShowAll_Items;
import com.example.egar.Models.Category;
import com.example.egar.R;
import com.example.egar.adapters.CategoryAdapter;
import com.example.egar.adapters.StoreAdapter;
import com.example.egar.databinding.FragmentHomeBinding;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Home#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Home extends Fragment {
    private RecyclerView categoryRecyclerView;
    private CategoryAdapter categoryAdapter;
    private List<Category> categoryList;

    private  FragmentHomeBinding binding;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Home() {

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Main.
     */
    // TODO: Rename and change types and number of parameters
    public static Home newInstance(String param1, String param2) {
        Home fragment = new Home();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding  = FragmentHomeBinding.inflate(inflater,container,false);
        intiRecyclerViewStore();
        binding.tvCategoryShowAll.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), ShowAll_Items.class);
            intent.putExtra("category","Categories");
            startActivity(intent);
        });
        return binding.getRoot();
    }
    private void intiRecyclerViewStore(){
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        LinearLayoutManager manager3 = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        GridLayoutManager manager2 = new GridLayoutManager(getActivity(),2);
//        StoreAdapter adapter = new StoreAdapter(getActivity(),"Qasem Brand", R.drawable.avatar);
        setupCategoryRecyclerView(getActivity(), binding.recyclerCategory, addDataToRecyclerView(), new CategoryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Category category) {
                Snackbar.make(binding.getRoot(),category.getName(),Snackbar.LENGTH_LONG).show();

            }
        });

//        binding.servicerv.setLayoutManager(manager3);
//        binding.servicerv.setAdapter(adapter);
//        binding.productrv.setLayoutManager(manager2);
//        binding.productrv.setAdapter(adapter);


    }
    private List<Category> addDataToRecyclerView(){

        categoryList = new ArrayList<>();
        categoryList.add(new Category("CARS", R.drawable.car));
        categoryList.add(new Category("CO WORKING", R.drawable.coworking));
        categoryList.add(new Category("HOUSE", R.drawable.house));
        categoryList.add(new Category("MAINTENANCE", R.drawable.maintenance));
        categoryList.add(new Category("MAN CLOTHES", R.drawable.man));
        categoryList.add(new Category("WOMAN CLOTHES", R.drawable.women));

        return  categoryList;

    }
    private CategoryAdapter setupCategoryRecyclerView(Context context, RecyclerView recyclerView, List<Category> categories, CategoryAdapter.OnItemClickListener listener) {
        // Set the layout manager for the RecyclerView
        LinearLayoutManager layoutManager = new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(layoutManager);

        // Initialize the adapter
        CategoryAdapter adapter = new CategoryAdapter(categories, listener);

        // Set the adapter for the RecyclerView
        recyclerView.setAdapter(adapter);

        return adapter;
    }


}