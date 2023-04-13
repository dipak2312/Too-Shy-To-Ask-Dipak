package com.neuronimbus.metropolis.adapters;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.neuronimbus.metropolis.Fragment.SplashScreenFragment.FirstSplashFragment;
import com.neuronimbus.metropolis.Fragment.SplashScreenFragment.SecondSplashFragment;
import com.neuronimbus.metropolis.Fragment.SplashScreenFragment.ThirdSplashFragment;
import com.neuronimbus.metropolis.Models.OnboardingList;

import java.util.ArrayList;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    Context context;
   ArrayList<OnboardingList> onboardingLists;

    public ViewPagerAdapter(@NonNull FragmentManager fm, ArrayList<OnboardingList> onboardingLists) {

        super(fm);
        this.onboardingLists=onboardingLists;

    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        if (position == 0) {
            FirstSplashFragment first = new FirstSplashFragment(onboardingLists);
            return first;

        } else if (position == 1) {

            SecondSplashFragment second = new SecondSplashFragment(onboardingLists);
            return second;

        } else if (position == 2) {

            ThirdSplashFragment third = new ThirdSplashFragment(onboardingLists);
            return third;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}

