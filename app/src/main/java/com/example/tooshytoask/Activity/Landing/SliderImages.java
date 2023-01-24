package com.example.tooshytoask.Activity.Landing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.tooshytoask.API.WebServiceModel;
import com.example.tooshytoask.Adapters.ViewPagerAdapter;
import com.example.tooshytoask.Helper.SPManager;
import com.example.tooshytoask.Models.OnBordingResponse;
import com.example.tooshytoask.Models.OnboardingList;
import com.example.tooshytoask.R;
import com.example.tooshytoask.Utils.CustomProgressDialog;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class SliderImages extends AppCompatActivity implements View.OnClickListener {
    //ActivitySliderImagesBinding binding;
    ViewPagerAdapter viewPagerAdapter;
    ViewPager viewPager;
    TextView skip_btn;
    Button started;
    ImageButton next_btn;
    DotsIndicator mBarLayout;
    CustomProgressDialog dialog;
    Context context;
    SPManager spManager;
    FragmentManager fm;
    ArrayList<OnboardingList> onboardingLists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //binding = ActivitySliderImagesBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_slider_images);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        context = SliderImages.this;
        spManager = new SPManager(context);
        dialog = new CustomProgressDialog(context);

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
        viewPagerAdapter = new ViewPagerAdapter(fm, context, onboardingLists);
        viewPager.setAdapter(viewPagerAdapter);
        mBarLayout.setViewPager(viewPager);
        //getOnBorading();

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
                     /*Animation alpha = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.alpha);
                     started.startAnimation(alpha);*/
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

    public void getOnBorading(){
        dialog.show("");

        WebServiceModel.getRestApi().getOnBorading()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<OnBordingResponse>() {
                    @Override
                    public void onNext(OnBordingResponse onBordingResponse) {
                        dialog.show("");
                        String msg = onBordingResponse.getMsg();

                        if (msg.equals("success")) {

                            onboardingLists = onBordingResponse.getOnboardingLists();

                            viewPagerAdapter = new ViewPagerAdapter(fm, context, onboardingLists);
                            viewPager.setAdapter(viewPagerAdapter);



                        } else {
                            //Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == next_btn.getId()) {
            viewPager.setCurrentItem(getitem(1), true);
        } else if (id == started.getId()){
            Intent intent = new Intent(context, LanguageActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();

        } if (id == skip_btn.getId()){
            Intent intent = new Intent(context, LanguageActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }
        }

    }