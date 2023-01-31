package com.example.tooshytoask.Fragment.SplashScreenFragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.tooshytoask.Models.OnboardingList;
import com.example.tooshytoask.R;

import java.util.ArrayList;

public class ThirdSplashFragment extends Fragment {
    ImageView img3;
    Context context;
    TextView heading, txt_desc;
    ArrayList<OnboardingList> onboardingLists;

    public ThirdSplashFragment(ArrayList<OnboardingList> onboardingLists) {
        this.onboardingLists=onboardingLists;

    }

    @SuppressLint("ResourceType")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_third_splash, container, false);
        img3 = view.findViewById(R.id.img3);
        heading = view.findViewById(R.id.heading);

        txt_desc = view.findViewById(R.id.txt_desc);


        heading.setText(onboardingLists.get(2).getTitle());
        txt_desc.setText(onboardingLists.get(2).getDesc());

        context = getActivity();
        Log.d("saggi",onboardingLists.toString());

        Glide.with(context).load(onboardingLists.get(2).getImg()).into(img3);

        return view;
    }
}