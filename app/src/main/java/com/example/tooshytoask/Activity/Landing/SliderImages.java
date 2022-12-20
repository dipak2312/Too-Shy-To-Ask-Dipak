package com.example.tooshytoask.Activity.Landing;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.Handler;
import android.view.ScrollCaptureCallback;
import android.view.ScrollCaptureSession;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tooshytoask.Adapters.SliderImageAdapter;
import com.example.tooshytoask.Adapters.SliderTextAdapter;
import com.example.tooshytoask.Helper.SPManager;
import com.example.tooshytoask.Models.SliderItem;
import com.example.tooshytoask.Models.SliderText;
import com.example.tooshytoask.R;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class SliderImages extends AppCompatActivity implements View.OnClickListener {
    //ActivitySliderImagesBinding binding;
    SliderImageAdapter adapter;
    SliderTextAdapter adapter2;
    List<SliderItem> sliderItems;
    List<SliderText> sliderText;
    ViewPager2 viewPager2, viewPager3;
    TextView skip_btn;
    ImageButton next_btn;
    DotsIndicator mBarLayout;
    LinearLayout lin_content, lin_content1, lin_content2, lin_content3;
    Handler handler = new Handler();
    TextView[] bars;
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

        viewPager2 = findViewById(R.id.view_pager_img);
        //viewPager3= findViewById(R.id.view_pager_headline);
        mBarLayout = findViewById(R.id.indicator_layout);
        lin_content = findViewById(R.id.lin_content);
        lin_content1 = findViewById(R.id.lin_content1);
        lin_content2 = findViewById(R.id.lin_content2);
        lin_content3 = findViewById(R.id.lin_content3);
        next_btn = findViewById(R.id.next_btn);
        next_btn.setOnClickListener(this);
        skip_btn = findViewById(R.id.skip_btn);
        skip_btn.setOnClickListener(this);

        //setting slider ViewPager Adapter
        sliderItems = new ArrayList<>();
        sliderItems.add(new SliderItem(R.drawable.welcome));
        sliderItems.add(new SliderItem(R.drawable.create_account));
        sliderItems.add(new SliderItem(R.drawable.welcome));
        adapter = new SliderImageAdapter(sliderItems, viewPager2);
        viewPager2.setAdapter(adapter);
        //mBarLayout.setViewPager2(viewPager2);
        viewPager2.setClipChildren(false);
        viewPager2.setClipToPadding(false);
        viewPager2.setOffscreenPageLimit(3);
        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_ALWAYS);

        /*sliderText = new ArrayList<>();
        sliderText.add(new SliderText("Nunc at elementum","Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod."));
        sliderText.add(new SliderText("Nunc at elementum","Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod."));
        sliderText.add(new SliderText("Nunc at elementum","Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod."));
        adapter2 = new SliderTextAdapter(sliderText, viewPager3);
        viewPager3.setAdapter(adapter2);*/

        //mBarLayout.setViewPager2(viewPager3);

        //viewPager2.addView(viewPager3);


        AddView();
        //setUpindicator(0);
    }

    private void AddView() {
        //adapter=new ViewPagerAdapter(getSupportFragmentManager());
        adapter = new SliderImageAdapter(sliderItems, viewPager2);
        viewPager2.setAdapter(adapter);


        mBarLayout.setViewPager2(viewPager2);

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                if (getitem(0) < 1) {
                    lin_content1.setVisibility(View.VISIBLE);
                    Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.alpha);
                    lin_content.startAnimation(animation);
                    lin_content2.setVisibility(View.GONE);
                    lin_content3.setVisibility(View.GONE);

                } else if (getitem(0) < 2) {
                    lin_content2.setVisibility(View.VISIBLE);
                    Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.alpha);
                    lin_content.startAnimation(animation);
                    lin_content1.setVisibility(View.GONE);
                    lin_content3.setVisibility(View.GONE);
                } else if (getitem(0) < 3) {
                    lin_content3.setVisibility(View.VISIBLE);
                    Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.alpha);
                    lin_content.startAnimation(animation);
                    lin_content1.setVisibility(View.GONE);
                    lin_content2.setVisibility(View.GONE);


                }

            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });

    }

    public void setUpindicator(int position) {

        bars = new TextView[3];
        //mBarLayout.setViewPager2(viewPager2);
        mBarLayout.removeAllViews();

        for (int i = 0; i < bars.length; i++) {

            bars[i] = new TextView(this);
            bars[i].setText("|");
            bars[i].setTextSize(10);
            bars[i].setWidth(20);
            bars[i].setTextColor(getResources().getColor(R.color.inactive, getApplicationContext().getTheme()));
            mBarLayout.addView(bars[i]);

        }

        bars[position].setTextColor(getResources().getColor(R.color.active, getApplicationContext().getTheme()));
        bars[position].setTextSize(20);
        bars[position].setWidth(20);

    }

   /* private int getitem(int i){

        return viewPager2.getCurrentItem() + i;
    }*/

    /*private Runnable sliderRunnable = new Runnable() {
        @Override
        public void run() {
            viewPager2.setCurrentItem(getitem(-1),true);
            viewPager3.setCurrentItem(getitem(-1), true);
        }
    };*/

    private int getitem(int i) {

        return viewPager2.getCurrentItem() + i;
    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == next_btn.getId()) {
            viewPager2.setCurrentItem(getitem(1), true);
        } else {
            Intent intent = new Intent(context, SignInActivity.class);
            startActivity(intent);
            finish();
        } if (id == skip_btn.getId()){
            Intent intent = new Intent(context, SignInActivity.class);
            startActivity(intent);
            finish();
        }
        }

    }