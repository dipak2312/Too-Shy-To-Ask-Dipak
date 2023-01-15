package com.example.tooshytoask.Fragment.InfoCard;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.tooshytoask.Adapters.CategoryAdapter;
import com.example.tooshytoask.Helper.SPManager;
import com.example.tooshytoask.Interface.CategoryListener;
import com.example.tooshytoask.Interface.ClickListener;
import com.example.tooshytoask.Models.CategoryItem;
import com.example.tooshytoask.R;

import java.util.ArrayList;

public class OneFragment extends Fragment implements View.OnClickListener, View.OnTouchListener, CategoryListener, ClickListener {
    Context context;
    SPManager spManager;
    RecyclerView recyclerView, category_recy;
    CategoryAdapter adapter;
    ArrayList<CategoryItem> categoryItems;
    TextView skip_btn;
    Button next_btn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_one, container, false);
        getActivity().getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        context = getActivity();
        spManager = new SPManager(context);
        //next_btn = view.findViewById(R.id.next_btn);
        //next_btn.setOnClickListener(this);
        skip_btn = view.findViewById(R.id.skip_btn);
        skip_btn.setOnClickListener(this);

        recyclerView = view.findViewById(R.id.category_recy);
        recyclerView.setOnTouchListener(this);

        recyclerView.setLayoutManager(new GridLayoutManager(context,3, GridLayoutManager.VERTICAL, false));

        categoryItems = new ArrayList<>();

        categoryItems.add(new CategoryItem(R.drawable.relationships,"Relationships"));
        categoryItems.add(new CategoryItem(R.drawable.relationships,"Sex & Sexuality"));
        categoryItems.add(new CategoryItem(R.drawable.relationships,"Reproduction"));
        categoryItems.add(new CategoryItem(R.drawable.relationships,"Mental Health"));
        categoryItems.add(new CategoryItem(R.drawable.relationships,"Education"));
        categoryItems.add(new CategoryItem(R.drawable.relationships,"Sexual Assault"));

        recyclerView.setAdapter(new CategoryAdapter(categoryItems));

    return view;
    }

    @Override
    public void onClick(View view) {
        /*int id = view.getId();

        if (id == recyclerView.getId()){
            next_btn.setBackgroundResource(R.drawable.circle_button_active);
        }*/


    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        return true;
    }

    @Override
    public void onSelectedCategory(Boolean isSelected) {
        if (isSelected){
            next_btn.setVisibility(View.VISIBLE);
            //next_btn.setBackgroundResource(R.drawable.circle_button_active);
        } else {
            next_btn.setVisibility(View.GONE);
            //next_btn.setBackgroundResource(R.drawable.circle_button_inactive);
        }
    }

    @Override
    public void onDisSelectedCategory(Boolean disSelected) {


    }

    @Override
    public void onClick() {

    }
}