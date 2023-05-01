package com.example.egar.Fragments.BottomNavigationFragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.egar.Models.Category;
import com.example.egar.R;
import com.example.egar.adapters.CategoryAdapter;
import com.example.egar.adapters.StoreAdapter;
import com.example.egar.databinding.FragmentCategoriesBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Categories#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Categories extends Fragment {
    private RecyclerView categoryRecyclerView;
    private CategoryAdapter categoryAdapter;
    private List<Category> categoryList;


    private FragmentCategoriesBinding binding;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Categories() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Categories.
     */
    // TODO: Rename and change types and number of parameters
    public static Categories newInstance(String param1, String param2) {
        Categories fragment = new Categories();
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
        // Inflate the layout for this fragment
        binding = FragmentCategoriesBinding.inflate(inflater,container,false);
        intiRecyclerViewStore();
        return binding.getRoot();
    }
    private void intiRecyclerViewStore(){
        GridLayoutManager manager = new GridLayoutManager(getActivity(),3);
//        StoreAdapter adapter = new StoreAdapter(getActivity(),"Qasem Brand", R.drawable.coworking);
        setupCategoryRecyclerView(getActivity(), binding.rvCategory, addDataToRecyclerView(), new CategoryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Category category) {

            }
        });


    }
    private List<Category> addDataToRecyclerView(){

        categoryList = new ArrayList<>();
        categoryList.add(new Category("Category 1", R.drawable.car));
        categoryList.add(new Category("Category 2", R.drawable.coworking));
        categoryList.add(new Category("Category 3", R.drawable.house));
        categoryList.add(new Category("Category 4", R.drawable.maintenance));
        categoryList.add(new Category("Category 5", R.drawable.man));
        categoryList.add(new Category("Category 5", R.drawable.women));

        return  categoryList;

    }
    private CategoryAdapter setupCategoryRecyclerView(Context context, RecyclerView recyclerView, List<Category> categories, CategoryAdapter.OnItemClickListener listener) {
        // Set the layout manager for the RecyclerView
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);

        // Initialize the adapter
        CategoryAdapter adapter = new CategoryAdapter(categories, listener);

        // Set the adapter for the RecyclerView
        recyclerView.setAdapter(adapter);

        return adapter;
    }


}