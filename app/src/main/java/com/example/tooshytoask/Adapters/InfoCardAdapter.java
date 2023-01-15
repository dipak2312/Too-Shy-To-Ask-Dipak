package com.example.tooshytoask.Adapters;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;

import com.example.tooshytoask.Fragment.InfoCard.FourFragment;
import com.example.tooshytoask.Fragment.InfoCard.OneFragment;
import com.example.tooshytoask.Fragment.InfoCard.ThreeFragment;
import com.example.tooshytoask.Fragment.InfoCard.TwoFragment;
import com.example.tooshytoask.R;

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

            OneFragment first=new OneFragment();
            return first;

        }
        else if(position==1){

            TwoFragment second=new TwoFragment();
            return second;

        }
        else if(position==2){

            ThreeFragment third=new ThreeFragment();
            return third;
        }
        else if(position==3){

            FourFragment third=new FourFragment();
            return third;
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
