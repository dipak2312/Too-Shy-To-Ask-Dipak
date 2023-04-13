package com.neuronimbus.metropolis.activity.Landing;

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
import android.widget.Toast;

import com.neuronimbus.metropolis.API.WebServiceModel;
import com.neuronimbus.metropolis.adapters.ViewPagerAdapter;
import com.neuronimbus.metropolis.Helper.SPManager;
import com.neuronimbus.metropolis.Models.OnBordingResponse;
import com.neuronimbus.metropolis.Models.OnboardingList;
import com.neuronimbus.metropolis.R;
import com.neuronimbus.metropolis.Utils.CustomProgressDialog;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class SliderImagesActivity extends AppCompatActivity implements View.OnClickListener {
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
        context = SliderImagesActivity.this;
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

        //AddView();
        getOnBorading();
    }

    private void AddView() {

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
        dialog.dismiss();

        WebServiceModel.getRestApi().getOnBorading()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<OnBordingResponse>() {
                    @Override
                    public void onNext(OnBordingResponse onBordingResponse) {
                        dialog.dismiss("") ;
                        String msg = onBordingResponse.getMsg();

                        if (msg.equals("success")) {

                            onboardingLists = onBordingResponse.getOnboardingLists();
                            if(onboardingLists !=null)
                            {
                                viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(),onboardingLists);
                                viewPager.setAdapter(viewPagerAdapter);
                                mBarLayout.setViewPager(viewPager);
                            }


                            AddView();



                        } else {
                            //Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(context, "msg", Toast.LENGTH_SHORT).show();
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