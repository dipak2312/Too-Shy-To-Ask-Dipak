package com.example.tooshytoask.Activity.Landing;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.Handler;
import android.view.ScrollCaptureCallback;
import android.view.ScrollCaptureSession;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tooshytoask.Adapters.SliderImageAdapter;
import com.example.tooshytoask.Adapters.SliderTextAdapter;
import com.example.tooshytoask.Helper.SPManager;
import com.example.tooshytoask.Models.SliderItem;
import com.example.tooshytoask.Models.SliderText;
import com.example.tooshytoask.R;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class SliderImages extends AppCompatActivity {
    //ActivitySliderImagesBinding binding;
    SliderImageAdapter adapter;
    SliderTextAdapter adapter2;
    List<SliderItem> sliderItems;
    List<SliderText> sliderText;
    ViewPager2 viewPager2, viewPager3, viewPager4;
    LinearLayout mBarLayout;
    Handler handler = new Handler();
    TextView[] bars;
    TextView[] headline;
    TextView dec, heading;
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
        viewPager3= findViewById(R.id.view_pager_headline);
        mBarLayout = (LinearLayout) findViewById(R.id.indicator_layout);


        //adapter = new SliderImageAdapter();



        //setting slider ViewPager Adapter
        sliderItems = new ArrayList<>();
        sliderItems.add(new SliderItem(R.drawable.welcome));
        sliderItems.add(new SliderItem(R.drawable.create_account));
        sliderItems.add(new SliderItem(R.drawable.welcome));
        adapter = new SliderImageAdapter(sliderItems, viewPager2);
        viewPager2.setAdapter(adapter);
        viewPager2.setClipChildren(false);
        viewPager2.setClipToPadding(false);
        viewPager2.setOffscreenPageLimit(3);
        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_ALWAYS);

        sliderText = new ArrayList<>();
        sliderText.add(new SliderText("Nunc at elementum","Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod."));
        sliderText.add(new SliderText("Nunc at elementum","Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod."));
        sliderText.add(new SliderText("Nunc at elementum","Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod."));
        adapter2 = new SliderTextAdapter(sliderText, viewPager3);
        viewPager3.setAdapter(adapter2);

       /* viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                handler.removeCallbacks(sliderRunnable);
                //handler.postDelayed(sliderRunnable, 2000);
            }
        });*/

        setUpindicator(0);
        scroll();
    }

    public void setUpindicator(int position){

        bars = new TextView[3];
        mBarLayout.removeAllViews();

        for (int i = 0 ; i < bars.length ; i++){

            bars[i] = new TextView(this);
            bars[i].setText("|");
            bars[i].setTextSize(3,8);
            bars[i].setWidth(20);
            bars[i].setTextColor(getResources().getColor(R.color.inactive,getApplicationContext().getTheme()));
            mBarLayout.addView(bars[i]);

        }

        bars[position].setTextColor(getResources().getColor(R.color.active,getApplicationContext().getTheme()));
        bars[position].setTextSize(3,14);
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

    public void scroll(){
        ScrollCaptureCallback scrollCaptureCallback = new ScrollCaptureCallback() {
            @Override
            public void onScrollCaptureSearch(@NonNull CancellationSignal cancellationSignal, @NonNull Consumer<Rect> consumer) {

            }

            @Override
            public void onScrollCaptureStart(@NonNull ScrollCaptureSession scrollCaptureSession, @NonNull CancellationSignal cancellationSignal, @NonNull Runnable runnable) {
                    viewPager3.setCurrentItem(getitem(-1),true);
            }

            @Override
            public void onScrollCaptureImageRequest(@NonNull ScrollCaptureSession scrollCaptureSession, @NonNull CancellationSignal cancellationSignal, @NonNull Rect rect, @NonNull Consumer<Rect> consumer) {

            }

            @Override
            public void onScrollCaptureEnd(@NonNull Runnable runnable) {

            }
        };
    }

}