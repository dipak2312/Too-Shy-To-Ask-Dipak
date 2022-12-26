package com.example.tooshytoask.Activity.Landing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.example.tooshytoask.Adapters.CategoryAdapter;
import com.example.tooshytoask.Helper.SPManager;
import com.example.tooshytoask.Models.CategoryItem;
import com.example.tooshytoask.R;

import java.util.ArrayList;

public class InfoCardCategoryActivity extends AppCompatActivity {
    Context context;
    ArrayList<CategoryItem>categoryItems;
    SPManager spManager;
    RecyclerView category_recy, recyclerView;
    CategoryAdapter adapter;
    ProgressBar progressbar_completed;
    double progrss_value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_card_category);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        context = InfoCardCategoryActivity.this;
        spManager = new SPManager(context);
        progressbar_completed = findViewById(R.id.progressbar_completed);

        recyclerView = findViewById(R.id.category_recy);
        recyclerView.setLayoutManager(new GridLayoutManager(context, 3, GridLayoutManager.VERTICAL, true));

        /*GridLayoutManager layout = new GridLayoutManager(context, 3, GridLayoutManager.VERTICAL, false);
        category_recy.setLayoutManager(layout);*/
        categoryItems = new ArrayList<>();

        categoryItems.add(new CategoryItem(R.drawable.relationships,"Relationship"));
        categoryItems.add(new CategoryItem(R.drawable.sexuality,"Sex & Sexuality"));
        categoryItems.add(new CategoryItem(R.drawable.reproduction,"Reproduction"));

        //category_recy.setAdapter(adapter);
        recyclerView.setAdapter(new CategoryAdapter(categoryItems));
    }

    /*public void init() {
        adapter = new CategoryAdapter(categoryItems);
        txt_count.setText(getResources().getString(R.string.page_count_total, 0 + 1, adapter.getCount()));

        progrss_value=(double)1/adapter.getCount()*100;
        progressbar_completed.setProgress((int) progrss_value);
        category_recy.setAdapter(adapter);
        category_recy.setOnTouchListener((view, motionEvent) -> true);
        category_recy.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {


            }

            @Override
            public void onPageSelected(int position) {
                txt_count.setText(
                        getResources().getString(R.string.page_count_total, position + 1,
                                pagerAdapter.getCount()));

                int value=position+1;
                progrss_value=(double)value/pagerAdapter.getCount()*100;
                progressbar_completed.setProgress((int) progrss_value);


            }

            @Override
            public void onPageScrollStateChanged(int state) {


            }
        });


    }*/
}