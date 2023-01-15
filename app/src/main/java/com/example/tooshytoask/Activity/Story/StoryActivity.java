package com.example.tooshytoask.Activity.Story;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.example.tooshytoask.Activity.Landing.SignInActivity;
import com.example.tooshytoask.Helper.SPManager;
import com.example.tooshytoask.R;

public class StoryActivity extends AppCompatActivity {
    Context context;
    SPManager spManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);
        getWindow().setStatusBarColor(getResources().getColor(R.color.black));
        context = StoryActivity.this;
        spManager = new SPManager(context);


    }
}