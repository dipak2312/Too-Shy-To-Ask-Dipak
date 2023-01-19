package com.example.tooshytoask.Activity.Help;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.tooshytoask.Adapters.HelpCategoryAdapter;
import com.example.tooshytoask.Helper.SPManager;
import com.example.tooshytoask.Models.HelpCategory;
import com.example.tooshytoask.R;

import java.util.ArrayList;

public class HelpActivity extends AppCompatActivity implements View.OnClickListener{
    RecyclerView help_category_recy, recyclerView;
    SPManager spManager;
    Context context;
    ArrayList<HelpCategory>helpCategories;
    HelpCategoryAdapter adapter;
    RelativeLayout rel_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        context = HelpActivity.this;
        spManager = new SPManager(context);
        rel_back = findViewById(R.id.rel_back);
        rel_back.setOnClickListener(this);
        help_category_recy = findViewById(R.id.help_category_recy);
        help_category_recy.setLayoutManager(new GridLayoutManager(context,3, GridLayoutManager.VERTICAL, false));

        helpCategories = new ArrayList<>();

        helpCategories.add(new HelpCategory(R.drawable.using_tsta,"Using TSTA"));
        helpCategories.add(new HelpCategory(R.drawable.acccount, "Account"));
        helpCategories.add(new HelpCategory(R.drawable.general, "General"));
        helpCategories.add(new HelpCategory(R.drawable.privacy, "Privacy & Security"));
        helpCategories.add(new HelpCategory(R.drawable.troubleshooting, "Troubleshooting"));
        helpCategories.add(new HelpCategory(R.drawable.others, "Others"));

        help_category_recy.setAdapter(new HelpCategoryAdapter(context, helpCategories));

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if (id == rel_back.getId()){
            finish();
        }
    }
}