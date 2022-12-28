package com.example.tooshytoask.Activity.Landing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;

import com.example.tooshytoask.Adapters.CategoryAdapter;
import com.example.tooshytoask.Adapters.InfoCardAdapter;
import com.example.tooshytoask.Adapters.ViewPagerAdapter;
import com.example.tooshytoask.Helper.SPManager;
import com.example.tooshytoask.Models.CategoryItem;
import com.example.tooshytoask.R;

import java.util.ArrayList;

public class InfoCardCategoryActivity extends AppCompatActivity {
    Context context;
    SPManager spManager;
    ProgressBar progressbar_completed;
    double progrss_value;
    InfoCardAdapter adapter;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_card_category);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        context = InfoCardCategoryActivity.this;
        spManager = new SPManager(context);
        progressbar_completed = findViewById(R.id.progressbar_completed);
        viewPager = findViewById(R.id.viewPager);

        AddView();

    }

    private void AddView() {
        adapter =new InfoCardAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private int getitem(int i) {

        return viewPager.getCurrentItem() + i;
    }
}