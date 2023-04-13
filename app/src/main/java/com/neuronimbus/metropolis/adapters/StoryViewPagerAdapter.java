package com.neuronimbus.metropolis.adapters;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.neuronimbus.metropolis.activity.story.StoryDisplayFragment;
import com.neuronimbus.metropolis.Models.StoryDetails;

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

            StoryDisplayFragment fragment = new StoryDisplayFragment(storyDetails);
            return fragment;

    }

    @Override
    public int getCount() {
        return 1;
    }
}
