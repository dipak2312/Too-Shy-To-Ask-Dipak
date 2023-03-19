package com.example.tooshytoask.adapters;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.tooshytoask.Fragment.InfoCard.PersonalInfoFragment;
import com.example.tooshytoask.Fragment.InfoCard.HealthCategoryFragment;
import com.example.tooshytoask.Fragment.InfoCard.HealthIssuesFragment;
import com.example.tooshytoask.Fragment.InfoCard.AvtarFragment;

public class InfoCardAdapter extends FragmentPagerAdapter implements View.OnClickListener {
    TextView skip_btn;
    Button next_btn;

    public InfoCardAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if(position==0){

            HealthCategoryFragment first=new HealthCategoryFragment();
            return first;

        }
        else if(position==1){

            AvtarFragment second=new AvtarFragment();
            return second;

        }
        else if(position==2){

            HealthIssuesFragment third=new HealthIssuesFragment();
            return third;
        }
        else if(position==3){

            PersonalInfoFragment forth=new PersonalInfoFragment();
            return forth;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if (id == skip_btn.getId()) {


        }
    }
}
