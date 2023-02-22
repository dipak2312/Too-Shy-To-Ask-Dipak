package com.example.tooshytoask.Adapters;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;

import com.example.tooshytoask.Activity.Story.StoryDisplayFragment;
import com.example.tooshytoask.Fragment.SplashScreenFragment.FirstSplashFragment;
import com.example.tooshytoask.Models.StoryDetails;

import java.util.ArrayList;

public class StoryViewPagerAdapter extends FragmentPagerAdapter {
    Context context;
    ArrayList<StoryDetails> storyDetails;

    public StoryViewPagerAdapter(@NonNull FragmentManager fm, ArrayList<StoryDetails> storyDetails) {
        super(fm);
        this.storyDetails = storyDetails;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            StoryDisplayFragment fragment = new StoryDisplayFragment(storyDetails);
            return fragment;

        }

        return null;
    }

    @Override
    public int getCount() {
        return 1;
    }
}
