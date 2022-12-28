package com.example.tooshytoask.Fragment.SplashScreenFragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.tooshytoask.R;

public class ThirdSplashFragment extends Fragment {
    ImageView img3;
    Context context;
    TextView heading, txt_desc;

    @SuppressLint("ResourceType")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_third_splash, container, false);
        img3 = view.findViewById(R.id.img3);
        heading = view.findViewById(R.id.heading);
        //Animation animation = AnimationUtils.loadAnimation(context, R.anim.alpha);
        //heading.startAnimation(animation);
        txt_desc = view.findViewById(R.id.txt_desc);
        //Animation anim = AnimationUtils.loadAnimation(context, R.anim.alpha);
        //txt_desc.startAnimation(anim);

        context = getActivity();


        Glide.with(context).load(R.drawable.welcome).into(img3);
        Glide.with(context).load(R.string.heading_three);
        Glide.with(context).load(R.string.desc_three);

        return view;
    }
}