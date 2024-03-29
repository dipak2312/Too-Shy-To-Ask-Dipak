package com.neuronimbus.metropolis.activity.Landing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.neuronimbus.metropolis.Utils.LocaleHelper;
import com.neuronimbus.metropolis.adapters.InfoCardAdapter;
import com.neuronimbus.metropolis.Helper.SPManager;
import com.neuronimbus.metropolis.Utils.ClickListener;
import com.neuronimbus.metropolis.R;

public class InfoCardCategoryActivity extends AppCompatActivity implements View.OnClickListener, View.OnTouchListener, ClickListener{
    Context context;
    SPManager spManager;
    ProgressBar progressbar_completed;
    double progrss_value;
    InfoCardAdapter adapter;
    ViewPager viewPager;
    RelativeLayout rel_back;
    TextView skip_btn, title_info_card;
    ClickListener clickListener;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_card_category);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        context = InfoCardCategoryActivity.this;
        spManager = new SPManager(context);
        progressbar_completed = findViewById(R.id.progressbar_completed);

        title_info_card = findViewById(R.id.title_info_card);
        skip_btn = findViewById(R.id.skip_btn);
        skip_btn.setOnClickListener(this);
        viewPager = findViewById(R.id.viewPager);
        viewPager.setOnTouchListener(this);
        rel_back = findViewById(R.id.rel_back);
        rel_back.setOnClickListener(this);
        clickListener=(ClickListener)context;
        clickListener.onClick(false);

        AddView();
    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase));
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

                    title_info_card.setText(R.string.info_card1);
                    rel_back.setVisibility(View.GONE);

                } else if (getitem(0) < 2) {

                    title_info_card.setText(R.string.info_card2);
                    rel_back.setVisibility(View.VISIBLE);

                } else if (getitem(0) < 3) {

                    title_info_card.setText(R.string.info_card3);
                    rel_back.setVisibility(View.VISIBLE);

                } else if (getitem(0) < 4) {

                    title_info_card.setText(R.string.info_card4);
                    rel_back.setVisibility(View.VISIBLE);
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

    @Override
    public void onBackPressed() {

        viewPager.setCurrentItem(viewPager.getCurrentItem()-1);

    };

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
            viewPager.setCurrentItem(viewPager.getCurrentItem()-1);

        }
    }

    @Override
    public void onClick(Boolean status) {

      if(status)
      {
          viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);

      }else
      {

      }

    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        for (Fragment fragment : getSupportFragmentManager().getFragments()) {
            fragment.onActivityResult(requestCode, resultCode, intent);
        }
    }

}