package com.example.tooshytoask.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.tooshytoask.Fragment.SplashScreenFragment.FirstSplashFragment;
import com.example.tooshytoask.Fragment.SplashScreenFragment.SecondSplashFragment;
import com.example.tooshytoask.Fragment.SplashScreenFragment.ThirdSplashFragment;

public class SlideImageAdapter extends FragmentPagerAdapter {
    public SlideImageAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if(position==0){

            FirstSplashFragment first=new FirstSplashFragment();
            return first;

        }
        else if(position==1){

            SecondSplashFragment second=new SecondSplashFragment();
            return second;

        }
        else if(position==2){

            ThirdSplashFragment third=new ThirdSplashFragment();
            return third;
        }

        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
