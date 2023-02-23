package com.example.tooshytoask.Activity.VideoGallery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.example.tooshytoask.Activity.Blogs.DetailBlogActivity;
import com.example.tooshytoask.Helper.SPManager;
import com.example.tooshytoask.R;
import com.example.tooshytoask.Utils.CustomProgressDialog;
import com.google.android.exoplayer2.ui.PlayerView;

public class VideoGallerySingleActivity extends AppCompatActivity implements View.OnClickListener {
    Context context;
    SPManager spManager;
    CustomProgressDialog dialog;
    PlayerView video_play;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_gallery_single);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        context = VideoGallerySingleActivity.this;
        spManager = new SPManager(context);
        dialog = new CustomProgressDialog(context);

        video_play = findViewById(R.id.video_play);
    }

    @Override
    public void onClick(View v) {

    }
}