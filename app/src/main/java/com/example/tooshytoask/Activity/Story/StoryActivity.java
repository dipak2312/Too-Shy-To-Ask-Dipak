package com.example.tooshytoask.Activity.Story;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.tooshytoask.Activity.Home.HomeActivity;
import com.example.tooshytoask.Activity.Landing.SignInActivity;
import com.example.tooshytoask.Helper.SPManager;
import com.example.tooshytoask.R;

public class StoryActivity extends AppCompatActivity implements View.OnClickListener{
    Context context;
    SPManager spManager;
    ImageView share_img, like_img;
    RelativeLayout back_img;

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
        back_img = findViewById(R.id.back_img);
        back_img.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if (id == like_img.getId()){
            like_img.setImageResource(R.drawable.like_active);
        }
        else if (id == back_img.getId()){
            Intent intent = new Intent(context, HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }
    }
}