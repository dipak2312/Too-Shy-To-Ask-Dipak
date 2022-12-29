package com.example.tooshytoask.Activity.Landing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tooshytoask.Adapters.ViewPagerAdapter;
import com.example.tooshytoask.Helper.SPManager;
import com.example.tooshytoask.Models.SliderItem;
import com.example.tooshytoask.R;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;

import java.util.ArrayList;
import java.util.List;

public class SliderImages extends AppCompatActivity implements View.OnClickListener {
    //ActivitySliderImagesBinding binding;
    ViewPagerAdapter adapter;
    ViewPager viewPager;
    TextView skip_btn;
    Button started;
    ImageButton next_btn;
    DotsIndicator mBarLayout;
    Context context;
    SPManager spManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //binding = ActivitySliderImagesBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_slider_images);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        context = SliderImages.this;
        spManager = new SPManager(context);

        viewPager = findViewById(R.id.view_pager_img);
        mBarLayout = findViewById(R.id.indicator_layout);

        next_btn = findViewById(R.id.next_btn);
        next_btn.setOnClickListener(this);
        skip_btn = findViewById(R.id.skip_btn);
        skip_btn.setOnClickListener(this);
        started = findViewById(R.id.started);
        started.setOnClickListener(this);

        AddView();
    }

    private void AddView() {
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        mBarLayout.setViewPager(viewPager);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                 if (getitem(0) < 1) {

                     skip_btn.setVisibility(View.VISIBLE);
                     next_btn.setVisibility(View.VISIBLE);
                     started.setVisibility(View.GONE);

                } else if (getitem(0) < 2) {

                     skip_btn.setVisibility(View.VISIBLE);
                     next_btn.setVisibility(View.VISIBLE);
                     started.setVisibility(View.GONE);

                } else if (getitem(0) < 3) {

                     started.setVisibility(View.VISIBLE);
                     Animation alpha = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.alpha);
                     started.startAnimation(alpha);
                     skip_btn.setVisibility(View.GONE);
                     next_btn.setVisibility(View.GONE);

                }
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


    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == next_btn.getId()) {
            viewPager.setCurrentItem(getitem(1), true);
        } else if (id == started.getId()){
            adapter = new ViewPagerAdapter(getSupportFragmentManager());
            viewPager.setAdapter(adapter);

        } if (id == skip_btn.getId()){
            Intent intent = new Intent(context, LanguageActivity.class);
            startActivity(intent);
            //finish();
        }
        }

    }