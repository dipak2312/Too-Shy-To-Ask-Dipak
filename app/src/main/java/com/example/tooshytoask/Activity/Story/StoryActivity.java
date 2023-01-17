package com.example.tooshytoask.Activity.Story;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.tooshytoask.Activity.Landing.SignInActivity;
import com.example.tooshytoask.Helper.SPManager;
import com.example.tooshytoask.R;

public class StoryActivity extends AppCompatActivity implements View.OnClickListener{
    Context context;
    SPManager spManager;
    ImageView share_img, like_img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);
        getWindow().setStatusBarColor(getResources().getColor(R.color.black));
        context = StoryActivity.this;
        spManager = new SPManager(context);
        like_img = findViewById(R.id.like_img);
        like_img.setOnClickListener(this);
        share_img = findViewById(R.id.share_img);
        share_img.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if (id == like_img.getId()){
            like_img.setImageResource(R.drawable.like_active);
        }
    }
}