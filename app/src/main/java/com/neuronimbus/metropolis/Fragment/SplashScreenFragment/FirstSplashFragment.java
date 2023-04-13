package com.neuronimbus.metropolis.Fragment.SplashScreenFragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Gallery;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.neuronimbus.metropolis.Models.OnboardingList;
import com.neuronimbus.metropolis.R;

import java.util.ArrayList;

public class FirstSplashFragment extends Fragment {
    ImageView img1;
    Context context;
    TextView heading, txt_desc;
    ArrayList<OnboardingList> onboardingLists;

    public FirstSplashFragment(ArrayList<OnboardingList> onboardingLists) {
        this.onboardingLists=onboardingLists;

    }

    @SuppressLint("ResourceType")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first_splash, container, false);
        context = getActivity();
        img1 = view.findViewById(R.id.img1);
        heading = view.findViewById(R.id.heading);

        txt_desc = view.findViewById(R.id.txt_desc);

        if (onboardingLists != null){
            heading.setText(Html.fromHtml(onboardingLists.get(0).getTitle()));
            txt_desc.setText(onboardingLists.get(0).getDesc());
            Log.d("saggi",onboardingLists.toString());
            Glide.with(context).load(onboardingLists.get(0).getImg()).into(img1);
        }









        return view;
    }
}