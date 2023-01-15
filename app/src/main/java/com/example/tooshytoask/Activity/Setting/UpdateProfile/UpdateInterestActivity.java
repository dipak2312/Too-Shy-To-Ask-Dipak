package com.example.tooshytoask.Activity.Setting.UpdateProfile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.tooshytoask.Activity.Setting.UpdateProfileActivity;
import com.example.tooshytoask.Adapters.CategoryAdapter;
import com.example.tooshytoask.Helper.SPManager;
import com.example.tooshytoask.Models.CategoryItem;
import com.example.tooshytoask.R;

import java.util.ArrayList;

public class UpdateInterestActivity extends AppCompatActivity implements View.OnClickListener{
    Context context;
    SPManager spManager;
    RecyclerView recyclerView, category_recy;
    CategoryAdapter adapter;
    ArrayList<CategoryItem> categoryItems;
    RelativeLayout rel_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_interest);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        context = UpdateInterestActivity.this;
        spManager = new SPManager(context);
        rel_back = findViewById(R.id.rel_back);
        rel_back.setOnClickListener(this);
        recyclerView = findViewById(R.id.category_recy);

        recyclerView.setLayoutManager(new GridLayoutManager(context,3, GridLayoutManager.VERTICAL, false));

        categoryItems = new ArrayList<>();

        categoryItems.add(new CategoryItem(R.drawable.relationships,"Relationships"));
        categoryItems.add(new CategoryItem(R.drawable.relationships,"Sex & Sexuality"));
        categoryItems.add(new CategoryItem(R.drawable.relationships,"Reproduction"));
        categoryItems.add(new CategoryItem(R.drawable.relationships,"Mental Health"));
        categoryItems.add(new CategoryItem(R.drawable.relationships,"Education"));
        categoryItems.add(new CategoryItem(R.drawable.relationships,"Sexual Assault"));

        recyclerView.setAdapter(new CategoryAdapter(categoryItems));
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if (id == rel_back.getId()){

            Intent intent = new Intent(context, UpdateProfileActivity.class);
            startActivity(intent);
        }

    }
}