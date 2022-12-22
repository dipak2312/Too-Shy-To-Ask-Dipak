package com.example.tooshytoask.Activity.Landing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.tooshytoask.Adapters.ViewPager2Adapter;
import com.example.tooshytoask.Helper.SPManager;
import com.example.tooshytoask.Models.SliderItem;
import com.example.tooshytoask.R;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;

import java.util.ArrayList;

public class SliderImages extends AppCompatActivity implements View.OnClickListener {
    //ActivitySliderImagesBinding binding;
    ViewPager2Adapter adapter;
    //List<SliderItem> sliderItems;
    ViewPager2 viewPager2;
    TextView skip_btn;
    ImageButton next_btn;
    DotsIndicator mBarLayout;
    //LinearLayout lin_content, lin_content1, lin_content2, lin_content3;
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
        mBarLayout = findViewById(R.id.indicator_layout);
        /*lin_content = findViewById(R.id.lin_content);
        lin_content1 = findViewById(R.id.lin_content1);
        lin_content2 = findViewById(R.id.lin_content2);
        lin_content3 = findViewById(R.id.lin_content3);*/
        next_btn = findViewById(R.id.next_btn);
        next_btn.setOnClickListener(this);
        skip_btn = findViewById(R.id.skip_btn);
        skip_btn.setOnClickListener(this);
        /*adapter = new ViewPagerAdapter(adapter);
        viewPager2.setAdapter(adapter);*/

        //setting slider ViewPager Adapter
       /* sliderItems = new ArrayList<>();
        sliderItems.add(new SliderItem(R.drawable.welcome));
        sliderItems.add(new SliderItem(R.drawable.create_account));
        sliderItems.add(new SliderItem(R.drawable.welcome));
        //adapter = new SliderImageAdapter(sliderItems, viewPager2);
        //viewPager2.setAdapter(adapter);
        viewPager2.setClipChildren(false);
        viewPager2.setClipToPadding(false);
        viewPager2.setOffscreenPageLimit(3);
        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_ALWAYS);*/

        AddView();
    }

    private void AddView() {
        adapter = new ViewPager2Adapter(this);
        viewPager2.setAdapter(adapter);
        //mBarLayout.setViewPager2(viewPager2);

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);

                /* if (getitem(0) < 1) {
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

                }*/

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