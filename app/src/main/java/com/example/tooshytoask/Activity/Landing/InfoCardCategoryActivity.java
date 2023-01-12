package com.example.tooshytoask.Activity.Landing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.tooshytoask.Adapters.InfoCardAdapter;
import com.example.tooshytoask.Interface.CategoryListener;
import com.example.tooshytoask.Helper.SPManager;
import com.example.tooshytoask.Interface.ClickListener;
import com.example.tooshytoask.R;

public class InfoCardCategoryActivity extends AppCompatActivity implements View.OnClickListener, View.OnTouchListener, CategoryListener, ClickListener {
    Context context;
    SPManager spManager;
    ProgressBar progressbar_completed;
    double progrss_value;
    InfoCardAdapter adapter;
    ViewPager viewPager;
    RelativeLayout rel_back;
    ImageButton next_btn;
    TextView skip_btn;
    private boolean enabled;
    String status="", myPosition = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_card_category);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        context = InfoCardCategoryActivity.this;
        spManager = new SPManager(context);
        progressbar_completed = findViewById(R.id.progressbar_completed);
        next_btn = findViewById(R.id.next_btn);
        next_btn.setOnClickListener(this);
        skip_btn = findViewById(R.id.skip_btn);
        skip_btn.setOnClickListener(this);
        viewPager = findViewById(R.id.viewPager);
        viewPager.setOnTouchListener(this);
        rel_back = findViewById(R.id.rel_back);
        rel_back.setOnClickListener(this);

        AddView();

    }

    private void AddView() {
        adapter =new InfoCardAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        progrss_value=(double)1/InfoCardAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT*25;
        progressbar_completed.setProgress((int) progrss_value);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (getitem(0) < 1) {

                    //next_btn.setBackgroundResource(R.drawable.circle_button_inactive);

                } else if (getitem(0) < 2) {

                    //next_btn.setBackgroundResource(R.drawable.circle_button_inactive);

                } else if (getitem(0) < 3) {

                    //next_btn.setBackgroundResource(R.drawable.circle_button_inactive);

                } else if (getitem(0) < 4) {

                    //next_btn.setBackgroundResource(R.drawable.circle_button_inactive);

                }

            }

            @Override
            public void onPageSelected(int position) {

                int value=position+1;
                progrss_value=(double)value/InfoCardAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT*25;
                progressbar_completed.setProgress((int) progrss_value);

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
    public boolean onTouch(View view, MotionEvent motionEvent) {

        return true;
    }


    @Override
    public void onClick(View view) {
        int id = view.getId();

        if (id == rel_back.getId()) {
            Intent intent = new Intent(context, SignUpActivity.class);
            startActivity(intent);
        }
        else if (id == next_btn.getId()) {
            viewPager.setCurrentItem(getitem(1), true);
        }

        else if (id == skip_btn.getId()){
            viewPager.setCurrentItem(getitem(1), true);
        }
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