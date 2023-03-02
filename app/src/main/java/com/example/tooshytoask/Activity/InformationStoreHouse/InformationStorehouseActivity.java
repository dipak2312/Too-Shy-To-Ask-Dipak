package com.example.tooshytoask.Activity.InformationStoreHouse;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.tooshytoask.Activity.Blogs.AllEventActivity;
import com.example.tooshytoask.Adapters.StoreHouseListingAdapter;
import com.example.tooshytoask.Helper.SPManager;
import com.example.tooshytoask.R;
import com.example.tooshytoask.Utils.CustomProgressDialog;

public class InformationStorehouseActivity extends AppCompatActivity implements View.OnClickListener {
    Context context;
    SPManager spManager;
    CustomProgressDialog dialog;
    TextView storehouse_title, tv_category_selection;
    ImageView bookmark_blog, selection_category;
    RelativeLayout categories, rel_back;
    RecyclerView storehouse_recy;
    StoreHouseListingAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_storehouse);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        context = InformationStorehouseActivity.this;
        spManager = new SPManager(context);
        dialog = new CustomProgressDialog(context);

        rel_back = findViewById(R.id.rel_back);
        rel_back.setOnClickListener(this);
        storehouse_title = findViewById(R.id.storehouse_title);
        bookmark_blog = findViewById(R.id.bookmark_blog);
        bookmark_blog.setOnClickListener(this);
        selection_category = findViewById(R.id.selection_category);
        categories = findViewById(R.id.categories);
        categories.setOnClickListener(this);
        tv_category_selection = findViewById(R.id.tv_category_selection);
        storehouse_recy = findViewById(R.id.storehouse_recy);
    }

    @Override
    public void onClick(View v) {

    }
}